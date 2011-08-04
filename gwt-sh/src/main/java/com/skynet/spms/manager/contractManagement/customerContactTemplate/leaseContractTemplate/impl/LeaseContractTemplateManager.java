package com.skynet.spms.manager.contractManagement.customerContactTemplate.leaseContractTemplate.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.contractManagement.customerContactTemplate.leaseContractTemplate.ILeaseContractTemplateManager;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate.LeaseContractTemplate;
import com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheet;

/**
 * 租赁合同实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class LeaseContractTemplateManager extends HibernateDaoSupport implements
		ILeaseContractTemplateManager {

	private final static String HQL = "select o from LeaseContractTemplate o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加租赁合同的方法
	 * 
	 * @param 租赁合同对象
	 */
	public void addLeaseContractTemplate(LeaseContractTemplate o) {
		getHibernateTemplate().save(o);
		// 更改租赁申请单
		LeaseRequisitionSheet sheet = getHibernateTemplate().get(
				LeaseRequisitionSheet.class, o.getRqId());

		sheet.setContractNumber(o.getContractNumber());
		getHibernateTemplate().update(sheet);
	}

	/**
	 * 修改租赁合同的方法
	 * 
	 * @param 新的数据对象
	 * @param 对象的ID
	 * @return 租赁合同对象
	 */
	public LeaseContractTemplate updateSLeaseContractTemplate(
			Map<String, Object> newValues, String itemID) {
		LeaseContractTemplate entity = new LeaseContractTemplate();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 删除租赁合同的方法
	 * 
	 * @param 对象ID
	 */
	public void deleteLeaseContractTemplate(String itemID) {
		String hql = "update LeaseContractTemplate set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 分页查询租赁合同的方法
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 集合对象
	 */
	public List<LeaseContractTemplate> queryLeaseContractTemplateList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 
	 * 根据合同ID查询合同对象
	 * 
	 * @param 租赁合同对象ID
	 * @return 租赁合同对象
	 */
	public LeaseContractTemplate getLeaseContractTemplateById(String sheetId) {

		return null;
	}

	/**
	 * 
	 * 根据合同编号所对应的合同
	 * 
	 * @param 合同编号
	 * @return 租赁合同对象集合
	 */
	@Override
	public List<LeaseContractTemplate> queryLeaseContractById(String string) {
		String hql = HQL
				+ " where o.deleted=false and o.id=? order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, string);
		return query.list();
	}

}
