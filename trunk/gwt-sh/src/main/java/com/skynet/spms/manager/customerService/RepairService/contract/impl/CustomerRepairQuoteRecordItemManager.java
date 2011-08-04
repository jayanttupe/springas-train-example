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
import com.skynet.spms.manager.customerService.RepairService.contract.CusRepairQuoteRecordItemManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerRepairQuoteRecordItem;
/**
 * 供应商检修记录业务实现类
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Service
@Transactional
public class CustomerRepairQuoteRecordItemManager extends HibernateDaoSupport
		implements CusRepairQuoteRecordItemManager {

	private final static String HQL = "select o from CustomerRepairQuoteRecordItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加供应商检修记录
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addRepairQuoteRecordItem(CustomerRepairQuoteRecordItem o) {
		o.setAmount(o.getUnitOfPrice() * o.getQuantity());
		getHibernateTemplate().save(o);
	}

	/**
	 * 更新供应商检修记录
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerRepairQuoteRecordItem
	 */
	public CustomerRepairQuoteRecordItem updateRepairQuoteRecordItem(
			Map<String, Object> newValues, String itemID) {
		CustomerRepairQuoteRecordItem record = getHibernateTemplate().get(
				CustomerRepairQuoteRecordItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	/**
	 * 删除供应商检修记录
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteRepairQuoteRecordItem(String itemID) {
		String hql = "update CustomerRepairQuoteRecordItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据编号查询供应商检修记录
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerRepairQuoteRecordItem
	 */
	public CustomerRepairQuoteRecordItem getRepairQuoteRecordItemById(
			String sheetId) {
		return (CustomerRepairQuoteRecordItem) getSession().get(
				CustomerRepairQuoteRecordItem.class, sheetId);
	}

	/**
	 * 分页查询供应商检修记录
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @return
	 * @return List<CustomerRepairQuoteRecordItem>
	 */
	public List<CustomerRepairQuoteRecordItem> queryRepairQuoteRecordItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据送修申请单编号查询客户检修记录
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @param recordId
	 * @param @return
	 * @return List<CustomerRepairQuoteRecordItem>
	 */
	public List<CustomerRepairQuoteRecordItem> queryRepairQuoteRecordItemListByRecordId(
			int startRow, int endRow, String recordId) {
		String hql = HQL
				+ " where o.deleted=false and o.repairQuoteRecordId=? ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		query.setParameter(0, recordId);
		return query.list();
	}

}
