/**
 * File: 
 * copyright  2010-2011   Shanghai  Skynetsoft, INC.All Rights Reserved.
 * Date        Author      Changes
 * 2011-3-24   黄贇	    
 */
package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.client.entity.UserInfo;
import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.userInformation.User;

/**
 * File: 
 * copyright  2010-2011   Shanghai  Skynetsoft, INC.All Rights Reserved.
 * Date        Author      Changes
 * 2011-3-24    黄贇	    
 */
public interface UserManager extends CommonManager<User>{
	
	public void insertUser(User user);
	
	public User updateUser(Map<String,Object> values,String itemId);
	
	public void updateUser(User user);
	
	public List<User> queryByProps(Map<String, Object> values);
	
	public List<User> queryByGroupId(String groupId);
	
	/*public List<User> loadUsers(String[] itemsId);
	
	public void addOrRemoveUserFromGroup(String userId,String groupId);

	public List<User> listUserByRestrictions(Map map,int startRow, int endRow) ;*/
	
	public List<User> queryByFilter(Map<String,Object> values,int startRow,int endRow);
	
	public void deleteUser(String userId);
	
	public User addRolesToUser(String id,List<String> roleIds);
	
	public List<User> listUsers(int startRow,int endRow);
	
	public List<User> queryByUsername(String username,boolean iseq);
	
	public boolean updateByUsername(String username,Map<String, Object> values);
	
	public CustomerIdentificationCode queryCustomerCodeByUsername(String username);

	UserInfo getUserInfoByName(String userName);
	
	
}
