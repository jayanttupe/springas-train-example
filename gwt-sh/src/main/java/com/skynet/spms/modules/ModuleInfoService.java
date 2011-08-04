package com.skynet.spms.modules;

import com.skynet.spms.modules.entity.ModuleList;
import com.skynet.spms.modules.entity.ModulesElement;
import com.skynet.spms.modules.entity.RuleElement;
import com.skynet.spms.modules.entity.RulesList;

public interface ModuleInfoService {
	
	public ModuleList getRootModules();
	
	
	public ModulesElement getModulesByName(String modName);
	
	public RuleElement getRulesByName(String rule,String name);

	public RulesList getRulesList(String ruleName);
	
}

