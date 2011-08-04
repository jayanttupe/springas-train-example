package com.skynet.spms.manager.customerService.LeaseService.LeaseContract.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.LeaseService.LeaseContract.LeaseAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 租赁合同明附件实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class LeaseAttachmentManagerImpl extends HibernateDaoSupport implements
		LeaseAttachmentManager {
	String hql = "select e from Attachment e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加一条记录
	 * 
	 * @param 附件对象
	 */

	@Override
	public void addSheet(Attachment item) {
		getHibernateTemplate().save(item);
	}

	/**
	 * 根据ID查询一条记录
	 * 
	 * @param 附件ID
	 * @return 附件对象
	 */
	@Override
	public Attachment getById(String id) {
		Attachment sheet = (Attachment) getSession().get(Attachment.class, id);
		return sheet;
	}

	/**
	 * 
	 * 描述这个方法的作用
	 * 
	 * @param 新数据对象
	 * @param 要修改的对象ID
	 * @return 合同附件对象
	 */
	@Override
	public Attachment update(Map<String, Object> newValues, String itemID) {
		Attachment entity = (Attachment) getSession().get(Attachment.class,
				itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 分页查询
	 * 
	 * @param 新的对象数据
	 * @param 首页
	 * @param 尾页
	 * @return 附件集合对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		Set<String> keys = values.keySet();
		String hql = this.hql + " where e.deleted=false";
		for (String key : keys) {
			Object obj = values.get(key);
			if (obj instanceof String) {
				hql += " and " + key + "='" + values.get(key) + "'";
			} else {
				hql += " and " + key + "=" + values.get(key);
			}
		}
		hql += " order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		System.out.println("doQuery 执行sql" + hql);
		return query.list();
	}

	/**
	 * 分页查询
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 附件集合对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getList(int startRow, int endRow) {
		String hql = this.hql
				+ " where e.deleted=false order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		System.out.println("getList 执行sql" + hql);
		return query.list();
	}

	/**
	 * 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param 附件ID
	 */
	@Override
	public void delete(String itemID) {
		String hql = "update Attachment set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
		System.out.println("delete 执行sql" + hql);
	}

}
