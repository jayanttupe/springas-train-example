package com.skynet.spms.datasource.imp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataInfoService;
import com.skynet.spms.datasource.EntityInfoService;
import com.skynet.spms.datasource.entity.EntityMetaInfo;
import com.skynet.spms.datasource.entity.ViewDataInfo;
import com.skynet.spms.modules.ModuleInfoService;
import com.skynet.spms.modules.RuleService;
import com.skynet.spms.modules.entity.DataElement;
import com.skynet.spms.modules.entity.ModulesElement;

@Component
public class DataInfoServiceImp implements DataInfoService {

	private Logger log = LoggerFactory.getLogger(DataInfoServiceImp.class);

	
	@Autowired
	private RuleService ruleLoader;
	
	@Autowired
	private ModuleInfoService moduleLoader;
	
	@Autowired
	private EntityInfoService infoGener;
	

	@CacheSource("viewDataSourceInfo")
	@Override
	public ViewDataInfo generDataInfo(String ruleName, String modName,
			String dsName) {
		
		ModulesElement modElem=moduleLoader.getModulesByName(modName);
		
		DataElement dataElem=modElem.getDataSourceList().getDataSource(dsName);
		
		EntityMetaInfo tableMetaInfo = infoGener.getEntityInfoByClsName(dataElem
				.getClassName());

		ViewDataInfo dataInfo = new ViewDataInfo();
		dataInfo.setViewFormCls(BeanPropUtil.getClassByName(dataElem.getClassName()));
		dataInfo.setMangName(dataElem.getName());
		dataInfo.setFieldList(dataElem.getFieldInfoSet(tableMetaInfo));
		dataInfo.setPkName(tableMetaInfo.getPkName());
		return dataInfo;
	}
	
	

	
	
	
}
