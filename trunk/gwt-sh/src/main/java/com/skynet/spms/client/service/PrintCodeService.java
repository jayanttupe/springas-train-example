package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("printCodeAction.form")
public interface PrintCodeService extends RemoteService{
	/**
	 * 打印需要输入的基本信息
	 * @param countent
	 * @param ip
	 * @param port
	 * @param lableid
	 */
	public void print(String[] countent,String ip,int port,String lableid) throws Exception;
}
