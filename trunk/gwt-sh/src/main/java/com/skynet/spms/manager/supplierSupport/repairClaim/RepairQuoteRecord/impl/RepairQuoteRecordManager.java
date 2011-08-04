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
import com.skynet.spms.manager.supplierSupport.repairClaim.RepairQuoteRecord.IRepairQuoteRecordManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecord;

@Service
@Transactional
public class RepairQuoteRecordManager extends HibernateDaoSupport implements
		IRepairQuoteRecordManager {

	private final static String HQL = "select o from RepairQuoteRecord o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addRepairQuoteRecord(RepairQuoteRecord o) {
		getHibernateTemplate().save(o);
	}

	public RepairQuoteRecord updateRepairQuoteRecord(
			Map<String, Object> newValues, String itemID) {
		RepairQuoteRecord record=getHibernateTemplate().get(RepairQuoteRecord.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	public void deleteRepairQuoteRecord(String itemID) {
		String hql = "update RepairQuoteRecord set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public List<RepairQuoteRecord> queryRepairQuoteRecordList(int startRow,
			int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	public RepairQuoteRecord getRepairQuoteRecordById(String sheetId) {
		return (RepairQuoteRecord) getSession().get(
				RepairQuoteRecord.class, sheetId);
	}

	public List<RepairQuoteRecord> getRepairQuoteRecordByContractID(String contractID) {
		String hql=HQL+" where o.deleted=false and o.supplierSupportContractId=? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, contractID);
		return query.list();
	}

}
