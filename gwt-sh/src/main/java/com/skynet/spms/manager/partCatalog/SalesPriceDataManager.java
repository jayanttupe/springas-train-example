package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;

public interface SalesPriceDataManager extends CommonManager<SalesPrice> {

	public List<SalesPrice> queryByProps(Map<String, Object> values);
	
	public SalesPrice updateCascade(Map<String,Object> values, String itemId);
	
}
