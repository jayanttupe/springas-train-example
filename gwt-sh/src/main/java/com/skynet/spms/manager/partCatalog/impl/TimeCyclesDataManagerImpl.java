package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.PartApplicationDataManager;
import com.skynet.spms.manager.partCatalog.TimeCyclesDataManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.timeCycleData.TimeCyclesData;

@Service
@Transactional
public class TimeCyclesDataManagerImpl extends CommonManagerImpl<TimeCyclesData> implements TimeCyclesDataManager {
	@Override
	public List<TimeCyclesData> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(TimeCyclesData.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<TimeCyclesData> list = criteria.list();
		return list;
	}
}
