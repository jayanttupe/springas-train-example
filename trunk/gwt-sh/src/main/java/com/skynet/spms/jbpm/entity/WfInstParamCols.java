package com.skynet.spms.jbpm.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.skynet.spms.client.entity.WfParams;

public class WfInstParamCols {

	public  static final String BUSINESS_KEY = "businessKey";

	public static final String PRIORITY_TYPE = "priorityType";

	public static final String FLOW_TYPE = "flowType";
	
	private Map<String,Serializable> instMap=new HashMap<String,Serializable>();
	
	private Map<String,String> persMap=new HashMap<String,String>();
	
	public void addInstWfParam(String key,Serializable param){
		instMap.put(key, param);
	}
	
	public void addPersistWfParam(String key,String val){
		persMap.put(key, val);
	}
		
	public Map<String,Serializable> getInstVarMap(){
		return instMap;
	}
	
	public Map<String,String> getPersistVarMap(){
		return persMap;
	}
	
	public void addBusinessKey(String busKey){
		instMap.put(BUSINESS_KEY,busKey);
	}
}
