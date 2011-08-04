package com.skynet.spms.manager.supplierSupport.consignment.consignRenew.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.consignment.consignRenew.IConsignRenewAddressManager;
import com.skynet.spms.persistence.entity.base.AddressInfo;

/**
 * 
 * 描述：寄售补库地址信息操作类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class ConsignRenewAddressManagerImpl extends HibernateDaoSupport
		implements IConsignRenewAddressManager {
	String hql = "select e from AddressInfo e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addAddress(AddressInfo form) {
		form.setCreateDate(new Date());
		String creatBy = form.getCreateBy();
		if (creatBy == null || "".equals(creatBy)) {
			form.setCreateBy(GwtActionHelper.getCurrUser());
		}
		getHibernateTemplate().save(form);
	}

	@Override
	public AddressInfo getById(String uuid) {
		return getHibernateTemplate().get(AddressInfo.class, uuid);
	}

	@Override
	public AddressInfo update(Map<String, Object> newValues, String itemID) {
		AddressInfo entity = (AddressInfo) getSession().get(AddressInfo.class,
				itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		if (itemID == null) {
			addAddress(entity);
		} else {
			getHibernateTemplate().update(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressInfo> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		Set<String> keys = values.keySet();
		String hql = this.hql + " where e.deleted=false";
		for (String key : keys) {
			Object obj = values.get(key);
			if (obj instanceof String) {
				hql += " and " + key + "='" + values.get(key) + "'";
			} else {
				hql += " and " + key + "=" + values.get(key);
			}
		}
		hql += " order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressInfo> getList(int startRow, int endRow) {
		String hql = this.hql
				+ " where e.deleted=false order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		System.out.println("getList 执行sql" + hql);
		return query.list();
	}

	@Override
	public void delete(String itemID) {
		String hql = "update AddressInfo set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

}
