package com.skynet.spms.datasource.imp;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.EntityInfoService;
import com.skynet.spms.datasource.annotation.DsField;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.datasource.entity.EntityMetaInfo;
import com.skynet.spms.datasource.entity.FieldMetaInfo;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseIDEntity;

@Component
public class EntityInfoServiceImp implements EntityInfoService {

	@CacheSource("datasourceInfo")
	@Override
	public EntityMetaInfo getEntityInfoByClsName(String className) {

		Class<?> viewCls = BeanPropUtil.getClassByName(className);
		String pkName="entityId";
		if(BaseIDEntity.class.isAssignableFrom(viewCls)){
			pkName="id";
		}else{
			ViewFormAnno dsAnno = viewCls.getAnnotation(ViewFormAnno.class);
			if(dsAnno!=null){
				pkName=dsAnno.value();
			}
		}
		EntityMetaInfo entityInfo = new EntityMetaInfo(pkName);
		
		Set<Class<?>> nestedClsSet = new HashSet<Class<?>>();
		nestedClsSet.add(viewCls);
		entityInfo.fillFieldSet(operEntityCls(viewCls, nestedClsSet));

		return entityInfo;
	}

	private Set<FieldMetaInfo> operEntityCls(Class<?> cls,
			Set<Class<?>> nestedClsSet) {
		Set<FieldMetaInfo> fieldSet = new LinkedHashSet<FieldMetaInfo>();
		
		Set<Method> methodSet=new LinkedHashSet<Method>();
		methodSet.addAll(Arrays.asList(cls.getDeclaredMethods()));
		methodSet.addAll(Arrays.asList(cls.getMethods()));
		
		for (Method method : methodSet) {

			Class<?> typeCls = method.getReturnType();

			String fieldName = getClearFieldName(method, cls);
			if (StringUtils.isBlank(fieldName)) {
				continue;
			}
		
			FieldMetaInfo fieldInfo=commFieldGener(method,fieldName);
			if(fieldInfo!=null){
				fieldSet.add(fieldInfo);
				continue;
			}
			
//			//with anno
//			if (method.isAnnotationPresent(DsField.class)) {
//				FieldMetaInfo info = new FieldMetaInfo();
//				info.fillFieldInfo(method);
//
//				info.setTypeName(fieldName);
//
//				fieldSet.add(info);
//				continue;
//			}
//					
//			//java cls
//			if(BeanPropUtil.isJavaCls(typeCls)||
//					typeCls.isEnum()){
//				//no anno,direct refect
//				FieldMetaInfo info = new FieldMetaInfo();
//				info.fillFieldInfoWithCls(method);
//
//				info.setTypeName(fieldName);
//
//				fieldSet.add(info);
//				continue;
//			}
			
			//business cls
			if (BeanPropUtil.isBusiness(typeCls)) {
				
				Set<FieldMetaInfo> nestedSet = null;
				if (nestedClsSet.contains(typeCls)
						&& typeCls.isAnnotationPresent(Entity.class)) {
					nestedSet = operNestedEntityCls(typeCls);
				} else {
					nestedClsSet.add(typeCls);
					nestedSet = operEntityCls(typeCls, nestedClsSet);
				}
				for (FieldMetaInfo field : nestedSet) {
					field.addPrefix(fieldName);
					fieldSet.add(field);
				}
				continue;
			}
			

			//TODO:
//			Reference refer = method.getAnnotation(Reference.class);
//			if (typeCls.isArray() || Collection.class.isAssignableFrom(typeCls)) {
////				TypeVariable<?>[] typeArray = typeCls.getTypeParameters();
//				
//				FieldMetaInfo colField=new FieldMetaInfo();
//				colField.setTypeName(fieldName);
////				colField.setColInfo(typeCls);
//				fieldSet.add(fieldInfo);
//				continue;
//			}
			
		}
		return fieldSet;
	}
	
	private Set<FieldMetaInfo> operNestedEntityCls(Class<?> cls) {
		Set<FieldMetaInfo> fieldSet = new HashSet<FieldMetaInfo>();
		for (Method method : cls.getDeclaredMethods()) {

			Class<?> typeCls = method.getReturnType();

			String fieldName = getClearFieldName(method, cls);
			if (StringUtils.isBlank(fieldName)) {
				continue;
			}

			FieldMetaInfo fieldInfo=commFieldGener(method,fieldName);
			if(fieldInfo!=null){
				fieldSet.add(fieldInfo);
				continue;
			}

			
			//business cls
			if (BeanPropUtil.isBusiness(typeCls)) {

				Set<FieldMetaInfo> nestedSet = null;
				if (typeCls.isAnnotationPresent(Entity.class)) {
					continue;
				} else {
					nestedSet = operEntityCls(typeCls, new HashSet<Class<?>>());
				}
				for (FieldMetaInfo field : nestedSet) {
					field.addPrefix(fieldName);
					fieldSet.add(field);
				}
				continue;
			}

			if (typeCls.isArray()) {
				// TODO:
				continue;
			}
		}
		return fieldSet;
	}	
	
	
	private FieldMetaInfo commFieldGener(Method method,String fieldName){
		Class<?> typeCls = method.getReturnType();

		//with anno
		if (method.isAnnotationPresent(DsField.class)) {
			FieldMetaInfo info = new FieldMetaInfo();
			info.fillFieldInfo(method);

			info.setTypeName(fieldName);

			return info;
		}
				
		//java cls
		if(BeanPropUtil.isJavaCls(typeCls)||
				typeCls.isEnum()){
			//no anno,direct refect
			FieldMetaInfo info = new FieldMetaInfo();
			info.fillFieldInfoWithCls(method);

			info.setTypeName(fieldName);

			return info;
		}
		
		return null;
		
	}
	private String getClearFieldName(Method method, Class<?> cls) {

		String name = method.getName();
		if (!StringUtils.startsWith(name, "get")&&
				!StringUtils.startsWith(name, "is")) {
			return null;
		}

		int preLength=3;
		if(StringUtils.startsWith(name, "is")){
			preLength=2;
		}

		String clearName = StringUtils.uncapitalize(name.substring(preLength));
		
		String setName = "set" + StringUtils.capitalize(clearName);
//		if(method.getReturnType().equals(Boolean.class)||
//				method.getReturnType().equals(boolean.class)){
//			setName="is"+StringUtils.capitalize(clearName);
//		}
		try {
			cls.getMethod(setName, method.getReturnType());
		} catch (SecurityException e) {
			return null;
		} catch (NoSuchMethodException e) {
			return null;
		}

		if(BaseEntity.class.isAssignableFrom(cls)&&
			nonBusinessField.contains(clearName)){
			
			return null;
			
		}
		return clearName;

	}
	
	private static Set<String> nonBusinessField=new HashSet<String>();
	static{
//		nonBusinessField.addAll(Arrays.asList(new String[]{"delete","version","lockVersion"}));
	}
	
}
