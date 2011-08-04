package com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheet;

/**
 * 租赁申请单接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface LeaseRequisitionSheetManager {

	/**
	 * 添加租赁申请单的方法
	 * 
	 * @param o
	 *            租赁申请单对象
	 */
	public void addLeaseRequisitionSheet(LeaseRequisitionSheet o);

	/**
	 * 删除租赁申请单的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseRequisitionSheet(String id);

	/**
	 * 修改租赁申请单的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public LeaseRequisitionSheet updateleaseRequisitonSheet(
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
	public List<LeaseRequisitionSheet> queryLeaseRequisitionSheetList(
			int startRow, int endRow);
}
