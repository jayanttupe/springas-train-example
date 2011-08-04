package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.cxf.common.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.IPickupDeliveryOrderManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 提货发货指令业务实现类
 * 
 * @author tqc
 * 
 */
@Service
@Transactional
public class PickupDeliveryOrderManager extends HibernateDaoSupport implements
		IPickupDeliveryOrderManager {
	/** 标准查询hql **/
	private final static String HQL = "select o from PickupDeliveryOrder o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	public void addPickupDeliveryOrder(PickupDeliveryOrder o) {
		String numberType = "";
		if ("1".equals(o.getPickupDeliveryOrderType())) {// 1:提货
			numberType = "RI";
		} else {// 发货
			numberType = "SI";
		}
		o.setOrderNumber(uuidGeneral.getSequence(numberType));
		o.setCreateBy(GwtActionHelper.getCurrUser());
		o.setCreateDate(new Date());
		getHibernateTemplate().save(o);
	}

	public PickupDeliveryOrder updatePickupDeliveryOrder(
			Map<String, Object> newValues, String itemID) {
		PickupDeliveryOrder o =getHibernateTemplate().get(PickupDeliveryOrder.class, itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		return o;
	}

	public void deletePickupDeliveryOrder(String itemID) {
		String hql = "update PickupDeliveryOrder set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public PickupDeliveryOrder getPickupDeliveryOrderById(String sheetId) {
		return (PickupDeliveryOrder) getSession().get(
				PickupDeliveryOrder.class, sheetId);
	}

	public List<PickupDeliveryOrder> queryPickupDeliveryOrderList(int startRow,
			int endRow, String type, String businessType) {
		String hql = HQL
				+ " where o.deleted=false and o.pickupDeliveryOrderType=?";
		if (!StringUtils.isEmpty(businessType)) {
			hql+=" and o.businessType=? ";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, type);
		if (!StringUtils.isEmpty(businessType)) {
			query.setParameter(1, businessType);
		}
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
