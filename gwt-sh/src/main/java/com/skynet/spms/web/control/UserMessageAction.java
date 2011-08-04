package com.skynet.spms.web.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.entity.UserMessage;
import com.skynet.spms.client.service.UserMessageService;
import com.skynet.spms.manager.portal.UserMsgRecordManager;
import com.skynet.spms.persistence.entity.organization.userInformation.UserMessageRecord;

@Controller
@GwtRpcEndPoint
public class UserMessageAction implements UserMessageService {

	@Autowired
	private UserMsgRecordManager mang;
	
	private static final ConcurrentMap<String,Date> userTimeStampMap=new ConcurrentHashMap<String,Date>();
	
	@Override
	public List<UserMessage> getNewMessageList(){
		String userName=GwtActionHelper.getCurrUser();
		
		Date date=userTimeStampMap.replace(userName,new Date());
		
		List<UserMessageRecord> msgList=mang.getNewUserMessages(date,userName);
		
		List<UserMessage> resultList = getClientMsgList(msgList);
				
		return resultList;
	}
	
	@Override
	public List<UserMessage> getMessageList() {
		
		String userName=GwtActionHelper.getCurrUser();
		
		userTimeStampMap.put(userName,new Date());
		
		List<UserMessageRecord> msgList=mang.getUserMessages(userName);
		
		List<UserMessage> resultList = getClientMsgList(msgList);
		
		return resultList;
	}

	private List<UserMessage> getClientMsgList(List<UserMessageRecord> msgList) {
		List<UserMessage> resultList=new ArrayList<UserMessage>();
		for(UserMessageRecord userMsg:msgList){
			UserMessage msg=new UserMessage();
			
			msg.setContext(userMsg.getM_MessageEntity().getMessage());
			msg.setMsgID(userMsg.getId());
			msg.setTime(userMsg.getCreateDate());
			msg.setFrom(userMsg.getCreateBy());
			resultList.add(msg);
		}
		return resultList;
	}

	@Override
	public void setMsgReaded(String msgID) {
		mang.setReadSign(msgID);
		
	}

	@Override
	public void addMessage(String toUser, String msgContext) {
		mang.addMessage(toUser, msgContext);
		
	}

}
