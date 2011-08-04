package com.skynet.spms.modules.common;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.skynet.spms.modules.entity.DataElement;
import com.skynet.spms.modules.entity.DataFilter;
import com.skynet.spms.modules.entity.RuleElement;

public class DataSourceList {
	
	private Map<String,DataElement> dataSourceMap=new HashMap<String,DataElement>();

	private final String defaultDataName;
	
	public void combineDataSource(RuleElement ruleElem){
		for(Map.Entry<String, DataFilter> entry:ruleElem.getDsFilterMap().entrySet()){
			String name=entry.getKey();
			DataFilter dataFilter=entry.getValue();
			
			if(dataSourceMap.containsKey(name)){
			   dataSourceMap.get(name).filterDataElem(dataFilter);
			}
		}
	}
	
	public DataSourceList(DataSourceList list){
		this.defaultDataName=list.defaultDataName;
		this.dataSourceMap.putAll(list.dataSourceMap);
	}
	public DataSourceList(Element dsElem){
		if(dsElem==null){
			defaultDataName="";
			return;
		}
		defaultDataName=dsElem.attributeValue("default");		
	}
	
	public void addData(DataElement data){
		dataSourceMap.put(data.getName(), data);
	}
	
	public void addDataList(DataSourceList mod,String ruleName){
 		dataSourceMap.putAll(mod.dataSourceMap);
 	}
		
	public DataElement getDefaultDataSource(){
		return dataSourceMap.get(defaultDataName);
	}
	
	public DataElement getDataSource(String name){
		return dataSourceMap.get(name);
	}

	
	
}
