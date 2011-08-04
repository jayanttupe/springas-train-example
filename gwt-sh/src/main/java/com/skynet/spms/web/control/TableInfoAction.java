package com.skynet.spms.web.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.datadictory.DataDictionaryManager;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.entity.FieldInfo;
import com.skynet.spms.client.service.TableInfoService;
import com.skynet.spms.datasource.EntityInfoService;
import com.skynet.spms.datasource.entity.EntityMetaInfo;
import com.skynet.spms.datasource.entity.FieldMetaInfo;
import com.skynet.spms.modules.ModuleDefineService;
import com.skynet.spms.modules.ModuleInfoService;
import com.skynet.spms.modules.RuleService;
import com.skynet.spms.modules.entity.DataElement;
import com.skynet.spms.modules.entity.ModulesElement;

@Controller
@GwtRpcEndPoint
public class TableInfoAction implements TableInfoService {

	Logger log = LoggerFactory.getLogger(TableInfoAction.class);

	@Autowired
	private RuleService ruleLoader;
	
	@Autowired
	private ModuleInfoService moduleLoader;
	
	@Autowired
	private PropManager messageProp;
	
	@Autowired
	private EntityInfoService entityInfoMang;
	
	@Autowired
	private DataDictionaryManager dataDictMang;


	@Override
	public DataInfo getFieldList(String moduleName) {
		
		ModulesElement modElem = moduleLoader.getModulesByName(moduleName);

		DataElement dataElem = modElem.getDataSourceList()
				.getDefaultDataSource();
		
		DataInfo dataInfo=getDataInfo(moduleName,dataElem);
				
		operDsInfo(dataInfo);		
		return dataInfo;
	}


	private void operDsInfo(DataInfo dataInfo) {
		PropertyEntity propEntity=messageProp.getPropEntity(GwtActionHelper.getLocale(),PropEnum.DatabaseInfo);
		for(FieldInfo field:dataInfo.getFieldList()){
			field.setTitle(propEntity.getProperty(dataInfo.getClsName()+"."+field.getName()));			
		}
	}
	

	public DataInfo generDataInfo(String moduleName,
			String dsName) {

		String rule=GwtActionHelper.getCurrRule();

		ModulesElement modElem = moduleLoader.getModulesByName(moduleName);

		DataElement dataElem = modElem.getDataSourceList()
				.getDataSource(dsName);

		return getDataInfo(modElem.getDesc(),dataElem);
	}
	
	
	
	/**
	 * @param moduleName
	 * @param dataElem
	 * @return
	 * 
	 * 获取元数据结构
	 */
	private DataInfo getDataInfo(String moduleName,DataElement dataElem) {
		EntityMetaInfo tableMetaInfo = entityInfoMang.getEntityInfoByClsName(dataElem
				.getClassName());

		DataInfo dataInfo = new DataInfo();
		dataInfo.setDsDefineName(moduleName+"."+dataElem.getName());
		dataInfo.setClsName(dataElem.getClassName());
		
		List<FieldMetaInfo> fieldInfoList=new ArrayList<FieldMetaInfo>(dataElem.getFieldInfoSet(tableMetaInfo));
//		Collections.sort(fieldInfoList);
		
		Locale locale=GwtActionHelper.getLocale();
		//后台国际化处理
		for(FieldMetaInfo fieldInfo:fieldInfoList){
			if(fieldInfo.getDataDictEnum()!=null){
				fieldInfo.setValueArray(dataDictMang.getDisplayDataArrayByName(fieldInfo.getDataDictEnum()));
			}else if(fieldInfo.getEnumCls()!=null){
				fieldInfo.setValueArray(messageProp.getDisplayMapByEnum(fieldInfo.getEnumCls(), locale));
			}
			
			dataInfo.addFieldInfo(fieldInfo.getFieldInfo());
		}
		return dataInfo;
	}


	@Override
	public DataInfo getFieldList(String moduleName, String dsName) {
		
		ModulesElement modElem = moduleLoader.getModulesByName(moduleName);

		DataElement dataElem = modElem.getDataSourceList()
				.getDataSource(dsName);
		
		DataInfo dataInfo=getDataInfo(moduleName,dataElem);
				
		operDsInfo(dataInfo);
		return dataInfo;
	}


	@Override
	public DataInfo getFieldListByClsName(String className) {
		EntityMetaInfo tableMetaInfo = entityInfoMang.getEntityInfoByClsName(className);

		DataInfo dataInfo = new DataInfo();
		dataInfo.setDsDefineName(className);
		dataInfo.setClsName(className);
		
//		List<FieldMetaInfo> fieldInfoList=new ArrayList<FieldMetaInfo>(dataElem.getFieldInfoSet(tableMetaInfo));
//		Collections.sort(fieldInfoList);
		
		Locale locale=GwtActionHelper.getLocale();
		for(FieldMetaInfo fieldInfo:tableMetaInfo.getFieldSet()){
			if(fieldInfo.getDataDictEnum()!=null){
				fieldInfo.setValueArray(dataDictMang.getDisplayDataArrayByName(fieldInfo.getDataDictEnum()));
			}else if(fieldInfo.getEnumCls()!=null){
				fieldInfo.setValueArray(messageProp.getDisplayMapByEnum(fieldInfo.getEnumCls(), locale));
			}
			
			dataInfo.addFieldInfo(fieldInfo.getFieldInfo());
		}
		return dataInfo;
	}


	@Override
	public DataInfo getFieldListWithouti18n(String moduleName,String dsName) {
		ModulesElement modElem = moduleLoader.getModulesByName(moduleName);

		DataElement dataElem = modElem.getDataSourceList()
				.getDataSource(dsName);
		
		DataInfo dataInfo=getDataInfo(moduleName,dataElem);
		
		return dataInfo;
	}

}
