package com.skynet.spms.web.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;

public class SpmsUserDetailSource implements AuthenticationDetailsSource{

	@Override
	public Object buildDetails(Object context) {
		if(context instanceof HttpServletRequest){
			
		}
		return null;
	}

}
