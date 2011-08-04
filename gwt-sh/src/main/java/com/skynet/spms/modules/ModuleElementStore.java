package com.skynet.spms.modules;

import java.util.List;

import org.dom4j.Element;

public interface ModuleElementStore {

	Element getSubModElement(String name);

	Element getRootModElement();

	Element getRuleElement(String rule);
	
	List<String> getFullRoleList();

}