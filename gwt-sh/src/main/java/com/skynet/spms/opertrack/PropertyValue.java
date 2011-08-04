package com.skynet.spms.opertrack;

import java.util.Date;

import org.dom4j.Element;

public class PropertyValue {
	
	private final String fieldName;
	
	private final String previousVal;
	
	private final String currVal;
	
	private Date modifyDate;
	
	private int id;
	
	public PropertyValue(Element elem){
		
		fieldName=elem.attributeValue("name");
		
		Element preElem=elem.element("old");
		if(preElem!=null){
			previousVal=preElem.getText();
		}else{
			previousVal=null;
		}
		
		Element currElem=elem.element("new");
		if(currElem!=null){
			currVal=currElem.getText();
		}else{
			currVal=null;
		}
		
	}

	public String getFieldName() {
		return fieldName;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return id;
	}

	public String getPreviousVal() {
		return previousVal;
	}

	public String getCurrVal() {
		return currVal;
	}
	
	public Date getModifyDate(){
		return modifyDate;
	}
	
	public void setModifyDate(Date date){
		this.modifyDate=date;
	}

}
