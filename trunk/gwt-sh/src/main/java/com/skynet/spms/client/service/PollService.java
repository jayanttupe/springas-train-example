package com.skynet.spms.client.service;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("pollAction.form")
public interface PollService extends RemoteService {

	 boolean checkIsMsgReceive();

}