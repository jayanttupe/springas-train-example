package com.skynet.spms.manager.supplierSupport.repairClaim.RepairQuoteRecord.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.repairClaim.RepairQuoteRecord.IRepairQuoteRecordItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecordItem.RepairQuoteRecordItem;

@Service
@Transactional
public class RepairQuoteRecordItemManager extends HibernateDaoSupport
		implements IRepairQuoteRecordItemManager {

	private final static String HQL = "select o from RepairQuoteRecordItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addRepairQuoteRecordItem(RepairQuoteRecordItem o) {
		getHibernateTemplate().save(o);
	}

	public RepairQuoteRecordItem updateRepairQuoteRecordItem(
			Map<String, Object> newValues, String itemID) {
		RepairQuoteRecordItem record=getHibernateTemplate().get(RepairQuoteRecordItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	public void deleteRepairQuoteRecordItem(String itemID) {
		String hql = "update RepairQuoteRecordItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public RepairQuoteRecordItem getRepairQuoteRecordItemById(String sheetId) {
		return (RepairQuoteRecordItem) getSession().get(
				RepairQuoteRecordItem.class, sheetId);
	}

	public List<RepairQuoteRecordItem> queryRepairQuoteRecordItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	public List<RepairQuoteRecordItem> queryRepairQuoteRecordItemListByRecordId(
			int startRow, int endRow, String recordId) {
		String hql = HQL + " where o.deleted=false and o.repairQuoteRecordId=? ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		query.setParameter(0, recordId);
		return query.list();
	}

}
