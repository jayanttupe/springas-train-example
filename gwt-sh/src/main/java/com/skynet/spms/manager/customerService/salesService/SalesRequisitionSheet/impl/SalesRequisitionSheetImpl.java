package com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.impl;

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
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
/**
 * 销售订单Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
@Service
@Transactional
public class SalesRequisitionSheetImpl extends HibernateDaoSupport implements
		SalesRequisitionSheetManager {
	private final static String HQL = "select o from SalesRequisitionSheet o ";
	
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 更新更新总额
	 * 
	 * @param pactid 订单id
	 */
	private void updateAmount(SalesRequisitionSheet entity) {
		String hql = "select sum(o.amount) from SalesRequisitionSheetItem o where o.deleted=false and o.salesRequisitionSheet.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, entity.getId());
		Object obj = query.uniqueResult();
		if (obj != null) {
			float amount = Float.parseFloat(query.uniqueResult().toString());
			entity.setTotalAmount(amount);
		}
	
	}
	
	/**
	 * 添加订单
	 * @param o 待添加的订单
	 */
	public void addSalesRequisitionSheet(SalesRequisitionSheet o) {
		getHibernateTemplate().save(o);
		
	
		
		
	}

	/**
	 * 修改订单
	 * @param newValues 新的数据对象
	 * @param itemID 订单Id
	 * @return 订单对象
	 */
	public SalesRequisitionSheet updateSalesRequisitionSheet(
			Map<String, Object> newValues, String itemID) {
//		SalesRequisitionSheet entity =(SalesRequisitionSheet) getSession().get(
//				SalesRequisitionSheet.class, itemID);
		SalesRequisitionSheet entity =new SalesRequisitionSheet();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		updateAmount(entity);//更新总金额
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 分页查询订单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  订单列表
	 */
	@SuppressWarnings("unchecked")
	public List<SalesRequisitionSheet> querySalesRequisitionSheetList(int startRow,
			int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 删除订单
	 * @param itemID 订单id
	 */
	public void deleteSalesRequisitionSheet(String itemID) {
		String hql = "update SalesRequisitionSheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据订单Id 获得订单
	 * @param sheetId 报价打ID
	 * @return 订单
	 */
	public SalesRequisitionSheet getSalesRequisitionSheetById(String sheetId) {
		SalesRequisitionSheet entity = (SalesRequisitionSheet) getSession().get(
				SalesRequisitionSheet.class, sheetId);
		return entity;
	}

	/***
	 * 根据不同条件查询
	 * @param values 筛选条件
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return  订单列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
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

	@Override
	public SalesRequisitionSheet getSalesRequisitionSheetByNumber(String number) {
		String hql = HQL + " where o.requisitionSheetNumber=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, number);
		SalesRequisitionSheet salesRequisitionSheet =(SalesRequisitionSheet) query.uniqueResult();
		return salesRequisitionSheet;
		
	}


}
