package com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem.IProcurementTransferSheetItemManager;
import com.skynet.spms.persistence.entity.customerService.sales.salesContract.SalesContractItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecordItem;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheetItem;

/**
 * 调拨单明细Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class ProcurementTransferSheetItemManager extends HibernateDaoSupport
		implements IProcurementTransferSheetItemManager {

	private final static String HQL = "select o from ProcurementTransferSheetItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * 添加调拨单明细
	 * @param o 待添加的调拨单明细
	 */
	public void addProcurementTransferSheetItem(ProcurementTransferSheetItem o) {
		getHibernateTemplate().save(o);
		updateAmount(o.getProcurementTransferSheet().getId());
	}

	/**
	 * 更新合同中的金额
	 * 
	 * @param pactid 调拨单id
	 */
	private void updateAmount(String pactid) {
		String hql = "select count(o.id) from ProcurementTransferSheetItem o where o.deleted=false and o.procurementTransferSheet.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		Object obj = query.uniqueResult();
		int count = 0;
		if (obj != null) {
			count = Integer.parseInt(obj+"");
		}
		hql = "update ProcurementTransferSheet set itemCount=" + count + " where id=?";
		query = getSession().createQuery(hql);
		query.setParameter(0, pactid);
		query.executeUpdate();
	}

	/**
	 * 更新调拨单明细
	 * @param newValues 新的数据对象
	 * @param itemID 调拨单明细Id
	 * @return 调拨单明细对象
	 */
	public ProcurementTransferSheetItem updateProcurementTransferSheetItem(
			Map<String, Object> newValues, String itemID) {
//		ProcurementTransferSheetItem o = (ProcurementTransferSheetItem) getSession().get(
//				ProcurementTransferSheetItem.class, itemID);
		ProcurementTransferSheetItem o =new ProcurementTransferSheetItem();
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		updateAmount(o.getProcurementTransferSheet().getId());
		return o;
	}

	/**
	 * 删除调拨单明细
	 * @param itemID 调拨单明细id
	 */
	public void deleteProcurementTransferSheetItem(String itemID) {
		ProcurementTransferSheetItem transferSheetItem = getHibernateTemplate().get(
				ProcurementTransferSheetItem.class, itemID);
		transferSheetItem.setDeleted(true);
		getHibernateTemplate().update(transferSheetItem);
		updateAmount(transferSheetItem.getProcurementTransferSheet().getId());
	}

	/**
	 * 根据调拨单明细Id 获得调拨单明细
	 * @param sheetId 报价打ID
	 * @return 调拨单明细
	 */
	public ProcurementTransferSheetItem getProcurementTransferSheetItemById(
			String sheetId) {
		return (ProcurementTransferSheetItem) getSession().get(
				ProcurementTransferSheetItem.class, sheetId);
	}

	/**
	 * 分页查询调拨单明细信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  调拨单明细列表
	 */
	public List<ProcurementTransferSheetItem> queryProcurementTransferSheetItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据调拨单Id 获得调拨单明细信息
	 * @param transferId 调拨单Id
	 * @return 调拨单明细列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProcurementTransferSheetItem> queryProcurementTransferSheetItemByTransferId(
			String transferId) {
		String hql = HQL
				+ "where o.procurementTransferSheet.id=? and o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, transferId);
		return query.list();
	}

}
