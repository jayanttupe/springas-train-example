package com.skynet.spms.modules.imp;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.common.aop.cache.GroupBind;
import com.skynet.common.strtemplate.SimpleTmpTool;
import com.skynet.spms.datasource.EntityInfoService;
import com.skynet.spms.datasource.entity.EntityMetaInfo;
import com.skynet.spms.modules.ModuleElementStore;
import com.skynet.spms.modules.ModuleDefineService;
import com.skynet.spms.modules.ModuleInfoService;
import com.skynet.spms.modules.common.ModuleTree;
import com.skynet.spms.modules.entity.DataElement;
import com.skynet.spms.modules.entity.ModuleList;
import com.skynet.spms.modules.entity.ModulesElement;
import com.skynet.spms.modules.entity.PreDefineElement;
import com.skynet.spms.modules.entity.RuleElement;
import com.skynet.spms.modules.entity.RulesList;

@Component
public class ModuleServiceImpl  implements ModuleInfoService{

	private Logger log = LoggerFactory.getLogger(ModuleServiceImpl.class);

	@Autowired
	private EntityInfoService entityMang;
	
	@Autowired
	private ModuleElementStore elemStore;
	
	@Autowired
	private ModuleDefineService defineService;

	@Override
	public ModuleList getRootModules() {

		return defineService.getRootModules();
	}

	@Override
	public ModulesElement getModulesByName(String modName) {

		ModuleTree modTreeNode = getModuleElemByPath(modName);
		
		Element modElem=modTreeNode.getElement();
		PreDefineElement preDefine = new PreDefineElement(modElem.getDocument().getRootElement());

		ModulesElement module = new ModulesElement(modTreeNode,preDefine);

		Element dataSourceElem = modElem.element("data");
		if (dataSourceElem != null) {
			for (Object dataVal : dataSourceElem.elements("dataSource")) {
				Element dataElem = (Element) dataVal;
				DataElement data = generDataElem(dataElem, preDefine);
				module.addData(data);
			}
		}
		log.info("gener module :" + module.getDesc());

		return module;

	}

//	private ModulesElement generModuleElem(PreDefineElement preDefine,
//			Element modElem) {
//		
//	}	
	
	private static final String XPATH_TMP="//modules/module[@name='${0}']";
	
	
	@CacheSource("rule.define")
	@Override
	public RuleElement getRulesByName(@GroupBind String rule,String name) {
		
		Element rootElem = elemStore.getRuleElement(rule);
			
		String modPath = SimpleTmpTool.generByTmp(XPATH_TMP, name);
		
		Element modElem = (Element) rootElem.selectSingleNode(modPath);
		if(modElem==null){
			return null;
		}
		PreDefineElement preDefine = new PreDefineElement(rootElem);
		
		RuleElement module = new RuleElement( modElem,preDefine);
			
		log.info("gener module :" + module.getModuleName());
		
		return module;
	}

	private DataElement generDataElem(Element dataElem,
			PreDefineElement preDefine) {

		DataElement data = new DataElement(dataElem);

		EntityMetaInfo info = entityMang.getEntityInfoByClsName(data
				.getClassName());

		data.fillFieldFilter(dataElem, preDefine, info);

		return data;
	}
	
//	private DataElement generRuleDataElem(Element dataElem,
//			PreDefineElement preDefine) {
//
//		DataElement data = new DataElement(dataElem);
//
//		data.fillFieldFilter(dataElem, preDefine, info);
//
//		return data;
//	}


	private ModuleTree getModuleElemByPath(String modNamePath) {
		
		if (StringUtils.isBlank(modNamePath)) {
			throw new IllegalArgumentException("module name cannot null");
		}
		
		ModuleList modList=defineService.getRootModules();
		
		if(modNamePath.contains(".")){
			String prefix=StringUtils.substringBefore(modNamePath, ".");
			ModuleTree tree=modList.getTreeByName(prefix);
			String subPath=StringUtils.substringAfter(modNamePath, ".");
			return tree.getSubModule(subPath);
		}else{
			return modList.getTreeByName(modNamePath);
		}			
	}
	

	@CacheSource("rule.predefine")
	@Override
	public RulesList getRulesList(@GroupBind String ruleName) {
		
		Element elem=elemStore.getRuleElement(ruleName);
		
		return new RulesList(elem);
	}
	
}
