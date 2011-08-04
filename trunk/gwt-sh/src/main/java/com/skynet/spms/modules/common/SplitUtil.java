package com.skynet.spms.modules.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;

public class SplitUtil {
	
	public static Collection<String> getSplitStr(String str,String prefix){
		
		str=StringUtils.trim(str);
		if (StringUtils.isNotBlank(str)) {
			String[] valArray =str.split(",");
			valArray = StringUtils.stripAll(valArray);
			for(int i=0;i<valArray.length;i++){
				valArray[i]=prefix+valArray[i];
			}
			return Arrays.asList(valArray);
		}else{
			return Collections.emptySet();
		}
	}
	
	
	public static Collection<String> getSplitStr(String str){
		
		return getSplitStr(str,"");
	}
	
	public static String joinStr(String prefix,String str){
		if(StringUtils.isBlank(prefix)){
			return str;
		}else{
			return prefix+"."+str;
		}
		
	}

}
