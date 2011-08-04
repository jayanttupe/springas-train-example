/**
 * File: 
 * copyright  2010-2011   Shanghai  Skynetsoft, INC.All Rights Reserved.
 * Date        Author      Changes
 * 2011-3-24   黄贇	    
 */
package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.organization.userInformation.User;

@Component
public class UserDatasourceAction implements DataSourceAction<User> {

	private Logger log=LoggerFactory.getLogger(UserDatasourceAction.class);

	@Autowired
	private UserManager userManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"user_dataSource"};
	}

	@Override
	public void insert(User user) {
		log.info("=============================UserDatasourceAction.insert()");
		log.info("id : " + user.getId());
		log.info("Username : " + user.getUsername());
		log.info("realname : " + user.getRealname());
		log.info("password : " + user.getPassword());
		log.info("email : " + user.getEmail());
		log.info("enterpriseId : " + user.getEnterpriseId());
		log.info("departmentId : " + user.getDepartmentId());
		log.info("dutyId : " + user.getDutyId());
		log.info("enableStatus : " + user.getM_EnableStatus());
		userManager.insertUser(user);
		
	}

	@Override
	public User update(Map<String,Object> newValues, String userId) {
		log.info("=============================UserDatasourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		List<String> roleIds = (List<String>)newValues.remove("roleIds");
		User user = null;
		if(roleIds != null){
			String id = (String)newValues.get("id");
			user = userManager.addRolesToUser(id,roleIds);
		}else{
			user = userManager.updateUser(newValues, userId);
		}
		return user;
	}

	@Override
	public void delete(String userId) {
		log.info("=============================UserDatasourceAction.delete()");
		log.info("userId : " + userId);
		userManager.deleteUser(userId);
	}

	@Override
	public List<User> doQuery(Map<String,Object> newValues, int startRow, int endRow) {
		log.info("=============================UserDatasourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		
		Object filter = newValues.remove("filter");
		String groupId = (String)newValues.remove("groupId");
		List<User> userList = null;
		if(filter != null){
			userList = userManager.queryByFilter(newValues,startRow,endRow);
		}else if(groupId != null){
			userList = userManager.queryByGroupId(groupId);
		}else{
			userList = userManager.queryByProps(newValues);
		}

		return userList;
	}

	@Override
	public List<User> getList(int startRow, int endRow) {
		log.info("=============================UserDatasourceAction.getList()");
		
		return userManager.listUsers(startRow, endRow);
	}

}
