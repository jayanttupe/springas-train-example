package com.skynet.spms.web.helper;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


public class PortalNoLoginAuthToken extends AbstractAuthenticationToken{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9156972542570114905L;

	private String userName;

	
	public PortalNoLoginAuthToken(String userName){
		super(null);
		this.userName=userName;
		this.setAuthenticated(true);
	}
	

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return userName;
	}
	
	public void setUserDetail(UserDetails userDetails){
		
	}
	
//	public PortalNoLoginAuth(UserDetails userDetail){
//		this.userDetail=userDetail;
//	}
	
//	@Override
//	public String getName() {
//		return userDetail.getUsername();
//	}
//
//	@Override
//	public Collection<GrantedAuthority> getAuthorities() {
//		return userDetail.getAuthorities();
//	}
//
//	@Override
//	public Object getCredentials() {
//		
//		return null;
//	}
//
//	@Override
//	public Object getDetails() {
//		return userDetail;
//	}
//
//	@Override
//	public Object getPrincipal() {
//		
//		return userDetail;
//	}
//
//	@Override
//	public boolean isAuthenticated() {
//		return isAuth;
//	}
//
//	@Override
//	public void setAuthenticated(boolean isAuthenticated)
//			throws IllegalArgumentException {
//		this.isAuth=isAuthenticated;
//	}

}
