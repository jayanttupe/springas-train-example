package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.entity.MsgEntity;

@RemoteServiceRelativePath("instMsgSendAction.form")
public interface InstMsgSendService extends RemoteService{

	void doSendMsg(String msg, boolean isBroad, String to);

	List<MsgEntity> getMessageList();

}