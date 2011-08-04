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
import com.skynet.spms.manager.partCatalog.SupplierPriceDataManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.AlternateSupplyLocationText;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.salesPrice.SupplierSalesPrice;
import com.skynet.spms.tools.OneToManyTools;

@Service
@Transactional
public class SupplierPriceDataManagerImpl extends CommonManagerImpl<SupplierSalesPrice> implements SupplierPriceDataManager {

	@Override
	public List<SupplierSalesPrice> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(SupplierSalesPrice.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<SupplierSalesPrice> list = criteria.list();
		return list;
	}

	@Override
	public SupplierSalesPrice updateCascade(Map<String, Object> values, String itemId) {
		/*List list = (List)values.get("m_AlternateSupplyLocationText");
		list = OneToManyTools.ConvertToEntity(list, AlternateSupplyLocationText.class);
		values.put("m_AlternateSupplyLocationText", list);*/
		SupplierSalesPrice supplierSalesPrice = (SupplierSalesPrice) getSession().get(SupplierSalesPrice.class, itemId);
		BeanPropUtil.fillEntityWithMap(supplierSalesPrice, values);
		getSession().saveOrUpdate(supplierSalesPrice);
		return supplierSalesPrice;
	}

}
