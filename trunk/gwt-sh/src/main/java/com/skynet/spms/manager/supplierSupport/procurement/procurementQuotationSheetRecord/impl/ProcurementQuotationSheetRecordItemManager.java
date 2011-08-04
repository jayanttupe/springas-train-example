package com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheetItem;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesPiecewiseQuotationItem.SalesPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.customerService.sales.salesQuotationSheet.SalesQuotationSheetItem;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementPiecewiseQuotationItem.ProcurementPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord.IProcurementQuotationSheetRecordItemManager;

import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;

/**
 * 采购报价明细Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class ProcurementQuotationSheetRecordItemManager extends
		HibernateDaoSupport implements
		IProcurementQuotationSheetRecordItemManager {

	private final static String HQL = "select o from ProcurementQuotationSheetRecordItem o ";

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
		String hql = "select sum(o.amount) from ProcurementQuotationSheetRecordItem o where o.deleted=false and o.procurementQuotationSheetRecord.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, parimaryKey);
		Object obj = query.uniqueResult();
		
		if(obj!=null){
			float amount = Float.parseFloat(obj+"");
			hql = "update ProcurementQuotationSheetRecord set totalAmount="
					+ amount + " where id=?";
			query = getSession().createQuery(hql);
			query.setParameter(0, parimaryKey);
			query.executeUpdate();
		}
		
	}

	/***
	 * ' 初始化 数值属性
	 * 
	 * @param o 报价单明细对象
	 */
	private void initNumerical(ProcurementQuotationSheetRecordItem o) {
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
	 * @param o 报价明细对象
	 */
	private void doPiecewiseQuotationItem(ProcurementQuotationSheetRecordItem o) {
		if (o.getIsBreakPrice() == null) {
			o.setIsBreakPrice(false);
		}
		// 判断是否分段报价
		if (o.getIsBreakPrice()) {
			// 获得分段报价集合
			List<ProcurementPiecewiseQuotationItem> piecewiseList = new ArrayList<ProcurementPiecewiseQuotationItem>();
			String hql = "from  ProcurementPiecewiseQuotationItem  o where o.procurementQuotationSheetRecordItem.id=?";
			Query query = getSession().createQuery(hql);
			query.setParameter(0, o.getId());
			piecewiseList = query.list();
			Float quantity = o.getQuantity();// 报价数量
			for (ProcurementPiecewiseQuotationItem piecewise : piecewiseList) {
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
	 * 添加报价单明细
	 * @param o 待添加的报价单明细
	 */
	public void addProcurementQuotationSheetRecordItem(
			ProcurementQuotationSheetRecordItem o) {
		o.setIsJoinParity(false);// 默认未比价
		initNumerical(o);// 初始或数字属性
		ProcurementInquirySheetItem inquiryItem = o
				.getProcurementInquirySheetItem();

		inquiryItem = (ProcurementInquirySheetItem) getSession().get(
				ProcurementInquirySheetItem.class, inquiryItem.getId());
		// 询价单
		ProcurementInquirySheet proInquirySheet = inquiryItem
				.getProcurementInquirySheet();
		// 设置明细已报价
		QuotationStatusEntity qsEntity = new QuotationStatusEntity();
		qsEntity.setM_QuotationStatus(QuotationStatus.alreadyOffer);// 已报价
		inquiryItem.setM_QuotationStatusEntity(qsEntity);

		List<ProcurementInquirySheetItem> itemList = proInquirySheet
				.getM_ProcurementInquirySheetItem();
		boolean ifAllQuotation = true;// 是否全部报价
		for (ProcurementInquirySheetItem proInquirySheetItem : itemList) {
			if (proInquirySheetItem != null) {
				if (proInquirySheetItem.getM_QuotationStatusEntity().m_QuotationStatus
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
		proInquirySheet.setM_QuotationStatusEntity(inquiryStatus);

		// 设置业务状态为已处理
		BussinessStatusEntity busStatus = new BussinessStatusEntity();
		busStatus.setStatus(EntityStatusMonitor.processed);
		proInquirySheet.setM_BussinessStatusEntity(busStatus);

		// 处理分段报价
		doPiecewiseQuotationItem(o);

		getHibernateTemplate().save(o);

		updateAmount(o.getProcurementQuotationSheetRecord().getId());// 更改主表金额

		// 更改询价单
		getHibernateTemplate().update(proInquirySheet);

	}

	/**
	 * 更新报价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 报价单明细Id
	 * @return 报价单明细对象
	 */
	public ProcurementQuotationSheetRecordItem updateProcurementQuotationSheetRecordItem(
			Map<String, Object> newValues, String itemID) {
//		ProcurementQuotationSheetRecordItem o = (ProcurementQuotationSheetRecordItem) getSession()
//				.get(ProcurementQuotationSheetRecordItem.class, itemID);
		
		ProcurementQuotationSheetRecordItem o = new ProcurementQuotationSheetRecordItem();
		
		BeanPropUtil.fillEntityWithMap(o, newValues);
		initNumerical(o);// 初始或数字属性
		doPiecewiseQuotationItem(o);
		getHibernateTemplate().update(o);
		updateAmount(o.getProcurementQuotationSheetRecord().getId());// 更改主表金额
		return o;
	}

	/**
	 * 删除报价单明细
	 * @param itemID 报价单明细id
	 */
	public void deleteProcurementQuotationSheetRecordItem(String itemID) {
		ProcurementQuotationSheetRecordItem o = (ProcurementQuotationSheetRecordItem) getSession()
				.get(ProcurementPiecewiseQuotationItem.class, itemID);
		o.setDeleted(true);
		getHibernateTemplate().update(o);
		updateAmount(o.getProcurementQuotationSheetRecord().getId());// 更改主表金额
	}

	/**
	 * 根据报价单明细Id 获得报价单明细
	 * @param sheetId 报价单ID
	 * @return 报价单明细
	 */
	public ProcurementQuotationSheetRecordItem getProcurementQuotationSheetRecordItemById(
			String sheetId) {
		return (ProcurementQuotationSheetRecordItem) getSession().get(
				ProcurementQuotationSheetRecordItem.class, sheetId);
	}

	
	/**
	 * 分页查询报价单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单明细列表
	 */
	public List<ProcurementQuotationSheetRecordItem> queryProcurementQuotationSheetRecordItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}
	/**
	 * 根据报价单单 Id获得报价单明细信息
	 * @param quotationId 报价单id
	 * @return 报价单明细列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementQuotationSheetRecordItem> queryProcurementQuotationSheetItemListByQuotationId(
			String quotationId) {
		String hql = HQL
				+ "where o.procurementQuotationSheetRecord.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, quotationId);
		return query.list();
	}

	/**
	 * 根据条件分页筛选
	 * @param values 条件Map
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单明细列表
	 */
	@Override
	public List<ProcurementQuotationSheetRecordItem> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		Set<String> keys = values.keySet();
		String hql = HQL + " where o.deleted=false";

		for (String key : keys) {
			Object obj = values.get(key);
			if (key.equals("key") || key.equals("r") || key.equals("_r")) {
				continue;
			}
			if (obj instanceof String) {
				hql += " and " + key + "='" + values.get(key) + "'";
			} else {
				hql += " and " + key + "=" + values.get(key);
			}
		}
		hql += " order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}


}
