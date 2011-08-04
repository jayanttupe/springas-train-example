package com.skynet.spms.manager.partCatalog;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;

public interface BasicInfoManager extends CommonManager<BasicInformation> {

	public List<BasicInformation> queryByProps(Map<String, Object> values);
	
	public BasicInformation updateCascade(Map<String, Object> values,String itemId);

}
