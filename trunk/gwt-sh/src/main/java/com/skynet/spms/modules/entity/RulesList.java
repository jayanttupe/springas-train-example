package com.skynet.spms.modules.entity;

import java.util.HashSet;
import java.util.Set;

import org.dom4j.Element;

import com.skynet.spms.modules.common.FilterTool;

public class RulesList {
	
	private FilterTool filterTool;
	
	private Set<String> modNameSet=new HashSet<String>();
	
	public RulesList(Element elem) {
		
		PreDefineElement preDefine=new PreDefineElement(elem);
		
		Element filterElem=elem.element("moduleFilter");
		if(filterElem==null){
			filterTool=new FilterTool(null,preDefine);
		}else{
			filterTool=new FilterTool(elem.element("moduleFilter"),preDefine);
		}
		
		Element modsElem=elem.element("modules");
		if(modsElem!=null){
			for(Object val:modsElem.elements("module")){
				Element modElem=(Element)val;			
				modNameSet.add(modElem.attributeValue("name"));
			}		
		}
	}

	public boolean isExistDefine(String modName){
		return modNameSet.contains(modName);
	}
	
//	public FilterTool getModuleFilter(){
//		return filterTool;
//	}

	public boolean doValid(String field) {
		if(modNameSet.contains(field)){
			return true;
		}
		return filterTool.doValid(field);
	}
	
	

}
