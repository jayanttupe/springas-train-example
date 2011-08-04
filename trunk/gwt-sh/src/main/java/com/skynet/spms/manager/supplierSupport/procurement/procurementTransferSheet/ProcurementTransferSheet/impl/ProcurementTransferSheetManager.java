package com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet.impl;

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
import com.skynet.spms.manager.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet.IProcurementTransferSheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementQuotationSheetRecord.ProcurementQuotationSheetRecord;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.procurementTransferSheet.ProcurementTransferSheet;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 调拨单Manager实现类
 * @author  李宁
 * @version 1.0
 * @date 2011-07-08
 */
@Service
@Transactional
public class ProcurementTransferSheetManager extends HibernateDaoSupport
		implements IProcurementTransferSheetManager {

	private final static String HQL = "select o from ProcurementTransferSheet o ";

	@Resource
	UUIDGeneral uuidGeneral;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加调拨单
	 * @param o 待添加的调拨单
	 */
	public void addProcurementTransferSheet(ProcurementTransferSheet o) {
		// 编号
		o.setTransferSheetNumber(uuidGeneral.getSequence("ATF"));

		// 业务状态
		BussinessStatusEntity bse = new BussinessStatusEntity();
		bse.setStatus(EntityStatusMonitor.created);// 已创建
		o.setM_BussinessStatusEntity(bse);

		// 发布状态
		BussinessPublishStatusEntity bpse = new BussinessPublishStatusEntity();
		bpse.setM_PublishStatus(PublishStatus.unpublished);
		o.setM_BussinessPublishStatusEntity(bpse);

		// 创建日期
		o.setCreateDate(new Date());

		getHibernateTemplate().save(o);
	}

	/**
	 * 更新调拨单
	 * @param newValues 新的数据对象
	 * @param itemID 调拨单Id
	 * @return 调拨单对象
	 */
	public ProcurementTransferSheet updateProcurementTransferSheet(
			Map<String, Object> newValues, String itemID) {
//		ProcurementTransferSheet o = (ProcurementTransferSheet) getSession().get(
//				ProcurementTransferSheet.class, itemID);
		
		ProcurementTransferSheet o = new ProcurementTransferSheet();
		
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		return o;
	}

	/**
	 * 删除调拨单
	 * @param itemID 调拨单id
	 */
	public void deleteProcurementTransferSheet(String itemID) {
		String hql = "update ProcurementTransferSheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据调拨单Id 获得调拨单
	 * @param sheetId 报价打ID
	 * @return 调拨单
	 */
	public ProcurementTransferSheet getProcurementTransferSheetById(
			String sheetId) {
		return (ProcurementTransferSheet) getSession().get(
				ProcurementTransferSheet.class, sheetId);
	}

	/**
	 * 分页查询调拨单信息
	 * @param startRow 开始行数
	 * @param endRow  结束行数
	 * @return  调拨单列表
	 */
	public List<ProcurementTransferSheet> queryProcurementTransferSheetList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false order by o.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
