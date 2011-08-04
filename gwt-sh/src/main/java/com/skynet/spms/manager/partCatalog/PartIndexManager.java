package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;

public interface PartIndexManager extends CommonManager<PartIndex> {

	public void insertCascade(PartIndex partIndex,String ManufacturerCodeId);
	
	public PartIndex updateCascade(Map<String, Object> newValues, String itemID);
	
	public List<PartIndex> queryFilter(List clientCriteria,int startRow,int endRow);
	
	public List<PartIndex> queryByProps(Map<String,Object> values,int startRow,int endRow);

}
