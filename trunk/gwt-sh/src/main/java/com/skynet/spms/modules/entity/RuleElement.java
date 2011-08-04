package com.skynet.spms.modules.entity;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;

import com.skynet.spms.modules.common.FeatureList;

public class RuleElement {
	
	private FeatureList featureList; 
	
	private final String moduleName;

	private String desc;
	
	private Map<String,DataFilter> dsFilterMap=new HashMap<String,DataFilter>();
	
	public RuleElement(Element elem,PreDefineElement preDefine) {
		moduleName = elem.attributeValue("name");
		
		featureList=new FeatureList(elem,preDefine);	
		
		Element dataSourceElem = elem.element("data");
		if(dataSourceElem!=null){
			for(Object val:dataSourceElem.elements("dataSource")){
				Element dsElem=(Element)val;
				String name=dsElem.attributeValue("name");
				DataFilter filter = new DataFilter(dsElem,preDefine);
				dsFilterMap.put(name,filter);
			}
		}

	}

	public FeatureList getFeatureList() {
		return featureList;
	}

	public void setFeatureList(FeatureList featureList) {
		this.featureList = featureList;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Map<String, DataFilter> getDsFilterMap() {
		return dsFilterMap;
	}

//	public void setDsFilterMap(Map<String, DataFilter> dsFilterMap) {
//		this.dsFilterMap = dsFilterMap;
//	}

	public String getModuleName() {
		return moduleName;
	}
	
	
}
