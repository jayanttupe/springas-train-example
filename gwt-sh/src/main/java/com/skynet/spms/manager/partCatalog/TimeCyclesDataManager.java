package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.timeCycleData.TimeCyclesData;

public interface TimeCyclesDataManager extends CommonManager<TimeCyclesData> {

	public List<TimeCyclesData> queryByProps(Map<String, Object> values);

}
