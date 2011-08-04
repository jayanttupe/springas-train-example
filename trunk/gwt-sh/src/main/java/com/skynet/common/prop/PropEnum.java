package com.skynet.common.prop;

import java.util.Locale;

public enum PropEnum {

	Feature("Features"),UIResource("UIResource"),
	DatabaseInfo("Database"), WfTaskInfo("WfTaskInfo");
	
	private final String name;
	
	public String getPropName(Locale locale){
		return name+"_"+locale.toString().toLowerCase();
	}
	
	PropEnum(String name){
		this.name=name;
	}
}
