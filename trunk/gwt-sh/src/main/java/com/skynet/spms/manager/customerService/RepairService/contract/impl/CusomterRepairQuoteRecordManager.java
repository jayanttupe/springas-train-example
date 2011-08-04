package com.skynet.spms.manager.customerService.RepairService.contract.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.RepairService.contract.CusRepairQuoteRecordManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerRepairQuoteRecord;

@Service
@Transactional
public class CusomterRepairQuoteRecordManager extends HibernateDaoSupport
		implements CusRepairQuoteRecordManager {

	private final static String HQL = "select o from CustomerRepairQuoteRecord o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addRepairQuoteRecord(CustomerRepairQuoteRecord o) {
		getHibernateTemplate().save(o);
	}

	public CustomerRepairQuoteRecord updateRepairQuoteRecord(
			Map<String, Object> newValues, String itemID) {
		CustomerRepairQuoteRecord record = getHibernateTemplate().get(
				CustomerRepairQuoteRecord.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	public void deleteRepairQuoteRecord(String itemID) {
		String hql = "update CustomerRepairQuoteRecord set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public List<CustomerRepairQuoteRecord> queryRepairQuoteRecordList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	public CustomerRepairQuoteRecord getRepairQuoteRecordById(String sheetId) {
		return (CustomerRepairQuoteRecord) getSession().get(
				CustomerRepairQuoteRecord.class, sheetId);
	}

	public List<CustomerRepairQuoteRecord> getRepairQuoteRecordByContractID(
			String contractID) {
		String hql = HQL
				+ " where o.deleted=false and o.supplierSupportContractId=? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, contractID);
		return query.list();
	}

}
