package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.DiscountMatrixManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.discountMatrix.DiscountItem;

@Service
@Transactional
public class DiscountMatrixManagerImpl extends CommonManagerImpl<DiscountItem> implements DiscountMatrixManager {
	@Override
	public List<DiscountItem> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(DiscountItem.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<DiscountItem> list = criteria.list();
		return list;
	}
}
