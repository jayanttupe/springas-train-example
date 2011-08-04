package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.MsgEntity;

public interface InstMsgSendServiceAsync {

	void doSendMsg(String msg, boolean isBroad, String to,
			AsyncCallback<Void> callback);

	void getMessageList(AsyncCallback<List<MsgEntity>> callback);
}
