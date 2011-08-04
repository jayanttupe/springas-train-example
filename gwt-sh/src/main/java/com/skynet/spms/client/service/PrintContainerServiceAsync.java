package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PrintContainerServiceAsync {
	/**
	 * 进行打印，返回相关信息
	 * @param countent
	 * @param ip
	 * @param port
	 * @param lableid
	 * @param callback
	 */
	public void print(String[] containerIDs,int port,AsyncCallback<String> callback);

}
