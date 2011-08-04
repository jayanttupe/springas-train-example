package com.skynet.spms.manager.customerService.salesService.salesPiecewiseQuotationItem.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.persistence.entity.customerService.sales.salesPiecewiseQuotationItem.SalesPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.manager.customerService.salesService.salesPiecewiseQuotationItem.ISalesPiecewiseQuotationItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
/**
 * 销售分段报价Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class SalesPiecewiseQuotationItemManager extends HibernateDaoSupport
		implements ISalesPiecewiseQuotationItemManager {

	private final static String HQL = "select o from SalesPiecewiseQuotationItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 描更改报价单金额
	 *
	 * @param  @param 分段报价对象   
	 */
	private void doPiecewiseQuotationItem(SalesPiecewiseQuotationItem o){
		String quantityType = o.getQuantityType();
		// 获得报价单信息
		SalesQuotationSheetItem salesQuotationSheetItem = o
				.getSalesQuotationSheetItem();
		salesQuotationSheetItem = (SalesQuotationSheetItem) getSession().get(
				SalesQuotationSheetItem.class, salesQuotationSheetItem.getId());

		Float quantity = salesQuotationSheetItem.getQuantity();// 获得报价数量
		// 报价单明细原金额
		Float oldAmount = salesQuotationSheetItem.getAmount();
		if (quantity != null) {
			// 如果报价数量 在 开始和结束 数量之间。则 给报价单重新复制
			if (quantity >= o.getFromQuantity()
					&& quantity <= o.getToQuantity()) {
				Float newUnitPrice = o.getPriceAmount();// 获得新单价
				salesQuotationSheetItem.setUnitPriceAmount(newUnitPrice);
				// 重新计算金额
				Float amount = quantity * newUnitPrice;
				// 如果特殊费用不为空，则累加 特殊费用
				if (salesQuotationSheetItem.getPackagePrice() != null) {
					amount += salesQuotationSheetItem.getPackagePrice();
					salesQuotationSheetItem.setAmount(amount);
				}
				//指定为 分段报价
				salesQuotationSheetItem.setIsBreakPrice(true);
				
				// 持久化更改报价明细
				getHibernateTemplate().update(salesQuotationSheetItem);

				/** 再更新报价单金额信息* */
				// 获得报价单所有信息
				SalesQuotationSheet quotation = salesQuotationSheetItem
						.getSalesQuotationSheet();
				Float newTotalAmount = quotation.getTotalAmount() + amount;// 原总价+新明细价格
				if (oldAmount != null) {
					newTotalAmount = newTotalAmount - oldAmount;// 减去原明细价格
				}
				quotation.setTotalAmount(newTotalAmount);
				// 持久化更新报价单
				getHibernateTemplate().update(quotation);

			}
		}
	}
	
	/**
	 * 添加分段报价
	 * @param o 待添加的分段报价
	 */
	public void addSalesPiecewiseQuotationItem(SalesPiecewiseQuotationItem o) {
		getHibernateTemplate().save(o);
		doPiecewiseQuotationItem(o);
		

	}
	
	/**
	 * 更新分段报价
	 * @param newValues 新的数据对象
	 * @param itemID 分段报价Id
	 * @return 分段报价对象
	 */
	public SalesPiecewiseQuotationItem updateSalesPiecewiseQuotationItem(
			Map<String, Object> newValues, String itemID) {
		SalesPiecewiseQuotationItem o =  (SalesPiecewiseQuotationItem) getSession().get(
				SalesPiecewiseQuotationItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		doPiecewiseQuotationItem(o);
		return o;
	}

	/**
	 * 删除分段报价
	 * @param itemID 分段报价id
	 */
	public void deleteSalesPiecewiseQuotationItem(String itemID) {
		String hql = "update SalesPiecewiseQuotationItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据分段报价明细Id 获得分段报价
	 * @param sheetId 分段报价id
	 * @return 分段报价
	 */
	public SalesPiecewiseQuotationItem getSalesPiecewiseQuotationItemById(
			String sheetId) {
		return (SalesPiecewiseQuotationItem) getSession().get(
				SalesPiecewiseQuotationItem.class, sheetId);
	}

	/**
	 * 分页查询分段报价
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  分段报价明细列表
	 */
	public List<SalesPiecewiseQuotationItem> querySalesPiecewiseQuotationItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据报价单明细Id 查询分段报价信息
	 * @param inquiryId 分段报价Id
	 * @return 分段报价列表
	 */
	@SuppressWarnings("unchecked")
	public List<SalesPiecewiseQuotationItem> querySalesPiecewiseQuotationItemListByQuotationItemId(
			String quotationItemId) {
		String hql = HQL
				+ "where o.salesQuotationSheetItem.id=? and o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, quotationItemId);
		return query.list();
	}

}
