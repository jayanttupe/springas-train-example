package com.skynet.spms.manager.contractManagement.supplierContactTemplate.leaseContractTemplate.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.contractManagement.supplierContactTemplate.leaseContractTemplate.ISSLeaseContractTemplateManager;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.leaseContractTemplate.SSLeaseContractTemplate;

/**
 * 供应商租赁合同实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class SSLeaseContractTemplateManager extends HibernateDaoSupport
		implements ISSLeaseContractTemplateManager {

	private final static String HQL = "select o from SSLeaseContractTemplate o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加供应商租赁合同的方法
	 * 
	 * @param 供应商租赁合同对象
	 */
	public void addLeaseContractTemplate(SSLeaseContractTemplate o) {

		getHibernateTemplate().save(o);
		// 更新送修申请单,关联合同模板
		// sheet.setContractNumber(o.getContractNumber());
		// getHibernateTemplate().update(sheet);
	}

	/**
	 * 修改供应商租赁合同的方法
	 * 
	 * @param 新的数据对象
	 * @param 对象的ID
	 * @return 供应商租赁合同对象
	 */
	public SSLeaseContractTemplate updateSLeaseContractTemplate(
			Map<String, Object> newValues, String itemID) {
		SSLeaseContractTemplate entity = new SSLeaseContractTemplate();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 删除供应商租赁合同的方法
	 * 
	 * @param 对象ID
	 */
	public void deleteLeaseContractTemplate(String itemID) {
		String hql = "update SSLeaseContractTemplate set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 分页查询供应商租赁合同的方法
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 集合对象
	 */
	public List<SSLeaseContractTemplate> queryLeaseContractTemplateList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 
	 * 根据供应商租赁合同ID查询供应商租赁合同
	 * 
	 * @param 供应商租赁合同ID
	 * @return 供应商租赁合同对象集合
	 */
	@Override
	public List<SSLeaseContractTemplate> querySSLeaseContractById(String string) {
		String hql = HQL
				+ " where o.deleted=false and o.id=? order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, string);
		return query.list();
	}

}
