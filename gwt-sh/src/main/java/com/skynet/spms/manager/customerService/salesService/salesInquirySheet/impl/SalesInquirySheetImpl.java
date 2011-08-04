package com.skynet.spms.manager.customerService.salesService.salesInquirySheet.impl;

import java.util.ArrayList;
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
import com.skynet.spms.manager.customerService.salesService.salesInquirySheet.SalesInquirySheetManager;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;

/**
 *销售询价单Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
@Service
@Transactional
public class SalesInquirySheetImpl extends HibernateDaoSupport implements
		SalesInquirySheetManager {

	private final static String HQL = "select o from SalesInquirySheet o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加询价单
	 * @param o 待添加的询价单
	 */
	public void addSalesInquirySheet(SalesInquirySheet o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 修改询价单
	 * @param newValues 新的数据对象
	 * @param itemID 询价单Id
	 * @return 询价单对象
	 */
	public SalesInquirySheet updateSalesInquirySheet(
			Map<String, Object> newValues, String itemID) {
//		SalesInquirySheet entity = (SalesInquirySheet) getSession().get(
//				SalesInquirySheet.class, itemID);
		SalesInquirySheet entity = new SalesInquirySheet();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 分页查询询价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单列表
	 */
	@SuppressWarnings("unchecked")
	public List<SalesInquirySheet> querySalesInquirySheetList(int startRow,
			int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 删除询价单
	 * @param itemID 询价单id
	 */
	public void deleteSalesInquirySheet(String itemID) {
		String hql = "update SalesInquirySheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据询价单Id 获得询价单
	 * @param sheetId 询价打ID
	 * @return 询价单
	 */
	public SalesInquirySheet getSalesInquirySheetById(String sheetId) {
		SalesInquirySheet entity = (SalesInquirySheet) getSession().get(
				SalesInquirySheet.class, sheetId);
		return entity;
	}

	/***
	 * 根据用户id获得询价单
	 * @param customerId 客户id
	 * @return 询价单列表
	 */
	public List<SalesInquirySheet> querySalesInquirySheetListByCustomerId(
			String customerId) {
		String hql = HQL
				+ "where o.m_CustomerIdentificationCode.id=? order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, customerId);
		return query.list();
	}

	/***
	 * 根据用户名称获得询价单
	 * @param customerId 客户名称
	 * @return 询价单列表
	 */
	@Override
	public List<SalesInquirySheet> querySalesInquirySheetListByCustomerName(
			String customerName) {
		
		
		return null;
	}
	
	/***
	 * 根据不同条件查询
	 * @param values 参数
	 * @param startRow 开始行
	 * @param endRow   结束行
	 * @return 询价单列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesInquirySheet> doQuery(Map<String, Object> values,
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
		
//		if(values.get("key")!=null){
//			if("reload".equals(values.get("key"))){
//				return this.getList(startRow, endRow);
//			}
//		}
//		List<SalesInquirySheet> items=new ArrayList<SalesInquirySheet>();
//		
//		//根据用户id获得用户询价单
//		if (values.get("customerName")!=null) {
//			CustomerIdentificationCode customerCode= userManager.queryCustomerCodeByUsername(values.get("customerName")+"");
//			if(customerCode!=null){
//				items = salesInquirySheetManager.querySalesInquirySheetListByCustomerId(customerCode.getId());
//			}
//			return items;
//			
//		}
//		else if(values.get("BussinessPublishStatusEntity")!=null){
//		}
//		
		
	
	}
}
