package com.skynet.spms.manager.customerService.LeaseService.LeaseContract.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.LeaseService.LeaseContract.LeaseContracItemManager;
import com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract.LeaseContractItem;

/**
 * 租赁合同明细接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class LeaseContracItemImpl extends HibernateDaoSupport implements
		LeaseContracItemManager {
	private String HQL = "select o from LeaseContractItem o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加租赁合同明细的方法
	 * 
	 * @param o
	 *            租赁合同明细对象
	 */
	public void addLeaseContractItem(LeaseContractItem o) {
		getHibernateTemplate().save(o);
		updateAmount(o.getLeaseContractTemplate().getId());
	}

	/**
	 * 删除租赁合同明细的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseContractItem(String id) {
		LeaseContractItem entity = (LeaseContractItem) getSession().get(
				LeaseContractItem.class, id);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getLeaseContractTemplate().getId());
	}

	/**
	 * 修改租赁合同明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁合同明细对象
	 */
	public LeaseContractItem updateLeaseContractItem(
			Map<String, Object> newValues, String itemID) {
		LeaseContractItem entity = new LeaseContractItem();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getLeaseContractTemplate().getId());
		return entity;

	}

	/**
	 * 分页查询租赁合同明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	@SuppressWarnings("unchecked")
	public List<LeaseContractItem> queryLeaseContractItemList(int startRow,
			int endRow) {

		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据租赁合同ID查询租赁合同明细
	 * 
	 * @param LeaseContractItemId
	 *            租赁合同ID
	 * @return 租赁合同明细的对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LeaseContractItem> queryLeaseConractTemplateListByLeaseContractItemId(
			String LeaseContractItemId) {
		String hql = HQL
				+ " where o.leaseContractTemplate.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, LeaseContractItemId);
		return query.list();
	}

	/**
	 * 根据租赁合同编号查询租赁合同明细
	 * 
	 * @param contractNumber
	 *            租赁合同编号
	 * @return 租赁合同明细的对象集合
	 */
	@Override
	public List<LeaseContractItem> queryLeaseConractTemplateListBycontractNumber(
			String contractNumber) {
		String hql = HQL
				+ " where o.leaseContractTemplate.contractNumber=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, contractNumber);
		return query.list();
	}

	/**
	 * 更新主申请单中的金额
	 * 
	 * @param pactid
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id), sum(o.price) from LeaseContractItem o where o.deleted=false and o.leaseContractTemplate.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object[] obj = (Object[]) query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update LeaseContractTemplate set extendedValueTotalAmount="
				+ amount + ",quantity=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();
	}
}
