package com.skynet.spms.web.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Controller;


public class PortalLoginFilter extends AbstractAuthenticationProcessingFilter{

	public PortalLoginFilter() {
		super("/portal_direct_login");	
		
	}
	
//	@Autowired
//	@Qualifier("userDetailService")
//	private UserDetailsService userDetailService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		
		String userName=request.getParameter("username");
		
		PortalNoLoginAuthToken authentication=new PortalNoLoginAuthToken(userName);

        return this.getAuthenticationManager().authenticate(authentication);
	}

}
