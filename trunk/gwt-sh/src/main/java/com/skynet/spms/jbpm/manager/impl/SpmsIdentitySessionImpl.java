package com.skynet.spms.jbpm.manager.impl;

import java.util.List;

import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.identity.impl.GroupImpl;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.spms.jbpm.dao.WfGroupUserDao;

public class SpmsIdentitySessionImpl implements IdentitySession{
	
	private WfGroupUserDao getWfDao() {
		EnvironmentImpl env=EnvironmentImpl.getCurrent();
		
		return env.get(WfGroupUserDao.class);
	}
	
	private Logger log=LoggerFactory.getLogger(MockSpmsIdentitySessionImpl.class);
	
	@Override
	public List<User> findUsersByGroup(String groupId) {
		
		return getWfDao().getUserNameByGroup(groupId);

		
	}	

	@Override
	public List<Group> findGroupsByUser(String userId) {
		return getWfDao().findGroupsByUser(userId);

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
