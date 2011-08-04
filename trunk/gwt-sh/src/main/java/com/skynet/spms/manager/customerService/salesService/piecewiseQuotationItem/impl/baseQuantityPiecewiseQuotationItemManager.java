package com.skynet.spms.manager.customerService.salesService.piecewiseQuotationItem.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.salesService.piecewiseQuotationItem.IbaseQuantityPiecewiseQuotationItemManager;
import com.skynet.spms.persistence.entity.base.partCatalog.base.baseQuantityPiecewiseQuotationItem.baseQuantityPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
@Service
@Transactional
public class baseQuantityPiecewiseQuotationItemManager extends
		HibernateDaoSupport implements
		IbaseQuantityPiecewiseQuotationItemManager {

	private final static String HQL = "select o from baseQuantityPiecewiseQuotationItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addbaseQuantityPiecewiseQuotationItem(
			baseQuantityPiecewiseQuotationItem o) {
		getHibernateTemplate().save(o);
		
//		String quantityType=o.getQuantityType();
//		//销售报价单
//		if(quantityType.equals("sales")){
//			//获得报价单信息
//			SalesQuotationSheetItem salesQuotationSheetItem = o
//					.getSalesQuotationSheetItem();
//			salesQuotationSheetItem = (SalesQuotationSheetItem) getSession().get(
//					SalesQuotationSheetItem.class, salesQuotationSheetItem.getId());
//			
//			Float quantity =salesQuotationSheetItem.getQuantity();//获得报价数量
//			//报价单明细原金额
//			Float oldAmount=salesQuotationSheetItem.getAmount();
//			if(quantity!=null){
//				//如果报价数量 在 开始和结束 数量之间。则 给报价单重新复制
//				if(quantity>=o.getFromQuantity()&&quantity<=o.getToQuantity()){
//					Float newUnitPrice=o.getPriceAmount();//获得新单价
//					salesQuotationSheetItem.setUnitPriceAmount(newUnitPrice);
//					//重新计算金额
//					Float amount=quantity*newUnitPrice;
//					//如果特殊费用不为空，则累加 特殊费用
//					if(salesQuotationSheetItem.getPackagePrice()!=null){
//						amount+=salesQuotationSheetItem.getPackagePrice();
//						salesQuotationSheetItem.setAmount(amount);
//					}
//					//持久化更改报价明细
//					getHibernateTemplate().update(salesQuotationSheetItem);
//					
//					/**再更新报价单金额信息**/
//					//获得报价单所有信息
//					SalesQuotationSheet quotation = salesQuotationSheetItem.getSalesQuotationSheet();
//					Float newTotalAmount=quotation.getTotalAmount()+amount;//原总价+新明细价格
//					if(oldAmount!=null){
//						newTotalAmount=newTotalAmount-oldAmount;//减去原明细价格
//					}
//					quotation.setTotalAmount(newTotalAmount);
//					//持久化更新报价单
//					getHibernateTemplate().update(quotation);
//					
//				}
//			}
//			
//		}
//		//采购报价单
//		else if(quantityType.equals("procurement")){
//			//获得报价单信息
//			ProcurementQuotationSheetRecordItem proQuotationSheetItem = o.getProcurementQuotationSheetRecordItem();
//					;
//			proQuotationSheetItem = (ProcurementQuotationSheetRecordItem) getSession().get(
//					ProcurementQuotationSheetRecordItem.class, proQuotationSheetItem.getId());
//			
//			Float quantity =proQuotationSheetItem.getQuantity();//获得报价数量
//			//报价单明细原金额
//			Float oldAmount=proQuotationSheetItem.getAmount();
//			if(quantity!=null){
//				//如果报价数量 在 开始和结束 数量之间。则 给报价单重新复制
//				if(quantity>=o.getFromQuantity()&&quantity<=o.getToQuantity()){
//					Float newUnitPrice=o.getPriceAmount();//获得新单价
//					proQuotationSheetItem.setUnitPriceAmount(newUnitPrice);
//					//重新计算金额
//					Float amount=quantity*newUnitPrice;
//					//如果特殊费用不为空，则累加 特殊费用
//					if(proQuotationSheetItem.getPackagePrice()!=null){
//						amount+=proQuotationSheetItem.getPackagePrice();
//						proQuotationSheetItem.setAmount(amount);
//					}
//					//持久化更改报价明细
//					getHibernateTemplate().update(proQuotationSheetItem);
//					
//					/**再更新报价单金额信息**/
//					//获得报价单所有信息
//					ProcurementQuotationSheetRecord quotation = proQuotationSheetItem.getProcurementQuotationSheetRecord();
//					Float newTotalAmount=quotation.getTotalAmount()+amount;//原总价+新明细价格
//					if(oldAmount!=null){
//						newTotalAmount=newTotalAmount-oldAmount;//减去原明细价格
//					}
//					quotation.setTotalAmount(newTotalAmount);
//					//持久化更新报价单
//					getHibernateTemplate().update(quotation);
//					
//				}
//			}
//			
//		}
//		
	
		
	}

	public baseQuantityPiecewiseQuotationItem updatebaseQuantityPiecewiseQuotationItem(
			Map<String, Object> newValues, String itemID) {
		baseQuantityPiecewiseQuotationItem o = new baseQuantityPiecewiseQuotationItem();
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		return o;
	}

	public void deletebaseQuantityPiecewiseQuotationItem(String itemID) {
		String hql = "update baseQuantityPiecewiseQuotationItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public baseQuantityPiecewiseQuotationItem getbaseQuantityPiecewiseQuotationItemById(
			String sheetId) {
		return (baseQuantityPiecewiseQuotationItem) getSession().get(
				baseQuantityPiecewiseQuotationItem.class, sheetId);
	}

	public List<baseQuantityPiecewiseQuotationItem> querybaseQuantityPiecewiseQuotationItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<baseQuantityPiecewiseQuotationItem> querybaseQuantityPiecewiseQuotationItemListByQuotationItemId(
			String QuotationItemId) {
		String hql = HQL
				+ "where o.salesQuotationSheetItem.id=? and o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, QuotationItemId);
		return query.list();
	}

}
