package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.RoleManager;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userRole.Role;

@Service
@Transactional
public class RoleManagerImpl extends CommonManagerImpl<Role> implements RoleManager {

	@Override
	public List<Role> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(Role.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<Role> list = criteria.list();
		return list;
	}
	
	@Override
	public List<Role> queryByFilter(Map<String, Object> values) {
		Query query = CriteriaConverter.convertValueMapToQuery(getSession(), values, Role.class);
		return query.list();
	}

	@Override
	public List<Role> queryByUserId(String userId) {
		Criteria criteria = getSession().createCriteria(Role.class);
		criteria.createCriteria("m_User", "usr", Criteria.INNER_JOIN);
		criteria.add(Restrictions.eq("usr.id", userId));
		return criteria.list();
	}

	@Override
	public Role updateRole(Map<String, Object> values, String itemId) {
		Role role = (Role)getSession().get(Role.class, itemId);
		Integer approvalQuota = null;
		Long quota = (Long)values.get("approvalQuota");
		if(quota != null){
			approvalQuota = (int)(long)quota;
		}
		role.setApprovalQuota(approvalQuota);
		getSession().saveOrUpdate(role);
		return role;
	}

}
