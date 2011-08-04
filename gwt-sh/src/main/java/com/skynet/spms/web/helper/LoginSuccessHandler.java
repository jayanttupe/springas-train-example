package com.skynet.spms.web.helper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("loginSuccessHandler")
//认证成功之后的处理器
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	
	@Value("${teach.url}")
	//培训系统的url地址，地址信息定义在 application.properties文件中
	private String teachingUrl;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		String  target=request.getParameter("target");
		if(StringUtils.isBlank(target)){
			target="spms";
		}
		if("demo".equals(target)){
			StringBuilder paramStr=new StringBuilder("?");
			for(Enumeration<?> nameEnum=request.getParameterNames();nameEnum.hasMoreElements();){
				String name=String.valueOf(nameEnum.nextElement());
				String val=request.getParameter(name);
				paramStr.append(name).append("=").append(val).append("&");
			}
			String fullUrl=teachingUrl+paramStr.toString();
			response.sendRedirect(fullUrl);
		}
		//转正式系统url路径
		else if("spms".equals(target)){
			String locale=request.getParameter("locale");
			if(StringUtils.isBlank(locale)){
				locale=Locale.SIMPLIFIED_CHINESE.toString();
			}
			// com.skynet.spms.web.control.RedirecCtrl 由实现类处理
			String fullUrl="./spms.form?locale="+locale;
			response.sendRedirect(fullUrl);
//			RequestDispatcher dispatcher=request.getRequestDispatcher("./spms.form");
//			dispatcher.forward(request, response);
		}
				
		
	}
}
