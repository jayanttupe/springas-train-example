package com.skynet.spms.manager.customerService.salesService.salesContract.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.salesService.salesContract.ISalesContractTemplateManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.SalesContractTemplate.SalesContractTemplate;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 
 * 描述：客户销售合同业务实现类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class SalesContractTemplateManager extends HibernateDaoSupport implements
		ISalesContractTemplateManager {

	private final static String HQL = "select o from SalesContractTemplate o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	public void addSalesContractTemplate(SalesContractTemplate item) {
		item.setContractNumber(uuidGeneral.getSequence("SO"));
		item.setCreateDate(new Date());

		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setActionDate(new Date());
		pbStatus.setActionDescription("");
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		pbStatus.setVersion(1);
		pbStatus.setOperator(GwtActionHelper.getCurrUser());
		item.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setActionDate(new Date());
		bStatus.setActionDescription("");
		bStatus.setStatus(EntityStatusMonitor.created);
		bStatus.setOperator(GwtActionHelper.getCurrUser());
		bStatus.setVersion(1);
		item.setM_BussinessStatusEntity(bStatus);
		// 设置审批状态
		item.setAuditStatus(AuditStatus.create);
		getHibernateTemplate().save(item);
		String sheetId = item.getSaleSheet().getId();

		// 更新申請單的合同状态
		String hql = "update SalesRequisitionSheet set isTemptlate=true where id='"
				+ sheetId + "'";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}

	public SalesContractTemplate updateSalesContractTemplate(
			Map<String, Object> newValues, String itemID) {
		SalesContractTemplate entity = (SalesContractTemplate) getSession()
				.get(SalesContractTemplate.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		return entity;
	}

	public void deleteSalesContractTemplate(String itemID) {
		SalesContractTemplate entity = (SalesContractTemplate) getSession()
				.get(SalesContractTemplate.class, itemID);
		if (entity != null) {
			entity.setDeleted(true);
			getHibernateTemplate().update(entity);
			String sheetId = entity.getSaleSheet().getId();
			// 更新申請單的合同状态
			String hql = "update SalesRequisitionSheet set isTemptlate=false where id='"
					+ sheetId + "'";
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
		}
	}

	public SalesContractTemplate getSalesContractTemplateById(String sheetId) {
		return (SalesContractTemplate) getSession().get(
				SalesContractTemplate.class, sheetId);
	}

	@SuppressWarnings("unchecked")
	public List<SalesContractTemplate> querySalesContractTemplateList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesContractTemplate> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		Set<String> keys = values.keySet();
		String hql = HQL + " where o.deleted=false";
		for (String key : keys) {
			Object obj = values.get(key);
			if (obj instanceof String) {
				hql += " and " + key + "='" + values.get(key) + "'";
			} else {
				hql += " and " + key + "=" + values.get(key);
			}
		}
		hql += " order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		List<SalesContractTemplate> list = query.list();
		return list;
	}

}
