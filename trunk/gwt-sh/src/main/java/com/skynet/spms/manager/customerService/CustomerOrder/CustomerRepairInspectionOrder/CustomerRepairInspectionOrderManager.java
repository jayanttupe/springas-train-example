package com.skynet.spms.manager.customerService.CustomerOrder.CustomerRepairInspectionOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.CustomerOrder.customerRepairInspectionOrder.CustomerRepairInspectionOrder;

/**
 * 客户送修申请单业务接口
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
public interface CustomerRepairInspectionOrderManager {
	/**
	 * 添加指令
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addCustomerRepairInspectionOrder(CustomerRepairInspectionOrder o);

	/**
	 * 更新指令
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerRepairInspectionOrder
	 */
	public CustomerRepairInspectionOrder updateCustomerRepairInspectionOrder(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除指令
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteCustomerRepairInspectionOrder(String itemID);

	/**
	 * 分页显示指令
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @return
	 * @return List<CustomerRepairInspectionOrder>
	 */
	public List<CustomerRepairInspectionOrder> queryCustomerRepairInspectionOrderList(
			int startRow, int endRow);

	/**
	 * 根据编号查询指令
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerRepairInspectionOrder
	 */
	public CustomerRepairInspectionOrder getCustomerRepairInspectionOrderById(
			String sheetId);

}
