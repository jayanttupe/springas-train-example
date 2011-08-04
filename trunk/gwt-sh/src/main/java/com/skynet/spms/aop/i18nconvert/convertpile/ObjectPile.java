package com.skynet.spms.aop.i18nconvert.convertpile;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.aop.i18nconvert.ConvertPile;
import com.skynet.spms.aop.i18nconvert.Needi18n;

class ObjectPile implements ConvertPile{

	Logger log = LoggerFactory.getLogger(ObjectPile.class);
	
	private final Map<Field,ConvertPile> convertMap=new HashMap<Field,ConvertPile>();
	
	private final Set<Field> fieldSet=new HashSet<Field>();
	
	public ObjectPile(Class<?> cls){		
		
		for (Field field : cls.getFields()) {
			Class<?> fieldCls=field.getType();
			
			Set<Class<?>> interSet=new HashSet<Class<?>>();			
			interSet.addAll(Arrays.asList(fieldCls.getInterfaces()));
			
			if (field.isAnnotationPresent(Needi18n.class)&&
				fieldCls.equals(String.class)) {
				
				fieldSet.add(field);
				continue;				 
			}			
			boolean isAnno=field.isAnnotationPresent(Needi18n.class);
			ConvertPile convert=PileFactory.getConvertInst(field.getType(),isAnno);
			if(convert!=null){
				convertMap.put(field,convert);
			}
		}		
		
	}
	
		
	@Override
	public void doConvert(Object obj, PropertyEntity prop) {
			
		for(Field field:fieldSet){
			
			try{
				String val=(String)field.get(obj);
				String result=prop.getProperty(val);
				field.set(obj, result);
			}catch(Exception e){
				log.error("set desc value fail:"+field,e);
			}		
		}
				
		for(Map.Entry<Field, ConvertPile> entry:convertMap.entrySet()){
			Field field=entry.getKey();
			ConvertPile pile=entry.getValue();
			
			try{
				Object val=field.get(obj);
				if(val==null){
					continue;
				}
				pile.doConvert(val, prop);
			}catch(Exception e){
				log.error("oper value fail:"+field,e);
			}							
		}
	}
	
}