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
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.IPickupDeliveryVanningItemsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItems;

/**
 * 提货发货指令明细业务实现类
 * 
 * @author tqc
 * 
 */
@Service
@Transactional
public class PickupDeliveryVanningItemsManager extends HibernateDaoSupport
		implements IPickupDeliveryVanningItemsManager {

	private final static String HQL = "select o from PickupDeliveryVanningItems o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addPickupDeliveryVanningItems(PickupDeliveryVanningItems o) {
		o.setCreateBy(GwtActionHelper.getCurrUser());
		o.setCreateDate(new Date());
		getHibernateTemplate().save(o);
	}

	public PickupDeliveryVanningItems updatePickupDeliveryVanningItems(
			Map<String, Object> newValues, String itemID) {
		PickupDeliveryVanningItems o = getHibernateTemplate().get(
				PickupDeliveryVanningItems.class, itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		o.setCreateBy(GwtActionHelper.getCurrUser());
		o.setCreateDate(new Date());
		getHibernateTemplate().update(o);
		return o;
	}

	public void deletePickupDeliveryVanningItems(String itemID) {
		String hql = "update PickupDeliveryVanningItems set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public PickupDeliveryVanningItems getPickupDeliveryVanningItemsById(
			String sheetId) {
		return (PickupDeliveryVanningItems) getSession().get(
				PickupDeliveryVanningItems.class, sheetId);
	}

	public List<PickupDeliveryVanningItems> queryPickupDeliveryVanningItemsList(
			int startRow, int endRow, String orderId) {
		String hql = HQL + " where o.deleted=false and o.orderID=? ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		query.setParameter(0, orderId);
		return query.list();
	}

}
