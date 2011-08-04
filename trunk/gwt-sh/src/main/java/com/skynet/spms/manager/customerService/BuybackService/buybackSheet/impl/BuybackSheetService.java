package com.skynet.spms.manager.customerService.BuybackService.buybackSheet.impl;

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
import com.skynet.spms.manager.customerService.BuybackService.buybackSheet.IBuybackSheetService;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.BuybackService.BuybackRequisitionSheet.BuybackRequisitionSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 
 * 描述：回购申请单操作类
 * 
 * @author 付磊
 * @version 1.0
 * @Date 2011-7-12
 */
@Service
@Transactional
public class BuybackSheetService extends HibernateDaoSupport implements
		IBuybackSheetService {

	String hql = "select e from BuybackRequisitionSheet e ";
	@Resource
	UUIDGeneral uuidGeneral;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void addSheet(BuybackRequisitionSheet sheet) {
		sheet.setCreateDate(new Date());
		String creatBy = sheet.getCreateBy();
		if (creatBy == null || "".equals(creatBy)) {
			sheet.setCreateBy(GwtActionHelper.getCurrUser());
		}
		sheet.setRequisitionSheetNumber(uuidGeneral.getSequence("BBK"));
		sheet.setIsTemptlate(false);
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
	public BuybackRequisitionSheet getById(String id) {
		BuybackRequisitionSheet sheet = (BuybackRequisitionSheet) getSession()
				.get(BuybackRequisitionSheet.class, id);
		return sheet;
	}

	@Override
	public BuybackRequisitionSheet update(Map<String, Object> newValues,
			String itemID) {
		BuybackRequisitionSheet entity = (BuybackRequisitionSheet) getSession()
				.get(BuybackRequisitionSheet.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuybackRequisitionSheet> doQuery(Map<String, Object> values,
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
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuybackRequisitionSheet> getList(int startRow, int endRow) {
		String hql = this.hql
				+ " where e.deleted=false order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@Override
	public void delete(String itemID) {
		String hql = "update BuybackRequisitionSheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

}
