package com.skynet.spms.web.control;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("locale")
//登录url转向处理类
public class RedirectCtrl {

	private Logger log=LoggerFactory.getLogger(RedirectCtrl.class);
	
	@RequestMapping("/spms")
	public  ModelAndView redirectToSpms(
			@RequestParam(value="locale",defaultValue="zh_CN")	String locale,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		//根据 mvc-servlet.xml中 jspViewResolver的定义， 映射WEB-INF/jsp/index.jsp文件
		mav.setViewName("index");
		mav.addObject("locale",new Locale(locale));
			
//		if(StringUtils.isNotBlank(modName)){
//			Cookie mod=new Cookie("seleMod",modName);
//			response.addCookie(mod);
//		}
		log.info("redirect to spms.form");		
		
		return mav;
	}
	
	@RequestMapping("/demo")
	public  ModelAndView redirectToSpms(@RequestParam(value="locale",defaultValue="zh_CN")	String locale) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("locale",new Locale(locale));
				
		log.info("redirect to portal.form");		
		
		return mav;
	}
	


	
}
