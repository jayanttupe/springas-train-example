package com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetItem.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetItem.LeaseRequisitionSheetItemManager;
import com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetItem;

/**
 * 租赁申请单明细实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-08
 */
@Service
@Transactional
public class LeaseRequisitionSheetItemImpl extends HibernateDaoSupport
		implements LeaseRequisitionSheetItemManager {
	private static final String HQL = "select o from LeaseRequisitionSheetItem o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加租赁申请单明细的方法
	 * 
	 * @param o
	 *            租赁申请单明细对象
	 */
	public void addLeaseRequisitionSheetItem(LeaseRequisitionSheetItem o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 删除租赁申请单明细的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseRequisitionSheetItem(String id) {
		String hql = "update  LeaseRequisitionSheetItem  set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();

	}

	/**
	 * 修改租赁申请单明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public LeaseRequisitionSheetItem updateLeaseRequisitionSheetItem(
			Map<String, Object> newValues, String itemID) {
		LeaseRequisitionSheetItem entity = new LeaseRequisitionSheetItem();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 分页查询租赁申请单的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	@SuppressWarnings("unchecked")
	public List<LeaseRequisitionSheetItem> queryLeaseRequisitionSheetItemList(
			int startRow, int endRow) {

		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据租赁申请单ID查询租赁申请单明细
	 * 
	 * @param leaseRequisitionSheetId
	 *            租赁申请单的ID
	 * @return 租赁申请单明细的对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LeaseRequisitionSheetItem> queryLeaseRequisitionSheetItemListByLeaseRequisitionSheetId(
			String leaseRequisitionSheetId) {
		String hql = HQL
				+ " where o.leaseRequisitionSheet.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, leaseRequisitionSheetId);
		return query.list();
	}

	/**
	 * 根据租赁申请单号查询租赁申请单明细
	 * 
	 * @param leaseNumber
	 *            租赁申请单号
	 * @return 租赁申请单明细的对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LeaseRequisitionSheetItem> queryLeaseRequisitionSheetItemListByLeaseNumber(
			String leaseNumber) {
		String hql = HQL
				+ " where o.leaseRequisitionSheet.requisitionSheetNumber=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, leaseNumber);
		return query.list();
	}

}
