package com.skynet.common.prop.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class PropCollection {

	private final Map<String,String> propMap=new HashMap<String,String>();
	
	private final String locale;
	
	public PropCollection(BufferedReader reader,String locale) throws IOException{
		
		this.locale=locale;
		
		String line=null;
		while((line=reader.readLine())!=null){
		
			if(StringUtils.isBlank(line)||!StringUtils.contains(line, "=")){
				continue;
			}
			String key=StringUtils.substringBefore(line, "=");
			key=StringUtils.trim(key);
			String value=StringUtils.substringAfter(line, "=");
			value=StringUtils.trim(value);
			
			if(StringUtils.isBlank(key)){
				continue;
			}
			if(key.startsWith("#")){
				continue;
			}
			propMap.put(key, value);
		}			
		
	}

	public String getLocale() {
		return locale;
	}

	public Map<String,String> getPropMap() {
		return propMap;
	}
	
}
