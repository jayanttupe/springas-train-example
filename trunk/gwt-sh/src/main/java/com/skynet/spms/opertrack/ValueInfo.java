package com.skynet.spms.opertrack;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ValueInfo {
	
	private static final String NULL = "NULL";

	private Logger log = LoggerFactory.getLogger(ValueInfo.class);

	
	public ValueInfo(Object previousStatus, Object currStatus,String fieldName) {
		this.previousVal=previousStatus;
		this.currVal=currStatus;
		this.fieldName=fieldName;
		isInsert=false;
	}
	
	public ValueInfo(Object currStatus,String fieldName){
		this.previousVal=null;
		this.currVal=currStatus;
		this.fieldName=fieldName;
		isInsert=true;
	}
	
	
	private final boolean isInsert;
	
	private final String fieldName;

	private final Object previousVal;
	
	private final Object currVal;
	
	public boolean isEqualsObj(){
		
		return EqualsBuilder.reflectionEquals(previousVal, currVal);
	}

	public boolean isEqualsObj(String fieldName){
		try{
			Object preVal=PropertyUtils.getProperty(previousVal, fieldName);
			Object curVal=PropertyUtils.getProperty(currVal,fieldName);		
			return EqualsBuilder.reflectionEquals(preVal, curVal);
		}catch(Exception e){
			log.debug("expend obj fail");
			return false;
		}
	}
	
	public boolean isInsert() {
		return isInsert;
	}

	public String getFieldName() {
		return fieldName;
	}
	
	public String getPrevious(){
		return getStringValue(previousVal);
	}

	public String getCurrent(){
		return getStringValue(currVal);
	}
	
	public String getPreviousProp(String propName){
		return getStringValue(previousVal,propName);
	}
	
	public String getCurrentProp(String propName){
		return getStringValue(currVal,propName);
	}
	
	private String getStringValue(Object val,String prop){
		if(val==null){
			return NULL;
		}
		
		try{
			return BeanUtils.getProperty(val, prop);
		}catch(Exception e){
			log.warn("field access fail:"+prop);
			return "INVALID";
		}
	}

	private String getStringValue(Object val){
		if(val==null){
			return NULL;
		}else if(val.getClass().isEnum()){
			return ((Enum)val).name();
		}else{
			return ConvertUtils.convert(val);
		}
	}
	
	
	
	
}
