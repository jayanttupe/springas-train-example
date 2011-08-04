package com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;

/**
 * 销售订单明细Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
@Service
@Transactional
public class SalesRequisitionSheetItemImpl extends HibernateDaoSupport
		implements SalesRequisitionSheetItemManager {
	private final static String HQL = "select o from SalesRequisitionSheetItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 更新总额
	 * 
	 * @param pactid 订单id
	 */
	private void updateAmount(String parimaryKey) {
		String hql = "select sum(o.amount) from SalesRequisitionSheetItem o where o.deleted=false and o.salesRequisitionSheet.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, parimaryKey);
		Object obj = query.uniqueResult();
		if (obj != null) {
			float amount = Float.parseFloat(obj+"");
			hql = "update SalesRequisitionSheet set totalAmount=" + amount
					+ " where id=?";
			query = getSession().createQuery(hql);
			query.setParameter(0, parimaryKey);
			query.executeUpdate();
		}

	}

	/***
	 * ' 初始化 数值属性
	 * 
	 * @param o 订单对象
	 */
	private void initNumerical(SalesRequisitionSheetItem o) {
		if (o.getUnitOfPrice() == null) {
			o.setUnitOfPrice(0.0f);
		}
		if (o.getQuantity() == null) {
			o.setQuantity(0.0f);
		}
		if (o.getAmount() == null) {
			o.setAmount(0.0f);
		}
	}

	/**
	 * 添加订单明细
	 * @param o 待添加的订单明细
	 */
	public void addSalesRequisitionSheetItem(SalesRequisitionSheetItem o) {
		initNumerical(o);// 初始化 数值属性
		getHibernateTemplate().save(o);
		updateAmount(o.getSalesRequisitionSheet().getId());// 更改总金额
	}

	/**
	 * 修改订单明细
	 * @param newValues 新的数据对象
	 * @param itemID 订单明细Id
	 * @return 订单明细对象
	 */
	public SalesRequisitionSheetItem updateSalesRequisitionSheetItem(
			Map<String, Object> newValues, String itemID) {
//		SalesRequisitionSheetItem entity = (SalesRequisitionSheetItem) getSession()
//				.get(SalesRequisitionSheetItem.class, itemID);
		SalesRequisitionSheetItem entity = (SalesRequisitionSheetItem) getSession()
		.get(SalesRequisitionSheetItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		initNumerical(entity);// 初始化 数值属性
		getHibernateTemplate().update(entity);
		updateAmount(entity.getSalesRequisitionSheet().getId());// 更改总金额
		return entity;
	}

	/**
	 * 删除订单明细
	 * @param itemID 订单明细id
	 */
	public void deleteSalesRequisitionSheetItem(String itemID) {
		SalesRequisitionSheetItem entity = (SalesRequisitionSheetItem) getSession()
				.get(SalesRequisitionSheetItem.class, itemID);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getSalesRequisitionSheet().getId());// 更改总金额
	}

	/**
	 * 分页查询订单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  订单明细列表
	 */
	@SuppressWarnings("unchecked")
	public List<SalesRequisitionSheetItem> querySalesRequisitionSheetItemListByRequisitionId(
			String requisitionId) {
		String hql = HQL
				+ "where o.salesRequisitionSheet.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, requisitionId);
		 List<SalesRequisitionSheetItem> items=query.list();
		return items;
	}
}
