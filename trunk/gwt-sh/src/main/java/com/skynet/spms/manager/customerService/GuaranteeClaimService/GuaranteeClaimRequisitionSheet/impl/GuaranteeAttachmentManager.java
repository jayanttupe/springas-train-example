package com.skynet.spms.manager.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.impl;

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
import com.skynet.spms.manager.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.IGuaranteeAttachmentManager;
import com.skynet.spms.persistence.entity.base.Attachment;

/**
 * 
 * 描述：担保索赔附件操作
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class GuaranteeAttachmentManager extends HibernateDaoSupport implements
		IGuaranteeAttachmentManager {
	String hql = "select e from Attachment e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void addSheet(Attachment item) {
		getHibernateTemplate().save(item);
	}

	@Override
	public Attachment getById(String id) {
		Attachment sheet = (Attachment) getSession().get(Attachment.class, id);
		return sheet;
	}

	@Override
	public Attachment update(Map<String, Object> newValues, String itemID) {
		Attachment entity = (Attachment) getSession().get(Attachment.class,
				itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

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

	@Override
	public void delete(String itemID) {
		String hql = "update Attachment set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
		System.out.println("delete 执行sql" + hql);
	}

}
