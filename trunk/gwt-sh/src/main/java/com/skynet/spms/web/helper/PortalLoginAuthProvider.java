package com.skynet.spms.web.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AbstractAuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.skynet.spms.web.form.SpmsUserDetail;

public class PortalLoginAuthProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier("userDetailService")
	private UserDetailsService userDetailService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		UserDetails userDetail = userDetailService
				.loadUserByUsername((String) authentication.getPrincipal());

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				userDetail, userDetail.getAuthorities(),
				userDetail.getAuthorities());

		return token;

	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (authentication.equals(PortalNoLoginAuthToken.class));
	}

}
