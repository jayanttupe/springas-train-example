package com.skynet.spms.modules.entity;

import java.util.Set;

import org.dom4j.Element;

import com.skynet.spms.modules.common.FilterTool;

public class DataFilter {
	
	private FilterTool editFilter;
	
	private FilterTool viewFilter;
	
	public DataFilter(Element dataElem,PreDefineElement preDefine){
		
		Element allFieldElem=dataElem.element("viewfilter");
		viewFilter = new FilterTool(allFieldElem, preDefine);
		
		Element editFieldElem=dataElem.element("editfilter");
		editFilter=new FilterTool(editFieldElem,preDefine);

	}

	public Set<String> filterView(Set<String> fieldSet) {
		return viewFilter.doFilter(fieldSet);		
	}
	
	public Set<String> filterEdit(Set<String> fieldSet) {
		return editFilter.doFilter(fieldSet);		
	}

}
