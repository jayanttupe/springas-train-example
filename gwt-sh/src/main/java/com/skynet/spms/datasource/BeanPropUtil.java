package com.skynet.spms.datasource;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.spms.datasource.convert.DateConvert;

public class BeanPropUtil {

	private static Logger log = LoggerFactory.getLogger(BeanPropUtil.class);

static{
	ConvertUtils.register(new DateConvert(), java.util.Date.class);
}
	
	
	public static final <T> T generEntityByMap(Map<String, Object> valMap,
			Class<T> cls) {
		T entity = getSafeNullEntity(cls);

		for (Map.Entry<String, Object> entry : valMap.entrySet()) {

			String key = entry.getKey();
			Object val = entry.getValue();
			log.info("prop name:" + key);

			safeSetProperty(entity, key, val);

		}
		return entity;
	}
	
	private static <T> void setProperty(T entity, String key, Object val) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		if(key.indexOf("isc_")!=-1||key.indexOf("_selection_")!=-1)
			        return;
		if(key.startsWith("_selection_")){
			return;
		}
		
		
		
		Class cls=PropertyUtils.getPropertyType(entity, key);
		if(cls==null) return;
		if(cls.isEnum()){
			Enum enumIt=getEnumByCls(cls,val);
			if(enumIt!=null){
				PropertyUtils.setProperty(entity,key,enumIt);
			}
		}else{
			if(val==null){
				return;
			}						
			Object instVal=ConvertUtils.convert(val, cls);
			PropertyUtils.setProperty(entity,key,instVal);			
		}
		
	}

	public static <T> void safeSetProperty(T entity, String key, Object val) {
		try {
			setProperty(entity, key, val);
		} catch (NestedNullException e) {
			// nested null
			try {
				fillNullObj(entity, key);
				setProperty(entity, key, val);
			} catch (NoSuchMethodException ex) {
				log.warn("unknow property:" + ex);
			} catch (Exception exc) {
				log.error("set value fail:" + key);
				throw new IllegalArgumentException(exc);
			}
		} catch (NoSuchMethodException e) {
			log.warn("unknow property:" + e);
		} catch (Exception e) {
			log.error("set value fail:" + key);
			throw new IllegalArgumentException(e);
		}
	}

	private static void fillNullObj(Object entity, String path)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, InstantiationException {
		//simple prop exists.
		if (!path.contains(".")) {
			PropertyUtils.getPropertyDescriptor(entity, path);			
			return;
		}

		String upperPath = StringUtils.substringBeforeLast(path, ".");
		String subPath = StringUtils.substringAfterLast(path, ".");

		try {
			Object val = PropertyUtils.getProperty(entity, upperPath);
			if(val==null){
				safeFill(entity,upperPath,subPath);
			}
		} catch (NestedNullException e) {
			fillNullObj(entity, upperPath);	
			safeFill(entity,upperPath,subPath);
		}
	}

	private static final void safeFill(Object entity, String path,String subPath) {
		try {
			Class<?> cls = PropertyUtils.getPropertyType(entity, path);
			Object val = cls.newInstance();
			setProperty(entity, path, val);
			fillNullObj(val,subPath);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

	}

	public static final <T> T getSafeNullEntity(Class<T> cls) {
		// Set<Class<?>> nestedClsSet=new HashSet<Class<?>>();
		// nestedClsSet.add(cls);
		// return getSafeNullEntity(cls,nestedClsSet);
		try {
			return cls.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}



	public static Object getSafeFailProp(Object bean, String propName) {

		try {
			return PropertyUtils.getProperty(bean, propName);
		}catch(NoSuchMethodException noe){
			
			return null;
		} catch (Exception e) {
			log.info("get fail",e);
			return null;
		}

	}

	public static boolean isBusiness(Class<?> cls) {
		
		return !cls.isPrimitive()
				&&!cls.isArray()
				&& cls.getPackage().getName().startsWith("com.skynet");
	}
	
	public static boolean isJavaCls(Class<?> cls){
		return cls.isPrimitive()||
		Number.class.isAssignableFrom(cls)||
		Date.class.isAssignableFrom(cls)||
		String.class.isAssignableFrom(cls)||
		Boolean.class.isAssignableFrom(cls);
	}

	
	private static ThreadLocal<String> dsInfoTL=new ThreadLocal<String>();
	
	public static void setDsName(String mangName){
		dsInfoTL.set(mangName);
	}
	
	public static String getDsName(){
		return dsInfoTL.get();
	}

	public static void fillEntityWithMap(Object entity,
			Map<String, Object> valMap) {
	
		for (Map.Entry<String, Object> entry : valMap.entrySet()) {

			String key = entry.getKey();
			Object val = entry.getValue();
			log.info("prop name:" + key);

			safeSetProperty(entity, key, val);

		}
		return;
	}
	
	public static  Class<?> getClassByName(String className) {
		Class<?> cls=safeGenerCls(null,className);
		if(cls==null){
			cls=safeGenerCls("com.skynet.spms.persistence.entity",className);
		}
		if(cls==null){
			cls=safeGenerCls("com.skynet.spms.web.form",className);
		}
		if(cls==null){
			throw new IllegalArgumentException("class not found:"+className);
		}else{
			return cls;
		}
		
	}

	private static Class<?> safeGenerCls(String preName,String className) {
		try {
			if(StringUtils.isBlank(className)){
				throw new NullPointerException();
			}
			String fullName=className;
			if(StringUtils.isNotBlank(preName)){
				fullName=preName+"."+className;
			}
			//combine with  package
			return  Class.forName(fullName);			

		} catch (ClassNotFoundException ex) {
			log.warn("className not found:" + className+" pre:"+preName);
			return null;
//			throw new IllegalArgumentException(ex);
		}
	}
	
	public static <T> T getEntityByMap(List<String> fieldList,Map map,Class<T> cls){
		Map<String,Object> objMap=(Map<String,Object>)map;
		
		T entity = getSafeNullEntity(cls);
		for(String fieldName:fieldList){
			String key=fieldName;
			Object val = map.get(fieldName);
			log.info("prop name:" + key);

			safeSetProperty(entity, key, val);
			
		}
		return entity;
	}

	
	public static Map getDataMap(List<String> fieldList,Object entity){
		Map<String,Object> objMap=new HashMap<String,Object>();
		for(String field:fieldList){
			try {
				Object obj = PropertyUtils.getProperty(entity, field);
				objMap.put(field,obj);
			} catch (Exception e) {
				log.error("set prop fail,field "+field,e);
			} 
			
		}
		return objMap;
	}
	
	public static Enum getEnumByCls(Class<?> cls,Object val){
		if(val==null){
			return null;
		}
		Class<Enum> enumCls=(Class<Enum>) cls.asSubclass(Enum.class);
		Enum[] enumArray=enumCls.getEnumConstants();
		for(Enum enumIt:enumArray){
			if(enumIt.name().equals(val)){
				return enumIt;
			}
		}
		throw new IllegalArgumentException(" enum value "+val+" not enum " +cls.getName());
	}
	
	
}
