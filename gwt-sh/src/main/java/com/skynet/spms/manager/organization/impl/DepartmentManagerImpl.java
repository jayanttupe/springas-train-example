package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.DepartmentManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.DepartmentInformation;

@Service
@Transactional
public class DepartmentManagerImpl extends CommonManagerImpl<DepartmentInformation> implements DepartmentManager {

	private Logger log=LoggerFactory.getLogger(DepartmentManagerImpl.class);
	
	@Override
	public List<DepartmentInformation> queryTree(Map<String, Object> props) {
		Criteria criteria = getSession().createCriteria(DepartmentInformation.class);
		for(Map.Entry<String, Object> entry : props.entrySet()){
			if(entry.getValue() != null){
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}	
		return criteria.list();
	}
	
}
