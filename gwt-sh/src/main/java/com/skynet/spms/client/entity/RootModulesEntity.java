package com.skynet.spms.client.entity;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.skynet.spms.client.constants.NavigationConst;
import com.skynet.spms.client.constants.NavigationMsg;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;

public class RootModulesEntity implements IsSerializable{

	private List<ModuleItem> modulesList;
	
	private UserInfo userInfo;

	public List<ModuleItem> getModulesList() {
		return modulesList;
	}

	public void setModulesList(List<ModuleItem> modulesList) {
		this.modulesList = modulesList;
	}

	public String getUserName() {
		return userInfo.getUserName();
	}
	
	public Canvas getUserInfoWidget(NavigationMsg navMsg){
		Label labName=new Label();
		labName.setContents("welcome: "+ userInfo.getRealName() + " ");
		labName.setAutoWidth();
		String toolTip="";
		if(userInfo.getDepartment()!=null){
			toolTip+=navMsg.getUserDep(userInfo.getDepartment());
		}
		if(userInfo.getDuty()!=null){
			toolTip+=navMsg.getUserDuty(userInfo.getDuty());
		}
		if(toolTip.equals("")){
			toolTip=userInfo.getRealName();
		}
		labName.setTooltip(toolTip);

		return labName;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
