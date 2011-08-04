package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.PriceBreakDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.PriceBreak;
@Service
@Transactional
public class PriceBreakDataManagerImp extends CommonManagerImpl <PriceBreak> implements PriceBreakDataManager {

	@Override
	public List<PriceBreak> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(PriceBreak.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<PriceBreak> list = criteria.list();
		return list;
	}
   
}
