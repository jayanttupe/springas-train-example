package com.skynet.common.datadictory.imp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.common.aop.cache.GroupBind;
import com.skynet.common.datadictory.DataDictEntity;
import com.skynet.common.datadictory.DataDictionaryManager;

@Component
public class DataDictionaryMapLoader {

	private Logger log=LoggerFactory.getLogger(DataDictionaryMapLoader.class);

	@Value("${datadictionary.basepath}")
	private String ddBasePath; 

	@Autowired
	private ResourceLoader resLoader;
	
	
	public Element getSourceByName(String ddName){
		
		String fullPath=ddBasePath+"/"+ddName+".dd.xml";
		InputStream input=null;
		try{
			input=resLoader.getResource(fullPath).getInputStream();	
			Document doc=DocumentHelper.parseText(IOUtils.toString(input,"UTF-8"));	
			
			Element elem= doc.getRootElement().element("datadict");
			return elem;
		}catch(Exception e){
			log.error("parse xml fail:"+ddName);
			throw new IllegalArgumentException(e);
		}finally{
			IOUtils.closeQuietly(input);
		}
	}
	
	@CacheSource("dd.objectmap")
	public Map<String, DataDictEntity> getDataDictoryByName(@GroupBind String ddName,Locale locale) {
		Element elem=getSourceByName(ddName);
		
		String type=elem.attributeValue("type");
		Map<String,DataDictEntity> map=new HashMap<String,DataDictEntity>();
		
		if("flat".equals(type)){
			String clsName=elem.attributeValue("name");
			
			List<DDFieldType> list=new ArrayList<DDFieldType>();
			for(Object elemObj:elem.element("fields").elements("field")){
				Element fieldElem=(Element)elemObj;
				
				list.add(new DDFieldType(fieldElem));			
			}
			for(Object obj:elem.element("entrys").elements("entry")){
				Element entryElem=(Element)obj;
				
				DataDictEntity entity=getInstance(clsName);	
				String[] valArray=StringUtils.split(entryElem.getText(), '|');
				for(int i=0;i<valArray.length;i++){
					String strVal=valArray[i];
					DDFieldType field=list.get(i);
					fillEntityByVal(strVal,field,entity,locale);
				}
				
				String key=entryElem.attributeValue("key");				
				map.put(StringUtils.trim(key),entity);								
			}			
			
		}else if("line".equals(type)){
			String clsName=elem.attributeValue("name");
			
			String keyList=elem.elementText("key");
			String[] keyArray=StringUtils.split(keyList, '|');
			for(String key:keyArray){
				map.put(StringUtils.trim(key),getInstance(clsName));
			}
			
			for(Object valObj:elem.element("vals").elements("val")){
				Element valElem=(Element)valObj;
				
				DDFieldType fieldType=new DDFieldType(valElem);
				
				String valStr=valElem.getText();
				String[] valArray=StringUtils.split(valStr,'|');
				
				for(int i=0;i<valArray.length;i++){
					DataDictEntity entity=map.get(keyArray[i]);
					fillEntityByVal(valArray[i],fieldType,entity,locale);												
				}
			}
			
		}
		
		return map;
	}
	
	private DataDictEntity getInstance(String name){
		String clsName=DataDictionaryManager.class.getPackage().getName();
		String fullEntityName=clsName+".entity."+name;
		
		try {
			Class<?> cls=Class.forName(fullEntityName);
			if(DataDictEntity.class.isAssignableFrom(cls)){
				return (DataDictEntity) cls.newInstance();
			}else{
				throw new IllegalArgumentException(" not DataDictEntity:"+name);
			}
		} catch (Exception e) {
			log.error("gener entity fail:"+name);
			throw new IllegalArgumentException(e);
		} 
		
	}
	
	private void fillEntityByVal(String strVal,DDFieldType field,DataDictEntity entity,Locale locale){
			
		Object val=StringUtils.trim(strVal);
		
		if(!field.isString()){
			val=ConvertUtils.convert(strVal, field.type);			
		}else{
			if(!field.check(locale)){
				//locale not match.
				return;
			}
		}
		
		try{
			BeanUtils.setProperty(entity,field.name,val);
		}catch(Exception e){
			log.error("set field fail:"+field,e);
		}
	}
	
	private class DDFieldType{
		private String name;
		private Class<?> type;
		
		private String  lang;
				
		public boolean check(Locale locale){
			return lang.equals(locale.toString().toLowerCase());			
		}
		
		public boolean isString(){
			return lang!=null;
		}
		public DDFieldType(Element elem){
			//	<field type="i18n-str" name="name" />
			name=elem.attributeValue("name");
			String strType=elem.attributeValue("type");
			
			if(strType.endsWith("-str")){
				type=String.class;				
				lang=StringUtils.substringBefore(strType, "-");
				
			}else{
				if("int".equals(strType)){
					type=int.class;
				}else if("float".equals(strType)){
					type=float.class;				
				}else if("date".equals(strType)){
					type=Date.class;
				}else if("boolean".equals(strType)){
					type=boolean.class;
				}else{
					type=String.class;
				}
			}
		}
	}
}
