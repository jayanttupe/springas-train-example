package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice.salseHistoryInfo;

public interface SalesHistoryInfoDataManager extends CommonManager<salseHistoryInfo> {

	public List<salseHistoryInfo> queryByProps(Map<String, Object> values);

}
