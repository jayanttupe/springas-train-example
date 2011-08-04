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
import com.skynet.spms.manager.customerService.RepairService.contract.CusInspectOutlayRecordItemManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerInspectOutlayRecordItem;

/**
 * 送修记录明细业务实现类
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Service
@Transactional
public class CusInspectOutlayRecordItemManagerImpl extends HibernateDaoSupport
		implements CusInspectOutlayRecordItemManager {

	private final static String HQL = "select o from CustomerInspectOutlayRecordItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加送修记录明细
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addInspectOutlayRecordItem(CustomerInspectOutlayRecordItem o) {
		o.setAmount(o.getUnitOfPrice() * o.getQuantity());
		getHibernateTemplate().save(o);
	}

	/**
	 * 更新送修记录明细
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerInspectOutlayRecordItem
	 */
	public CustomerInspectOutlayRecordItem updateInspectOutlayRecordItem(
			Map<String, Object> newValues, String itemID) {
		CustomerInspectOutlayRecordItem record = getHibernateTemplate().get(
				CustomerInspectOutlayRecordItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(record, newValues);
		getHibernateTemplate().update(record);
		return record;
	}

	/**
	 * 删除送修记录明细
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteInspectOutlayRecordItem(String itemID) {
		String hql = "update CustomerInspectOutlayRecordItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据送修记录编号查询送修记录明细
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerInspectOutlayRecordItem
	 */
	public CustomerInspectOutlayRecordItem getInspectOutlayRecordItemById(
			String sheetId) {
		return (CustomerInspectOutlayRecordItem) getSession().get(
				CustomerInspectOutlayRecordItem.class, sheetId);
	}

	/**
	 * 根据送修记录编号查询送修记录明细
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerInspectOutlayRecordItem
	 */
	public List<CustomerInspectOutlayRecordItem> queryInspectOutlayRecordItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据送修记录编号分页查询送修记录明细
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @param recordId
	 * @param @return
	 * @return List<CustomerInspectOutlayRecordItem>
	 */
	public List<CustomerInspectOutlayRecordItem> queryInspectOutlayRecordItemListByRecordId(
			int startRow, int endRow, String recordId) {
		String hql = HQL
				+ " where o.deleted=false and o.inspectOutlayRecordId=? ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		query.setParameter(0, recordId);
		return query.list();
	}

}
