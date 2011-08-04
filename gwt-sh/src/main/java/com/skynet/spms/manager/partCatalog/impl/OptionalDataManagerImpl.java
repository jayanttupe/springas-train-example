package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.OptionalDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.optionalData.OptionalPart;

@Service
@Transactional
public class OptionalDataManagerImpl extends CommonManagerImpl<OptionalPart> implements OptionalDataManager {
	@Override
	public List<OptionalPart> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(OptionalPart.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<OptionalPart> list = criteria.list();
		return list;
	}


	
}
