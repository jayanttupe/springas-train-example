package com.skynet.spms.manager.supplierSupport.procurement.PartRequirment.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.PartRequirment.PartRequirementManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.partRequirement.PartRequirement;

/**
 * 备件计划需求
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class PartRequirementImpl extends HibernateDaoSupport implements
		PartRequirementManager {

	private String HQL = "select o from PartRequirement o";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 添加备件计划需求
	 * 
	 * @param o
	 *            备件计划需求对象
	 * @return void
	 */
	public void addPartRequirement(PartRequirement o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 
	 * 删除备件计划需求的方法
	 * 
	 * @param id
	 *            备件计划需求ID
	 * @return void
	 * @throws
	 */
	public void deletePartRequirement(String id) {
		String hql = "update PartRequirement set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	/**
	 * 
	 * 修改备件计划需求的方法
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            对象ID
	 * @return 备件计划需求对象
	 */
	public PartRequirement updatePartRequirement(Map<String, Object> newValues,
			String itemID) {

		PartRequirement entity = new PartRequirement();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		entity.setLastTime(new Date());
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 
	 * 分页查询备件计划需求的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 备件计划需求对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<PartRequirement> queryPartRequirementList(int startRow,
			int endRow) {

		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
