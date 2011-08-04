package com.skynet.spms.datasource;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.CustomType;
import org.hibernate.type.EnumType;
import org.hibernate.type.Type;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class QueryGeneral {
	
	
	private Logger log=LoggerFactory.getLogger(QueryGeneral.class);
	
	@Autowired
	private ApplicationContext context;
	
	private SessionFactory getSessionFactory() {
		
		return context.getBean(SessionFactory.class);		

	}


	public Criterion generQueryHQL(Map<String, Object> values,Class<?> cls) {
		
		String construct=(String)values.get("_constructor");
		
		if (StringUtils.equals("AdvancedCriteria",construct)) {

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> criteriaList = (List<Map<String, Object>>) values
					.get("criteria");
			String operator = (String) values.get("operator");
			ConditionOper oper=ConditionOper.valueOf(operator);
			
			return generCriteriaGroup(criteriaList,oper,cls);
		} else  {
			return generSimpleCriteria(values,cls);
		} 
	}
	
	private Criterion generCriteriaGroup(List<Map<String, Object>> criteriaList,ConditionOper oper,Class<?> cls){
			
		Junction junction=getJunction(oper);
		
		for (Map<String, Object> map : criteriaList) {
			Criterion subQuery = generQueryHQL(map,cls);
			junction.add(subQuery);
		}
		return junction;
	}

	private Criterion generSimpleCriteria(Map<String, Object> criteriaMap,Class cls) {
		
		log.debug(" simple query map:"+criteriaMap);
		
		String fieldName = (String) criteriaMap.get("fieldName");
		String operator = (String) criteriaMap.get("operator");
		Object value=criteriaMap.get("value");
		
		Type type=getSessionFactory().getClassMetadata(cls).getPropertyType(fieldName);
		
		if(type instanceof CustomType){
			
			UserType userType=((CustomType)type).getUserType();
			if(userType instanceof EnumType){
				value=BeanPropUtil.getEnumByCls(type.getReturnedClass(), value);
			}
		}
		
		SQLOper oper=SQLOper.valueOf(operator);		
		return getCriterion(oper,fieldName,value);
	}
	
	private Junction getJunction(ConditionOper oper){
		log.info(" join condition:"+oper);
		switch(oper){
		case and:
			return Restrictions.conjunction();
		case or:
			return Restrictions.disjunction();
		default:
			throw new IllegalStateException(oper.name());
		}
	}
	
	private Criterion getCriterion(SQLOper operator,String name,Object value){
		switch(operator){
			case lessThan:
				return Restrictions.lt(name, value);
			case lessOrEqual:
				return Restrictions.le(name,value);
			case greaterThan:
				return Restrictions.gt(name, value);
			case greaterOrEqual:
				return Restrictions.ge(name, value);
			case equals:
				return Restrictions.eq(name, value);
			case iContains:
				return Restrictions.like(name,value);
			case notEqual:
				return Restrictions.ne(name, value);
			default:
				throw new IllegalStateException(operator.name());
		}
		
	}
	
	private enum SQLOper{
		notEqual,lessThan,lessOrEqual,equals,greaterThan,
		greaterOrEqual,between,betweenInclusive,iContains;		
	}

	private enum ConditionOper{
		and,or;
	}
	
}
