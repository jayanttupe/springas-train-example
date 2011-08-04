package com.skynet.spms.manager.customerService.salesService.salesContract.impl;

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
import com.skynet.spms.manager.customerService.salesService.salesContract.ISalesContractItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesContract.SalesContractItem;

/**
 * 
 * 描述：销售合同明细业务实现类
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class SalesContractItemManager extends HibernateDaoSupport implements
		ISalesContractItemManager {

	private final static String HQL = "select o from SalesContractItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addSalesContractItem(SalesContractItem item) {
		item.setCreateDate(new Date());
		item.setCreateBy(GwtActionHelper.getCurrUser());
		getHibernateTemplate().save(item);
		updateAmount(item.getSalesTemplate().getId());
	}

	/**
	 * 更新合同中的金额
	 * 
	 * @param pactid
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id),sum(o.price) from SalesContractItem o where o.deleted=false and o.salesTemplate.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object[] obj = (Object[])query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update SalesContractTemplate set extendedValueTotalAmount="
				+ amount + ",totalCount=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();
	}

	/** 修改销售合同 明细项 **/
	public SalesContractItem updateSalesContractItem(
			Map<String, Object> newValues, String itemID) {
		SalesContractItem o = getHibernateTemplate().get(
				SalesContractItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		updateAmount(o.getSalesTemplate().getId());
		return o;
	}

	/** 删除销售合同明细项 **/
	public void deleteSalesContractItem(String itemID) {
		SalesContractItem contractItem = getHibernateTemplate().get(
				SalesContractItem.class, itemID);
		contractItem.setDeleted(true);
		getHibernateTemplate().update(contractItem);
		updateAmount(contractItem.getSalesTemplate().getId());
	}

	public SalesContractItem getSalesContractItemById(String sheetId) {
		return (SalesContractItem) getSession().get(SalesContractItem.class,
				sheetId);
	}

	@SuppressWarnings("unchecked")
	public List<SalesContractItem> querySalesContractItemList(int startRow,
			int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	// 实现修改销售合同信息
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesContractItem> querySalesContractItemList(
			Map<String, Object> values, int startRow, int endRow) {
		Set<String> keys = values.keySet();
		String hql = HQL + " where o.deleted=false";
		for (String key : keys) {
			Object obj = values.get(key);
			if (obj instanceof String) {
				hql += " and " + key + "='" + values.get(key) + "'";
			} else {
				hql += " and " + key + "=" + values.get(key);
			}
		}
		hql += " order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		System.out.println("doQuery 执行sql" + hql);
		return query.list();
	}

}
