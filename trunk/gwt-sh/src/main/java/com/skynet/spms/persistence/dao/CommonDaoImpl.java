package com.skynet.spms.persistence.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.CommonDao;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

@Service
public class CommonDaoImpl implements  CommonDao {

	private Logger log = LoggerFactory.getLogger(CommonDaoImpl.class);

	@Autowired
	private SessionFactory factory;

	@Override	
	public Session getClearSession() {
		return factory.getCurrentSession();
	}


	@Override
	public Session getSession() {
		Session session = factory.getCurrentSession();

		log.info("filter:" + factory.getDefinedFilterNames());

		session.enableFilter("active").setParameter("isDele", false);

		return session;
	}


	@Override
	public  <T extends BaseEntity> void insertEntity(T entity) {
		// TODO Auto-generated method stub
		log.info("do insert " + entity.getClass().getName());
		getSession().save(entity);

	}


	@Override
	public <T extends BaseEntity> void deleteEntity(String entityId, Class<T> cls) {
		log.info("do delete " + cls.getName());
		T entity = cls.cast(getSession().get(cls, entityId));

		entity.setDeleted(true);
		// getSession().delete(entity);

	}


	@Override
	public <T extends BaseEntity> T updateEntity(Map<String,Object> map, String entityId, Class<T> cls) {
		T entity = (T) getSession().get(cls, entityId);
		BeanPropUtil.fillEntityWithMap(entity, map);
		getSession().merge(entity);
		return entity;
	}


	@Override
	public <T extends BaseEntity>  T queryById(String entityId, Class<T> cls) {
		log.info("do query by id " + cls.getName());

		T entity = cls.cast(getSession().get(cls, entityId));
		return entity;
	}


	@Override
	public  <T extends BaseEntity>  List<T> list(int startRow, int endRow,
			Class<T> cls) {
		log.info("do query all " + cls.getName());

		Criteria criteria = getSession().createCriteria(cls);
		if (endRow > 0) {
			criteria.setFirstResult(startRow);
			criteria.setMaxResults(endRow - startRow);
		}

		List list= criteria.list();
		
		return list.subList(startRow, list.size());
	}


	@Override
	public <T extends BaseEntity> List<T> queryByBean(T query, int startRow,
			int endRow) {

		Criteria criteria = getSession().createCriteria(query.getClass());

		Example example = Example.create(query).excludeZeroes().enableLike()
				.enableLike(MatchMode.ANYWHERE);
		criteria.add(example);
		log.info("do query list:" + query.getClass().getName());
		if (endRow > 0) {
			criteria.setFirstResult(startRow).setMaxResults(endRow - startRow);
		}
		List<T> result = criteria.list();
		return result;

	}


	@Override
	public FullTextSession getFullTextSession() {

		Session session = factory.getCurrentSession();

		FullTextSession textSession = Search.getFullTextSession(session);

		return textSession;

	}

	

}
