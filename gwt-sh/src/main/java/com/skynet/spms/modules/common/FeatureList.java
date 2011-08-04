package com.skynet.spms.modules.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.skynet.spms.modules.entity.FeatureDefine;
import com.skynet.spms.modules.entity.PreDefineElement;

public class FeatureList implements Iterable<FeatureDefine>{
	
	private Set<FeatureDefine> featureSet=new LinkedHashSet<FeatureDefine>();
	
//	private FilterTool featureFilter;
	
	public FeatureList(FeatureList list){
		this.featureSet.addAll(list.featureSet);
	}

	public FeatureList(Element elem,PreDefineElement preDefine){
		Element featureElem=elem.element("feature");
		if(featureElem==null){
			return;
		}

		String text = featureElem.getText();
		if (StringUtils.isNotBlank(text)) {
			String[] array = StringUtils.split(text, ",");
			array=StringUtils.stripAll(array);
			for(String val:array){
				FeatureDefine feature=new FeatureDefine(val);
				featureSet.add(feature);
			}
		}
		String refDefine = featureElem.attributeValue("ref");
		if(StringUtils.isNotBlank(refDefine)){
			List<String> preDefList=preDefine.getPreDefRuleByName(refDefine);
			for(String val:preDefList){
				FeatureDefine feature=new FeatureDefine(val);
				featureSet.add(feature);
			}
			
		}
		
//		featureFilter = new FilterTool(featureElem, preDefine);

	}
	
	public void addAll(String[] featureArray){
		for(String str:featureArray){
			addFeature(str);
		}		
	}
	
	public void addAll(List<String> list) {
		addAll(list.toArray(new String[0]));
	}
	
	public void addFeature(String str){
		FeatureDefine feature=new FeatureDefine(str);
		featureSet.add(feature);
	}	

	@Override
	public Iterator<FeatureDefine> iterator() {
		return featureSet.iterator();
	}
	
	public void validFeature(FeatureList list){
	
		for(FeatureDefine def:featureSet){
			
			if(list.doValid(def)){
				def.setEnable(true);
			}else{
				def.setEnable(false);
			}
		}
	}

		
	public Set<String> getFeatureNames() {
		Set<String> nameSet=new HashSet<String>();
		for(FeatureDefine def:featureSet){
			nameSet.add(def.getFeatureName());
		}
		return nameSet;
	}

	public boolean doValid(FeatureDefine define){
//		if(featureSet.contains(define)){
		return featureSet.contains(define);
//		}else{
//			return featureFilter.doValid(define.getFeatureName());
//		}
		
	}

}
