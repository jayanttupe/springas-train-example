package com.skynet.spms.modules.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.skynet.spms.modules.common.ModuleSign;
import com.skynet.spms.modules.common.ModuleTree;
import com.skynet.spms.modules.common.RootModuleInfo;

public class ModuleList {
	
	private Map<String,ModuleSign> accessMap=new HashMap<String,ModuleSign>();
	
	private Map<String,ModuleTree> treeMap=new LinkedHashMap<String,ModuleTree>();
	
	
	public ModuleList combineModule(ModuleList newList){
		
		for(String field:accessMap.keySet()){			
			
			ModuleSign newSign=newList.accessMap.get(field);			
			ModuleSign sign=accessMap.get(field);			
			accessMap.get(field).setIsValid(sign.isValid()||newSign.isValid());		
			
		}
		
		return this;
		
	}
	
	public void doFilterValid(RulesList ruleList) {
		
		for(String field:accessMap.keySet()){
			boolean sign=ruleList.doValid(field);
			
			accessMap.get(field).setIsValid(sign);			
		}		
		for(Map.Entry<String,ModuleSign> entry:accessMap.entrySet()){
			ModuleSign sign=entry.getValue();
			if(sign.isValid()){
				String field=entry.getKey();
				String[] pathArray=StringUtils.split(field, ".");
				String fullPath="";
				for(String path:pathArray){
					if(StringUtils.isNotBlank(fullPath)){
						fullPath+=".";
					}
					fullPath+=path;
					accessMap.get(fullPath).setIsValid(true);
				}
			}			
		}
		
		for(ModuleTree node:treeMap.values()){			
			node.doFilter(accessMap);			
		}
		
	}	

	
	public List<RootModuleInfo> getRootModules(){
		List<RootModuleInfo> infoList=new ArrayList<RootModuleInfo>();
		for(ModuleTree node:treeMap.values()){
			
			if(!node.isValid()){
				continue;
			}
			RootModuleInfo info=new RootModuleInfo(node);
			
			for(ModuleTree subTree:node.getSubModules()){
				if(subTree.isValid()){
					info.addSubModuleList(subTree);
				}
			}			
			infoList.add(info);
		}
		return infoList; 
	}
	
	public ModuleTree getTreeByName(String moduleName){
		return treeMap.get(moduleName);
	}
	
	public void addSubModule(ModuleTree node){
		
		treeMap.put(node.getFullName(), node);
		
		for(String field:node.getFlatModNameSet()){
			accessMap.put(field,new ModuleSign());
		}
		
	}
	
	public boolean isValid(String modName){
		return accessMap.get(modName).isValid();
	}
	
	public boolean isLocal(String modName){
		return accessMap.get(modName).isLocal();
	}
	
	
	public ModuleList cloneList(String ruleName) {
		ModuleList newList=new ModuleList();
				
		newList.treeMap=new LinkedHashMap<String,ModuleTree>();
		for(Map.Entry<String, ModuleTree> entry:treeMap.entrySet()){
			newList.treeMap.put(entry.getKey(), new ModuleTree(entry.getValue()));
		}
		
		newList.accessMap=new HashMap<String,ModuleSign>();
		for(Map.Entry<String,ModuleSign> entry:accessMap.entrySet()){
			newList.accessMap.put(entry.getKey(),entry.getValue().clone());
		}
		return newList;
	}


}
