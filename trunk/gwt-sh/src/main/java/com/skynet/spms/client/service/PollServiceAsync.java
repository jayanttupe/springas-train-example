package com.skynet.spms.client.service;


import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PollServiceAsync {

	void checkIsMsgReceive(AsyncCallback<Boolean> callback);

}
