package com.skynet.spms.manager.customerService.BuybackService.BuybackContract.impl;

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
import com.skynet.spms.manager.customerService.BuybackService.BuybackContract.IBuybackPactManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.BuybackContractTemplate.BuybackContractTemplate;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 
 * 描述：合同信息操作类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class BuybackPactManager extends HibernateDaoSupport implements
		IBuybackPactManager {

	String hql = "select e from BuybackContractTemplate e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	@Override
	public void addSheet(BuybackContractTemplate item) {
		item.setContractNumber(uuidGeneral.getSequence("SBC"));
		item.setCreateDate(new Date());
		String creatBy = item.getCreateBy();
		if (creatBy == null || "".equals(creatBy)) {
			item.setCreateBy(GwtActionHelper.getCurrUser());
		}
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
		String sheetId = item.getSheet().getId();
		// 更新申請單的合同状态
		String hql = "update BuybackRequisitionSheet set isTemptlate=true where id='"
				+ sheetId + "'";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public BuybackContractTemplate getById(String id) {
		BuybackContractTemplate sheet = (BuybackContractTemplate) getSession()
				.get(BuybackContractTemplate.class, id);
		return sheet;
	}

	@Override
	public BuybackContractTemplate update(Map<String, Object> newValues,
			String itemID) {
		BuybackContractTemplate entity = (BuybackContractTemplate) getSession()
				.get(BuybackContractTemplate.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuybackContractTemplate> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
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
		System.out.println("doQuery 执行sql" + hql);
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuybackContractTemplate> getList(int startRow, int endRow) {
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
		BuybackContractTemplate pact = (BuybackContractTemplate) getSession()
				.get(BuybackContractTemplate.class, itemID);
		if (pact != null) {
			pact.setDeleted(true);
			getHibernateTemplate().update(pact);
			String sheetId = pact.getSheet().getId();
			// 更新申請單的合同状态
			hql = "update BuybackRequisitionSheet set isTemptlate=false where id='"
					+ sheetId + "'";
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
		}
	}

}
