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
import com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.RepairRequisitionSheetItemManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.RepairRequisitionSheetItem;
/**
 *送修申请单明细业务实现类
 * @author   taiqichao
 * @version  
 * @Date 	 2011-7-11
 */
@Service
@Transactional
public class RepairRequisitionSheetItemManagerImpl extends HibernateDaoSupport
		implements RepairRequisitionSheetItemManager {

	private final static String HQL = "select o from RepairRequisitionSheetItem o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	/**
	 * 添加送修申请单明细
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addRepairRequisitionSheetItem(RepairRequisitionSheetItem o) {
		super.getHibernateTemplate().save(o);
	}

	/**
	 * 更新送修申请单明细
	 * 
	 * @param @param newValues 客户端修改值
	 * @param @param itemID 编号
	 * @param @return
	 * @return RepairRequisitionSheetItem
	 */
	public RepairRequisitionSheetItem updateRepairRequisitionSheetItem(
			Map<String, Object> newValues, String itemID) {
		RepairRequisitionSheetItem entity = (RepairRequisitionSheetItem) getSession()
				.get(RepairRequisitionSheetItem.class, itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		getHibernateTemplate().update(entity);
		return entity;
	}
	/**
	 * 删除送修申请明细
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteRepairRequisitionSheetItem(String itemID) {
		String hql = "update RepairRequisitionSheetItem set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}
	/**
	 * 分页查询送修申请单明细
	 * 
	 * @param @param startRow 当前页索引
	 * @param @param endRow 页大小
	 * @param @return
	 * @return List<RepairRequisitionSheetItem>
	 */
	@SuppressWarnings("unchecked")
	public List<RepairRequisitionSheetItem> queryRepairRequisitionSheetItemList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}
	/**
	 * 根据编号查询送修申请单明细
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return RepairRequisitionSheetItem
	 */
	public RepairRequisitionSheetItem getRepairRequisitionSheetItemById(
			String sheetId) {
		RepairRequisitionSheetItem entity = (RepairRequisitionSheetItem) getSession()
				.get(RepairRequisitionSheetItem.class, sheetId);
		return entity;
	}

}
