package com.skynet.spms.action.customerService.repairService.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.CustomerOrder.CustomerRepairInspectionOrder.CustomerRepairInspectionOrderManager;
import com.skynet.spms.persistence.entity.customerService.CustomerOrder.customerRepairInspectionOrder.CustomerRepairInspectionOrder;

/**
 * 客户送修送检指令
 * 
 * @author tqc
 */
@Component
public class CustomerRepairInspectionOrderAction implements
		DataSourceAction<CustomerRepairInspectionOrder> {

	@Resource
	CustomerRepairInspectionOrderManager manager;

	/**
	 * 绑定数据源 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "customerRepairInsOrder_datasource" };
	}

	/**
	 * 插入 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(CustomerRepairInspectionOrder item) {
		manager.addCustomerRepairInspectionOrder(item);
	}

	/**
	 * 分页查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<CustomerRepairInspectionOrder> getList(int startRow, int endRow) {
		return manager.queryCustomerRepairInspectionOrderList(startRow, endRow);
	}

	/**
	 * 更新 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public CustomerRepairInspectionOrder update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateCustomerRepairInspectionOrder(newValues, itemID);
	}

	/**
	 * 删除 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteCustomerRepairInspectionOrder(itemID);
	}

	/**
	 * 高级查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<CustomerRepairInspectionOrder> doQuery(
			Map<String, Object> values, int startRow, int endRow) {

		return null;
	}

}
