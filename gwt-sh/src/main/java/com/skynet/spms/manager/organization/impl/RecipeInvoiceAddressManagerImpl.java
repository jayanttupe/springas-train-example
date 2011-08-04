package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.ConsigneeAddressManager;
import com.skynet.spms.manager.organization.RecipeInvoiceAddressManager;
import com.skynet.spms.manager.organization.ShippingAddressManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.RecipeInvoiceAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;

@Service
@Transactional
public class RecipeInvoiceAddressManagerImpl extends CommonManagerImpl<RecipeInvoiceAddress> implements RecipeInvoiceAddressManager {

	@Override
	public List<RecipeInvoiceAddress> queryByProps(Map<String, Object> props) {
		Criteria criteria = getSession().createCriteria(RecipeInvoiceAddress.class);
		for(Map.Entry<String, Object> entry : props.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return criteria.list();
	}

	
}
