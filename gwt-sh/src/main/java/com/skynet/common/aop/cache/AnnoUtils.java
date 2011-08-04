package com.skynet.common.aop.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 辅助类，用于提取annotation等aop常规操作
 * @author jiang
 *
 */
public class AnnoUtils {
	
	private static Logger log=LoggerFactory.getLogger(AnnoUtils.class);
	
	/**
	 * 从class中解析出匹配aop声明的annotation
	 * @param <T> 获取到的annotation	  
	 * @param call aop横切点声明
	 * @param annoCls 需要提取的annotation声明类
	 * @return
	 */
	public static  Method getAnno(JoinPoint call,Class<? extends Annotation> annoCls){
		
		String methodName=call.getSignature().getName();
		
		Class<?> cls=call.getTarget().getClass();
		
		log.debug("cls:"+cls.getName());
		
		for(Method method:cls.getMethods()){
			log.debug("aop method:"+method.getName());
			log.debug("annotation:"+Arrays.asList(method.getAnnotations()));
			if(method.getName().equals(methodName)
					&&method.isAnnotationPresent(annoCls)){
				return method;
			}
		}
		throw new IllegalStateException();
	}
}
