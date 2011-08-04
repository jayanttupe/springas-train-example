package com.skynet.spms.manager.customerService.BuybackService.BuybackContract.impl;

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
import com.skynet.spms.manager.customerService.BuybackService.BuybackContract.IBuybackPactItemManager;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuyBackPact.BuybackPactItem;

/**
 * 
 * 描述：回购合同明细操作类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class BuybackPactItemManager extends HibernateDaoSupport implements
		IBuybackPactItemManager {

	String hql = "select e from BuybackPactItem e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void addSheet(BuybackPactItem item) {
		item.setCreateDate(new Date());
		String creatBy = item.getCreateBy();
		if (creatBy == null || "".equals(creatBy)) {
			item.setCreateBy(GwtActionHelper.getCurrUser());
		}
		getHibernateTemplate().save(item);
		updateAmount(item.getTemplate().getId());
	}

	@Override
	public BuybackPactItem getById(String id) {
		BuybackPactItem sheet = (BuybackPactItem) getSession().get(
				BuybackPactItem.class, id);
		return sheet;
	}

	@Override
	public BuybackPactItem update(Map<String, Object> newValues, String itemID) {
		BuybackPactItem entity = (BuybackPactItem) getSession().get(
				BuybackPactItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getTemplate().getId());
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuybackPactItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
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
		System.out.println("doQuery 执行sql" + hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuybackPactItem> getList(int startRow, int endRow) {
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
		BuybackPactItem entity = (BuybackPactItem) getSession().get(
				BuybackPactItem.class, itemID);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getTemplate().getId());
	}

	/**
	 * 更新金额
	 * 
	 * @param id
	 */
	private void updateAmount(String id) {
		String hql = "select count(o.id),sum(o.price) from BuybackPactItem o where o.deleted=false and o.template.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		Object[] obj = (Object[]) query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update BuybackContractTemplate set extendedValueTotalAmount="
				+ amount + ",totalCount=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}
}
