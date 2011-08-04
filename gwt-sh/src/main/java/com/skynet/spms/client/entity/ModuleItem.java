package com.skynet.spms.client.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ModuleItem implements IsSerializable{
	
	private String name;
	
	private String desc;
	
	private int order;
	
	private String icon;
	
	private List<ModuleItem> subModuleList=new ArrayList<ModuleItem>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<ModuleItem> getSubModuleList() {
		return subModuleList;
	}


	public void addSubItem(ModuleItem sub) {
		subModuleList.add(sub);		
	}

	public String getIcon() {
		return "treememus/"+icon+".png";
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	

}
