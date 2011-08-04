package com.skynet.spms.manager.organization.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.UserGroupManager;
import com.skynet.spms.persistence.entity.organization.userGroup.UserGroup;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userRole.Role;

@Service
@Transactional
public class UserGroupManagerImpl extends CommonManagerImpl<UserGroup> implements UserGroupManager {

	@Override
	public List<UserGroup> queryByFilter(Map<String, Object> values) {
		Query query = CriteriaConverter.convertValueMapToQuery(getSession(), values, UserGroup.class);
		return query.list();
	}

	@Override
	public UserGroup addGroupUsers(String id, List<String> userIds) {
		UserGroup userGroup = (UserGroup)getSession().get(UserGroup.class, id);
		List<User> userList = new ArrayList<User>();
		for(String userId : userIds){
			User user = (User)getSession().get(User.class, userId);
			userList.add(user);
		}
		userGroup.setM_User(userList);
		getSession().saveOrUpdate(userGroup);
		return userGroup;
	}

	@Override
	public void deleteUserGroup(String id) {
		UserGroup userGroup = (UserGroup)getSession().get(UserGroup.class, id);
		userGroup.setM_User(new ArrayList<User>());
		userGroup.setDeleted(true);
		getSession().saveOrUpdate(userGroup);
	}

	@Override
	public UserGroup addRolesToGroupUsers(String id, List<String> roleIds) {
		UserGroup userGroup = (UserGroup)getSession().get(UserGroup.class, id);
		List<User> userList = userGroup.getM_User();
		for(User user : userList){
			List<Role> roleList = new ArrayList<Role>();
			for(String roleId : roleIds){
				Role role = (Role)getSession().get(Role.class, roleId);
				roleList.add(role);
			}
			user.setM_Role(roleList);
			getSession().saveOrUpdate(user);
		}
		return userGroup;
	}

}
