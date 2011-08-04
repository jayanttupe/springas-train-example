package com.skynet.spms.modules.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.dom4j.Element;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.ModuleItem;
import com.skynet.spms.client.entity.ViewModuleTree;

public class ModuleTree {

	private final String moduleName;

	private Map<String,ModuleTree> subModules = new LinkedHashMap<String,ModuleTree>();
	
	private String desc;
	
	private String icon;
	
	private Element elem;
	
	private boolean isValid;
	
	public void setIsValid(boolean sign){
		this.isValid=sign;
	}
	
	public boolean isValid(){
		return isValid;
	}
	
	private ModuleTree(String upper,Element elem){

		moduleName=elem.attributeValue("name");
		if(StringUtils.isNotBlank(upper)){
			desc=upper+"."+moduleName;
		}else{
			desc=moduleName;
		}
		icon=elem.attributeValue("icon");
		this.elem=elem;
	}		

	
	public ModuleTree(ModuleTree otherTree){
		moduleName=otherTree.moduleName;
		this.desc=otherTree.desc;
		this.elem=otherTree.elem;
		this.icon=otherTree.icon;
		for (ModuleTree sub : otherTree.subModules.values()) {
			this.addSubModule(new ModuleTree(sub));
		}
	}

		
	public static ModuleTree getStandardInstance(String upper,Element elem) {
		ModuleTree tree=new ModuleTree(upper,elem);
		return tree;
	}
	
	public static ModuleTree getRootInstance(Element elem) {
		ModuleTree tree=new ModuleTree(null,elem);
		return tree;
	}

	public void setTreeValid(boolean sign) {
		for (ModuleTree sub : this.subModules.values()) {
			sub.setTreeValid(sign);
		}
	}

	public String getFullName() {
		return desc;
	}
	
	public String getIcon(){
		return icon;
	}

	public Collection<ModuleTree> getSubModules() {
		return subModules.values();
	}

	public void addSubModule(ModuleTree node) {
		if(StringUtils.isBlank(node.icon)){
			node.icon=this.icon;
		}
		subModules.put(node.moduleName,node);
	}

	public void removeSubModule(String name) {
		subModules.remove(name);
	}


	public ViewModuleTree getViewModuleTree(PropertyEntity prop) {
		
		ModuleItem root = new ModuleItem();
		root.setName(desc);
		root.setDesc(prop.getProperty(desc));
		root.setIcon(icon);
		
		ViewModuleTree viewTree = new ViewModuleTree(root);
		for (ModuleTree sub : subModules.values()) {
			if(sub.isValid){
				viewTree.addSubModule(sub.getViewModuleTree(prop));
			}
		}

		return viewTree;
	}

	public Set<String> getFlatModNameSet() {
		Set<String> modSet = new HashSet<String>();

		modSet.add(desc);

		for (ModuleTree subTree : subModules.values()) {
			modSet.addAll(subTree.getFlatModNameSet());
		}
		return modSet;
	}
	

	public Element getElement(){
		return elem;
	}
	
	public ModuleTree getSubModule(String path) {
		if(path.contains(".")){
			String subPath=StringUtils.substringAfter(path, ".");
			String prefix=StringUtils.substringBefore(path, ".");
			ModuleTree sub=this.subModules.get(prefix);
			return sub.getSubModule(subPath);
		}else{
			ModuleTree sub=this.subModules.get(path);
			return sub;
		}
	}

	public void doFilter(Map<String, ModuleSign> accessMap) {
		isValid=accessMap.get(desc).isValid();
		if(!isValid){
			return;
		}
		for(ModuleTree subTree:subModules.values()){
			subTree.doFilter(accessMap);
		}
	}
	//

	// ======================================

	@Override
	public int hashCode() {

		return new HashCodeBuilder(31, 1).append(desc).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return new EqualsBuilder().append(desc,
				((ModuleTree) obj).desc).isEquals();
	}

	



	

}
