package com.skynet.spms.web.control;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.MsgEntity;
import com.skynet.spms.client.service.InstMsgSendService;
import com.skynet.spms.event.SpmsEventType;
import com.skynet.spms.instmsg.InstMsg;
import com.skynet.spms.instmsg.InstMsgEntity;
import com.skynet.spms.instmsg.InstMsgManager;
import com.skynet.spms.instmsg.UserMsgSign;

@Controller
@GwtRpcEndPoint
public class InstMsgSendAction implements InstMsgSendService {

	
	@Autowired
	@Qualifier("topicMsgTemplate")
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private PropManager messageProp;

	
	private ConcurrentMap<String,Queue<InstMsg>> msgMap=new ConcurrentHashMap<String,Queue<InstMsg>>();
			
	private Queue<InstMsg> broadQueue=new ConcurrentLinkedQueue<InstMsg>();

	
	@Override
	public List<MsgEntity> getMessageList() {

		List<MsgEntity> msgList=new ArrayList<MsgEntity>();
		
		PropertyEntity prop=messageProp.getPropEntity(GwtActionHelper.getLocale(),PropEnum.UIResource);
				
		Queue<InstMsg> msgQueue=msgMap.get(GwtActionHelper.getCurrUser());

		if(msgQueue==null){
			return new ArrayList<MsgEntity>();
		}
		while(!msgQueue.isEmpty()){
			InstMsg msg=msgQueue.poll();		
			msgList.add(msg.getMsgEntity(prop));
		}	
		
		return msgList;
	}
	

	@Override
	public void doSendMsg(String msg,boolean isBroad,String to){
		
		
		InstMsgEntity event=new InstMsgEntity();
		event.setMsg(msg);
		event.setIsBroadCast(isBroad);
		event.setFrom(GwtActionHelper.getCurrUser());
		event.setToTarget(to);
		
		if(isBroad){
			broadQueue.add(event);
			for(final Map.Entry<String,Queue<InstMsg>> entry:msgMap.entrySet()){
				Queue<InstMsg> queue=entry.getValue();
				queue.add(event);
				sendNotifyMsg(entry.getKey());
			}
			return;
		}
			
		
		Queue<InstMsg> msgQueue=new ConcurrentLinkedQueue<InstMsg>();
		Queue<InstMsg> val=msgMap.putIfAbsent(to,msgQueue);
				
		if(val==null){
			msgQueue.addAll(broadQueue);
			msgQueue.add(event);
		}else{
			val.add(event);
		}		
		sendNotifyMsg(to);
		
	}


	private void sendNotifyMsg(final String user) {
		jmsTemplate.send(new MessageCreator(){

			@Override
			public Message createMessage(Session session)
					throws JMSException {
				UserMsgSign msgSign=new UserMsgSign();
				msgSign.setToUser(user);
				msgSign.setMsgType("instMsg");
				
				ObjectMessage msg=session.createObjectMessage();
				msg.setObject(msgSign);
				msg.setStringProperty("type", "pollMsg");
				return msg;
			}
			
		});
	}	
	


}
