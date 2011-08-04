package com.skynet.spms.service.imp;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.datadictory.DataDictionaryManager;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.datasource.BeanPropUtil;

@Component
public class DsConfigConvert {
	
	@Autowired
	private DataDictionaryManager dataDictMang;
	
//	private String start="<isomorphicXML xmlns:xsi=\"nativeType\">";
//	private String end="</isomorphicXML>";
		
	public String convertToDsXml(String dsTmpXml) throws DocumentException, IOException{
		
		Document doc=DocumentHelper.parseText(dsTmpXml);		
		Element dsElem=doc.getRootElement();
		
		if("generic".equals(dsElem.attributeValue("serverType"))){
			return dsTmpXml;
		}
				
		Document newDoc=DocumentHelper.createDocument();
//		QName qname=new QName("xmlns:xsi");
//		.addAttribute(qname,"nativeType");;
		Element newElem=newDoc.addElement("DataSource");
		
		newElem.addAttribute("ID",dsElem.attributeValue("ID"));
		newElem.addAttribute("serverType", "generic");
		Element fieldsElem=newElem.addElement("fields");
			
		for(Object val:dsElem.element("fields").elements("field")){
			Element fieldElem=(Element)val;
			String type=fieldElem.attributeValue("type");
			
			Element newFieldElem=null;
			if("enumClass".equals(type)){
				newFieldElem=operEnumMap(fieldElem);
			}else if("dataDict".equals(type)){
				newFieldElem=operDataDict(fieldElem);
			}else if("enum".equals(type)){
				newFieldElem=operValueMap(fieldElem);
			}else{
				newFieldElem=operTopic(fieldElem);
			}
						
			
			
			Element validsElem=fieldElem.element("validators");
			if(validsElem!=null){
				newFieldElem.add(copyElement(validsElem));
			}
			
			fieldsElem.add(newFieldElem);
		}	
		//<serverObject lookupStyle="spring" bean="simpleDataSourceService"/>
		
		newElem.addElement("serverObject")
		.addAttribute("lookupStyle", "spring")
		.addAttribute("bean","simpleDataSourceService");
				
//		newElem.add(dsElem.element("operationBindings"));
		
		StringWriter strWriter=new StringWriter();
		XMLWriter writer=new XMLWriter(strWriter);
		writer.write(newDoc);		
		return strWriter.toString();
	}
	
//	private Element getValidate(Element validsElem){
//		Element newValidsElem=DocumentHelper.createElement("validators");
//		
//		for(Object valValid:validsElem.elements("validator")){
//			
//			Element newValid=DocumentHelper.createElement("validator");
//			
//			Element validElem=(Element)valValid;
//			newValid.setAttributes(validElem.attributes());
////			
////			String errorMsg=validElem.attributeValue("errorMessage");
////			if(StringUtils.isNotBlank(errorMsg)){
////				errorMsg=validElem.elementText("errorMessage");
////				newValid.addElement("errorMessage").addText(errorMsg);
////			}
//			
//			for(Object subVal:validElem.elements()){
//				Element sub=(Element)subVal;
////				if("errorMessage".equals(sub.getName())){
////					continue;
////				}
//				Element newSub=DocumentHelper.createElement(sub.getName());
//				newSub.setAttributes(sub.attributes());
//				newSub.setText(sub.getText());
//				newValid.add(newSub);
//			}			
//			newValidsElem.add(newValid);
//		}
//
//		return newValidsElem;
//	}
	
//	
//	private Element operValidate(Element validatesElem){
//		
//		List<Element> list=validatesElem.elements("validator");
//		for(Element valiElem:list){
//			String topic=valiElem.attributeValue("topic");			
//			valiElem.addAttribute("topic",combinTopic(validatesElem,"valiate",topic));
//		}
//		return validatesElem;
//	}
	
