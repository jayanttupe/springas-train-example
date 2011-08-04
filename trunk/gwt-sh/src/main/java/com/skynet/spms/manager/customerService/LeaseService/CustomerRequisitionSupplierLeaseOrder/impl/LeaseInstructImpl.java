package com.skynet.spms.manager.customerService.LeaseService.CustomerRequisitionSupplierLeaseOrder.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.LeaseService.CustomerRequisitionSupplierLeaseOrder.LeaseInstructManager;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate.LeaseContractTemplate;
import com.skynet.spms.persistence.entity.customerService.order.requisitionSupplierLeaseOrder.CustomerRequisitionSupplierLeaseOrder;

/**
 * 客户申请供应商租赁指令实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class LeaseInstructImpl extends HibernateDaoSupport implements
		LeaseInstructManager {
	private String HQL = "select o from CustomerRequisitionSupplierLeaseOrder o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加客户申请供应商租赁指令的方法
	 * 
	 * @param o
	 *            客户申请供应商租赁指令对象
	 */
	public void addLeaseInstruct(CustomerRequisitionSupplierLeaseOrder o) {
		LeaseContractTemplate e = getHibernateTemplate().get(
				LeaseContractTemplate.class,
				o.getM_LeaseContractTemplate().getId());
		o.setM_LeaseContractTemplate(e);
		getHibernateTemplate().save(o);
	}

	/**
	 * 删除客户申请供应商租赁指令的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseInstruct(String id) {
		String hql = "update CustomerRequisitionSupplierLeaseOrder set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	/**
	 * 修改客户申请供应商租赁指令的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public CustomerRequisitionSupplierLeaseOrder updateleaseInstruct(
			Map<String, Object> newValues, String itemID) {

		CustomerRequisitionSupplierLeaseOrder entity = new CustomerRequisitionSupplierLeaseOrder();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 分页查询客户申请供应商租赁指令的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	@SuppressWarnings("unchecked")
	public List<CustomerRequisitionSupplierLeaseOrder> queryLeaseInstructList(
			int startRow, int endRow) {

		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 分页查询客户申请供应商租赁指令已发布的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	@SuppressWarnings("unchecked")
	public List<CustomerRequisitionSupplierLeaseOrder> querySsLeaseInstructList(
			int startRow, int endRow) {
		String hql = HQL
				+ " where o.m_BussinessPublishStatusEntity.m_PublishStatus='published' and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
