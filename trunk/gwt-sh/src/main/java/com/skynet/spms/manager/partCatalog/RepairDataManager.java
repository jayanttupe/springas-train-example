package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.repairableCatalog.RepairData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;

public interface RepairDataManager extends CommonManager<RepairData> {

	public List<RepairData> queryByProps(Map<String, Object> values);
	
	public RepairData updateCascade(Map<String, Object> values,String itemId);

	public void insertCascade(RepairData repairData);
	
	public List<RepairData> queryByFilter(Map<String, Object> values);
}
