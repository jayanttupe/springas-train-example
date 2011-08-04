package com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet.impl;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet.IProcurementInquirySheetItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesPiecewiseQuotationItem.SalesPiecewiseQuotationItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheetItem;

/**
 * 采购询价单明细
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class ProcurementInquirySheetItemManager extends HibernateDaoSupport
		implements IProcurementInquirySheetItemManager {

	private final static String HQL = "select o from ProcurementInquirySheetItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加询价单明细
	 * @param o 待添加的询价单明细
	 */
	public void addProcurementInquirySheetItem(ProcurementInquirySheetItem o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 更新询价单明细
	 * @param newValues 新的数据对象
	 * @param itemID 询价单明细Id
	 * @return 询价单明细对象
	 */
	public ProcurementInquirySheetItem updateProcurementInquirySheetItem(
			Map<String, Object> newValues, String itemID) {
		ProcurementInquirySheetItem o=(ProcurementInquirySheetItem) getSession().get(
				ProcurementInquirySheetItem.class, itemID);
		//ProcurementInquirySheetItem o=new ProcurementInquirySheetItem();
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		return o;
	}

	/**
	 * 删除询价单明细
	 * @param itemID 询价单明细id
	 */
	public void deleteProcurementInquirySheetItem(String itemID) {
		String hql = "update ProcurementInquirySheetItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据询价单明细Id 获得询价单明细
	 * @param sheetId 询价单明细Id
	 * @return 询价单明细
	 */
	public ProcurementInquirySheetItem getProcurementInquirySheetItemById(String sheetId) {
		return (ProcurementInquirySheetItem) getSession().get(
				ProcurementInquirySheetItem.class, sheetId);
	}
	/**
	 * 分页查询询价单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单明细列表
	 */
	public List<ProcurementInquirySheetItem> queryProcurementInquirySheetItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}
	
	/**
	 * 根据询价单id查询询价单明细
	 * @param inquiryId 询价单Id
	 * @return 询价单明细
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementInquirySheetItem> queryProcurementInquirySheetItemListByInquiryId(
			String inquiryId) {
		String hql = HQL
				+ "where o.procurementInquirySheet.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, inquiryId);
		return query.list();
	}
}
