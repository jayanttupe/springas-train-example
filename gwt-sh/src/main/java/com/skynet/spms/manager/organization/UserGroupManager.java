package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.userGroup.UserGroup;

public interface UserGroupManager extends CommonManager<UserGroup> {


	public List<UserGroup> queryByFilter(Map<String,Object> values);
	
	public UserGroup addGroupUsers(String id,List<String> userIds);
	
	public void deleteUserGroup(String id);
	
	public UserGroup addRolesToGroupUsers(String id,List<String> roleIds);
	
}
