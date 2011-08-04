package com.skynet.spms.modules.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.spms.datasource.entity.EntityMetaInfo;
import com.skynet.spms.datasource.entity.FieldMetaInfo;
import com.skynet.spms.modules.common.FilterTool;


public class DataElement {
	
	private Logger log=LoggerFactory.getLogger(DataElement.class);
			
	private String className;
	
	private String name;
	
	private Set<String> viewFieldSet=new HashSet<String>();
	
	private Set<String> editFieldSet=new HashSet<String>();
		
//	private DataElement(DataElement data){
//		this.className=data.className;
//		this.name=data.name;
//		this.viewFieldSet=new HashSet<String>(data.viewFieldSet);
//		this.editFieldSet=new HashSet<String>(data.editFieldSet);
//		
//	}
	
	public void filterDataElem(DataFilter filter){
		filter.filterView(viewFieldSet);
		filter.filterEdit(editFieldSet);
		return;
	}
	
	public String getClassName(){
		return className;
	}
	
	public String getName() {
		return name;
	}
	
	public  DataElement(Element dataElem) {
		
		name=dataElem.attributeValue("name");
		className=dataElem.attributeValue("className");
	}
	

	public void refreshFieldFilter(Element dataElem,PreDefineElement preDefine){
		
		Element allFieldElem=dataElem.element("viewfilter");
		if(allFieldElem!=null){
			FilterTool filterTool = new FilterTool(allFieldElem, preDefine);
			viewFieldSet=filterTool.doFilter(viewFieldSet);			
			
			editFieldSet.retainAll(viewFieldSet);
		}
				
		Element editFieldElem=dataElem.element("editfilter");
		if(editFieldElem!=null){
			editFieldSet=getSpecFieldSet(editFieldElem, preDefine,editFieldSet);			
		}
	}
	
	public void fillFieldFilter(Element dataElem,
			PreDefineElement preDefine,EntityMetaInfo tableMetaInfo){
		
		Set<String> allFieldSet=tableMetaInfo.getFieldNameSet();
		
		Element allFieldElem=dataElem.element("viewfilter");
		FilterTool filterTool = new FilterTool(allFieldElem, preDefine);
		viewFieldSet=filterTool.doFilter(allFieldSet);
		
		Element editFieldElem=dataElem.element("editfilter");
		editFieldSet=getSpecFieldSet(editFieldElem, preDefine,viewFieldSet);
	
	}
	
	
	
	private Set<String>  getSpecFieldSet(Element editFieldElem, PreDefineElement preDefine,Set<String> fieldSet) {
		if(editFieldElem==null){
			return new HashSet<String>(fieldSet);
		}
		FilterTool filterTool = new FilterTool(editFieldElem, preDefine);
		Set<String> editFieldSet=filterTool.doFilter(fieldSet);
		return editFieldSet;
	}
	
	public Set<FieldMetaInfo> getFieldInfoSet(EntityMetaInfo tableMetaInfo){
		
		Set<FieldMetaInfo> fieldSet=tableMetaInfo.getFieldSet();
		
		for(Iterator<FieldMetaInfo> iter=fieldSet.iterator();iter.hasNext();){
			FieldMetaInfo fieldInfo=iter.next();
			if(!viewFieldSet.contains(fieldInfo.getName())&&
					!fieldInfo.isNonDisplay()){
				iter.remove();
				continue;
			}
			if(editFieldSet.contains(fieldInfo.getName())){;
				fieldInfo.setCanEdit(true);
			}
			log.info("field val:"+fieldInfo);
		}
		return fieldSet;
	}

	
	
	
}
