package com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet.impl;

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
import com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet.SalesQuotationSheetManager;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;

/**
 * 销售报价Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
@Service
@Transactional
public class SalesQuotationSheetImpl extends HibernateDaoSupport implements SalesQuotationSheetManager {

	private final static String HQL = "select o from SalesQuotationSheet o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加报价单
	 * @param o 待添加的报价单
	 */
	public void addSalesQuotationSheet(SalesQuotationSheet o) {
		getHibernateTemplate().save(o);
		
	}

	/**
	 * 修改报价单
	 * @param newValues 新的数据对象
	 * @param itemID 报价单Id
	 * @return 报价单对象
	 */
	public SalesQuotationSheet updateSalesQuotationSheet(
			Map<String, Object> newValues, String itemID) {
		SalesQuotationSheet entity = new SalesQuotationSheet();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		
		return entity;
	}

	/**
	 * 分页查询报价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单列表
	 */
	@SuppressWarnings("unchecked")
	public List<SalesQuotationSheet> querySalesQuotationSheetList(int startRow,
			int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 删除报价单
	 * @param itemID 报价单id
	 */
	public void deleteSalesQuotationSheet(String itemID) {
		String hql = "update SalesQuotationSheet set deleted=true where id=? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
		
		
	}

	/**
	 * 根据报价单Id 获得报价单
	 * @param sheetId 报价打ID
	 * @return 报价单
	 */
	public SalesQuotationSheet getSalesQuotationSheetById(String sheetId) {
		SalesQuotationSheet entity = (SalesQuotationSheet) getSession().get(
				SalesQuotationSheet.class, sheetId);
		return entity;
	}
	
	/***
	 * 根据不同条件查询
	 * @param values 参数
	 * @param startRow 开始行
	 * @param endRow 结束行
	 * @return 报价单列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SalesQuotationSheet> doQuery(Map<String, Object> values,
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
	public void updateBussinessState(String className, String id, String state) {
		
		String hql="select o.m_BussinessStatusEntity.id  from "+className+" o where o.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, id);
		String stateId=(String) query.uniqueResult();
		
		
		String hql2 = "update BussinessStatusEntity set status=? where id=? ";
		query =getSession().createQuery(hql2);
		if(state!=null){
			try {
				EntityStatusMonitor statusEnum=EntityStatusMonitor.valueOf(state);
				query.setParameter(0, statusEnum);
				query.setParameter(1, stateId);
				query.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}

}
