package com.skynet.spms.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.entity.ModuleDetail;
import com.skynet.spms.client.entity.RootModulesEntity;
import com.skynet.spms.client.entity.ViewModuleTree;


public interface ModuleInfoServiceAsync {

	void getRootModuleList(
			AsyncCallback<RootModulesEntity> callback);

	void getModuleTree(String modName,AsyncCallback<ViewModuleTree> callback);

	void getModuleDetail(String modName,
			AsyncCallback<ModuleDetail> callback);
	
	void getPortalMembers(AsyncCallback<Set<String>> callback); 


}
