package com.skynet.spms.manager.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.impl;

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
import com.skynet.spms.manager.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.GuaranteeClaimRequisitionSheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.GuaranteeClaimService.GuaranteeClaimRequisitionSheet.GuaranteeClaimRequisitionSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 
 * 描述：担保索赔申请单操作
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class GuaranteeClaimRequisitionSheetImpl extends HibernateDaoSupport
		implements GuaranteeClaimRequisitionSheetManager {

	String hql = "select e from GuaranteeClaimRequisitionSheet e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	@Override
	public void addSheet(GuaranteeClaimRequisitionSheet sheet) {
		sheet.setCreateDate(new Date());
		sheet.setRequisitionDate(new Date());
		sheet.setRequisitionSheetNumber(uuidGeneral.getSequence("GCR"));
		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setActionDate(new Date());
		pbStatus.setActionDescription("");
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		pbStatus.setVersion(1);
		pbStatus.setOperator(GwtActionHelper.getCurrUser());
		sheet.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setActionDate(new Date());
		bStatus.setActionDescription("");
		bStatus.setStatus(EntityStatusMonitor.created);
		bStatus.setOperator(GwtActionHelper.getCurrUser());
		bStatus.setVersion(1);
		sheet.setM_BussinessStatusEntity(bStatus);
		getHibernateTemplate().save(sheet);
	}

	@Override
	public GuaranteeClaimRequisitionSheet getById(String id) {
		GuaranteeClaimRequisitionSheet sheet = (GuaranteeClaimRequisitionSheet) getSession()
				.get(GuaranteeClaimRequisitionSheet.class, id);
		return sheet;
	}

	@Override
	public GuaranteeClaimRequisitionSheet update(Map<String, Object> newValues,
			String itemID) {
		GuaranteeClaimRequisitionSheet entity = (GuaranteeClaimRequisitionSheet) getSession()
				.get(GuaranteeClaimRequisitionSheet.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteeClaimRequisitionSheet> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
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
		hql += " order by e.requisitionDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GuaranteeClaimRequisitionSheet> getList(int startRow, int endRow) {
		String hql = this.hql
				+ " where e.deleted=false order by e.requisitionDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@Override
	public void delete(String itemID) {
		String hql = "update GuaranteeClaimRequisitionSheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

}
