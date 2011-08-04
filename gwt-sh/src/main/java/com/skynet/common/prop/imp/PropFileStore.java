package com.skynet.common.prop.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.skynet.common.prop.PropertyEntity;

@Component
public class PropFileStore {
	
	private Logger log = LoggerFactory.getLogger(PropFileStore.class);

	
	@Autowired
	private ResourceLoader loader;
		
	@Value("${resource.basepath}")
	private String basePropPath;
	
	@CacheSource("property")
	public PropCollection getPropCollection(String propName) throws IOException{
		
		Matcher match=namePtn.matcher(propName);
		
		if(!match.matches()){
			throw new IllegalStateException();
		}
		
		String loc=match.group(1);
				
		InputStream stream=loader.getResource(basePropPath+"/"+propName).getInputStream();
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(stream,"UTF-8"));
		
		return new PropCollection(reader,loc);		
		
	}
	private static final Pattern namePtn=Pattern.compile("^[^_]+_([^.]+).*");
	
	public String[] getPropFileNameArray() {
		
		try{
		File basePath=loader.getResource(basePropPath).getFile();
				
		return basePath.list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				
				return name.endsWith(".properties")&&namePtn.matcher(name).find();
			}
			
		});	
		}catch(Exception e){
			throw new IllegalArgumentException(e);
		}
		
	}
	
	
//	private PropertyEntity getProperty(String propFileName) {
//		
//		Resource resource=loader.getResource(basePropPath+propFileName+".properties");
//		
//		InputStream input=null;
//		
//		BufferedReader propReader;
//		PropertyEntity entity=new PropertyEntity();
//		try {
//			input=resource.getInputStream();
//			propReader = new BufferedReader(
//						new InputStreamReader(input, "UTF-8"));
//						
//			String nextLine=null;		
//			while((nextLine=getNextLine(propReader))!=null){
//				entity.addProp(nextLine);		
//			};
//			
//		} catch (UnsupportedEncodingException e) {
//			log.error("impossible");
//			throw new IllegalArgumentException(e);
//		} catch (IOException e) {
//			log.error("file io error:"+propFileName);			
//			throw new IllegalArgumentException(e);
//		}finally{
//			IOUtils.closeQuietly(input);
//		}	
//		
//		return entity;
//
//	}
//	
//	private String getNextLine(BufferedReader propReader) {
//		String tmpLine = null;
//		while (true) {
//
//			try {
//				tmpLine = propReader.readLine();
//			} catch (IOException e) {
//				log.error("read fail", e);
//				IOUtils.closeQuietly(propReader);
//				break;
//			}
//
//			if (tmpLine == null) {
//				IOUtils.closeQuietly(propReader);
//				break;
//			}
//
//			tmpLine = StringUtils.trim(tmpLine);
//
//			if (StringUtils.isBlank(tmpLine)
//					||tmpLine.startsWith("#")
//					||tmpLine.startsWith("=")) {
//				continue;
//			}
//
//			break;
//		}
//		return tmpLine;
//	}

}
