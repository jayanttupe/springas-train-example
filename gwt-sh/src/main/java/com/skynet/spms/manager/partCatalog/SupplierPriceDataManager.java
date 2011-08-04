package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.SalesPrice;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.salesPrice.SupplierSalesPrice;

public interface SupplierPriceDataManager extends CommonManager<SupplierSalesPrice> {

	public List<SupplierSalesPrice> queryByProps(Map<String, Object> values);
	
	public SupplierSalesPrice updateCascade(Map<String,Object> values, String itemId);
	
}
