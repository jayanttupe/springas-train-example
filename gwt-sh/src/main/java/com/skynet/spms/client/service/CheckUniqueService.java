package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("checkUniqueAction.form")
public interface CheckUniqueService extends RemoteService {

	public Boolean isUnique(String entityClassName,String fieldName,String value);

}
