package com.skynet.common.gwt;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * 辅助类,用以传递httpSession,用户locale信息，登录用户信息等
 * 供gwt业务action使用
 * 
 * 所保存的值由GwtRcpEndPointHandlerAdapter初始化,与当前用户线程绑定
 * @author jiang
 * @see com.skynet.common.gwt.GwtRcpEndPointHandlerAdapter
 */
public class GwtActionHelper {
	
	private static Logger log=LoggerFactory.getLogger(GwtActionHelper.class);
	
	private static ThreadLocal<HttpSession> sessionStore = new ThreadLocal<HttpSession>();
		
	/**
	 * 获取当前用户选择的locale
	 * @return
	 */
	public static Locale getLocale(){
		
		Locale locale=(Locale) getSession().getAttribute("locale");
		
		if(locale==null){
			locale=Locale.SIMPLIFIED_CHINESE;
		}
		log.debug("locale:"+locale);
		return locale;
	}
	
	/**
	 * 获取当前用户的session
	 * @return
	 */
	public static HttpSession getSession(){
		HttpSession session= sessionStore.get();
		
		log.debug("session:"+session.getId());		
		return session;
	}
	
	/**
	 * 获取当前用户所属的rule
	 * @return
	 */
	public static String getCurrRule(){
		Authentication authent = SecurityContextHolder
		.getContext()
		.getAuthentication();
		if(authent==null){
			return "guest";
		}
		
		return AuthHelper.getCurrRule(authent);
	}

	/**
	 * 获取当前用户名称
	 * @return
	 */
	public static String getCurrUser() {
		Authentication authent = SecurityContextHolder
		.getContext()
		.getAuthentication();
		
		if(authent==null){
			return "anonymously";
		}
		
		
		return AuthHelper.getUserByAuth(authent);

	}
	
//	public static String getCurrUserID() {
//		Authentication authent = SecurityContextHolder
//		.getContext()
//		.getAuthentication();
//		
//		if(authent==null){
//			return "anonymously";
//		}
//		
//		
//		return AuthHelper.getUserByAuth(authent);
//
//	}
	/**
	 * 根据当前request初始化相关用户信息
	 * @param request
	 */
	public static void init(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		sessionStore.set(session);
	}

}
