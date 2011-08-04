package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.CustomerCreditLineManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.creditLine.customerCreditLine.CustomerCreditLine;

@Service
@Transactional
public class CustomerCreditLineManagerImpl extends CommonManagerImpl<CustomerCreditLine> implements CustomerCreditLineManager {

	@Override
	public List<CustomerCreditLine> queryByProps(Map<String, Object> props) {
		Criteria criteria = getSession().createCriteria(CustomerCreditLine.class);
		for(Map.Entry<String, Object> entry : props.entrySet()){
			if(entry.getValue() != null){
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}	
		return criteria.list();
	}

}
