package com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.supplierSupport.procurement.procurementInquirySheet.IProcurementInquirySheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.QuotationStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.spmsdd.QuotationStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementInquirySheet.ProcurementInquirySheet;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 采购询价单
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class ProcurementInquirySheetManager extends HibernateDaoSupport
		implements IProcurementInquirySheetManager {

	private final static String HQL = "select o from ProcurementInquirySheet o ";

	@Resource
	UUIDGeneral uuidGeneral;
	
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * 添加询价单
	 * @param o 待添加的询价单
	 */
	public void addProcurementInquirySheet(ProcurementInquirySheet o) {
		//计算询价供应商数量
		o.setInquirySuppliersCount(calculationInquirySuppliersCount(o.getM_supplier()));
		
		// 业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);// 已创建
		o.setM_BussinessStatusEntity(bse);

		// 报价状态
		QuotationStatusEntity qsEntity = new QuotationStatusEntity();
		qsEntity.setM_QuotationStatus(QuotationStatus.didNotOffer);// 未报价
		o.setM_QuotationStatusEntity(qsEntity);

		// 发布状态
		BussinessPublishStatusEntity bpse = new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		o.setM_BussinessPublishStatusEntity(bpse);
		
			
		
		// 询价编号
		o.setInquirySheetNumber(uuidGeneral.getSequence("PIQ"));

		// 创建日期
		o.setCreateDate(new Date());
		getHibernateTemplate().save(o);
	}
	/**
	 * 更新询价单
	 * @param newValues 新的数据对象
	 * @param itemID 询价单Id
	 * @return 询价单对象
	 */
	public ProcurementInquirySheet updateProcurementInquirySheet(
			Map<String, Object> newValues, String itemID) {
//		ProcurementInquirySheet o=(ProcurementInquirySheet) getSession().get(
//				ProcurementInquirySheet.class, itemID);
		ProcurementInquirySheet o=new ProcurementInquirySheet();
		BeanPropUtil.fillEntityWithMap(o, newValues);
		//计算询价供应商数量
		o.setInquirySuppliersCount(calculationInquirySuppliersCount(o.getM_supplier()));
		getHibernateTemplate().update(o);
		
		
		return o;
	}

	/**
	 * 删除询价单
	 * @param itemID 询价单id
	 */
	public void deleteProcurementInquirySheet(String itemID) {
		String hql = "update ProcurementInquirySheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据询价单Id 获得询价单
	 * @param sheetId  询价单id
	 * @return　询价单
	 */
	public ProcurementInquirySheet getProcurementInquirySheetById(String sheetId) {
		return (ProcurementInquirySheet) getSession().get(
				ProcurementInquirySheet.class, sheetId);
	}
	
	/**
	 * 分页查询询价单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  询价单列表
	 */
	public List<ProcurementInquirySheet> queryProcurementInquirySheetList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc" ;
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 计算询价供应商数量
	 * @param supplierStr 供应商Id字符串
	 * @return 供应商数量
	 */
	private Integer calculationInquirySuppliersCount(String supplierStr) {
		
		Integer inquirySuppliersCount = 0;
		if (supplierStr != null) {
			String[] supplierArray = supplierStr.split(",");
			if (supplierArray == null) {
				inquirySuppliersCount = 1;
			} else {
				inquirySuppliersCount = supplierArray.length;
			}
		}
		return inquirySuppliersCount;
	}
}
