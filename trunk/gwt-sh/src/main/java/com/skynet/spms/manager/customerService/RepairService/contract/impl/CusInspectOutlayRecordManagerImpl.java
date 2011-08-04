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
import com.skynet.spms.manager.customerService.RepairService.contract.CusInspectOutlayRecordManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerInspectOutlayRecord;

/**
 * 客户送修记录业务实现类
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Service
@Transactional
public class CusInspectOutlayRecordManagerImpl extends HibernateDaoSupport
		implements CusInspectOutlayRecordManager {

	private final static String HQL = "select o from CustomerInspectOutlayRecord o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加客户送修记录
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addInspectOutlayRecord(CustomerInspectOutlayRecord o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 更新 客户送修记录
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerInspectOutlayRecord
	 */
	public CustomerInspectOutlayRecord updateInspectOutlayRecord(
			Map<String, Object> newValues, String itemID) {
		CustomerInspectOutlayRecord record = getHibernateTemplate().get(
				CustomerInspectOutlayRecord.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	/**
	 * 删除客户送修记录
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteInspectOutlayRecord(String itemID) {
		String hql = "update CustomerInspectOutlayRecord set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 分页查询送修记录
	 * 
	 * @param @param startRow 当前页的索引
	 * @param @param endRow 页大小
	 * @param @return
	 * @return List<CustomerInspectOutlayRecord>
	 */
	public List<CustomerInspectOutlayRecord> queryInspectOutlayRecordList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据编号查询送修记录
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerInspectOutlayRecord
	 */
	public CustomerInspectOutlayRecord getInspectOutlayRecordById(String sheetId) {
		return (CustomerInspectOutlayRecord) getSession().get(
				CustomerInspectOutlayRecord.class, sheetId);
	}

	/**
	 * 根据合同编号查询送修记录
	 * 
	 * @param @param contractId
	 * @param @return
	 * @return List<CustomerInspectOutlayRecord>
	 */
	public List<CustomerInspectOutlayRecord> getInspectOutlayRecordByContractId(
			String supplierSupportContractId) {
		String hql = HQL
				+ " where o.deleted=false and o.supplierSupportContractId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, supplierSupportContractId);
		return query.list();
	}

}
