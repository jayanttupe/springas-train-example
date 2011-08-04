package com.skynet.spms.manager.partCatalog.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.partCatalog.SalesPriceDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.AlternateSupplyLocationText;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;
import com.skynet.spms.tools.OneToManyTools;

@Service
@Transactional
public class SalesPriceDataManagerImpl extends CommonManagerImpl<SalesPrice> implements SalesPriceDataManager {

	@Override
	public List<SalesPrice> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(SalesPrice.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<SalesPrice> list = criteria.list();
		return list;
	}

	@Override
	public SalesPrice updateCascade(Map<String, Object> values, String itemId) {
		/*List list = (List)values.get("m_AlternateSupplyLocationText");
		list = OneToManyTools.ConvertToEntity(list, AlternateSupplyLocationText.class);
		values.put("m_AlternateSupplyLocationText", list);*/
		SalesPrice salesPrice = (SalesPrice) getSession().get(SalesPrice.class, itemId);
		BeanPropUtil.fillEntityWithMap(salesPrice, values);
		getSession().saveOrUpdate(salesPrice);
		return salesPrice;
	}

	
}
