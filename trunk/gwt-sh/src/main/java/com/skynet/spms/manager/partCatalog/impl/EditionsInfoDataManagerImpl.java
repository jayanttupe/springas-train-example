package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.EditionsInfoDataManager;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;

@Service
@Transactional
public class EditionsInfoDataManagerImpl extends CommonManagerImpl<EditionsInformation> implements EditionsInfoDataManager {
	@Override
	public List<EditionsInformation> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(EditionsInformation.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<EditionsInformation> list = criteria.list();
		return list;
	}
}
