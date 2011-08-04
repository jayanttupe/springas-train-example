package com.skynet.spms.manager.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.CommDsManager;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

@Service
@Transactional
class CommDsManagerImp  implements CommDsManager<BaseEntity> {

	private Logger log = LoggerFactory.getLogger(CommDsManagerImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.manager.imp.CommDsMang#queryByBean(com.skynet.spms.
	 * persistence.entity.PersistenEntity, int, int)
	 */
	@Override
	public List<BaseEntity> queryByBean(BaseEntity query,
			int startRow, int endRow) {
		Criteria criteria = getSession().createCriteria(query.getClass());

		Example example = Example.create(query).excludeZeroes().enableLike()
				.enableLike(MatchMode.ANYWHERE);
		criteria.add(example);
		log.info("do query list:" + query.getClass().getName());
		if (endRow > 0) {
			criteria.setFirstResult(startRow).setMaxResults(endRow - startRow);
		}
		List<BaseEntity> result = criteria.list();
		return result;
	}

	@Override
	public void deleteEntity(String itemID, Class<BaseEntity> cls) {
		log.info("do delete " + cls.getName());
		Object entity = getSession().get(cls, itemID);
		getSession().delete(entity);
	}

	@Override
	public BaseEntity updateEntity(Map map, String itemID, Class cls) {
		BaseEntity entity = (BaseEntity) getSession()
				.get(cls, itemID);

		log.info("update " + cls.getName());

		BeanPropUtil.fillEntityWithMap(entity, map);
		getSession().persist(entity);

		return entity;
	}

	@Override
	public void insertEntity(BaseEntity info) {
		getSession().save(info);
	}

	@Override
	public List<BaseEntity> list(int startRow, int endRow, Class<BaseEntity> cls) {
		log.info("do list:"+cls.getName());
		
		 Criteria criteria=getSession().createCriteria(cls);
		 if(endRow>0){
		 criteria.setFirstResult(startRow)
		 .setMaxResults(endRow-startRow);
		 }
		 return criteria.list();
	}	

}