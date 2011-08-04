package com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProcurementPlanManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.ProcurementPlan;

/**
 * 采购计划实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class ProcurementPlanImpl extends HibernateDaoSupport implements
		ProcurementPlanManager {

	private String HQL = "select o from ProcurementPlan o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 添加采购计划
	 * 
	 * @param o
	 *            采购计划对象
	 * @return void
	 */
	public void addProcurementPlan(ProcurementPlan o) {

		getHibernateTemplate().save(o);
	}

	/**
	 * 
	 * 删除采购计划的方法
	 * 
	 * @param id
	 *            采购计划ID
	 * @return void
	 */
	public void deleteProcurementPlan(String id) {
		String hql = "update ProcurementPlan set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();

	}

	/**
	 * 
	 * 修改采购计划
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购计划ID
	 * @return 采购计划对象
	 */
	public ProcurementPlan updateProcurementPlan(Map<String, Object> newValues,
			String itemID) {

		ProcurementPlan entity = new ProcurementPlan();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 
	 * 分页查询采购计划的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划对象集合
	 */
	public List<ProcurementPlan> queryProcurementPlanList(int startRow,
			int endRow) {

		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 
	 * 根据ID查询采购计划
	 * 
	 * @param id
	 *            采购几乎ID
	 * @return 采购计划对象集合
	 */

	@Override
	public List<ProcurementPlan> queryProcurementById(String id) {
		String hql = HQL
				+ " where o.deleted=false and o.id=? order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		return query.list();
	}

}
