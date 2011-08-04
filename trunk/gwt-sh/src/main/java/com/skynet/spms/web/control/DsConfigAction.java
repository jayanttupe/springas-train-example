package com.skynet.spms.web.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.common.strtemplate.TemplateTool;
import com.skynet.common.strtemplate.TemplateToolFactory;
import com.skynet.spms.service.DSConfigService;

@Controller
public class DsConfigAction {
	
	@Autowired
	private DSConfigService dsConfigService;
	
	@Autowired
	private TemplateToolFactory templateFactory;
		
	
	@RequestMapping("/dsLoad/{dsName}")
	public  ModelAndView redirectToDsLoad(@PathVariable("dsName") String dsName) {
		
		ModelAndView mav = new ModelAndView();
	 	String dsPath="/shared/ds/"+dsName+".ds.xml";

		mav.addObject("dsPath", dsPath);
		mav.setViewName("dsLoad");
	
		return mav;
	}
	
	@RequestMapping(value="/ds/xml/{dsName}",method = RequestMethod.GET)
	public void generDsXml(@PathVariable("dsName") String dsFileName,HttpServletRequest request,HttpServletResponse  response) throws IOException{
		GwtActionHelper.init(request);
		String dsName=StringUtils.substringBefore(dsFileName,".");
		String result = dsConfigService.getCompleteDsXml(dsName,GwtActionHelper.getCurrRule(),GwtActionHelper.getLocale());
		
		response.setContentType(MediaType.TEXT_XML.toString());
		response.getWriter().write(result);
				
	}

	
	
	@RequestMapping(value="/ds/json/{dsName}",method = RequestMethod.GET)
	public void generDsJson(@PathVariable("dsName") String dsFileName,HttpServletRequest request,HttpServletResponse  response) throws IOException{
		GwtActionHelper.init(request);
		String dsName=StringUtils.substringBefore(dsFileName,".");

		String result = dsConfigService.getCompleteDsJson(dsName,GwtActionHelper.getCurrRule(),GwtActionHelper.getLocale());
		
		response.setContentType(MediaType.TEXT_HTML.toString());
		
		response.getWriter().write(result);
		
		return;
		
	}

	
	@RequestMapping(value="/ds/loadJs",method = RequestMethod.GET)
	public void loadDsConfigLoaderJs(HttpServletRequest request,HttpServletResponse  response) throws IOException{
		GwtActionHelper.init(request);

		
		String[] dsArray=dsConfigService.getDataSourceList();
		
		StringBuilder builder=new StringBuilder();
		for(String dsName:dsArray){
			String result = dsConfigService.getCompleteDsJson(dsName,GwtActionHelper.getCurrRule(),GwtActionHelper.getLocale());
			builder.append(result).append(";");
		}
//		Map<String,Object> paramMap=new HashMap<String,Object>();
//		paramMap.put("dsList",dsArray);
//		
//		TemplateTool strTemplate=templateFactory.getGlobeTemplateTool("loadds");
//		
//		String fullJs=strTemplate.gener(paramMap);
//		
		response.setContentType(MediaType.TEXT_HTML.toString());
		response.getWriter().write(builder.toString());
	}
		
		
}
