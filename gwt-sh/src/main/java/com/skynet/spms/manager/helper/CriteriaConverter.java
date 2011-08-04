package com.skynet.spms.manager.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CriteriaConverter {
	private static Logger log=LoggerFactory.getLogger(CriteriaConverter.class);
	
	public static Query convertValueMapToQuery(Session session,Map<String,Object> valueMap,Class entityClass){
		List criteria = (List)valueMap.remove("criteria");
		if(criteria != null){
			return convertCriteriaToQuery(session,criteria,entityClass);
		}
		String hql = "select a from " + entityClass.getSimpleName() + " a ";
		
		List<String> whereClauses = new ArrayList<String>();
		List<String> joinClauses = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		
		try {
//			Object entity = entityClass.newInstance();
			for(Map.Entry<String,Object> entry : valueMap.entrySet()){
				String key = entry.getKey();
				Object value = entry.getValue();
				String[] props = key.split("\\.");
				Class cls = null;
				if(props.length > 1){
					/*cls = PropertyUtils.getPropertyType(entity, props[0]);
					
					Object subEntity = cls.newInstance();
					cls = PropertyUtils.getPropertyType(subEntity, props[1]);*/
					cls = getPropertyType(entityClass,props[0]);
					cls = getPropertyType(cls,props[1]);
					value = convertValue(cls, value);
					
					String alias = props[0] + "_0";
					String join = "join a." + props[0] + " " + alias + " ";
					if(!joinClauses.contains(join))
						joinClauses.add(join);
					key = alias + "." + props[1];
					
				}else{
					/*cls = PropertyUtils.getPropertyType(entity, key);*/
					cls = getPropertyType(entityClass,key);
					value = convertValue(cls, value);
					key = "a." + key;
				}
				String where = "";
				if(cls == String.class){
					where = key + " like ? ";
					value = "%" + value + "%";
				}else{
					where = key + " = ?";
				}
				whereClauses.add(where);
				values.add(value);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		for(String join : joinClauses){
			hql += join;
		}
		if(whereClauses.size() > 0){
			hql += "where ";
			for(int i=0;i<whereClauses.size();i++){
				hql += whereClauses.get(i);
				if(i<whereClauses.size()-1){
					hql += "and ";
				}
			}
		}
		Query query = session.createQuery(hql);
		for(int i=0;i<values.size();i++){
			query.setParameter(i, values.get(i));
		}
		return query;
	}
	
	/**
	 * 使用ListGrid的Filter过滤时，如果ListGrid带有日期字段时，传递到DataSourceAction的doQuery方法的Map参数带有一个名为"criteria"的键，其值为一个List，该方法将这个List转换为Hibernate的Query。
	 * 
	 * @param session 
	 * 		Hibernate Session
	 * @param clientCriteria 
	 * 		由一组Map构成的List，表示ListGrid进行过滤时的条件组合
	 * @param entityClass 
	 * 		ListGrid所对应的Entity实体的Class
	 * @return 
	 * 		Hibernate Query对象
	 */
	public static Query convertCriteriaToQuery(Session session,List clientCriteria,Class entityClass){
		String hql = "select a from " + entityClass.getSimpleName() + " a ";
		List<String> whereClauses = new ArrayList<String>();
		List<String> joinClauses = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		try {
			Object entity = entityClass.newInstance();
			for(int i=0; i<clientCriteria.size();i++){
				Map map = (Map)clientCriteria.get(i);
				if(map.containsKey("criteria")){
					List subCriteria = (List)map.get("criteria");
					for(int j=0; j<subCriteria.size();j++){
						Map subMap = (Map)subCriteria.get(j);
						fillClauses(entity,subMap,joinClauses,whereClauses,values);
					}
				}else{
					fillClauses(entity,map,joinClauses,whereClauses,values);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		for(String join : joinClauses){
			hql += join;
		}
		if(whereClauses.size() > 0){
			hql += "where ";
			for(int i=0;i<whereClauses.size();i++){
				hql += whereClauses.get(i);
				if(i<whereClauses.size()-1){
					hql += "and ";
				}
			}
		}
		Query query = session.createQuery(hql);
		for(int i=0;i<values.size();i++){
			query.setParameter(i, values.get(i));
		}
		return query;
	}
	
	private static void fillClauses(Object entity,Map map,List<String> joinClauses,List<String> whereClauses,List<Object> values) 
				throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		String fieldName = (String)map.get("fieldName");
		Object value = map.get("value");
		String operator = (String)map.get("operator");
		
		String[] props = fieldName.split("\\.");
		if(props.length > 1){
			Class cls = PropertyUtils.getPropertyType(entity, props[0]);
			Object subEntity = cls.newInstance();
			cls = PropertyUtils.getPropertyType(subEntity, props[1]);
			value = convertValue(cls, value);
			if(cls.isPrimitive()){
				operator = "equals";
			}
			String alias = props[0] + "_0";
			String join = "join a." + props[0] + " " + alias + " ";
			if(!joinClauses.contains(join))
				joinClauses.add(join);
			fieldName = alias + "." + props[1];
		}else{
			Class cls = PropertyUtils.getPropertyType(entity, fieldName);
			value = convertValue(cls, value);
			if(cls.isPrimitive()){
				operator = "equals";
			}
			fieldName = "a." + fieldName;
		}
		String where = "";
		if(operator.equals("iContains")){
			where = fieldName + " like ? "; 
			value = "%" + value + "%";
		}else if(operator.equals("greaterOrEqual")){
			where = fieldName + " >= ? ";
		}else if(operator.equals("lessOrEqual")){
			where = fieldName + "<= ? ";
		}else if(operator.equals("equals")){
			where = fieldName + "= ? ";
		}
		whereClauses.add(where);
		values.add(value);
	}
	
	public static Object convertValue(Class cls,Object value){
		if(cls.isEnum()){
			Class<Enum> enumCls=cls.asSubclass(Enum.class);
			Enum[] enumArray=enumCls.getEnumConstants();
			for(Enum enumIt:enumArray){
				if(enumIt.name().equals(value)){
					return enumIt;
				}
			}	
		}
		if(value instanceof String){
			if(cls == boolean.class || cls == Boolean.class){
				return Boolean.valueOf((String)value);
			}
			if(cls == int.class || cls == Integer.class){
				return Integer.valueOf((String)value);
			}
			if(cls == float.class || cls == Float.class){
				return Float.valueOf((String)value);
			}
			if(cls == double.class || cls == Double.class){
				return Double.valueOf((String)value);
			}
		}
		return value;
	}
	
	public static Object convertSqlValue(Class cls,Object value){
		if(cls.isEnum()){
			Class<Enum> enumCls=cls.asSubclass(Enum.class);
			Enum[] enumArray=enumCls.getEnumConstants();
			for(Enum enumIt:enumArray){
				if(enumIt.name().equals(value)){
					return enumIt;
				}
			}	
		}
		if(value instanceof String){
			if(cls == boolean.class || cls == Boolean.class){
				return (Boolean.valueOf((String)value) == true) ? 1 : 0;
			}
			if(cls == int.class || cls == Integer.class){
				return Integer.valueOf((String)value);
			}
			if(cls == float.class || cls == Float.class){
				return Float.valueOf((String)value);
			}
			if(cls == double.class || cls == Double.class){
				return Double.valueOf((String)value);
			}
		} else {
			if(cls == boolean.class || cls == Boolean.class){
				return ((Boolean)value == true) ? 1 : 0;
			}
		}
		return value;
	}
	
	public static Class getPropertyType(Class cls,String fieldName){
		if(cls == null || fieldName== null || "".equals(fieldName))
			return null;
		
		Method method = null;
		String begin = "" + fieldName.charAt(0);
		String mstr = begin.toUpperCase() + fieldName.substring(1);
		try {
			method = cls.getMethod("get" + mstr);
			
		}catch (NoSuchMethodException e) {
			try {
				method = cls.getMethod("is" + mstr);
			}catch (NoSuchMethodException e1) {
			}
		}
		if(method != null){
			return method.getReturnType();
		}
		return null;
	}
	
}
