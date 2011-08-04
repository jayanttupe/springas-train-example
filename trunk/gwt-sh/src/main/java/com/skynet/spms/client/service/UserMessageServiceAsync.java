package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.UserMessage;

public interface UserMessageServiceAsync {
	
	void getMessageList(AsyncCallback<List<UserMessage>> callback);
	
	void getNewMessageList(AsyncCallback<List<UserMessage>> callback);
//	
	void setMsgReaded(String msgID,AsyncCallback<Void> callback);
	
	void addMessage(String toUser,String msgContext,AsyncCallback<Void> callback);

}
