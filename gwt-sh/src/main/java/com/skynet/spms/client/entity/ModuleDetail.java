package com.skynet.spms.client.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ModuleDetail implements IsSerializable{
	
	private String moduleDetail;
	
	private List<FeatureInfo> featureList=new ArrayList<FeatureInfo>();
	
	private DataInfo dataInfo;
	
	private ModuleItem moduleItem;
	
	public ModuleItem getModuleItem(){
		return moduleItem;
	}
	
	public void setModuleItem(ModuleItem moduleItem) {
		this.moduleItem = moduleItem;
	}

	public String getModuleDetail() {
		return moduleDetail;
	}

	public void setModuleDetail(String moduleDetail) {
		this.moduleDetail = moduleDetail;
	}

	public List<FeatureInfo> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<FeatureInfo> featureList) {
		this.featureList = featureList;
	}
	
	public void addFeature(FeatureInfo feature){
		this.featureList.add(feature);
	}

	public DataInfo getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}
	
	

}
