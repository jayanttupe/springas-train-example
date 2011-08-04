package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("printUserIdCardAction.form")
public interface PrintUserIdCardService extends RemoteService{
	/**
	 * 打印需要输入的基本信息
	 * @param countent
	 * @param ip
	 * @param port
	 * @param lableid
	 * @throws Exception 
	 * @throws Throwable 
	 */
	public String print(String userId, String printType) throws Exception;
}