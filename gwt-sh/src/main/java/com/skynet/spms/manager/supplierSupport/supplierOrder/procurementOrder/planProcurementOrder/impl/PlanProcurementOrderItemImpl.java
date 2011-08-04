package com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder.PlanProcurementOrderItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrderItem;

/**
 * 采购指令明细实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class PlanProcurementOrderItemImpl extends HibernateDaoSupport implements
		PlanProcurementOrderItemManager {
	private String HQL = "select o from ProcurementOrderItem o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 添加采购指令明细
	 * 
	 * @param o
	 *            采购指令明细对象
	 * @return void
	 */
	public void addProcurementOrderItem(ProcurementOrderItem o) {
		getHibernateTemplate().save(o);
		updateAmount(o.getProcurementOrder().getId());
	}

	/**
	 * 
	 * 删除采购指令明细的方法
	 * 
	 * @param id
	 *            采购指令明细ID
	 * @return void
	 */
	public void deleteProcurementOrderItem(String id) {
		ProcurementOrderItem entity = (ProcurementOrderItem) getSession().get(
				ProcurementOrderItem.class, id);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getProcurementOrder().getId());
	}

	/**
	 * 
	 * 修改采购指令明细
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购指令明细ID
	 * @return 采购指令明细对象
	 */
	public ProcurementOrderItem updateProcurementOrderItem(
			Map<String, Object> newValues, String itemID) {

		ProcurementOrderItem entity = new ProcurementOrderItem();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getProcurementOrder().getId());
		return entity;
	}

	/**
	 * 
	 * 分页查询采购指令明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划明细对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementOrderItem> queryProcurementOrderItemList(
			int startRow, int endRow) {

		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 
	 * 根据ID查询采购指令明细
	 * 
	 * @param id
	 *            采购指令明细的ID
	 * @return 采购指令明细对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementOrderItem> queryProcurementOrderItemListById(
			String id) {
		String hql = HQL
				+ " where o.procurementOrder.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		return query.list();
	}

	/**
	 * 
	 * 根据采购指令编号查询采购指令明细数据
	 * 
	 * @param orderNumber
	 *            采购指令编号
	 * @return 采购指令编号对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementOrderItem> queryProcurementOrderListByOrderNumber(
			String orderNumber) {
		String hql = HQL
				+ " where o.procurementOrder.orderNumber=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, orderNumber);
		return query.list();
	}

	/**
	 * 更新主申请单中的金额
	 * 
	 * @param pactid
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id), sum(o.unitPriceAmount) from ProcurementOrderItem o where o.deleted=false and o.procurementOrder.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object[] obj = (Object[]) query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update ProcurementOrder set totalAmount=" + amount
				+ ",itemCount=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();
	}
}
