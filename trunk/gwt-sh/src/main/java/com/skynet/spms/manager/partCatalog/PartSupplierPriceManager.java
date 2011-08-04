package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.PartSaleRelease;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.PartSupplierPriceIndex;

public interface  PartSupplierPriceManager extends CommonManager<PartSupplierPriceIndex> {

	public List<PartSupplierPriceIndex> queryByProps(Map<String, Object> values);

	public PartSupplierPriceIndex updateCascade(Map<String, Object> newValues, String itemID);

	public void insertCascade(PartSupplierPriceIndex partSupplierPrice,String partIndexId,String supplierCodeId);

	public List<PartSupplierPriceIndex> queryFilter(List clientCriteria);
}
