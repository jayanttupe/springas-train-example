package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.DataInfo;


public interface TableInfoServiceAsync {

	void getFieldList(String moduleName, AsyncCallback<DataInfo> callback);

	void getFieldList(String moduleName, String dsName,
			AsyncCallback<DataInfo> callback);

	void getFieldListByClsName(String className,AsyncCallback<DataInfo> callback);

	void getFieldListWithouti18n(String moduleName,String dsName,AsyncCallback<DataInfo> callback);
}
