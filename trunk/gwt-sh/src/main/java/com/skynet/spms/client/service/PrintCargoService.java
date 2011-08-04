package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("printCargoAction.form")
public interface PrintCargoService extends RemoteService{
	/**
	 * 打印需要输入的基本信息
	 * @param countent
	 * @param ip
	 * @param port
	 * @param lableid
	 * @throws Exception 
	 * @throws Throwable 
	 */
	public String print(String[] cargoSpaceIDs,String ip,int port,String lableid) throws Exception;
}