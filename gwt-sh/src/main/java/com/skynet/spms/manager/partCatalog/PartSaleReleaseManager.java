package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.PartSaleRelease;

public interface  PartSaleReleaseManager extends CommonManager<PartSaleRelease> {

	public List<PartSaleRelease> queryByProps(Map<String, Object> values);

	public PartSaleRelease updateCascade(Map<String, Object> newValues, String itemID);

	public void insertCascade(PartSaleRelease partSaleRelease,String partIndexId);
	
	public List<PartSaleRelease> queryFilter(List clientCriteria);

}
