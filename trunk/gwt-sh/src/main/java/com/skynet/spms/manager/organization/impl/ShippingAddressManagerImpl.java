package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Service
@Transactional
public class ShippingAddressManagerImpl extends CommonManagerImpl<ShippingAddress> implements ShippingAddressManager {

	@Override
	public List<ShippingAddress> queryByProps(Map<String, Object> props) {
		Criteria criteria = getSession().createCriteria(ShippingAddress.class);
		for(Map.Entry<String, Object> entry : props.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return criteria.list();
	}

	
}
