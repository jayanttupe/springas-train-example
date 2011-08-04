package com.skynet.common.prop;

import java.util.Locale;
import java.util.Map;


/**
 * 管理国际化资源文件，要求资源文件位于resource目录下
 * 
 * 简单文本资源文件规范：
 * 文件名以资源名开头，下划线分割，以locale信息(zh_cn,en,etc.)结尾，文件扩展名为properties
 * 文件内容一律utf-8编码，无需native2ascii转换
 * 文件格式：
 * 键值=内容，每键一行，可以使用${n}作为占位符,n=0,1,2,3,...
 * #开头为注释行
 * 前置后置空格自动忽略
 *  
 * 例：
 * feature_zh_cn.properties
 * feature主题下的中文/简体资源
 * 
 * foo.bar=示例
 * foo.bar.param=示例${0}
 * 
 * @author jiang
 * @see com.skynet.common.prop.PropertyEntity
 * @see com.skynet.common.prop.imp.PropAccessTool
 */
public interface PropManager {

	/**
	 * 获取指定locale对应的资源实体类
	 * @param local
	 * @return
	 */
	public PropertyEntity getPropEntity(Locale local,PropEnum type);

	public PropertyEntity getPropEntity(Locale local,String type);
	
	public PropertyEntity getPropEntity(Locale local);

	 <T extends Enum> String[][] getDisplayMapByEnum(Class<T> enumCls, Locale locale);
	 
	 <T extends Enum> Map<T,String> getLocaleMapByEnum(Class<T> enumCls, Locale locale);
	 
		
}
