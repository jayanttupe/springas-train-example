package com.skynet.spms.web.control;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础控制层
 * 
 * @author tqc
 * 
 */
public class BaseAction {

	private Logger log = LoggerFactory.getLogger(BaseAction.class);

	/**
	 * 复制简单属性
	 * @param dest
	 * @param vo
	 */
	protected void copySimpleProperties(Object dest, Object vo) {
		try {
			BeanUtils.copyProperties(dest, vo);
		} catch (IllegalAccessException e) {
			log.error("vo to pojo cast exception");
			e.printStackTrace();

		} catch (InvocationTargetException e) {
			log.error("vo to pojo cast exception");
			e.printStackTrace();

		}
	}
}
