package com.skynet.spms.jbpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.impl.GroupImpl;
import org.jbpm.pvm.internal.identity.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.persistence.entity.organization.userGroup.UserGroup;
import com.skynet.spms.persistence.entity.organization.userRole.Role;

@Component
public class WfGroupUserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public List<User> getUserNameByGroup(String groupName){
		
		@SuppressWarnings("unchecked")
		List<com.skynet.spms.persistence.entity.organization.userInformation.User>
		   list=getSession().createQuery(
				   " select user " +
				   " from Role m_role join m_role.m_User user " +
				   " where m_role.roleName like :role" )	  
			.setParameter("role", "%"+groupName+"%")
			.list();
		
		List<User> userList=new ArrayList<User>();
		
		for(com.skynet.spms.persistence.entity.organization.userInformation.User 
				wfUser:list){
			
			User user=new UserImpl(wfUser.getUsername(),wfUser.getUsername(),wfUser.getRealname());
						
			userList.add(user);		
		}
		return userList;
		
	}
	

	public List<Group> findGroupsByUser(String userId) {
		@SuppressWarnings("unchecked")
		List<Role> 
		   list=getSession().createQuery(
				   " select m_role from User user join user.m_Role m_role" +
		   		" where user.username = :username" )
			.setParameter("username", userId)
			.list();
		
		
		List<Group> groupList=new ArrayList<Group>();
		for(Role role:list){		
			Group group=new GroupImpl(role.getRoleName());
							
			groupList.add(group);		
		}
		return groupList;
	}
	
	
}
