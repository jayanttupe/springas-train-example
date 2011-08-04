package com.skynet.spms.modules.entity;


import org.dom4j.Element;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.FeatureInfo;
import com.skynet.spms.client.entity.ModuleDetail;
import com.skynet.spms.client.entity.ModuleItem;
import com.skynet.spms.modules.common.DataSourceList;
import com.skynet.spms.modules.common.FeatureList;
import com.skynet.spms.modules.common.ModuleTree;

public class ModulesElement{

	private FeatureList featureList; 

	private String desc;
	
	private String icon;
	
	private DataSourceList dsList;
	
	public void combinModElem(RuleElement modElem){
		
		featureList.validFeature(modElem.getFeatureList());		
		dsList.combineDataSource(modElem);		
	}
	
	public ModulesElement(ModulesElement modulesByName) {
		this.desc=modulesByName.desc;
		this.icon=modulesByName.icon;
		
		this.dsList=new DataSourceList(modulesByName.dsList);
		
		this.featureList=new FeatureList(modulesByName.featureList);
		
	}


	public ModulesElement(ModuleTree tree,PreDefineElement preDefine) {
//		moduleName = tree.getName();
		desc=tree.getFullName();
		icon=tree.getIcon();		
				
		Element elem=tree.getElement();
		
		featureList=new FeatureList(elem,preDefine);	
		
		Element dataSourceElem = elem.element("data");		
		dsList = new DataSourceList(dataSourceElem);		

	}
	

	public String getDesc() {
		return desc;
	}

	public ModuleDetail getModuleDetail(PropertyEntity prop) {
		ModuleDetail detail = new ModuleDetail();
		detail.setModuleDetail(prop.getProperty(desc));

		ModuleItem item = new ModuleItem();
		item.setDesc(prop.getProperty(desc));
		item.setName(desc);
		item.setIcon(icon);
		detail.setModuleItem(item);

		for (FeatureDefine feature : featureList) {
			FeatureInfo info = new FeatureInfo();
			info.setName(feature.getFeatureName());
			
			String desc=prop.getProperty("Feature"+"."+feature.getFeatureName());			
			info.setDesc(desc);
			
			info.setEnable(feature.isEnable());
			detail.addFeature(info);
		}
		return detail;
	}

	

	public DataSourceList getDataSourceList(){
		return dsList;
	}
	


	public FeatureList getFeatureList() {
		return featureList;
	}

	public void addData(DataElement data) {
		dsList.addData(data);		
	}

	

}
