package com.skynet.spms.manager.helper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.skynet.spms.tools.AnnotationUtil;

public class SqlHelperTool {

	public static String compareTypeEqual = "=";
	public static String compareTypeLike = "LIKE";
	
	public static String getSqlCondition(boolean isFilter, String fieldName, Class fieldClass, String fieldValue) {
		String strResult = "";
		if (fieldClass == Boolean.class ||
				fieldClass == boolean.class) {
			return " AND " + fieldName + " = " + fieldValue + " ";
		}
		
		if (isFilter == true &&
				!fieldValue.getClass().isEnum()) {
			strResult = " AND " + fieldName + " " + compareTypeLike + " '%" + fieldValue + "%'";
		} else {
			strResult = " AND " + fieldName + " " + compareTypeEqual + " '" + fieldValue + "'";
		}
		
		return strResult;
	}
	
	public static void setCriteriaCondition(boolean isFilter, String fieldName, Class fieldClass, Object fieldValue, Criteria criteria) {
		if (isFilter == true &&
				!fieldValue.getClass().isEnum() &&
				fieldClass != Boolean.class &&
				fieldClass != boolean.class) {
			criteria.add(Restrictions.like(fieldName, fieldValue.toString(), MatchMode.ANYWHERE));
		} else {
			criteria.add(Restrictions.eq(fieldName, fieldValue));
		}
	}
	
	private static String getColumnName(boolean isSqlCondition, String fieldName, Class cls)
	{
		String strResult = "";
		if (isSqlCondition == true) {
			strResult = AnnotationUtil.getColumnName(cls, fieldName);	
		} else {
			strResult = fieldName;	
		}
		
		return strResult;
	}

	public static void createCriteria(Map values,
			Criteria criteria,
			Class cls,
			Map extraKey)
	{
		if (values != null) {
			boolean isFilter = false;
			if (values.containsKey("filter")) {
				// 过滤查询的情况
				isFilter = true;
			} else {
				// 非过滤查询的情况
				isFilter = false;
			}

			Set set = values.entrySet();
			Iterator iterator = set.iterator();
	        while(iterator.hasNext())
	        {
	        	Map.Entry entry =(Map.Entry)iterator.next();
	        	String fieldName = "";
	        	String fieldValue = "";
	        	if (entry.getKey() != null) {
		        	fieldName = (String)entry.getKey().toString();	
	        	}
	        	if (entry.getValue() != null) {
		        	fieldValue = (String)entry.getValue().toString();	
	        	}
	        	
	        	if (fieldName.equals("criteria")) {
					List criteriaList = (List)values.get("criteria");
					for(int i = 0; i < criteriaList.size(); i++){
						Map map = (Map)criteriaList.get(i);
						
						if (map.containsKey("criteria")) {
							List subCriteriaList = (List)map.get("criteria");
							for(int j = 0; j < subCriteriaList.size();j++){
								Map subMap = (Map)subCriteriaList.get(j);
								// 查找条件项目的字段名
								String subFieldName = (String)subMap.get("fieldName");
					        	if (subFieldName.equals("temp") ||
					        			subFieldName.equals("filter") ||
					        			(extraKey != null && extraKey.containsKey(subFieldName))) {
					        		continue;
					        	}
								// 查找条件项目的值
								Object subFieldValue = subMap.get("value");
								// 查找条件项目的Class名
					        	Class fieldClass = CriteriaConverter.getPropertyType(cls, subFieldName);
					        	// 查找条件项目是枚举的情况，转换为枚举值
					        	Object objValue = CriteriaConverter.convertValue(fieldClass, subFieldValue);
					        	// 条件操作类型
								String operator = (String)subMap.get("operator");

								if(operator.equals("iContains")){
					        		SqlHelperTool.setCriteriaCondition(isFilter, subFieldName, fieldClass, objValue, criteria);
								}else if(operator.equals("greaterOrEqual")){
									criteria.add(Restrictions.ge(subFieldName, objValue));
								}else if(operator.equals("lessOrEqual")){
									criteria.add(Restrictions.le(subFieldName, objValue));
								}else if(operator.equals("equals")){
					        		SqlHelperTool.setCriteriaCondition(isFilter, subFieldName, fieldClass, objValue, criteria);
								}
							}
						} else {
							// 查找条件项目的字段名
							String subFieldName = (String)map.get("fieldName");
				        	if (subFieldName.equals("temp") ||
				        			subFieldName.equals("filter") ||
				        			subFieldName.equals("operator") ||
				        			(extraKey != null && extraKey.containsKey(subFieldName))) {
				        		continue;
				        	}
							// 查找条件项目的值
							Object subFieldValue = map.get("value");
							// 查找条件项目的Class名
				        	Class fieldClass = CriteriaConverter.getPropertyType(cls, subFieldName);
				        	// 查找条件项目是枚举的情况，转换为枚举值
				        	Object objValue = CriteriaConverter.convertValue(fieldClass, subFieldValue);
			        		SqlHelperTool.setCriteriaCondition(isFilter, subFieldName, fieldClass, objValue, criteria);
				        }
					}
	        	} else {
		        	if (!fieldName.equals("temp") &&
		        			!fieldName.equals("filter") &&
		        			!fieldName.equals("operator") && 
		        			((extraKey != null && !extraKey.containsKey(fieldName)) || extraKey == null)) {
			        	Class fieldClass = CriteriaConverter.getPropertyType(cls, fieldName);
			        	Object objValue = CriteriaConverter.convertValue(fieldClass, fieldValue);
		        		SqlHelperTool.setCriteriaCondition(isFilter, fieldName, fieldClass, objValue, criteria);
		        	}
	        	}
	        }
		}
	}

