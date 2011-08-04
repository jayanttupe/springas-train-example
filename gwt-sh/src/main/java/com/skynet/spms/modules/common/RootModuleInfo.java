package com.skynet.spms.modules.common;

import java.util.ArrayList;
import java.util.List;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.ModuleItem;


public class RootModuleInfo {
	
	
	private String desc;
	
	private String icon;
	
	private List<ModuleTree> subModuleList=new ArrayList<ModuleTree>();
	
	public RootModuleInfo(ModuleTree tree) {
		
		desc = tree.getFullName();
		icon=tree.getIcon();
	}
	
	public ModuleItem getModuleItem(PropertyEntity prop){
		ModuleItem item=new ModuleItem();
		item.setDesc(prop.getProperty(desc));
		item.setName(desc);
		item.setIcon(icon);
		for(ModuleTree tree:subModuleList){
			ModuleItem subItem=new ModuleItem();
			subItem.setName(tree.getFullName());
			subItem.setDesc(prop.getProperty(tree.getFullName()));
			subItem.setIcon(tree.getIcon());
			item.addSubItem(subItem);
		}
		return item;		
	}


	public void addSubModuleList(ModuleTree subMod) {
		this.subModuleList.add(subMod);
	}

}
