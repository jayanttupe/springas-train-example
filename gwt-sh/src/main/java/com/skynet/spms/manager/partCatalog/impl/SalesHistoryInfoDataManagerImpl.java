package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.SalesHistoryInfoDataManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.salseHistoryInfo;

@Service
@Transactional
public class SalesHistoryInfoDataManagerImpl extends CommonManagerImpl<salseHistoryInfo> implements SalesHistoryInfoDataManager {
	@Override
	public List<salseHistoryInfo> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(salseHistoryInfo.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<salseHistoryInfo> list = criteria.list();
		return list;
	}
}