	private Element operDataDict(Element fieldElem){
		Element newElem=DocumentHelper.createElement("field");
		newElem.setAttributes(fieldElem.attributes());
		newElem.addAttribute("type", "enum");
		
		String dictName=fieldElem.element("datadict").attributeValue("name");
		
		String[][] value=dataDictMang.getDisplayDataArrayByName(dictName);
		Element valMapElem=newElem.addElement("valueMap");		
		for(String[] valArray:value){
			String name=valArray[0];
			String val=valArray[1];
			
			Element valElement=DocumentHelper.createElement("value");
			
			valElement.addAttribute("ID", name);
			valElement.setText(combinTopic(val));
			
			valMapElem.add(valElement);
		}
//		newElem.add(valMapElem);
		
		return newElem;
	}
	
	private Element operEnumMap(Element fieldElem) {
		String clsName=fieldElem.element("enum").attributeValue("class");
		Class<Enum> cls=null;
		try{
			cls=(Class<Enum>) Class.forName(clsName).asSubclass(Enum.class);
		}catch(Exception e){
			throw new IllegalArgumentException(e);
		}
		Enum[] enumArray=cls.getEnumConstants();
		
		Element newElem=DocumentHelper.createElement("field");
		newElem.setAttributes(fieldElem.attributes());
		newElem.addAttribute("type", "enum");
		
		Element valMapElem=newElem.addElement("valueMap");
		boolean isI18n=Boolean.getBoolean(fieldElem.attributeValue("i18n"));
		for(Enum enumIns:enumArray){
			String name=enumIns.name();
			Element valElement=DocumentHelper.createElement("value");
			
			valElement.addAttribute("ID", name);
			if(isI18n){
				valElement.setText(combinTopic(cls.getSimpleName(),name));
			}else{
				valElement.setText(name);
			}
			valMapElem.add(valElement);
		}
		
		return newElem;
	}
	
	
	
	private  Element operValueMap(Element fieldElem){
		Element newElem=DocumentHelper.createElement("field");

		String valMapName=fieldElem.attributeValue("name");
		newElem.setAttributes(fieldElem.attributes());
		
		Element mapElem=newElem.addElement("valueMap");
		Element valMapElem=fieldElem.element("valueMap");

		for(Object val:valMapElem.elements("value")){
			Element valElem=(Element)val;
			
			Element newVal=DocumentHelper.createElement("value");
			
			String id=valElem.attributeValue("ID");
			newVal.addAttribute("ID", id);
			String topic=valElem.getText();
			if(StringUtils.isBlank(topic)){
				topic=combinTopic(fieldElem,valMapName,id);				
				newVal.addText(topic);
			}			
			mapElem.add(newVal);
		}
		return newElem;
	}


	private Element operTopic(Element fieldElem) {
		
		Element newElem=DocumentHelper.createElement("field");
		
		newElem.setAttributes(fieldElem.attributes());
		String topic=fieldElem.attributeValue("topic");
		String name=fieldElem.attributeValue("name");
		
		if(StringUtils.isBlank(topic)||
				!StringUtils.startsWith(topic, "${")){
			topic=combinTopic(fieldElem,name);
			newElem.addAttribute("topic", topic);			
		}	
		return newElem;
	}
	

	
	private String combinTopic(Element elem,String... params){
		String dsName = getDsName(elem);
		StringBuilder build=new StringBuilder();
		build.append("${").append(dsName);
		for(String str:params){
			build.append(".").append(str);
		}
		build.append("}");
		return build.toString();
	}
	
	private String combinTopic(String... params){
		StringBuilder build=new StringBuilder();
		
		build.append("${");
		for(String str:params){
			build.append(str).append(".");
		}
		build.deleteCharAt(build.lastIndexOf("."));
		build.append("}");
		
		return build.toString();
	}
	
	private Element copyElement(Element elem){
		Element newElem=DocumentHelper.createElement(elem.getName());
		newElem.setAttributes(elem.attributes());
		newElem.setText(elem.getText());
		for(Object sub:elem.elements()){
			Element subElem=(Element)sub;
			newElem.add(copyElement(subElem));
		}
		return newElem;
	}

	private String getDsName(Element fieldElem) {
		String dsName=fieldElem.getDocument().getRootElement().attributeValue("ID");
		return dsName;
	}
}
