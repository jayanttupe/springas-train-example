package com.skynet.spms.modules.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.common.aop.cache.GroupBind;
import com.skynet.spms.modules.ModuleInfoService;
import com.skynet.spms.modules.RuleService;
import com.skynet.spms.modules.entity.ModuleList;
import com.skynet.spms.modules.entity.ModulesElement;
import com.skynet.spms.modules.entity.RuleElement;
import com.skynet.spms.modules.entity.RulesList;

@Component
public class RuleManagerImp implements RuleService {

	private Logger log=LoggerFactory.getLogger(RuleManagerImp.class);

	@Autowired
	private ModuleInfoService moduleManager;
	

	@Override
	@CacheSource("rule.module.tree")
	public ModuleList getRootModules(@GroupBind String rule) {
		ModuleList modList = moduleManager.getRootModules().cloneList(rule);
		
		RulesList ruleList=moduleManager.getRulesList(rule);
		
		modList.doFilterValid(ruleList);
		
		return modList;
	}

	@Override
	@CacheSource("rule.module.detail")
	public ModulesElement getModulesByName(@GroupBind String rule, String name) {

		ModulesElement modElem = new ModulesElement(moduleManager.getModulesByName(name));
	
		RulesList ruleList=moduleManager.getRulesList(rule);
		
		if(!ruleList.doValid(name)){
			log.warn("this module invalid");
			throw new SecurityException("the module " + name
					+ " no access right");
		}
		
		RuleElement ruleElem=moduleManager.getRulesByName(rule,name);
		if(ruleElem==null){
			return modElem;
		}
		
		modElem.combinModElem(ruleElem);
		
		return modElem;
	}	

}
