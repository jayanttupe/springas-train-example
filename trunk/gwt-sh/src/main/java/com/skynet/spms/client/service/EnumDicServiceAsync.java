package com.skynet.spms.client.service;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EnumDicServiceAsync {

	void get(String className, AsyncCallback<LinkedHashMap<String, String>> callback);
}
