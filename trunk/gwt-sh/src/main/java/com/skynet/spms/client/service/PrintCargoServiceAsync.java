package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PrintCargoServiceAsync {
	/**
	 * 进行打印，返回相关信息
	 * @param countent
	 * @param ip
	 * @param port
	 * @param lableid
	 * @param callback
	 */
	public void print(String[] cargoSpaceIDs,String ip,int port,String lableid,AsyncCallback<String> callback);

}
