package com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProcurementPlanItem.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProcurementPlanItem.ProcurementPlanItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementPlanItem.ProcurementPlanItem;

/**
 * 采购计划明细接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class ProcurementPlanItemImpl extends HibernateDaoSupport implements
		ProcurementPlanItemManager {
	private String HQL = "select o from ProcurementPlanItem o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 添加采购计划明细
	 * 
	 * @param o
	 *            采购计划明细对象
	 * @return void
	 */
	public void addProcurementPlanItem(ProcurementPlanItem o) {
		getHibernateTemplate().save(o);
		updateAmount(o.getProcurementPlan().getId());
	}

	/**
	 * 
	 * 删除采购计划明细的方法
	 * 
	 * @param id
	 *            采购计划明细ID
	 * @return void
	 */
	public void deleteProcurementPlanItem(String id) {
		ProcurementPlanItem entity = (ProcurementPlanItem) getSession().get(
				ProcurementPlanItem.class, id);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getProcurementPlan().getId());
	}

	/**
	 * 
	 * 修改采购计划明细
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购计划明细ID
	 * @return 采购计划明细对象
	 */
	public ProcurementPlanItem updateProcurementPlanItem(
			Map<String, Object> newValues, String itemID) {

		ProcurementPlanItem entity = new ProcurementPlanItem();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getProcurementPlan().getId());
		return entity;

	}

	/**
	 * 
	 * 分页查询采购计划明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划明细对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementPlanItem> queryProcurementPlanItemList(int startRow,
			int endRow) {

		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 
	 * 根据ID查询采购计划明细
	 * 
	 * @param id
	 *            采购计划明细的ID
	 * @return 采购计划明细对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProcurementPlanItem> queryProcurementPlanItemListById(String id) {
		String hql = HQL
				+ " where o.procurementPlan.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		return query.list();
	}

	/**
	 * 
	 * 根据采购计划编号查询采购计划明细数据
	 * 
	 * @param procurementPlanNumber
	 *            采购计划编号
	 * @return 采购计划编号对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementPlanItem> queryProcurementOrderByprocurementPlanNumber(
			String procurementPlanNumber) {
		String hql = HQL
				+ " where o.procurementPlan.procurementPlanNumber=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, procurementPlanNumber);
		return query.list();
	}

	/**
	 * 更新主申请单中的金额
	 * 
	 * @param pactid
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id), sum(o.unitPriceAmount) from ProcurementPlanItem o where o.deleted=false and o.procurementPlan.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object[] obj = (Object[]) query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update ProcurementPlan set totalAmount=" + amount
				+ ",itemCount=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();

	}
}
