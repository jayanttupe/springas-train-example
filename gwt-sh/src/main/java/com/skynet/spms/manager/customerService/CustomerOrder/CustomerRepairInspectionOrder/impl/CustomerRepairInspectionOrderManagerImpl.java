package com.skynet.spms.manager.customerService.CustomerOrder.CustomerRepairInspectionOrder.impl;

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
import com.skynet.spms.manager.customerService.CustomerOrder.CustomerRepairInspectionOrder.CustomerRepairInspectionOrderManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.CustomerOrder.customerRepairInspectionOrder.CustomerRepairInspectionOrder;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 客户送修申请单业务实现
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Service
@Transactional
public class CustomerRepairInspectionOrderManagerImpl extends
		HibernateDaoSupport implements CustomerRepairInspectionOrderManager {

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 添加指令
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addCustomerRepairInspectionOrder(CustomerRepairInspectionOrder o) {
		o.setOrderNumber(uuidGeneral.getSequence("RIO"));
		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setActionDate(new Date());
		pbStatus.setActionDescription("");
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		pbStatus.setVersion(1);
		pbStatus.setOperator(GwtActionHelper.getCurrUser());
		o.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setActionDate(new Date());
		bStatus.setActionDescription("");
		bStatus.setStatus(EntityStatusMonitor.created);
		bStatus.setOperator(GwtActionHelper.getCurrUser());
		bStatus.setVersion(1);
		o.setM_BussinessStatusEntity(bStatus);
		o.setCreateBy(GwtActionHelper.getCurrUser());
		o.setOrderedBy(GwtActionHelper.getCurrUser());
		getHibernateTemplate().save(o);
	}

	/**
	 * 更新指令
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerRepairInspectionOrder
	 */
	public CustomerRepairInspectionOrder updateCustomerRepairInspectionOrder(
			Map<String, Object> newValues, String itemID) {
		CustomerRepairInspectionOrder entity = new CustomerRepairInspectionOrder();
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 删除指令
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteCustomerRepairInspectionOrder(String itemID) {
		String hql = "update CustomerRepairInspectionOrder set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 分页显示指令
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @return
	 * @return List<CustomerRepairInspectionOrder>
	 */
	public List<CustomerRepairInspectionOrder> queryCustomerRepairInspectionOrderList(
			int startRow, int endRow) {
		String hql = "select o from CustomerRepairInspectionOrder o where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 根据编号查询指令
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerRepairInspectionOrder
	 */
	public CustomerRepairInspectionOrder getCustomerRepairInspectionOrderById(
			String sheetId) {
		return getHibernateTemplate().get(CustomerRepairInspectionOrder.class,
				sheetId);
	}

}
