/**
 * File: 
 * copyright  2010-2011   Shanghai  Skynetsoft, INC.All Rights Reserved.
 * Date        Author      Changes
 * 2011-3-24   黄贇	    
 */

package com.skynet.spms.manager.organization.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.client.entity.UserInfo;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.Duty;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.customers.Customer;
import com.skynet.spms.persistence.entity.organization.userGroup.UserGroup;
import com.skynet.spms.persistence.entity.organization.userInformation.IDCard;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userInformation.UserInformation;
import com.skynet.spms.persistence.entity.organization.userRole.Role;
import com.skynet.spms.persistence.entity.spmsdd.EnableStatus;
import com.skynet.spms.web.form.SpmsUserDetail;

/**
 * Description
 * 
 * @see com.skynet.spms.manager.organization.impl.UserManagerImpl
 * @author 黄贇
 * @version 0.5
 */
@Service
@Transactional
public class UserManagerImpl extends CommonManagerImpl<User> implements UserManager {
	private Logger log = LoggerFactory.getLogger(UserManagerImpl.class);

	@Override
	public List<User> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(User.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<User> list = criteria.list();
		return list;
	}
	/**
	 * Description listUserByRestrictions
	 * 
	 * @see 根据用户组id来查询相应的User信息
	 * @param Map
	 * @param int
	 * @param int
	 * @return List
	 * @throws
	 */
	/*public List<User> listUserByRestrictions(Map map, int startRow, int endRow) {

		String userId = (String) map.get("userId");
		String groupId = (String) map.get("m_UserGroup");
		if (userId != null && !"".equals(userId))
			getSession()
					.createSQLQuery(
							"delete from spms_user_usergroup where user_id=? and usergroup_id=?")
					.setParameter(0, userId).setParameter(1, groupId)
					.executeUpdate();

		Criteria criteria = getSession().createCriteria(User.class);		
		criteria.add(Restrictions.eq("deleted", false));

		if(groupId!=null&&!"".equals(groupId))
			criteria.createCriteria("m_UserGroup", "m_UserGroup_joinname",
					Criteria.INNER_JOIN)
					.add(
					Restrictions.eq("m_UserGroup_joinname.id", groupId));
		
		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow);
		}

		return criteria.list();
	}
	
	public List<User> loadUsers(String[] itemsId){
		
		List usersList = new ArrayList();
		for(String id:itemsId){
			User user = (User) getSession().load(User.class, id);
			usersList.add(user);
		}
		
		return usersList;
		
	}

	@Override
	public void addOrRemoveUserFromGroup(String userId, String groupId) {
		// TODO Auto-generated method stub

		User user = (User) getSession().load(User.class, userId);
		UserGroup userGroup = (UserGroup) getSession().load(UserGroup.class,
				groupId);
		if (!user.getM_UserGroup().contains(userGroup)) {
			user.getM_UserGroup().add(userGroup);
			getSession().saveOrUpdate(user);
		}

	}*/
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private SaltSource saltSource;

	@Override
	public void insertUser(User user) {
		String enterpriseId = user.getEnterpriseId();
		if(enterpriseId != null){
			BaseEnterpriseInformation enterprise = (BaseEnterpriseInformation)getSession().get(BaseEnterpriseInformation.class, enterpriseId);
			user.setM_BaseEnterpriseInformation(enterprise);
		}
		String departmentId = user.getDepartmentId();
		if(departmentId != null){
			DepartmentInformation department = (DepartmentInformation)getSession().get(DepartmentInformation.class, departmentId);
			user.setM_DepartmentInformation(department);
		}
		String dutyId = user.getDutyId();
		if(dutyId != null){
			Duty duty = (Duty)getSession().get(Duty.class, dutyId);
			user.setM_Duty(duty);
		}
		//创建者及日期
		String currentUser = GwtActionHelper.getCurrUser();
		user.setCreateBy(currentUser);
		user.setCreateDate(new Date());
		//用户个人信息及用户识别卡，创建用户时同时创建一对一关联对象
		UserInformation userInfo = new UserInformation();
		IDCard idCard = new IDCard();
		user.setM_UserInformation(userInfo);
		user.setM_IDCard(idCard);
		
		doEncodePwd(user);
		
		getSession().saveOrUpdate(user);
	}

	private void doEncodePwd(User user) {
		if(StringUtils.isBlank(user.getPassword())){
			return;
		}
		UserDetails userDetail=new SpmsUserDetail(user);
		Object salt=saltSource.getSalt(userDetail);
		String encodePwd=pwdEncoder.encodePassword(userDetail.getPassword(), salt);
		user.setPassword(encodePwd);
		return ;
	}

