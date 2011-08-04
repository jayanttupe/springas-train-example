package com.skynet.spms.service.imp;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.isomorphic.datasource.DataSource;
import com.isomorphic.datasource.ValidationContext;
import com.isomorphic.js.JSTranslater;
import com.skynet.common.aop.cache.CacheSource;
import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.prop.PropEnum;
import com.skynet.common.prop.PropManager;
import com.skynet.common.prop.PropertyEntity;
import com.skynet.common.strtemplate.SimpleTmpTool;
import com.skynet.spms.service.DSConfigService;

@Component
public class DsConfigServiceImpl implements DSConfigService{
	
	private static final String TMP_RULE="classpath:datasource/${0}-${1}.ds.xml";
	private static final String TMP_COMM="classpath:datasource/${0}.ds.xml";
	
	@Autowired
	private ResourceLoader loader;
	
	@Autowired
	private PropManager msgMang;
	
	@Autowired
	private DsConfigConvert convert;
	
	private final DocumentBuilder builder;
	

	public DsConfigServiceImpl(){
		try {
			builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new IllegalArgumentException(e);
		}
	}
	

	@CacheSource("ds.xml")
	@Override
	public String getCompleteDsXml(String dsName,String rule,Locale locale)  {
		String dsFilePath=SimpleTmpTool.generByTmp(TMP_RULE, dsName,rule);
		String commDsFilePath=SimpleTmpTool.generByTmp(TMP_COMM, dsName);
		
		Resource resource=loader.getResource(dsFilePath);
		if(!resource.exists()){
			resource=loader.getResource(commDsFilePath);
		}
		try{
			String str=IOUtils.toString(resource.getInputStream(),"UTF-8");
			
			String xmlStr = convert.convertToDsXml(str);
			
			PropertyEntity prop=msgMang.getPropEntity(locale, PropEnum.Feature);
			
			String result=prop.fillWithProp(xmlStr);
			return result;
		}catch(IOException e){
			throw new IllegalArgumentException(e);
	
		} catch (DocumentException e) {
			
			e.printStackTrace();
			throw new IllegalArgumentException("xml format fail",e);
		}
		

	}
	
	@CacheSource("ds.json")
	@Override
	public String getCompleteDsJson(String dsName,String rule,Locale locale)  {
		String fullXml=this.getCompleteDsXml(dsName,rule,locale);
		
        Writer out=new StringWriter();
		try{
			Document doc=builder.parse(IOUtils.toInputStream(fullXml, "UTF-8"));
	        
	        ValidationContext context = new ValidationContext();
	        Object result = DataSource.recordsFromXML(doc.getDocumentElement(), context);
	    
	        JSTranslater trans=new JSTranslater();
	        trans.toJS(result, out);
	        
	        if(context.hasErrors()){
	            out.write("\n");
	            JSTranslater errors=new JSTranslater();
	            errors.toJSVariable(context.getErrors(), "error", out);
	        }
		}catch(Exception e){
			throw new IllegalArgumentException(e.getMessage());
		}
        
		return out.toString();
	}

	@CacheSource("ds.instance")
	@Override
	public DataSource getDataSourceByName(String dsName, String rule,
			Locale locale) throws Exception {
		String customXml=getCompleteDsXml(dsName, rule,locale);
		
		Reader reader= new StringReader(customXml);

		DataSource ds = DataSource.fromXML(reader);
		
		return ds;
	}
	
	@Override
	public String[] getDataSourceList(){
		Resource resource=loader.getResource("classpath:datasource");
		File basePath=null;
		try {
			basePath=resource.getFile();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
			String[] nameArray= basePath.list(new FilenameFilter(){

				@Override
				public boolean accept(File dir, String name) {
					
					return name.endsWith(".ds.xml")&&
					!(name.startsWith("."));
						
					
				}
				
			});
			
		for(int i=0;i<nameArray.length;i++){
			String name=nameArray[i];
			String clearName=StringUtils.substringBefore(name, ".");
			nameArray[i]=clearName;
		}
		
		return nameArray;
		
	}
}
