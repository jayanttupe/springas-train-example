package com.skynet.spms.manager.supplierSupport.procurement.ProcurementContract.impl;

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
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementContract.IProcurementContractItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementContract.ProcurementContractItem;

/**
 * 
 * 描述：供应商合同明细操作类
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class ProcurementContractItemManager extends HibernateDaoSupport
		implements IProcurementContractItemManager {

	String hql = "select e from ProcurementContractItem e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void addSheet(ProcurementContractItem item) {
		item.setCreateDate(new Date());
		String creatBy = item.getCreateBy();
		if (creatBy == null || "".equals(creatBy)) {
			item.setCreateBy(GwtActionHelper.getCurrUser());
		}
		getHibernateTemplate().save(item);
		String pactid = item.getTemplate().getId();
		updateAmount(pactid);
	}

	/**
	 * 更新合同中的采购金额
	 * 
	 * @param pactid
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id),sum(o.amount) from ProcurementContractItem o where o.deleted=false and o.template.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object[] obj = (Object[]) query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update ProcurementContractTemplate set extendedValueTotalAmount="
				+ amount + ",totalCount=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();
	}

	@Override
	public ProcurementContractItem getById(String id) {
		ProcurementContractItem sheet = (ProcurementContractItem) getSession()
				.get(ProcurementContractItem.class, id);
		return sheet;
	}

	@Override
	public ProcurementContractItem update(Map<String, Object> newValues,
			String itemID) {
		ProcurementContractItem entity = (ProcurementContractItem) getSession()
				.get(ProcurementContractItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getTemplate().getId());
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProcurementContractItem> doQuery(Map<String, Object> values,
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
	public List<ProcurementContractItem> getList(int startRow, int endRow) {
		String hql = this.hql
				+ " where e.deleted=false order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@Override
	public void delete(String itemID) {
		ProcurementContractItem entity = (ProcurementContractItem) getSession()
				.get(ProcurementContractItem.class, itemID);
		entity.setDeleted(true);
		getHibernateTemplate().saveOrUpdate(entity);
		updateAmount(entity.getTemplate().getId());
	}

}
