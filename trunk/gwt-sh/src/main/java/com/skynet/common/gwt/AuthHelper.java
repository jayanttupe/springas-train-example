package com.skynet.common.gwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * spring security静态辅助类
 * 用于从当前用户上下文authentication提取user/rule信息
 * @author jiang
 *
 */
public class AuthHelper {
	
	private AuthHelper(){
		
	}
	
	/**
	 * 由authentication获取role字段
	 * @param authent
	 * @return
	 */
	public static String getCurrRule(Authentication authent){
			
		return getCurrRules(authent)[0];

	}
	
	/**
	 * 由authentication获取role字段数组
	 * @param authent
	 * @return
	 */
	public static String[] getCurrRules(Authentication authent){
		
		if(authent==null||!authent.isAuthenticated()){
			throw new UserNotLogginException();
		}		
		if(authent instanceof AnonymousAuthenticationToken){
			return new String[]{"guest"}; 
		}

		Object principal=authent.getPrincipal();
		
		
		if (principal instanceof UserDetails) {
			UserDetails userDetail=(UserDetails)principal;
			
			Collection<GrantedAuthority>  authCol=userDetail.getAuthorities();
			
			List<String> list=new ArrayList<String>();
			if(authCol.isEmpty()){
				throw new UserNotLogginException();
			}
			for(GrantedAuthority auth:authCol){
				
				list.add(auth.getAuthority());				
			}
			return list.toArray(new String[0]);
			
		} else {
			throw new IllegalStateException();
		}

	}

	/**
	 * 由authentication获取userName
	 * @param authent
	 * @return
	 */
	public String getCurrUser() {
		Authentication authent = SecurityContextHolder
		.getContext()
		.getAuthentication();
		
		
		if(authent==null||!authent.isAuthenticated()){
			throw new UserNotLogginException();
		}		

		return getUserByAuth(authent);

	}


	public static String getUserByAuth(Authentication authent) {
		if(authent==null){
			if(authent==null||!authent.isAuthenticated()){
				throw new UserNotLogginException();
			}	
		}
		
		if(authent instanceof AnonymousAuthenticationToken){
			return "Anonymous"; 
		}
		Object principal=authent.getPrincipal();
		
		if (principal instanceof UserDetails) {
			UserDetails userDetail=(UserDetails)principal;
			
			return userDetail.getUsername();
		}else{
			throw new IllegalStateException();
		}
		
	}
	
}
