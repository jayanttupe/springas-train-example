package com.skynet.spms.manager.supplierSupport.procurement.SafetyStockStrategy.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.SafetyStockStrategy.SafetyStockStrategyManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.SafetyStockStrategy.SafetyStockStrategy;

@Service
@Transactional
public class SafetyStockStrategyImpl extends HibernateDaoSupport implements
		SafetyStockStrategyManager {

	private String HQL = "select o from SafetyStockStrategy o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addSafetyStockStrategy(SafetyStockStrategy o) {
		getHibernateTemplate().save(o);
	}

	public void deleteSafetyStockStrategy(String id) {
		String hql = "update SafetyStockStrategy set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	public SafetyStockStrategy updateSafetyStockStrategy(
			Map<String, Object> newValues, String itemID) {

		SafetyStockStrategy entity = new SafetyStockStrategy();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	public List<SafetyStockStrategy> querySafetyStockStrategyList(int startRow,
			int endRow) {

		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
