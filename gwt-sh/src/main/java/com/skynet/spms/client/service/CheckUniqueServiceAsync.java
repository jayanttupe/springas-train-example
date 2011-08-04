package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface CheckUniqueServiceAsync{

	public void isUnique(String entityClassName,String fieldName,String value,AsyncCallback<Boolean> callback);

}
