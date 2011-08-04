package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.userGroup.UserGroup;
import com.skynet.spms.persistence.entity.organization.userInformation.UserInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;


public interface UserInfoManager extends CommonManager<UserInformation>{
	
	public List<UserInformation> queryByProps(Map<String, Object> values);
	
	public UserInformation updateUserInfo(Map<String,Object> newValues,String itemID);

}
