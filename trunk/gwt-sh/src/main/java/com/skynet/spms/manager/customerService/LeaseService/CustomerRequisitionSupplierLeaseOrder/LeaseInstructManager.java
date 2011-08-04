package com.skynet.spms.manager.customerService.LeaseService.CustomerRequisitionSupplierLeaseOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.order.requisitionSupplierLeaseOrder.CustomerRequisitionSupplierLeaseOrder;

/**
 * 客户申请供应商租赁指令接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface LeaseInstructManager {
	/**
	 * 添加客户申请供应商租赁指令的方法
	 * 
	 * @param o
	 *            客户申请供应商租赁指令对象
	 */
	public void addLeaseInstruct(CustomerRequisitionSupplierLeaseOrder o);

	/**
	 * 删除客户申请供应商租赁指令的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseInstruct(String id);

	/**
	 * 修改客户申请供应商租赁指令的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public CustomerRequisitionSupplierLeaseOrder updateleaseInstruct(
			Map<String, Object> newValues, String itemID);

	/**
	 * 分页查询客户申请供应商租赁指令的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<CustomerRequisitionSupplierLeaseOrder> queryLeaseInstructList(
			int startRow, int endRow);

	/**
	 * 分页查询客户申请供应商租赁指令已发布的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<CustomerRequisitionSupplierLeaseOrder> querySsLeaseInstructList(
			int startRow, int endRow);

}