	public static String createSqlOrHqlCondition(Map values,
			Class cls,
			String fieldMarkName,
			boolean isSqlCondition,
			Map extraKey)
	{
		String strWhere = "";
		if (values != null) {
			boolean isFilter = false;
			if (values.containsKey("filter")) {
				// 过滤查询的情况
				isFilter = true;
			} else {
				// 非过滤查询的情况
				isFilter = false;
			}
			
			Set set = values.entrySet();
			Iterator iterator = set.iterator();
	        while(iterator.hasNext())
	        {
	        	Map.Entry entry =(Map.Entry)iterator.next();
	        	String fieldName = "";
	        	String fieldValue = "";
	        	if (entry.getKey() != null) {
		        	fieldName = (String)entry.getKey().toString();	
	        	}
	        	if (entry.getValue() != null) {
		        	fieldValue = (String)entry.getValue().toString();	
	        	}

				if (fieldName.equals("criteria")) {
					List criteriaList = (List)values.get("criteria");
					for(int i = 0; i < criteriaList.size(); i++){
						Map map = (Map)criteriaList.get(i);
						
						if (map.containsKey("criteria")) {
							List subCriteriaList = (List)map.get("criteria");
							for(int j = 0; j < subCriteriaList.size();j++){
								Map subMap = (Map)subCriteriaList.get(j);
								// 查找条件项目的字段名
								String subFieldName = (String)subMap.get("fieldName");
					        	if (subFieldName.equals("temp") ||
					        			subFieldName.equals("filter") ||
					        			(extraKey != null && extraKey.containsKey(subFieldName))) {
					        		continue;
					        	}
					        	
								// 查找条件项目的值
								Object subFieldValue = subMap.get("value");
								// 查找条件项目的Class名
					        	Class fieldClass = CriteriaConverter.getPropertyType(cls, subFieldName);
					        	// 查找条件项目是枚举的情况，转换为枚举值
					        	Object objValue = null;
					        	if (isSqlCondition == true) {
					        		objValue = CriteriaConverter.convertSqlValue(fieldClass, subFieldValue);
					        	} else {
					        		objValue = CriteriaConverter.convertValue(fieldClass, subFieldValue);
					        	}
					        	// 获取字段对应表中的物理名(或者entity中的字段名称)
				        		String columnName = getColumnName(isSqlCondition, subFieldName, cls);
					        	// 条件操作类型
								String operator = (String)subMap.get("operator");

								if(operator.equals("iContains")){
					        		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, fieldMarkName + columnName, fieldClass, objValue.toString());
								}else if(operator.equals("greaterOrEqual")){
									strWhere = strWhere + " AND " + fieldMarkName + columnName + " >= '" + subFieldValue + "'";
								}else if(operator.equals("lessOrEqual")){
									strWhere = strWhere + " AND " + fieldMarkName + columnName + " <= '" + subFieldValue + "'";
								}else if(operator.equals("equals")){
					        		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, fieldMarkName + columnName, fieldClass, objValue.toString());
								}
							}
						} else {
							// 查找条件项目的字段名
							String subFieldName = (String)map.get("fieldName");
				        	if (subFieldName.equals("temp") ||
				        			subFieldName.equals("filter") ||
				        			subFieldName.equals("operator") ||
				        			(extraKey != null && extraKey.containsKey(subFieldName))) {
				        		continue;
				        	}
							// 查找条件项目的值
							Object subFieldValue = map.get("value");
							// 查找条件项目的Class名
				        	Class fieldClass = CriteriaConverter.getPropertyType(cls, subFieldName);
				        	// 查找条件项目是枚举的情况，转换为枚举值
				        	Object objValue = null;
				        	if (isSqlCondition == true) {
				        		objValue = CriteriaConverter.convertSqlValue(fieldClass, subFieldValue);
				        	} else {
				        		objValue = CriteriaConverter.convertValue(fieldClass, subFieldValue);
				        	}
				        	// 获取字段对应表中的物理名(或者entity中的字段名称)
			        		String columnName = getColumnName(isSqlCondition, subFieldName, cls);
			        		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, fieldMarkName + columnName, fieldClass, objValue.toString());
				        }
					}
				} else {
		        	if (!fieldName.equals("temp") &&
		        			!fieldName.equals("filter") &&
		        			!fieldName.equals("operator") && 
		        			((extraKey != null && !extraKey.containsKey(fieldName)) || extraKey == null)) {
			        	// 获取字段对应表中的物理名(或者entity中的字段名称)
		        		String columnName = getColumnName(isSqlCondition, fieldName, cls);
			        	Class fieldClass = CriteriaConverter.getPropertyType(cls, fieldName);
			        	Object objValue = null;
			        	if (isSqlCondition == true) {
			        		objValue = CriteriaConverter.convertSqlValue(fieldClass, fieldValue);
			        	} else {
			        		objValue = CriteriaConverter.convertValue(fieldClass, fieldValue);
			        	}

		        		strWhere = strWhere + SqlHelperTool.getSqlCondition(isFilter, fieldMarkName + columnName, fieldClass, objValue.toString());
		        	}
				}
	        }
		}
		
		return strWhere;
	}
}