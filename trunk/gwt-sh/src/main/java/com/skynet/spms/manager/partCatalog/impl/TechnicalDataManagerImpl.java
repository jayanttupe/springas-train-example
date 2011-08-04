package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.TechnicalDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;

@Service
@Transactional
public class TechnicalDataManagerImpl extends CommonManagerImpl<PartTechnicalData> implements TechnicalDataManager {

	@Override
	public List<PartTechnicalData> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(PartTechnicalData.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<PartTechnicalData> list = criteria.list();
		return list;
	}

	
}
