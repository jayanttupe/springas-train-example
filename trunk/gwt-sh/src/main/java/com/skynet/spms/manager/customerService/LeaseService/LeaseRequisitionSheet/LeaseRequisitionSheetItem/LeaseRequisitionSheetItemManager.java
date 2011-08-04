package com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetItem;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetItem;

/**
 * 租赁申请单明细接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface LeaseRequisitionSheetItemManager {
	/**
	 * 添加租赁申请单明细的方法
	 * 
	 * @param o
	 *            租赁申请单明细对象
	 */
	public void addLeaseRequisitionSheetItem(LeaseRequisitionSheetItem o);

	/**
	 * 删除租赁申请单明细的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseRequisitionSheetItem(String id);

	/**
	 * 修改租赁申请单明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public LeaseRequisitionSheetItem updateLeaseRequisitionSheetItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 分页查询租赁申请单的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseRequisitionSheetItem> queryLeaseRequisitionSheetItemList(
			int startRow, int endRow);

	/**
	 * 根据租赁申请单ID查询租赁申请单明细
	 * 
	 * @param leaseRequisitionSheetId
	 *            租赁申请单的ID
	 * @return 租赁申请单明细的对象集合
	 */
	public List<LeaseRequisitionSheetItem> queryLeaseRequisitionSheetItemListByLeaseRequisitionSheetId(
			String leaseRequisitionSheetId);

	/**
	 * 根据租赁申请单号查询租赁申请单明细
	 * 
	 * @param leaseNumber
	 *            租赁申请单号
	 * @return 租赁申请单明细的对象集合
	 */
	public List<LeaseRequisitionSheetItem> queryLeaseRequisitionSheetItemListByLeaseNumber(
			String leaseNumber);
}
