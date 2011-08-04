package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AttachmentServiceAsync {

	public void deleteFile(String fileIdx,AsyncCallback<Void> callback);

}