	@Override
	public User updateUser(Map<String, Object> values, String itemId) {
		User user = (User)getSession().get(User.class, itemId);
		String enterpriseId = (String)values.remove("enterpriseId");
		if(enterpriseId != null){
			BaseEnterpriseInformation enterprise = (BaseEnterpriseInformation)getSession().get(BaseEnterpriseInformation.class, enterpriseId);
			user.setM_BaseEnterpriseInformation(enterprise);
		}
		String departmentId = (String)values.remove("departmentId");
		if(departmentId != null){
			DepartmentInformation department = (DepartmentInformation)getSession().get(DepartmentInformation.class, departmentId);
			user.setM_DepartmentInformation(department);
		}else{
			user.setM_DepartmentInformation(null);
		}
		String dutyId = (String)values.remove("dutyId");
		if(dutyId != null){
			Duty duty = (Duty)getSession().get(Duty.class, dutyId);
			user.setM_Duty(duty);
		}else{
			user.setM_Duty(null);
		}
		
		String username = (String)values.get("username");
		user.setUsername(username);
		String realname = (String)values.get("realname");
		user.setRealname(realname);
		String jobNumber = (String)values.get("jobNumber");
		user.setJobNumber(jobNumber);
		String password = (String)values.get("password");
		user.setPassword(password);
		String email = (String)values.get("email");
		user.setEmail(email);
		String status = (String)values.get("m_EnableStatus");
		EnableStatus m_EnableStatus = null;
		if(status != null){
			m_EnableStatus = EnableStatus.valueOf(status);
		}
		user.setM_EnableStatus(m_EnableStatus);
		
		getSession().saveOrUpdate(user);
		return user;
	}

	@Override
	public List<User> queryByFilter(Map<String, Object> values,int startRow,int endRow) {
		Query query = CriteriaConverter.convertValueMapToQuery(getSession(), values, User.class);
		if(endRow > 0){
			query.setFirstResult(startRow);
			query.setMaxResults(endRow - startRow);
		}
		log.info("queryString : " + query.getQueryString());
		return query.list();
	}
	@Override
	public List<User> queryByGroupId(String groupId) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.createCriteria("m_UserGroup", "ug", Criteria.INNER_JOIN);
		criteria.add(Restrictions.eq("ug.id", groupId));
		return criteria.list();
	}
	@Override
	public void deleteUser(String userId) {
		User user = (User)getSession().get(User.class, userId);
		user.setM_UserGroup(new ArrayList<UserGroup>());
		user.setM_Role(new ArrayList<Role>());
		user.setDeleted(true);
		getSession().saveOrUpdate(user);
	}
	@Override
	public User addRolesToUser(String id, List<String> roleIds) {
		User user = (User)getSession().get(User.class, id);
		List<Role> roleList = new ArrayList<Role>();
		for(String roleId : roleIds){
			Role role = (Role)getSession().get(Role.class, roleId);
			roleList.add(role);
		}
		user.setM_Role(roleList);
		getSession().saveOrUpdate(user);
		return user;
	}
	@Override
	public List<User> listUsers(int startRow,int endRow) {
		Criteria criteria = getSession().createCriteria(User.class);
		if(endRow > 0){
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow - startRow);
		}
		List<User> list = criteria.list();
		//临时代码，用于防止一对一关联表数据被删除造成运行错误，实际运行时需要删除
		for(User user : list){
			if(user.getM_IDCard() == null){
				IDCard idCard = new IDCard();
				user.setM_IDCard(idCard);
				getSession().saveOrUpdate(user);
			}
			if(user.getM_UserInformation() == null){
				UserInformation userInfo = new UserInformation();
				user.setM_UserInformation(userInfo);
				getSession().saveOrUpdate(user);
			}
		}
		//结束
		return list;
	}
	/**
	 * Description 供WebService调用的方法，根据用户名查询用户实体
	 * 
	 * @param username 用户名
	 * @return List 用户实体列表
	 */
	@Override
	public List<User> queryByUsername(String username,boolean iseq) {
		Criteria criteria = getSession().createCriteria(User.class);
		if(iseq){
			criteria.add(Restrictions.eq("username", username));
		}else{
			criteria.add(Restrictions.like("username","%" + username + "%"));
		}
		return criteria.list();
	}
	/**
	 * Description 供WebService调用的方法，根据用户名更新该用户的相关信息
	 * 
	 * @param username 用户名
	 * @param values 其他待更新数据
	 * @return boolean 是否更新成功
	 */
	@Override
	public boolean updateByUsername(String username, Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User)criteria.uniqueResult();
		if(user == null) return false;
		
		user.setRealname((String)values.get("realname"));
		user.setAccessLevel((String)values.get("accessLevel"));
		IDCard idCard = user.getM_IDCard();
		if(idCard == null){
			idCard = new IDCard();
			user.setM_IDCard(idCard);
		}
		idCard.setIdCardNumber((String)values.get("idCardNumber"));
		idCard.setExpiryDate((Date)values.get("expiryDate"));
		getSession().saveOrUpdate(user)	;
		return true;
	}
	@Override
	public CustomerIdentificationCode queryCustomerCodeByUsername(
			String username) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User)criteria.uniqueResult();
		if(user != null){
			BaseEnterpriseInformation enterprise = user.getM_BaseEnterpriseInformation();
			if(enterprise instanceof Customer){
				Customer customer = (Customer)enterprise;
				return customer.getM_CustomerIdentificationCode();
			}
		}
		return null;
	}
	@Override
	public void updateUser(User user) {
//		doEncodePwd(user);
		getSession().saveOrUpdate(user);
		
	}
	
	@Override
	public UserInfo getUserInfoByName(String userName){
				
		User user=(User) getSession()
		.createQuery("select user from User user " +
				" left join fetch user.m_DepartmentInformation department "+
				" left join fetch user.m_Duty duty where user.username = :name")
		.setString("name", userName)
		.uniqueResult();
		
		return user.getUserInfo();
		
	}
}

