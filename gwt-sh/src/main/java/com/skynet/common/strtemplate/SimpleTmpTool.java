package com.skynet.common.strtemplate;


import org.apache.commons.lang.StringUtils;

/**
 * 简单模板构建器
 * @author jiang
 *
 */
public class SimpleTmpTool {
	
	
	/**
	 * 拼装模板与参数
	 * 	 * 
	 * @param tmp 字符串模板,以${n}为占位符,例:this is ${0}'s demo
	 * @param params 参数列表,按顺序依次对应${0},${1},${2},不支持嵌套,例:user
	 * @return 完整的拼装结果,例:this is user's demo
	 */
	public static String generByTmp(String tmp,String... params){
		
		int idx=0;
		String result=tmp;
		for(String param:params){
			String val=param;
			if(StringUtils.isBlank(val)){
				val="";
			}
			result=result.replaceAll("\\$\\{"+idx+"\\}", val);
			idx++;
		}
		return result;
	}
	
	public static String generPropByTmp(String tmp,String... params){
		
		int idx=0;
		String result=tmp;
		for(String param:params){
			String val=param;
			if(StringUtils.isBlank(val)){
				val="";
			}
			result=result.replaceAll("\\{"+idx+"\\}", val);
			idx++;
		}
		return result;
	}

}
