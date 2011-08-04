package com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord.impl;

import java.util.List;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementPiecewiseQuotationItem.ProcurementPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.manager.supplierSupport.procurement.procurementQuotationSheetRecord.IProcurementQuotationSheetRecordManager;

/**
 * 采购报价Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class ProcurementQuotationSheetRecordManager extends HibernateDaoSupport
		implements IProcurementQuotationSheetRecordManager {

	private final static String HQL = "select o from ProcurementQuotationSheetRecord o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加报价单
	 * @param o 待添加的报价单
	 */
	public void addProcurementQuotationSheetRecord(
			ProcurementQuotationSheetRecord o) {

		getHibernateTemplate().save(o);

		updateInquiryQuotationNo(o.getProcurementInquirySheet().getId(), "add");

	}

	/**
	 * 更新报价单
	 * @param newValues 新的数据对象
	 * @param itemID 报价单Id
	 * @return 报价单对象
	 */
	public ProcurementQuotationSheetRecord updateProcurementQuotationSheetRecord(
			Map<String, Object> newValues, String itemID) {
		// ProcurementQuotationSheetRecord o=(ProcurementQuotationSheetRecord)
		// getSession().get(
		// ProcurementQuotationSheetRecord.class, itemID);
		ProcurementQuotationSheetRecord o = new ProcurementQuotationSheetRecord();
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);

		return o;
	}

	/**
	 * 删除报价单
	 * @param itemID 报价单id
	 */
	public void deleteProcurementQuotationSheetRecord(String itemID) {
		String hql = "update ProcurementQuotationSheetRecord set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
		ProcurementQuotationSheetRecord o = (ProcurementQuotationSheetRecord) getSession()
				.get(ProcurementQuotationSheetRecord.class, itemID);
		updateInquiryQuotationNo(o.getProcurementInquirySheet().getId(), "del");

	}

	/**
	 * 根据报价单Id 获得报价单
	 * @param sheetId 报价打ID
	 * @return 报价单
	 */
	public ProcurementQuotationSheetRecord getProcurementQuotationSheetRecordById(
			String sheetId) {
		return (ProcurementQuotationSheetRecord) getSession().get(
				ProcurementQuotationSheetRecord.class, sheetId);
	}

	/**
	 * 分页查询报价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  报价单列表
	 */
	public List<ProcurementQuotationSheetRecord> queryProcurementQuotationSheetRecordList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 更改询价单供应商报价数
	 * 
	 * @param inquiryId
	 *            询价单id
	 * @param operation
	 *            增加操作 还是减少操作
	 */
	private void updateInquiryQuotationNo(String inquiryId, String operation) {
		ProcurementInquirySheet inquiry = getHibernateTemplate().get(
				ProcurementInquirySheet.class, inquiryId);
		if (inquiry != null) {

			Integer quotationNo = inquiry.getQuotationSuppliersCount();
			if (quotationNo == null) {
				quotationNo = 0;
			}
			if (operation.equals("add")) {
				inquiry.setQuotationSuppliersCount(quotationNo + 1);
			} else if (operation.equals("del")) {
				if (quotationNo != 0) {
					inquiry.setQuotationSuppliersCount(quotationNo - 1);
				}
			}
			getHibernateTemplate().update(inquiry);
		}
	}

}
