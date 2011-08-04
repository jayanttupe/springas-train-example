package com.skynet.spms.manager.supplierSupport.consignment.ConsignmentContract.impl;

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
import com.skynet.spms.manager.supplierSupport.consignment.ConsignmentContract.IConsignProtocolItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.consignment.consignProtocol.ConsignmentAgreementItem;

/**
 * 
 * 描述：寄售协议明细操作类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class ConsignProtocolItemManager extends HibernateDaoSupport implements
		IConsignProtocolItemManager {

	String hql = "select e from ConsignmentAgreementItem e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void addSheet(ConsignmentAgreementItem item) {
		item.setCreateDate(new Date());
		String creatBy = item.getCreateBy();
		if (creatBy == null || "".equals(creatBy)) {
			item.setCreateBy(GwtActionHelper.getCurrUser());
		}
		getHibernateTemplate().save(item);
		updateAmount(item.getConsignId());
	}

	@Override
	public ConsignmentAgreementItem getById(String id) {
		ConsignmentAgreementItem sheet = (ConsignmentAgreementItem) getSession()
				.get(ConsignmentAgreementItem.class, id);
		return sheet;
	}

	@Override
	public ConsignmentAgreementItem update(Map<String, Object> newValues,
			String itemID) {
		ConsignmentAgreementItem entity = (ConsignmentAgreementItem) getSession()
				.get(ConsignmentAgreementItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getConsignId());
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsignmentAgreementItem> doQuery(Map<String, Object> values,
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
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsignmentAgreementItem> getList(int startRow, int endRow) {
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
		ConsignmentAgreementItem entity = (ConsignmentAgreementItem) getSession()
				.get(ConsignmentAgreementItem.class, itemID);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getConsignId());
	}

	/**
	 * 更新合同中的采购金额
	 * 
	 * @param pactid
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id),sum(o.price) from ConsignmentAgreementItem o where o.deleted=false and o.consignId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object[] obj = (Object[]) query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update ConsignmentAgreementTemplate set totalAmount=" + amount
				+ ",totalCount=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();
	}
}
