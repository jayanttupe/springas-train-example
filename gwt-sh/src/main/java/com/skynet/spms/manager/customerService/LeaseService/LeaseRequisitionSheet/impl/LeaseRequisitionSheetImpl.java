package com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetManager;
import com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheet;

/**
 * 租赁申请单实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class LeaseRequisitionSheetImpl extends HibernateDaoSupport implements
		LeaseRequisitionSheetManager {
	private String HQL = "select o from LeaseRequisitionSheet o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加租赁申请单的方法
	 * 
	 * @param o
	 *            租赁申请单对象
	 */
	public void addLeaseRequisitionSheet(LeaseRequisitionSheet o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 删除租赁申请单的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseRequisitionSheet(String id) {
		String hql = "update LeaseRequisitionSheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	/**
	 * 修改租赁申请单的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public LeaseRequisitionSheet updateleaseRequisitonSheet(
			Map<String, Object> newValues, String itemID) {
		LeaseRequisitionSheet entity = new LeaseRequisitionSheet();
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
	public List<LeaseRequisitionSheet> queryLeaseRequisitionSheetList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
