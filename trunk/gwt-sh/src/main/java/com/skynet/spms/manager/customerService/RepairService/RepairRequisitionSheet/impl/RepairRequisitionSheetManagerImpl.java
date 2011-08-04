package com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 送修申请单业务实现类
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Service
@Transactional
public class RepairRequisitionSheetManagerImpl extends HibernateDaoSupport
		implements RepairRequisitionSheetManager {

	private final static String HQL = "select o from RepairRequisitionSheet o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 添加送修申请单
	 * 
	 * @param @param o 送修申请单
	 * @return void
	 */
	public void addRepairRequisitionSheet(RepairRequisitionSheet item) {
		// 生成编号
		item.setRequisitionSheetNumber(uuidGeneral.getSequence("RC"));
		// 添加申请时间
		item.setRequisitionDate(new Date());
		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setActionDate(new Date());
		pbStatus.setActionDescription("");
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		pbStatus.setVersion(1);
		pbStatus.setOperator(GwtActionHelper.getCurrUser());
		item.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setActionDate(new Date());
		bStatus.setActionDescription("");
		bStatus.setStatus(EntityStatusMonitor.created);
		bStatus.setOperator(GwtActionHelper.getCurrUser());
		bStatus.setVersion(1);
		item.setM_BussinessStatusEntity(bStatus);
		getHibernateTemplate().save(item);
	}

	/**
	 * 更新送修申请单
	 * 
	 * @param @param newValues 客户端修改值
	 * @param @param itemID
	 * @param @return
	 * @return RepairRequisitionSheet
	 */
	public RepairRequisitionSheet updateRepairRequisitionSheet(
			Map<String, Object> newValues, String itemID) {
		RepairRequisitionSheet entity = new RepairRequisitionSheet();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 删除送修申请单
	 * 
	 * @param @param itemID 修申请单编号
	 * @return void
	 */
	public void deleteRepairRequisitionSheet(String itemID) {
		String hql = "update RepairRequisitionSheet set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 分页显示送修申请单
	 * 
	 * @param @param startRow 当前页索引
	 * @param @param endRow 页大小
	 * @param @return
	 * @return List<RepairRequisitionSheet>
	 */
	@SuppressWarnings("unchecked")
	public List<RepairRequisitionSheet> queryRepairRequisitionSheetList(
			int startRow, int endRow) {
		String hql = HQL
				+ " where o.deleted=false order by o.requisitionDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据编号查询送修申请单
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return RepairRequisitionSheet
	 */
	public RepairRequisitionSheet getRepairRequisitionSheetById(String sheetId) {
		RepairRequisitionSheet entity = (RepairRequisitionSheet) getSession()
				.get(RepairRequisitionSheet.class, sheetId);
		return entity;
	}

}
