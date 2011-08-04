package com.skynet.spms.manager.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.CheckUniqueManager;

@Service
@Transactional
public class CheckUniqueManagerImpl implements CheckUniqueManager {

	private Logger log=LoggerFactory.getLogger(CheckUniqueManagerImpl.class);
	@Autowired
	private SessionFactory factory;
	
	protected  Session getSession(){
		Session session= factory.getCurrentSession();
		
		log.info("filter:"+factory.getDefinedFilterNames());
		
		session.enableFilter("active").setParameter("isDele", false);
		
		return session;
	}
	@Override
	public Boolean isUnique(String entityClassName, String fieldName, String value) {
		Criteria criteria = getSession().createCriteria(entityClassName);
		criteria.add(Restrictions.eq(fieldName, value));
		List entityList = criteria.list();
		
		return entityList.size() == 0 ;
	}

	
}
