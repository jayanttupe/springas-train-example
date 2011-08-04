package com.skynet.spms.jbpm.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.identity.impl.GroupImpl;
import org.jbpm.pvm.internal.identity.impl.UserImpl;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockSpmsIdentitySessionImpl implements IdentitySession {

	private Session getSession() {
		EnvironmentImpl env=EnvironmentImpl.getCurrent();

		SessionFactory sessionFactory=env.get(SessionFactory.class);
		return sessionFactory.getCurrentSession();
	}
	
	private Logger log=LoggerFactory.getLogger(MockSpmsIdentitySessionImpl.class);
	
	@Override
	public List<User> findUsersByGroup(String groupId) {
		log.info("find users by group id:"+groupId);
		
		List<User> list=new ArrayList<User>();
		for(int i=0;i<10;i++){
			User user=new UserImpl(groupId+"_"+i, "foo", "bar");
			list.add(user);
		}		
		return list;
	}	

	@Override
	public List<Group> findGroupsByUser(String userId) {
		log.info("find groups by user id:"+userId);
		
		String groupName=StringUtils.substringBefore(userId, "_");
		
		Group group=new GroupImpl(groupName);		
		List<Group> list= new ArrayList<Group>();
		list.add(group);
		
		return list;
	}
	
	@Override
	public GroupImpl findGroupById(String groupId) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public User findUserById(String userId) {
		throw new UnsupportedOperationException();
	}	
	
	@Override
	public List<User> findUsersById(String... userIds) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public List<Group> findGroupsByUserAndGroupType(String userId,
			String groupType) {
		throw new UnsupportedOperationException();
	}

	
	@Override
	public String createUser(String userName, String givenName,
			String familyName, String businessEmail) {
		throw new UnsupportedOperationException();
	}


	@Override
	public void deleteUser(String userId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String createGroup(String groupName, String groupType,
			String parentGroupId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteGroup(String groupId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void createMembership(String userId, String groupId, String role) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteMembership(String userId, String groupId, String role) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> findUsers() {
		throw new UnsupportedOperationException();
	}
}
