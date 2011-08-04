package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.technicalData.PartTechnicalData;

public interface TechnicalDataManager extends CommonManager<PartTechnicalData> {

	public List<PartTechnicalData> queryByProps(Map<String, Object> values);
	
}
