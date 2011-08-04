package com.skynet.spms.modules.entity;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;

import com.skynet.common.aop.cache.ICacheGroup;
import com.skynet.common.strtemplate.SimpleTmpTool;
import com.skynet.spms.modules.common.FilterTool;

public class RuleGlobeConfig implements ICacheGroup{
	
	private final String upperRule;
	
	private final String name;
		
	private final Element rootElem;
	
	
	public boolean isTop(){
		return StringUtils.isBlank(upperRule);
	}
	
	public RuleGlobeConfig(Element elem){
		this.rootElem=elem;
		this.name=elem.attributeValue("name");
		this.upperRule=elem.attributeValue("extends");		
	}
	

	public PreDefineElement generLocalPreDefine(PreDefineElement upperDefine) {
		return new PreDefineElement(rootElem);
	}
	
	public FilterTool generFilterTool(PreDefineElement preDefine){
		return new FilterTool(rootElem,preDefine);
	}
	
	
	
	public String getUpperRule(){
		return upperRule;
	}

	private static final String Tmp="//modules/module[@name='${0}']";
	public Element getRuleModule(String modName) {
		
		String xpath=SimpleTmpTool.generByTmp(Tmp,modName);
		
		return (Element) rootElem.selectSingleNode(xpath);
	}
	
	private static final String NameList="//modules/module/@name";
	public List<String> getLocalModule(){
		@SuppressWarnings("unchecked")
		List<Attribute> nodeList=rootElem.selectNodes(NameList);
		List<String> resultList=new ArrayList<String>();
		for(Attribute attr:nodeList){
			resultList.add(attr.getValue());
		}
		return resultList;
	}
	
//	public FilterTool getModuleFilter(){
//		return moduleFilter;
//	}

	@Override
	public String[] getCacheGroups() {
		return new String[]{name};
	}

//	public void generRuleRelationWithUpper(
//			RuleRelationInfo upperRelInfo, RuleGlobeConfig ruleConfig) {
//		
//		preDefElem=new PreDefineElement(ruleConfig.getLocalPreDefine(upperRelInfo));
//		return;
//	}



}
