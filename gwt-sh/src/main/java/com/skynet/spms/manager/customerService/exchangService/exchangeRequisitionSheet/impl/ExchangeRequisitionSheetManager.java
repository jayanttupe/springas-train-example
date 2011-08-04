package com.skynet.spms.manager.customerService.exchangService.exchangeRequisitionSheet.impl;
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

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.sales.exchangeRequisitionSheet.ExchangeRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.service.UUIDGeneral;
import com.skynet.spms.manager.customerService.exchangService.exchangeRequisitionSheet.IExchangeRequisitionSheetManager;
/**
 * 交换申请单业务实现类
 * @author  lining
 *
 */
@Service
@Transactional
public class ExchangeRequisitionSheetManager extends HibernateDaoSupport
		implements IExchangeRequisitionSheetManager {

	private final static String HQL = "select o from ExchangeRequisitionSheet o ";

	@Resource
	UUIDGeneral uuidGeneral;
	
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addExchangeRequisitionSheet(ExchangeRequisitionSheet o) {
		
		// 业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);// 已创建
		o.setM_BussinessStatusEntity(bse);

		// 发布状态
		BussinessPublishStatusEntity bpse = new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		o.setM_BussinessPublishStatusEntity(bpse);

		o.setRequisitionDate(new Date());
		
		// 询价编号
		o.setRequisitionSheetNumber(uuidGeneral.getSequence("CEC"));
		getHibernateTemplate().save(o);
	}

	public ExchangeRequisitionSheet updateExchangeRequisitionSheet(
			Map<String, Object> newValues, String itemID) {
		ExchangeRequisitionSheet o=getHibernateTemplate().get(ExchangeRequisitionSheet.class, itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		return o;
	}

	public void deleteExchangeRequisitionSheet(String itemID) {
		String hql = "update ExchangeRequisitionSheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public ExchangeRequisitionSheet getExchangeRequisitionSheetById(String sheetId) {
		return (ExchangeRequisitionSheet) getSession().get(
				ExchangeRequisitionSheet.class, sheetId);
	}

	public List<ExchangeRequisitionSheet> queryExchangeRequisitionSheetList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@Override
	public List<ExchangeRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// TODO Auto-generated method stub
		Set<String> keys = values.keySet();
		String hql = HQL + " where o.deleted=false";
		
			for (String key : keys) {
				Object obj = values.get(key);
				if(key.equals("key")||key.equals("r")||key.equals("_r")){
					continue;
				}
				if (obj instanceof String) {
					hql += " and " + key + "='" + values.get(key) + "'";
				} else {
					hql += " and " + key + "=" + values.get(key);
				}
			}
		hql+=" order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
