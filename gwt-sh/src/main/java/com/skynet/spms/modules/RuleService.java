package com.skynet.spms.modules;

import com.skynet.spms.modules.entity.ModuleList;
import com.skynet.spms.modules.entity.ModulesElement;

public interface RuleService {
	
	
	ModuleList getRootModules(String rule);

	ModulesElement getModulesByName(String rule, String name);

//	List<ModuleItem> getSubModuleList(String name);
}
