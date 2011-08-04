package com.skynet.spms.datasource.entity;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class EntityMetaInfo {
	
	private Map<String,FieldMetaInfo> fieldMap=new LinkedHashMap<String,FieldMetaInfo>();
		
	private final String pkName;
	
	public String getPkName(){
		return pkName;
	}
	
	public EntityMetaInfo(String pkName){
	
		this.pkName=pkName;
	}
	
	public FieldMetaInfo getFieldMetaInfoByName(String fieldName){
		return  fieldMap.get(fieldName);
	}

	public Set<String> getFieldNameSet() {
		Set<String> nameSet=new LinkedHashSet<String>();
		for(FieldMetaInfo field:fieldMap.values()){
			if(field.isNonDisplay()){
				continue;
			}
			nameSet.add(field.getName());
		}
		return nameSet;
	}
	
	public Set<FieldMetaInfo> getFieldSet(){
		Set<FieldMetaInfo> nameSet=new LinkedHashSet<FieldMetaInfo>();
		for(FieldMetaInfo field:fieldMap.values()){
			nameSet.add(field.cloneNew());
		}
		return nameSet;
	}

	public void fillFieldSet(Set<FieldMetaInfo> fieldSet) {
		for(FieldMetaInfo field:fieldSet){
			fieldMap.put(field.getName(), field);
		}		
		if(StringUtils.isNotBlank(pkName)){
			fieldMap.get(pkName).setPk();
		}
	}
	
	

}
