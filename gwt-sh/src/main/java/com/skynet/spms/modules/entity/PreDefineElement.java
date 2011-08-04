package com.skynet.spms.modules.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class PreDefineElement {
	
	private Map<String,List<String>> ruleMap=new HashMap<String,List<String>>();
			
	private final String ruleName;	
	
//	private PreDefineElement upperDefine;
	
	public  List<String> getPreDefRuleByName(String name){
		if(ruleMap.containsKey(name)){
			return ruleMap.get(name);
		}else{
			return new ArrayList<String>();
		}
	}
		
	public PreDefineElement(Element elem){
		fillPreDefMap(elem);	
		this.ruleName=elem.attributeValue("name");
	}

	private void fillPreDefMap(Element elem) {
		
		Element predefElem=elem.element("predefines");
		if(predefElem==null){
			return;
		}
		for(Object val:predefElem.elements("predefine")){
			Element preElem=(Element)val;
			
			String name=preElem.attributeValue("name");
			
			String[] valArray=StringUtils.stripAll(StringUtils.split(preElem.getText(),","));
			
			List<String> list=new ArrayList<String>(Arrays.asList(valArray));
			ruleMap.put(name,list);
			
			String ref=preElem.attributeValue("ref");
			if(StringUtils.isNotBlank(ref)){
				for(String refVal:StringUtils.split(ref,",")){
					List<String> refSet=ruleMap.get(refVal);
					ruleMap.get(name).addAll(refSet);
				}
			}
		}
	}
	
//	public PreDefineElement(Element elem,PreDefineElement upperPreDef){
//		ruleMap.putAll(upperPreDef.ruleMap);
//		fillPreDefMap(elem);
//		this.ruleName=elem.attributeValue("name");
//	}	
	

	public String getName() {
		return ruleName;
	}	

	
}
