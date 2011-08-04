package com.skynet.spms.manager.supplierSupport.procurement.procurementPiecewiseQuotationItem.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.procurementPiecewiseQuotationItem.IProcurementPiecewiseQuotationItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementPiecewiseQuotationItem.ProcurementPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;

/**
 * 采购分段报价Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class ProcurementPiecewiseQuotationItemManager extends
		HibernateDaoSupport implements
		IProcurementPiecewiseQuotationItemManager {

	private final static String HQL = "select o from ProcurementPiecewiseQuotationItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * 反向更新报价单单价，金额，
	 * @param o 分段报价对象
	 */
	public void doUpdateQuotation(ProcurementPiecewiseQuotationItem o) {
		// 获得报价单信息
		ProcurementQuotationSheetRecordItem proQuotationSheetItem = o
				.getProcurementQuotationSheetRecordItem();
		;
		proQuotationSheetItem = (ProcurementQuotationSheetRecordItem) getSession()
				.get(ProcurementQuotationSheetRecordItem.class,
						proQuotationSheetItem.getId());

		Float quantity = proQuotationSheetItem.getQuantity();// 获得报价数量
		// 报价单明细原金额
		Float oldAmount = proQuotationSheetItem.getAmount();
		if (quantity != null) {
			// 如果报价数量 在 开始和结束 数量之间。则 给报价单重新复制
			if (quantity >= o.getFromQuantity()
					&& quantity <= o.getToQuantity()) {
				Float newUnitPrice = o.getPriceAmount();// 获得新单价
				proQuotationSheetItem.setUnitPriceAmount(newUnitPrice);
				// 重新计算金额
				Float amount = quantity * newUnitPrice;
				// 如果特殊费用不为空，则累加 特殊费用
				if (proQuotationSheetItem.getPackagePrice() != null) {
					amount += proQuotationSheetItem.getPackagePrice();
					proQuotationSheetItem.setAmount(amount);
				}
				//指定为 分段报价
				proQuotationSheetItem.setIsBreakPrice(true);
				// 持久化更改报价明细
				getHibernateTemplate().update(proQuotationSheetItem);

				/** 再更新报价单金额信息 **/
				// 获得报价单所有信息
				ProcurementQuotationSheetRecord quotation = proQuotationSheetItem
						.getProcurementQuotationSheetRecord();
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
	public void addProcurementPiecewiseQuotationItem(
			ProcurementPiecewiseQuotationItem o) {
		getHibernateTemplate().save(o);
		doUpdateQuotation(o);

	}

	/**
	 * 更新分段报价
	 * @param newValues 新的数据对象
	 * @param itemID 分段报价Id
	 * @return 分段报价对象
	 */
	public ProcurementPiecewiseQuotationItem updateProcurementPiecewiseQuotationItem(
			Map<String, Object> newValues, String itemID) {
		ProcurementPiecewiseQuotationItem o =(ProcurementPiecewiseQuotationItem) getSession().get(
				ProcurementPiecewiseQuotationItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		doUpdateQuotation(o);
		return o;
	}

	/**
	 * 删除分段报价
	 * @param itemID 分段报价id
	 */
	public void deleteProcurementPiecewiseQuotationItem(String itemID) {
		String hql = "update ProcurementPiecewiseQuotationItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据分段报价明细Id 获得分段报价
	 * @param sheetId 分段报价id
	 * @return 分段报价
	 */
	public ProcurementPiecewiseQuotationItem getProcurementPiecewiseQuotationItemById(
			String sheetId) {
		return (ProcurementPiecewiseQuotationItem) getSession().get(
				ProcurementPiecewiseQuotationItem.class, sheetId);
	}

	/**
	 * 分页查询分段报价
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  分段报价明细列表
	 */
	public List<ProcurementPiecewiseQuotationItem> queryProcurementPiecewiseQuotationItemList(
			int startRow, int endRow) { 
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
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
	public List<ProcurementPiecewiseQuotationItem> queryProcurementPiecewiseQuotationItemListByQuotationItemId(
			String quotationItemId) {
		String hql = HQL
				+ "where o.procurementQuotationSheetRecordItem.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, quotationItemId);
		return query.list();
	}

}
