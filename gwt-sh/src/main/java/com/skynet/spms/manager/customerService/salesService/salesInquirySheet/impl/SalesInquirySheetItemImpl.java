package com.skynet.spms.manager.customerService.salesService.salesInquirySheet.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.salesService.salesInquirySheet.SalesInquirySheetItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesInquirySheet.SalesInquirySheetItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem;

/**
 * 销售收询价单明细
 * @author  李宁
 * @version 1.0
 * @Date 2011-07-11
 */
@Service
@Transactional
public class SalesInquirySheetItemImpl extends HibernateDaoSupport implements
		SalesInquirySheetItemManager {

	private final static String HQL = "select o from SalesInquirySheetItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * 添加询价单明细
	 * @param o 待添加的询价单明细
	 */
	public void addSalesInquirySheetItem(SalesInquirySheetItem o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 修改询价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 询价单明细Id
	 * @return 询价单明细对象
	 */
	public SalesInquirySheetItem updateSalesInquirySheetItem(
			Map<String, Object> newValues, String itemID) {
//		SalesInquirySheetItem entity = (SalesInquirySheetItem) getSession().get(
//				SalesInquirySheetItem.class, itemID);
		SalesInquirySheetItem entity = new SalesInquirySheetItem();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 删除询价单明细
	 * @param itemID 询价单明细id
	 */
	public void deleteSalesInquirySheetItem(String itemID) {
		String hql = "update SalesInquirySheetItem set deleted=true where id=? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	
	/**
	 * 根据询价单Id 获得询价单明细
	 * @param sheetId 询价单明细Id
	 * @return 询价单明细
	 */
	@SuppressWarnings("unchecked")
	public List<SalesInquirySheetItem> querySalesInquirySheetItemListByInquiryId(
			String inquiryId) {
		String hql = HQL + "where o.salesInquirySheet.id=? and o.deleted=false order by o.createDate desc";

		Query query = getSession().createQuery(hql);
		query.setParameter(0, inquiryId);
		return query.list();
	}

	/**
	 * 根据客户id获得 询价单信息
	 * @param customerId 客户id
	 * @return 询价单列表
	 */
	public List<SalesInquirySheetItem> querySalesInquirySheetItemListByCustomerId(
			String customerId) {
		String hql = HQL
				+ "where o.salesInquirySheet.m_CustomerIdentificationCode.id=? "
				+"and o.salesInquirySheet.m_QuotationStatusEntity.m_QuotationStatus<>'alreadyOffer' and o.deleted=false order by o.createDate desc";

		Query query = getSession().createQuery(hql);
		query.setParameter(0, customerId);
		return query.list();
	}

	/**
	 * 获得所有询价单明细
	 * @return 询价单明细
	 */
	public List<SalesInquirySheetItem> querySalesInquirySheetItemAll() {
		String hql = HQL + "where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
