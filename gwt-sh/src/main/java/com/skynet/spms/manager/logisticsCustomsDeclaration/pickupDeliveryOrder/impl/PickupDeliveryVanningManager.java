package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.IPickupDeliveryVanningManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;

/**
 * 装箱业务实现类
 * 
 * @author tqc
 * 
 */
@Service
@Transactional
public class PickupDeliveryVanningManager extends HibernateDaoSupport implements
		IPickupDeliveryVanningManager {

	private final static String HQL = "select o from PickupDeliveryVanning o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addPickupDeliveryVanning(PickupDeliveryVanning o) {
		o.setCreateBy(GwtActionHelper.getCurrUser());
		o.setCreateDate(new Date());
		getHibernateTemplate().save(o);
	}

	public PickupDeliveryVanning updatePickupDeliveryVanning(
			Map<String, Object> newValues, String itemID) {
		PickupDeliveryVanning o =getHibernateTemplate().get(PickupDeliveryVanning.class, itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		o.setCreateBy(GwtActionHelper.getCurrUser());
		o.setCreateDate(new Date());
		getHibernateTemplate().update(o);
		return o;
	}

	public void deletePickupDeliveryVanning(String itemID) {
		String hql = "update PickupDeliveryVanning set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public PickupDeliveryVanning getPickupDeliveryVanningById(String sheetId) {
		return (PickupDeliveryVanning) getSession().get(
				PickupDeliveryVanning.class, sheetId);
	}

	public List<PickupDeliveryVanning> queryPickupDeliveryVanningList(
			int startRow, int endRow, String orderID) {
		String hql = HQL + " where o.deleted=false and o.orderID=?";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		query.setParameter(0, orderID);
		return query.list();
	}

}
