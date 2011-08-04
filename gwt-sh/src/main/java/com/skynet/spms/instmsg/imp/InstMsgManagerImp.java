package com.skynet.spms.instmsg.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.MsgEntity;
import com.skynet.spms.event.EventEntry;
import com.skynet.spms.instmsg.InstMsg;
import com.skynet.spms.instmsg.InstMsgEntity;
import com.skynet.spms.instmsg.InstMsgManager;

@Component("msgManager")
public class InstMsgManagerImp implements InstMsgManager{
	
	private Logger log=LoggerFactory.getLogger(InstMsgManagerImp.class);

	@Autowired
	private PropManager messageProp;

	
	private ConcurrentMap<String,Queue<InstMsg>> msgMap=new ConcurrentHashMap<String,Queue<InstMsg>>();
		
	private Queue<InstMsg> broadQueue=new ConcurrentLinkedQueue<InstMsg>();
		

	public void onInstMessage(InstMsgEntity event) {

		if(event.isBroadCast()){
			broadMsg(event);
			return;
		}
		
		Queue<InstMsg> msgQueue=getUserQueue(event.getTarget());
		
		msgQueue.add(event);
	}

	private Queue<InstMsg> getUserQueue(String user) {
		Queue<InstMsg> msgQueue=new ConcurrentLinkedQueue<InstMsg>();
		
		Queue<InstMsg> val=msgMap.putIfAbsent(user,msgQueue);
				
		if(val==null){
			msgQueue.addAll(broadQueue);
			return msgQueue;
		}else{
			return val;
		}
	}
	
	private void broadMsg(InstMsg msg){
		for(Queue<InstMsg> queue:msgMap.values()){
			queue.add(msg);
		}
		broadQueue.add(msg);
	}
	
	/* (non-Javadoc)
	 * @see com.skynet.spms.manager.imp.InstMsgManager#getMsgList(java.lang.String)
	 */
	@Override
	public List<MsgEntity> getMsgList(String userName,Locale locale){
	
		
		List<MsgEntity> msgList=new ArrayList<MsgEntity>();
		
		PropertyEntity prop=messageProp.getPropEntity(locale,PropEnum.UIResource);
				
		Queue<InstMsg> msgQueue=getUserQueue(userName);

		int count=0;
		while(count<10){
			InstMsg msg=msgQueue.poll();
			if(msg==null){
				break;
			}
			msgList.add(msg.getMsgEntity(prop));
			count++;		
		}	
		
		return msgList;
	}	

}
