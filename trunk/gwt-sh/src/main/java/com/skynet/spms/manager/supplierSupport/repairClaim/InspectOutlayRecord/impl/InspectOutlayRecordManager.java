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
import com.skynet.spms.manager.supplierSupport.repairClaim.InspectOutlayRecord.IInspectOutlayRecordManager;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecord;

@Service
@Transactional
public class InspectOutlayRecordManager extends HibernateDaoSupport implements
		IInspectOutlayRecordManager {

	private final static String HQL = "select o from SupplierSupportInspectOutlayRecord o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addInspectOutlayRecord(InspectOutlayRecord o) {
		getHibernateTemplate().save(o);
	}

	public InspectOutlayRecord updateInspectOutlayRecord(
			Map<String, Object> newValues, String itemID) {
		InspectOutlayRecord record=getHibernateTemplate().get(InspectOutlayRecord.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	public void deleteInspectOutlayRecord(String itemID) {
		String hql = "update SupplierSupportInspectOutlayRecord set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public List<InspectOutlayRecord> queryInspectOutlayRecordList(int startRow,
			int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	public InspectOutlayRecord getInspectOutlayRecordById(String sheetId) {
		return (InspectOutlayRecord) getSession().get(
				InspectOutlayRecord.class, sheetId);
	}

	public List<InspectOutlayRecord> getInspectOutlayRecordByContractId(
			String supplierSupportContractId) {
		String hql = HQL + " where o.deleted=false and o.supplierSupportContractId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, supplierSupportContractId);
		return query.list();
	}

}
