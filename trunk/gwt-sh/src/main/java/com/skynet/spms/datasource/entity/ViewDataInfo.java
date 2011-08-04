package com.skynet.spms.datasource.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ViewDataInfo {

	private Logger log = LoggerFactory.getLogger(ViewDataInfo.class);

	private Set<FieldMetaInfo> fieldSet;
	
	private Set<String> fieldNameSet=new HashSet<String>();

	private Class<?> cls;
	
	private String pkName;

	private String mangName;

	public void setMangName(String mangName) {
		this.mangName = mangName;
	}

	public String getMangName() {
		return mangName;
	}

	public Class<?> getViewFormCls() {
		return cls;
	}
	
	public String getPkName(){
		return pkName;
	}
	
	public void setPkName(String name){
		this.pkName=name;
	}

	public void setViewFormCls(Class<?> cls) {
		this.cls = cls;
	}

	public Map getDataMap(Object entity) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		for (FieldMetaInfo field : fieldSet) {

			String name = field.getName();
			try {
				Object val = PropertyUtils.getProperty(entity, name);
				dataMap.put(name, val);
			} catch (Exception e) {
				log.error("field read fail:" + name);
			}

		}
		return dataMap;
	}

	public void setFieldList(Set<FieldMetaInfo> fieldInfoList) {
		fieldSet = fieldInfoList;
		for(FieldMetaInfo field:fieldSet){
			fieldNameSet.add(field.getName());
		}
	}

	
	public Map<String,Object> getClearDataMap(Map map){
		Map<String,Object> dataMap=new HashMap<String,Object>();
		for(Object keyObj:map.keySet()){
			String key=(String)keyObj;
			Object obj=map.get(keyObj);
			
			//add custom filter.
			if(key.startsWith("_")&&
					!fieldNameSet.contains(key)){
					continue;				
			}
			
			dataMap.put(key,obj);		
		}
		
		
		return dataMap;
	}
}
