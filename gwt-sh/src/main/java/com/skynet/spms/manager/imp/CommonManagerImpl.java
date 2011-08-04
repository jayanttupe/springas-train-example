package com.skynet.spms.manager.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.CommonDao;
import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

@Transactional
public abstract class CommonManagerImpl<T extends BaseEntity> implements CommonManager<T> {

	private Logger log=LoggerFactory.getLogger(CommonManagerImpl.class);
	@Autowired
	private SessionFactory factory;
	
	protected  Session getSession(){
		Session session= factory.getCurrentSession();
		
		log.info("filter:"+factory.getDefinedFilterNames());
		
		session.enableFilter("active").setParameter("isDele", false);
		
		return session;
	}
	
	
	@Autowired
	private CommonDao commDao;
	
	@Override
	public void deleteEntity(String entityId, Class<T> cls) {
		commDao.deleteEntity(entityId, cls);
	}

	@Override
	public T updateEntity(Map<String, Object> entity, String entityId,
			Class<T> cls) {
		return (T) commDao.updateEntity(entity, entityId, cls);
	}

	@Override
	public void insertEntity(T entity) {
		commDao.insertEntity(entity);
	}

	@Override
	public T queryById(String entityId, Class<T> cls) {
		return (T) commDao.queryById(entityId, cls);
	}

	@Override
	public List<T> list(int startRow, int endRow, Class<T> cls) {
		return (List<T>) commDao.list(startRow, endRow, cls);
	}

	@Override
	public List<T> queryByBean(T query, int startRow,
			int endRow) {
		
		return (List<T>) commDao.queryByBean(query, startRow, endRow);
	}

}
