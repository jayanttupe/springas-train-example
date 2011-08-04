package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userRole.Role;

public interface RoleManager extends CommonManager<Role>{

	public List<Role> queryByProps(Map<String, Object> values);
	
	public List<Role> queryByFilter(Map<String, Object> values);
	
	public List<Role> queryByUserId(String userId);
	
	public Role updateRole(Map<String,Object> values,String itemId);
}
