package com.skynet.spms.manager.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheetItem.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheetItem.GuaranteeClaimRequisitionSheetItemManager;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheetItem.BuybackRequisitionSheetItem;
import com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheetItem.GuaranteeClaimRequisitionSheetItem;

/**
 * 
 * 描述：担保索赔申请单明细操作类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class GuaranteeClaimRequisitionSheetItemImpl extends HibernateDaoSupport
		implements GuaranteeClaimRequisitionSheetItemManager {

	String hql = "select e from GuaranteeClaimRequisitionSheetItem e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void addSheet(GuaranteeClaimRequisitionSheetItem sheet) {
		getHibernateTemplate().save(sheet);

	}

	@Override
	public GuaranteeClaimRequisitionSheetItem getById(String id) {
		GuaranteeClaimRequisitionSheetItem sheet = (GuaranteeClaimRequisitionSheetItem) getSession()
				.get(BuybackRequisitionSheetItem.class, id);
		return sheet;
	}

	@Override
	public GuaranteeClaimRequisitionSheetItem update(
			Map<String, Object> newValues, String itemID) {
		GuaranteeClaimRequisitionSheetItem entity = (GuaranteeClaimRequisitionSheetItem) getSession()
				.get(GuaranteeClaimRequisitionSheetItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteeClaimRequisitionSheetItem> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		Set<String> keys = values.keySet();
		String hql = this.hql + " where e.deleted=false";
		for (String key : keys) {
			Object obj = values.get(key);
			if (obj instanceof String) {
				hql += " and e." + key + "='" + values.get(key) + "'";
			} else {
				hql += " and e." + key + "=" + values.get(key);
			}
		}
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteeClaimRequisitionSheetItem> getList(int startRow,
			int endRow) {
		String hql = this.hql + " where e.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@Override
	public void delete(String itemID) {
		String hql = "update GuaranteeClaimRequisitionSheetItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<GuaranteeClaimRequisitionSheetItem> getItemListBySheetId(
	// String sheetId) {
	// String hql = this.hql + " where e.sheet.id=? and e.deleted=false";
	// Query query = getSession().createQuery(hql);
	// query.setParameter(0, sheetId);
	// return query.list();
	// }

}
