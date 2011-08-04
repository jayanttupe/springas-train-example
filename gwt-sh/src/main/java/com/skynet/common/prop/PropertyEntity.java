package com.skynet.common.prop;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skynet.common.prop.imp.PropCollection;
import com.skynet.common.strtemplate.SimpleTmpTool;

/**
 * 资源实体类，每实例对应一个资源文件
 * @author jiang
 *
 */
public class PropertyEntity {
	
	private Logger log=LoggerFactory.getLogger(PropertyEntity.class);
	
	private Map<String,String> propMap=new HashMap<String,String>();
		
	
	/**
	 * 由构建器在初始化时调用,任何情况下该方法都不会执行持久化操作
	 * @param propLine
	 */
	public void addProp(String propLine){
		String key=StringUtils.substringBefore(propLine, "=");
		key=StringUtils.trim(key);
		String value=StringUtils.substringAfter(propLine, "=");
		value=StringUtils.trim(value);
		
		if(StringUtils.isBlank(key)){
			return;
		}
		propMap.put(key, value);
	}
	
	public void addProp(PropCollection propCol) {
		
		propMap.putAll(propCol.getPropMap());
	}
	
	public String getProperty(String key){
		if(StringUtils.isBlank(key)){
			log.warn("warn:spec prop key is blank");
			return "";
		}
		
		String tmp=propMap.get(key);
		if(StringUtils.isEmpty(tmp)){
			log.warn("warn:spec prop key "+key+" mapping to empty");
			return key;
		}
		
		
		return  tmp;
	}
	
	/**
	 * 根据指定键值获取资源内容，
	       如果获取失败，则直接返回键值
	       
	       如果指定了参数，则执行参数代换操作
	 * @param key
	 * @param params
	 * @return
	 */
	public String getProperty(String key,String... params){
		
		if(StringUtils.isBlank(key)){
			log.warn("warn:spec prop key is blank");
			return "";
		}
		
		String tmp=propMap.get(key);
		if(StringUtils.isEmpty(tmp)){
			log.warn("warn:spec prop key "+key+" mapping to empty");
			return key;
		}
		
		if(params.length==0){
			return  tmp;
		}else{
			return SimpleTmpTool.generPropByTmp(tmp, params);
		}
	}

	private static Pattern pattern=Pattern.compile("\\$\\{[^}]+\\}",Pattern.MULTILINE);
	
	public String fillWithProp(String input){
		
		Matcher match=pattern.matcher(input);
		
		StringBuffer result=new StringBuffer();
		
		while(match.find()){
			int start=match.start();
			int end=match.end();
			String key=input.substring(start+2,end-1);
			String val=getProperty(key);
			
			match.appendReplacement(result, val);
		}
		match.appendTail(result);
		
		return result.toString();

	}




	
}
