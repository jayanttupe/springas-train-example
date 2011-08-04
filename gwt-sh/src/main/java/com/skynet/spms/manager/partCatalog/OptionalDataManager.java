package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.optionalData.OptionalPart;

public interface OptionalDataManager extends CommonManager<OptionalPart> {

	public List<OptionalPart> queryByProps(Map<String, Object> values);
	
}
