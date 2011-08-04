package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PrintCodeServiceAsync {
	/**
	 * 进行打印，返回相关信息
	 * @param countent
	 * @param ip
	 * @param port
	 * @param lableid
	 * @param callback
	 */
	public void print(String[] countent,String ip,int port,String lableid,AsyncCallback<Void> callback);

}
