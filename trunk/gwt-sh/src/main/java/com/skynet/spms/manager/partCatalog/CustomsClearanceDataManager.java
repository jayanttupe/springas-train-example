package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.customsData.CustomsClearanceData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;

public interface CustomsClearanceDataManager extends CommonManager<CustomsClearanceData> {

	public List<CustomsClearanceData> queryByProps(Map<String, Object> values);
	
	public CustomsClearanceData updateCascade(Map<String, Object> values,String itemId);
	
}
