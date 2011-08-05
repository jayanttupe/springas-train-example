package com.skynet.spms.web.control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.ModuleDetail;
import com.skynet.spms.client.entity.ModuleItem;
import com.skynet.spms.client.entity.RootModulesEntity;
import com.skynet.spms.client.entity.ViewModuleTree;
import com.skynet.spms.client.service.ModuleInfoService;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.modules.RuleService;
import com.skynet.spms.modules.common.ModuleTree;
import com.skynet.spms.modules.common.RootModuleInfo;
import com.skynet.spms.modules.entity.ModuleList;
import com.skynet.spms.modules.entity.ModulesElement;

import edu.emory.mathcs.backport.java.util.Arrays;

@Controller
@GwtRpcEndPoint
public class ModuleInfoAction implements ModuleInfoService{
	
	private Logger log=LoggerFactory.getLogger(ModuleInfoAction.class);
	
	@Autowired
	private RuleService ruleLoader;
	
	@Autowired
	private PropManager messageProp;
	
	@Autowired
	private UserManager userMang;

	public RootModulesEntity getRootModuleList() {
		
		log.debug("locale:"+GwtActionHelper.getLocale());

		RootModulesEntity entity=new RootModulesEntity();
		entity.setUserInfo(userMang.getUserInfoByName(GwtActionHelper.getCurrUser()));
		
		String rule=GwtActionHelper.getCurrRule();
		
		PropertyEntity prop=messageProp.getPropEntity(GwtActionHelper.getLocale(),PropEnum.Feature);
		
		List<RootModuleInfo> rootModList=ruleLoader.getRootModules(rule).getRootModules();
		List<ModuleItem> itemList=new ArrayList<ModuleItem>();
		for(RootModuleInfo info:rootModList){
			itemList.add(info.getModuleItem(prop));
		}
		entity.setModulesList(itemList);
		return entity;
	}
	

	public ViewModuleTree getModuleTree(String modName) {
		String rule=GwtActionHelper.getCurrRule();
		PropertyEntity prop=messageProp.getPropEntity(GwtActionHelper.getLocale(),PropEnum.Feature);
		
		ModuleList rootList=ruleLoader.getRootModules(rule);
		ModuleTree tree=rootList.getTreeByName(modName); 

		ViewModuleTree viewTree= tree.getViewModuleTree(prop);
		return viewTree;
	}

	public ModuleDetail getModuleDetail(String modName) {
		ModulesElement modElem=ruleLoader.getModulesByName(GwtActionHelper.getCurrRule(), modName);
		PropertyEntity prop=messageProp.getPropEntity(GwtActionHelper.getLocale(),PropEnum.Feature);
		
		return modElem.getModuleDetail(prop);
	}

	public Set<String> getPortalMembers() {
		String[] array= new String[]{"Info","InstMsg",
				"TaskList",
				"Comment",
				"Blank",
				"Chart",
				"Calendar",
				"Mock"};
		
		Set<String> set=new HashSet<String>(Arrays.asList(array));
		
		return set;
	}
	
	
	
}
