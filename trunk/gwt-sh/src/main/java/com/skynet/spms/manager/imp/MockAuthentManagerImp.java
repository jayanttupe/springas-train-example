package com.skynet.spms.manager.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.skynet.spms.web.form.SpmsUserDetail;

public class MockAuthentManagerImp implements UserDetailsService{
	
	@Autowired
	private SaltSource saltSource;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
	
		if(username.contains("_")){
			
			SpmsUserDetail userInfo=   new SpmsUserDetail(username);
			
			Object salt=saltSource.getSalt(userInfo);
			String encodePwd=pwdEncoder.encodePassword(userInfo.getPassword(), salt);
			userInfo.setPassword(encodePwd);
			return userInfo;
		}else{
			throw new UsernameNotFoundException(username);
		}
	}	
}
