package com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.CustomerPartRepairDisassembleDataManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.CustomerPartRepairDisassembleData;

/**
 * 
 * 备件拆换信息业务实现类
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Service
@Transactional
public class CustomerPartRepairDisassembleDataManagerImpl extends
		HibernateDaoSupport implements CustomerPartRepairDisassembleDataManager {
	private final static String HQL = "select o from CustomerPartRepairDisassembleData o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加部件拆换信息
	 * 
	 * @param @param o 部件拆换信息
	 * @return void
	 */
	public void addCustomerPartRepairDisassembleData(
			CustomerPartRepairDisassembleData o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 
	 * 更新部件拆换信息
	 * 
	 * @param @param newValues 客户端修改新值
	 * @param @param itemID
	 * @param @return
	 * @return CustomerPartRepairDisassembleData 部件拆换信息
	 */
	public CustomerPartRepairDisassembleData updateCustomerPartRepairDisassembleData(
			Map<String, Object> newValues, String itemID) {
		CustomerPartRepairDisassembleData entity = (CustomerPartRepairDisassembleData) getSession()
				.get(CustomerPartRepairDisassembleData.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 
	 * 删除部件拆换信息
	 * 
	 * @param @param itemID 部件编号
	 * @return void
	 */
	public void deleteCustomerPartRepairDisassembleData(String itemID) {
		String hql = "update CustomerPartRepairDisassembleData set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 
	 * 分页查询部件拆换信息
	 * 
	 * @param @param startRow 当前页索引
	 * @param @param endRow 页大小
	 * @param @return
	 * @return List<CustomerPartRepairDisassembleData> 部件拆换信息集合
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<CustomerPartRepairDisassembleData> queryCustomerPartRepairDisassembleDataList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 
	 * 根据送修申请单编号查询备件拆换信息
	 * 
	 * @param @param sheetId 送修申请单编号
	 * @param @return
	 * @return CustomerPartRepairDisassembleData 备件拆换信息
	 */
	public CustomerPartRepairDisassembleData getCustomerPartRepairDisassembleDataById(
			String sheetId) {
		CustomerPartRepairDisassembleData entity = (CustomerPartRepairDisassembleData) getSession()
				.get(CustomerPartRepairDisassembleData.class, sheetId);
		return entity;
	}

	/**
	 * 
	 * 根据拆换信息编号查询
	 * 
	 * @param @param itemID 编号
	 * @param @return
	 * @return List<CustomerPartRepairDisassembleData>
	 * @throws
	 */
	public List<CustomerPartRepairDisassembleData> getCustomerPartRepairDisassembleDataByItemID(
			String itemID) {
		String hql = HQL
				+ " where o.deleted=false and o.repairRequisitionSheetItemID=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		return query.list();
	}

}
