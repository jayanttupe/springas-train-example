package com.skynet.spms.client.service;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.entity.DataInfo;

@RemoteServiceRelativePath("tableInfoAction.form")
public interface TableInfoService extends RemoteService{
	
	public DataInfo getFieldList(String moduleName);
	
	public DataInfo getFieldListWithouti18n(String moduleName,String dsName);
	
	public DataInfo getFieldList(String moduleName,String dsName);
	
	public DataInfo getFieldListByClsName(String className);
}
