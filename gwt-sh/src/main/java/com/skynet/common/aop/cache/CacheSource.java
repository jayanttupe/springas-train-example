package com.skynet.common.aop.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 修饰需要缓存结果的函数
 * 
 * 函数参数建议为一个字符串类型，便于直接作为查询key
 * 如果有多个参数，拦截点会将参数依次转为string后拼成查询key
 * 如果无参数，则将业务类名合并函数名作为查询key
 * 
 * @author jiang
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheSource {
	
	/**
	 * 可选，对应缓存key的唯一前缀，用于区分不同的缓存对象
	 * 如果所修饰的函数无参数列表，这该值将直接作为缓存唯一key
	 * @return
	 */
	String value()default "default";
	
	String groupBind() default "";

}
