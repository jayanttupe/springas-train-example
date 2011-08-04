package com.skynet.spms.modules.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.spms.modules.ModuleElementStore;
import com.skynet.spms.modules.ModuleDefineService;
import com.skynet.spms.modules.common.ModuleTree;
import com.skynet.spms.modules.entity.ModuleList;

@Component
public class ModuleDefineServiceImp implements ModuleDefineService {
	
	
	@Autowired
	private ModuleElementStore elemStore;
	
	/* (non-Javadoc)
	 * @see com.skynet.spms.modules.imp.ModuleDefineService#getRootModules()
	 */
	@CacheSource("module.define")
	@Override
	public ModuleList getRootModules() {

		ModuleList rootList = new ModuleList();

		Element rootElem = elemStore.getRootModElement();
		@SuppressWarnings("unchecked")
		List<Element> elemList = rootElem.element("modules").elements("module");
		for (Element elem : elemList) {
			ModuleTree tree = getModuleNode(elem,null,null);
			rootList.addSubModule(tree);
		}

		return rootList;
	}
	
	private ModuleTree getModuleNode(Element elem,String upper,String bindCfg) {
		
		
		String ref = elem.attributeValue("reference");
		if (StringUtils.isNotBlank(ref)) {
			elem = elemStore.getSubModElement(ref).element("module");
			bindCfg=ref;
		}

		ModuleTree tree = ModuleTree.getStandardInstance(upper,elem);
//		tree.setBindCft(bindCfg);
		
		Element subElem = elem.element("submodules");
		if (subElem == null) {
			return tree;
		}

		for (Object val : subElem.elements("module")) {
			Element modElem = (Element) val;
			ModuleTree subTree = getModuleNode(modElem,tree.getFullName(),bindCfg);
			tree.addSubModule(subTree);
		}
		return tree;
	}

	
}
