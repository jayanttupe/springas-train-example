package com.skynet.spms.manager.supplierSupport.lease.LeaseContract.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.lease.LeaseContract.SSLeaseContracItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.lease.leaseContract.SSLeaseContractItem;

/**
 * 供应商租赁合同明细实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class SSLeaseContracItemImpl extends HibernateDaoSupport implements
		SSLeaseContracItemManager {
	private String HQL = "select o from SSLeaseContractItem o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加供应商租赁合同明细的方法
	 * 
	 * @param o
	 *            供应商租赁合同明细对象
	 */
	public void addLeaseContractItem(SSLeaseContractItem o) {
		getHibernateTemplate().save(o);
		updateAmount(o.getSsleaseContractTemplate().getId());
	}

	/**
	 * 删除供应商租赁合同明细的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseContractItem(String id) {
		SSLeaseContractItem entity = (SSLeaseContractItem) getSession().get(
				SSLeaseContractItem.class, id);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getSsleaseContractTemplate().getId());
	}

	/**
	 * 修改供应商租赁合同明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 供应商租赁合同明细对象
	 */
	public SSLeaseContractItem updateLeaseContractItem(
			Map<String, Object> newValues, String itemID) {
		SSLeaseContractItem entity = new SSLeaseContractItem();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getSsleaseContractTemplate().getId());
		return entity;
	}

	/**
	 * 分页查询供应商租赁合同明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	@SuppressWarnings("unchecked")
	public List<SSLeaseContractItem> queryLeaseContractItemList(int startRow,
			int endRow) {

		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据供应商租赁合同ID查询租赁合同明细
	 * 
	 * @param LeaseContractItemId
	 *            供应商租赁合同ID
	 * @return 供应商租赁合同明细的对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SSLeaseContractItem> queryLeaseConractTemplateListByLeaseContractItemId(
			String LeaseContractItemId) {
		String hql = HQL
				+ " where o.ssleaseContractTemplate.id=? and o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, LeaseContractItemId);
		return query.list();
	}

	/**
	 * 更新主申请单中的金额
	 * 
	 * @param pactid
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id), sum(o.price) from SSLeaseContractItem o where o.deleted=false and o.ssleaseContractTemplate.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object[] obj = (Object[]) query.uniqueResult();
		Float amount = 0F;
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj[0].toString());
			amount = Float.parseFloat(obj[1].toString());
		}
		hql = "update SSLeaseContractTemplate set extendedValueTotalAmount="
				+ amount + ",quantity=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();

	}
}
