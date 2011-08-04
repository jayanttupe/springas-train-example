package com.skynet.spms.manager.supplierSupport.repairClaim.InspectOutlayRecord.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.repairClaim.InspectOutlayRecord.IInspectOutlayRecordItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecordItem;

@Service
@Transactional
public class InspectOutlayRecordItemManager extends HibernateDaoSupport
		implements IInspectOutlayRecordItemManager {

	private final static String HQL = "select o from SupplierSupportInspectOutlayRecordItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addInspectOutlayRecordItem(InspectOutlayRecordItem o) {
		getHibernateTemplate().save(o);
	}

	public InspectOutlayRecordItem updateInspectOutlayRecordItem(
			Map<String, Object> newValues, String itemID) {
		InspectOutlayRecordItem record=getHibernateTemplate().get(InspectOutlayRecordItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	public void deleteInspectOutlayRecordItem(String itemID) {
		String hql = "update SupplierSupportInspectOutlayRecordItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public InspectOutlayRecordItem getInspectOutlayRecordItemById(String sheetId) {
		return (InspectOutlayRecordItem) getSession().get(
				InspectOutlayRecordItem.class, sheetId);
	}

	public List<InspectOutlayRecordItem> queryInspectOutlayRecordItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	public List<InspectOutlayRecordItem> queryInspectOutlayRecordItemListByRecordId(
			int startRow, int endRow, String recordId) {
		String hql = HQL + " where o.deleted=false and o.inspectOutlayRecordId=? ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		query.setParameter(0, recordId);
		return query.list();
	}

}
