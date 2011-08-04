package com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.salesService.SalesQuotationSheet.SalesQuotationSheetItemManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesPiecewiseQuotationItem.SalesPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;

/**
 * 销售报价明细Manager
 * @author  李宁
 * @version 1.0
 * @date 2011-07-11
 */
@Service
@Transactional
public class SalesQuotationSheetItemImpl extends HibernateDaoSupport implements
		SalesQuotationSheetItemManager {

	private final static String HQL = "select o from SalesQuotationSheetItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 更新报价的采购金额
	 * 
	 * @param pactid 报价单id
	 */
	private void updateAmount(String parimaryKey) {
		String hql = "select sum(o.amount) from SalesQuotationSheetItem o where o.deleted=false and o.salesQuotationSheet.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, parimaryKey);
		Object obj = query.uniqueResult();
		
		if (obj != null) {
			float amount = Float.parseFloat(obj+"");
			hql = "update SalesQuotationSheet set totalAmount=" + amount
					+ " where id=?";
			query = getSession().createQuery(hql);
			query.setParameter(0, parimaryKey);
			query.executeUpdate();
		}

	}

	/***
	 * ' 初始化 数值属性
	 * 
	 * @param o 报价单明细
	 */
	private void initNumerical(SalesQuotationSheetItem o) {
		if (o.getQuantity() == null) {
			o.setQuantity(0f);
		}

		if (o.getUnitPriceAmount() == null) {
			o.setUnitPriceAmount(0.00f);
		}
		if (o.getAmount() == null) {
			o.setAmount(0.00f);
		}
		if (o.getPackagePrice() == null) {
			o.setPackagePrice(0.0f);
		}

	}

	/***
	 * 处理分段报价
	 * 
	 * @param o 报价单明细对象
	 */
	private void doPiecewiseQuotationItem(SalesQuotationSheetItem o) {
		if (o.getIsBreakPrice() == null) {
			o.setIsBreakPrice(false);
		}
		// 判断是否分段报价
		if (o.getIsBreakPrice()) {
			// 获得分段报价集合
			List<SalesPiecewiseQuotationItem> piecewiseList = new ArrayList<SalesPiecewiseQuotationItem>();
			String hql = "from  SalesPiecewiseQuotationItem  o where o.salesQuotationSheetItem.id=?";
			Query query = getSession().createQuery(hql);
			query.setParameter(0, o.getId());
			piecewiseList = query.list();
			Float quantity = o.getQuantity();// 报价数量
			for (SalesPiecewiseQuotationItem piecewise : piecewiseList) {
				if (quantity >= piecewise.getFromQuantity()
						&& quantity <= piecewise.getToQuantity()) {
					Float newUnitPrice = piecewise.getPriceAmount();// 获得新单价
					o.setUnitPriceAmount(newUnitPrice);// 更新单价
					// 重新计算金额
					Float amount = quantity * newUnitPrice;
					// 如果特殊费用不为空，则累加 特殊费用
					if (o.getPackagePrice() != null) {
						amount += o.getPackagePrice();
						o.setAmount(amount);// 更新金额
					}
					break;
				}
			}
		}
	}
	
	
	/**
	 * 更改询价单报价状态
	 * 更改询价单明细报价状态
	 * @param o 报价单明细对象
	 */
	private void doUpdateSaleInquiryAndItemState(SalesQuotationSheetItem o){
		SalesInquirySheetItem inquiryItem = o.getSalesInquirySheetItem();

		inquiryItem = (SalesInquirySheetItem) getSession().get(
				SalesInquirySheetItem.class, inquiryItem.getId());
		// 询价单
		SalesInquirySheet salesInquirySheet = inquiryItem
				.getSalesInquirySheet();
		// 设置明细已报价
		QuotationStatusEntity qsEntity = new QuotationStatusEntity();
		qsEntity.setM_QuotationStatus(QuotationStatus.alreadyOffer);// 已报价
		inquiryItem.setM_QuotationStatusEntity(qsEntity);

		List<SalesInquirySheetItem> itemList = salesInquirySheet
				.getM_SalesInquirySheetItem();
		boolean ifAllQuotation = true;// 是否全部报价
		for (SalesInquirySheetItem salesInquirySheetItem : itemList) {
			if (salesInquirySheetItem != null) {
				if (salesInquirySheetItem.getM_QuotationStatusEntity().m_QuotationStatus
						.equals(QuotationStatus.didNotOffer)) {
					ifAllQuotation = false;
					break;
				}
			}
		}

		QuotationStatusEntity inquiryStatus = new QuotationStatusEntity();
		if (ifAllQuotation) {
			inquiryStatus.setM_QuotationStatus(QuotationStatus.alreadyOffer);// 全部报价
		} else {
			inquiryStatus.setM_QuotationStatus(QuotationStatus.partsOffer);// 部分报价
		}
		// 这是报价状态
		salesInquirySheet.setM_QuotationStatusEntity(inquiryStatus);

		
		// 更改询价单
		getHibernateTemplate().update(salesInquirySheet);
	}

	/**
	 * 添加报价单明细
	 * @param o 待添加的报价单明细
	 */
	public void addSalesQuotationSheetItem(SalesQuotationSheetItem o) {
		initNumerical(o);// 初始或数字属性
	
		//更改询价单状态：
		doUpdateSaleInquiryAndItemState(o);
		
		// 处理分段报价
		doPiecewiseQuotationItem(o);

		getHibernateTemplate().save(o);

		updateAmount(o.getSalesQuotationSheet().getId());// 更改主表金额

	}

	/**
	 * 修改报价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 报价单明细Id
	 * @return 报价单明细对象
	 */
	public SalesQuotationSheetItem updateSalesQuotationSheetItem(
			Map<String, Object> newValues, String itemID) {
//		SalesQuotationSheetItem entity = (SalesQuotationSheetItem) getSession()
//				.get(SalesQuotationSheetItem.class, itemID);
		SalesQuotationSheetItem entity =new SalesQuotationSheetItem();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		// 初始或数字属性
		initNumerical(entity);
		//分段报价
		doPiecewiseQuotationItem(entity);
		//更改询价单状态：
		doUpdateSaleInquiryAndItemState(entity);
		//修改明细
		getHibernateTemplate().update(entity);
		// 更改主表金额
		updateAmount(entity.getSalesQuotationSheet().getId());
		return entity;
	}

	/**
	 * 删除报价单明细
	 * @param itemID 报价单明细id
	 */
	public void deleteSalesQuotationSheetItem(String itemID) {

		SalesQuotationSheetItem entity = (SalesQuotationSheetItem) getSession()
				.get(SalesQuotationSheetItem.class, itemID);
		entity.setDeleted(true);
		getHibernateTemplate().update(entity);
		updateAmount(entity.getSalesQuotationSheet().getId());// 更改主表金额

	}

	/**
	 * 根据报价单id获得报价单明细
	 * @param QuotationId 报价单明细id
	 * @return 报价单明细列表
	 */
	@SuppressWarnings("unchecked")
	public List<SalesQuotationSheetItem> querySalesQuotationSheetItemListByQuotationId(
			String QuotationId) {
		String hql = HQL
				+ "where o.salesQuotationSheet.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, QuotationId);
		return query.list();
	}

}
