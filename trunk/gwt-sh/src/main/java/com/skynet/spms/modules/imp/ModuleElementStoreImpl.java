package com.skynet.spms.modules.imp;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.skynet.common.aop.cache.CacheSource;
import com.skynet.spms.modules.ModuleElementStore;


@Component
public class ModuleElementStoreImpl implements ModuleElementStore {
	
	private static final String ROOT_MODULE_XML = "root.module.xml";

	private Logger log = LoggerFactory.getLogger(ModuleElementStoreImpl.class);

	@Value("${modules.basepath}")
	private String modulePath;
	
	@Value("${rules.basepath}")
	private String rulePath;

	@Autowired
	private ResourceLoader resourceLoader;

	private Element getElement(String name,String basePath) {
		Resource res = resourceLoader.getResource(basePath + "/"+name);

		try {

			String str = FileUtils.readFileToString(res.getFile(), "UTF-8");

			Element rootElem = DocumentHelper.parseText(str).getRootElement();

			return rootElem;
		} catch (IOException e) {
			log.error("file " + name + " read fail");
			throw new IllegalArgumentException(e);
		} catch (DocumentException e) {
			log.error("file " + name + " parse fail");
			throw new IllegalArgumentException(e);
		}

	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.modules.imp.IModuleElementStore#getSubModElement(java.lang.String)
	 */
	@Override
	public Element getSubModElement(String name) {
		Element elem = getElement(name,modulePath);

		return elem;
	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.modules.imp.IModuleElementStore#getRootModElement()
	 */
	@Override
	public Element getRootModElement() {
		return getElement(ROOT_MODULE_XML,modulePath);
	}
	
	/* (non-Javadoc)
	 * @see com.skynet.spms.modules.imp.IModuleElementStore#getRuleElement(java.lang.String)
	 */
	@Override
	public Element getRuleElement(String rule){
		try{
			return getElement(rule+".rule.xml",rulePath);
		}catch(IllegalArgumentException e){
			return getElement(ROOT_MODULE_XML,modulePath);
		}
	}
	
	private Pattern titlePtn = Pattern.compile(
			"^\\s*(<config)[^(name)]*name=\"([^\"]+)", Pattern.MULTILINE);

	
	@CacheSource("rule.list")
	@Override
	public List<String> getFullRoleList() {
		Resource res = resourceLoader.getResource(rulePath);
		
		try{
			File basePath=res.getFile();		
			List<String> ruleList=new ArrayList<String>();
			
			for(File ruleFile:basePath.listFiles(new FilenameFilter(){
	
				@Override
				public boolean accept(File dir, String name) {				
					return name.endsWith(".rule.xml");
				}
				
			})){
				
				try{
					String str = FileUtils.readFileToString(ruleFile, "UTF-8");
					Matcher match = titlePtn.matcher(str);
					if (match.find()) {
						String ruleName= match.group(2);
						ruleList.add(ruleName);
					} else {
						continue;
					}	
				}catch(IOException e){
					log.warn("file read fail:"+ruleFile.getName());
					continue;
				}
			}
		
			return ruleList;
		}catch(Exception e){
			throw new IllegalArgumentException(e);
		}
	}
}
