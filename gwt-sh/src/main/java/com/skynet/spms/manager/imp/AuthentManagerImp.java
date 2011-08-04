package com.skynet.spms.manager.imp;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.web.form.SpmsUserDetail;


@Transactional
public class AuthentManagerImp implements UserDetailsService{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private SaltSource saltSource;
	
	private Session getSession(){
		
		Session session=  sessionFactory.getCurrentSession();
		
		session.enableFilter("active").setParameter("isDele", false);

		return session;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		@SuppressWarnings("unchecked")
		User user=(User) getSession().createQuery(
				" from User where username = :username" )
		.setParameter("username", username)
		.uniqueResult();
		
		if(user==null){
			throw new UsernameNotFoundException(username+ " not found");
		}
							
		SpmsUserDetail userInfo= new SpmsUserDetail(user);
			
		Object salt=saltSource.getSalt(userInfo);
		String encodePwd=pwdEncoder.encodePassword(userInfo.getPassword(), salt);
		userInfo.setPassword(encodePwd);
		
		return userInfo;
		
	}

	

}
