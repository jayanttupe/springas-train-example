package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("attachmentService.action")
public interface AttachmentService extends RemoteService{

	public void deleteFile(String fileIdx);
	
}
