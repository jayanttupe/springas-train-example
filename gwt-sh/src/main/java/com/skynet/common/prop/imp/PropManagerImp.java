package com.skynet.common.prop.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.common.aop.cache.GroupBind;
import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;


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
 * 默认locale为zh_cn
 * @author jiang
 * @see com.skynet.common.prop.PropertyEntity
 * @see com.skynet.common.prop.imp.PropAccessTool
 */
@Component
public class PropManagerImp implements PropManager{

	private Logger log = LoggerFactory.getLogger(PropManagerImp.class);

	@Autowired
	private PropFileStore store;
	
	private final Map<String,PropertyEntity> propMap=new HashMap<String,PropertyEntity>();
	
	@PostConstruct
	public void initProp(){
		
		for(String fileName:store.getPropFileNameArray()){
			
			try {
				PropCollection propCol=store.getPropCollection(fileName);
				
				if(propMap.containsKey(propCol.getLocale())){
					propMap.get(propCol.getLocale()).addProp(propCol);
				}else{
					PropertyEntity entity=new PropertyEntity();
					entity.addProp(propCol);
					propMap.put(propCol.getLocale(), entity);
				}
				
			} catch (IOException e) {
				log.info(" prop file "+fileName +" read fail.");
				continue;
			}			
			
		}		
		
	}
	/**
	 * 获取指定locale对应的资源实体类
	 * @param local
	 * @return
	 */
	@Override
	public PropertyEntity getPropEntity(Locale local,PropEnum type){
		
		return getPropEntity(local);
	}
	
	@Override
	public PropertyEntity getPropEntity(Locale local,String type){
		return getPropEntity(local);
	}

	

	@Override	
	public PropertyEntity getPropEntity(Locale locale){
				
		PropertyEntity entity= propMap.get(locale.toString().toLowerCase());
		
		if(entity==null){
			entity= propMap.get(Locale.SIMPLIFIED_CHINESE.toString().toLowerCase());
		}
		return entity;
	}
	

	
	private static final String DICT_DATIONARY="datadictory";
	
	@Override
	@CacheSource("prop.enum")
	public <T extends Enum> String[][] getDisplayMapByEnum(Class<T> enumCls,Locale locale) {
		
		PropertyEntity propEntity=getPropEntity(locale);
				
		T[] conArray=(T[]) enumCls.getEnumConstants();
		String clsName=enumCls.getSimpleName();
		
		String[][] resultArray=new String[2][conArray.length];
		for(int i=0;i<conArray.length;i++){
			T con=conArray[i];
			String fullKey=clsName+"."+con.name();
			resultArray[0][i]=con.name();
			resultArray[1][i]=propEntity.getProperty(fullKey);
		}
		
		return resultArray;
	}

	@Override
	public <T extends Enum> Map<T, String> getLocaleMapByEnum(Class<T> enumCls,
			Locale locale) {
		String propName=DICT_DATIONARY+"_"+locale.toString().toLowerCase();
		
		PropertyEntity propEntity=getPropEntity(locale);
		
		
		T[] conArray=(T[]) enumCls.getEnumConstants();
		String clsName=enumCls.getSimpleName();
		
		Map<T,String> map=new HashMap<T,String>();
		for(int i=0;i<conArray.length;i++){
			T con=conArray[i];
			String fullKey=clsName+"."+con.name();
			map.put(con,propEntity.getProperty(fullKey));
		}
		
		return map;
	}
	
}
