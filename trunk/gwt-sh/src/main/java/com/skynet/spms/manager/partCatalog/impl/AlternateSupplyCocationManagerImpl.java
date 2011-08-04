package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.AlternateSupplyLocationManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.AlternateSupplyLocationText;


@Service
@Transactional
public class AlternateSupplyCocationManagerImpl extends CommonManagerImpl<AlternateSupplyLocationText> implements AlternateSupplyLocationManager {
	@Override
	public List<AlternateSupplyLocationText> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(AlternateSupplyLocationText.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		return criteria.list();
	}
	
}
