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
import com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder.PlanProcurementOrderManager;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrder;

/**
 * 采购指令实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class PlanProcurementOrderImpl extends HibernateDaoSupport implements
		PlanProcurementOrderManager {

	private String HQL = "select o from ProcurementOrder o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 添加采购指令
	 * 
	 * @param o
	 *            采购指令对象
	 * @return void
	 */
	public void addProcurementOrder(ProcurementOrder o) {
		getHibernateTemplate().save(o);

	}

	/**
	 * 
	 * 删除采购指令的方法
	 * 
	 * @param id
	 *            采购指令ID
	 * @return void
	 */
	public void deleteProcurementOrder(String id) {
		String hql = "update ProcurementOrder set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();

	}

	/**
	 * 
	 * 修改采购指令
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购指令ID
	 * @return 采购指令对象
	 */
	public ProcurementOrder updateProcurementOrder(
			Map<String, Object> newValues, String itemID) {

		ProcurementOrder entity = new ProcurementOrder();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 
	 * 分页查询采购指令的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购指令对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementOrder> queryProcurementOrderList(int startRow,
			int endRow) {

		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 
	 * 分页查询执行采购指令的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购指令对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementOrder> queryDoProcuremetnOrderList(int startRow,
			int endRow) {
		String hql = HQL
				+ " where o.m_BussinessPublishStatusEntity.m_PublishStatus='published' and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
