package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.entity.UserMessage;

@RemoteServiceRelativePath("userMessageAction.form")
public interface UserMessageService extends RemoteService{

	List<UserMessage> getMessageList();
	
	void setMsgReaded(String msgID);

	List<UserMessage> getNewMessageList();
	
	void addMessage(String toUser,String msgContext);
}
