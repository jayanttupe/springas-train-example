package com.skynet.spms.datasource;


import com.skynet.spms.datasource.entity.ViewDataInfo;

public interface DataInfoService {
	
	ViewDataInfo generDataInfo(String ruleName, String modName, String dsName);

	
}
