package com.skynet.common.strtemplate;

import java.util.Date;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.commons.beanutils.PropertyUtils;

public class TemplateTool {

	private final StringTemplate strTpl;

	TemplateTool(StringTemplate strTpl) {
		this.strTpl = strTpl;
	}

	/**
	 * 内容生成
	 * 
	 * @param templateName
	 *            模板名称, 对应template目录下以模板名称开头的.st文件，或者.stg文件中的对应段
	 * @param entity
	 *            ,参数类,其get/set方法应能映射到模板占位符,而对应值会被替换入模板生成结果
	 * @return 完整结果
	 */
	public String gener(Object entity) {

		fillAttribute(entity);

		return strTpl.toString();
	}

	/**
	 * 内容生成
	 * 
	 * @param templateName
	 *            模板名称, 对应template目录下以模板名称开头的.st文件，或者.stg文件中的对应段
	 * @param paramMap
	 *            参数映射表,key值对应占位符，而value对应替换值
	 * @return 拼合结果
	 */
	public String gener(Map<String, Object> paramMap) {

		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			String key = (String) entry.getKey();
			strTpl.setAttribute(key, entry.getValue());
		}

		String result= strTpl.toString();
		strTpl.reset();
		return result;
	}

	private void fillAttribute(Object entity) {
		try {
			Map<?, ?> propMap = PropertyUtils.describe(entity);

			for (Map.Entry<?, ?> entry : propMap.entrySet()) {
				String key = (String) entry.getKey();
				strTpl.setAttribute(key, entry.getValue());
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void addFloatFormat(String name,String format) {
		((TypeAttributeRender) strTpl.getAttributeRenderer(Float.class))
		.addFormatWithType(name, format);
		((TypeAttributeRender) strTpl.getAttributeRenderer(Double.class))
		.addFormatWithType(name, format);

	}

	public void addIntFormat(String name,String format) {
		((TypeAttributeRender) strTpl.getAttributeRenderer(Integer.class))
		.addFormatWithType(name, format);
		((TypeAttributeRender) strTpl.getAttributeRenderer(Long.class))
		.addFormatWithType(name, format);
	}
	
	public void addDateFormat(String name, String format) {
		((TypeAttributeRender) strTpl.getAttributeRenderer(Date.class))
				.addFormatWithType(name, format);
	}
}
