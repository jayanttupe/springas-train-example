package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;

public interface PartApplicationDataManager extends CommonManager<PartApplicationData> {

	public List<PartApplicationData> queryByProps(Map<String, Object> values);

	public PartApplicationData updateCascade(Map<String, Object> values,String itemId);
}
