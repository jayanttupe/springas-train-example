package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fileOperAction.form")
public interface FileOperService extends RemoteService{
	
	public void fileManualOper() throws Exception;

}

