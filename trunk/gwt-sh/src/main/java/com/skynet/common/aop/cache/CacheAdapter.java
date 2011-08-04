package com.skynet.common.aop.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 缓存管理Aop适配类 拦截com.skynet.包下所有被CacheSource标识的函数 以方法参数做为key缓存函数返回结果
 * 并在下次调用时直接由缓存读取数据
 * 
 * todo:接收jms的invalidCache消息清空对应缓存
 * 
 * @author jiang
 * 
 */
@Aspect
@Component
public class CacheAdapter {

	Logger log = LoggerFactory.getLogger(CacheAdapter.class);

	@Autowired
	private GeneralCacheAdministrator cacheAdmin;

	/**
	 * 拦截点入口
	 * 
	 * @param call
	 *            aop拦截点参数
	 * @return
	 * @throws Throwable
	 *             如果原始函数被调用时出现异常，则直接抛出
	 */
	@Around("execution(* com.skynet..*.*(..))"
			+ "&& @annotation(com.skynet.common.aop.cache.CacheSource)")
	public Object doCacheOper(ProceedingJoinPoint call) throws Throwable {

		Method method = AnnoUtils.getAnno(call, CacheSource.class);

		CacheSource cacheAnno = method.getAnnotation(CacheSource.class);

		Object[] paramArray = call.getArgs();

		String group = getGroupBindObj(paramArray, method);

		String fullKey = getFullKey(cacheAnno, paramArray);
		if (StringUtils.isBlank(fullKey)) {
			log.warn("warn,no default key ,use method to key");
			fullKey = call.getSignature().getName();
		}
		Object result = null;
		try {
			result = cacheAdmin.getFromCache(fullKey);
			log.debug("get context by key:" + fullKey);
		} catch (NeedsRefreshException e) {
			log.info("cache is null,direct access value,key:" + fullKey);

			try {
				result = call.proceed();
				doCacheSave(fullKey, result, group);
			} catch (Exception ex) {
				cacheAdmin.cancelUpdate(fullKey);
				throw ex;
			}

		}

		return result;

	}

	private String getGroupBindObj(Object[] paramArray, Method method) {

		Object group = null;
		Annotation[][] paramAnnos = method.getParameterAnnotations();
		for (int i = 0; i < paramAnnos.length; i++) {
			Annotation[] annos = paramAnnos[i];
			if (annos.length == 0) {
				continue;
			}
			Annotation anno = annos[0];
			if (anno.annotationType().equals(GroupBind.class)) {
				group = paramArray[i];
				break;
			}
		}
		if (group == null) {
			return null;
		} else if (group.getClass().isEnum()) {
			return Enum.class.cast(group).name();
		} else {
			return ConvertUtils.convert(group);
		}
	}

	/**
	 * 将实体类置入缓存
	 * 
	 * @param fullKey
	 *            唯一key
	 * 
	 * @param result
	 *            需要缓存的实体类
	 */
	private void doCacheSave(String fullKey, Object result, String group) {
		List<String> groups = new ArrayList<String>();
		if (StringUtils.isNotBlank(group)) {
			groups.add(group);
		}
		if (result instanceof ICacheGroup) {
			String[] groupArray = ((ICacheGroup) result).getCacheGroups();
			if (groupArray == null) {
				throw new NullPointerException("null group names");
			}
			groups.addAll(Arrays.asList(groupArray));
		}
		;

		if (groups.isEmpty()) {
			log.debug("the entity no group info:" + result.getClass().getName());
			cacheAdmin.putInCache(fullKey, result);
		} else {
			log.debug("the entity 's cache group:" + groups);
			cacheAdmin.putInCache(fullKey, result,
					groups.toArray(new String[0]));
		}
	}

	/**
	 * 根据参数列表拼接唯一key
	 * 
	 * @param anno
	 *            cacheSource声明，可以指定key前缀
	 * @param paramArray
	 *            函数参数列表，通过aop获取
	 * @return
	 */
	private String getFullKey(CacheSource anno, Object[] paramArray) {

		StringBuilder sb = new StringBuilder(anno.value());
		if (paramArray == null) {
			return sb.toString();
		}

		for (Object obj : paramArray) {
			if (obj == null) {
				throw new NullPointerException("null key or non-String key");
			}
			sb.append(".").append(ConvertUtils.convert(obj));
		}
		return sb.toString();

	}

	public void onFlushCache(String group) {

		cacheAdmin.flushGroup(group);
	}

}
