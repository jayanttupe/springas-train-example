package com.skynet.spms.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.entity.ModuleDetail;
import com.skynet.spms.client.entity.RootModulesEntity;
import com.skynet.spms.client.entity.ViewModuleTree;


@RemoteServiceRelativePath("moduleAction.form")
public interface ModuleInfoService extends RemoteService {
	
	public RootModulesEntity getRootModuleList();
	
	public ViewModuleTree getModuleTree(String modName);
	
	public ModuleDetail getModuleDetail(String modName);
		
	public Set<String> getPortalMembers(); 
}
