package com.skynet.common.strtemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.antlr.stringtemplate.StringTemplateGroup;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


/**
 * 模板工具,根据模板与参数生成字符串
 * 
 * 相关模板语法详见 http://www.antlr.org/wiki/display/ST/StringTemplate+Documentation
 * 
 * @author jiang
 * @see org.antlr.stringtemplate
 */
@Component
public class TemplateToolFactory {

	private Map<String,TemplateGroup> groupMap;
	
	@Value("${template.basepath}")
	private String baseTmplatePath;
	
	@Autowired
	private ResourceLoader resLoader;


	@PostConstruct
	public void init() throws IOException {
		Map<String,TemplateGroup> map=new HashMap<String,TemplateGroup>();
				
		File basePath=resLoader.getResource(baseTmplatePath).getFile();
		
		generGroup(basePath,map);
		
		for(File file:basePath.listFiles(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				return StringUtils.endsWith(name, ".stg");
			}
			
		})){
			generGroup(file,map);
		}
		
		groupMap=Collections.unmodifiableMap(map);
		
	}

	private void generGroup(File file,Map<String,TemplateGroup> map) throws FileNotFoundException{
		
		StringTemplateGroup group;
		if (file.isDirectory()) {
			String rootPath = file.getAbsolutePath();
			group = new StringTemplateGroup("singleCol", rootPath);
		} else {
			group = new StringTemplateGroup(new FileReader(file));
		} 

		group.setFileCharEncoding("UTF-8");
		group.setRefreshInterval(3600);

		map.put(group.getName(), new TemplateGroup(group));
	}

	
	public TemplateGroup getTemplateGroup(String groupName){
		return groupMap.get(groupName);
	}
	
	public TemplateGroup getGlobeTemplateGroup(){
		return groupMap.get("singleCol");
	}	
	
	public TemplateTool getGlobeTemplateTool(String templateName){
		return getGlobeTemplateGroup().getTemplateTool(templateName);
	}	

}
