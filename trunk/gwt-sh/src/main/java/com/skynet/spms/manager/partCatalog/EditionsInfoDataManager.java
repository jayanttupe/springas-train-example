package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.editionsInfo.EditionsInformation;

public interface EditionsInfoDataManager extends CommonManager<EditionsInformation> {

	public List<EditionsInformation> queryByProps(Map<String, Object> values);

}
