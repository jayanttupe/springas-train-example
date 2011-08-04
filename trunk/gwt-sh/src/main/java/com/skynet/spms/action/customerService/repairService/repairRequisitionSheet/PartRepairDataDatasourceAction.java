package com.skynet.spms.action.customerService.repairService.repairRequisitionSheet;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.CustomerPartRepairDisassembleDataManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.CustomerPartRepairDisassembleData;

/**
 * 备件交换信息
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Component
public class PartRepairDataDatasourceAction implements
		DataSourceAction<CustomerPartRepairDisassembleData> {

	@Autowired
	private CustomerPartRepairDisassembleDataManager customerPartRepairDisassembleDataManager;

	/**
	 * 绑定数据源 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "customerPartRepairDisassembleData_dataSource" };
	}

	/**
	 * 插入 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(CustomerPartRepairDisassembleData item) {
		customerPartRepairDisassembleDataManager
				.addCustomerPartRepairDisassembleData(item);
	}

	/**
	 * 分页查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<CustomerPartRepairDisassembleData> getList(int startRow,
			int endRow) {
		List<CustomerPartRepairDisassembleData> result = customerPartRepairDisassembleDataManager
				.queryCustomerPartRepairDisassembleDataList(startRow, endRow);
		return result;
	}

	/**
	 * 更新 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public CustomerPartRepairDisassembleData update(
			Map<String, Object> newValues, String itemID) {
		return customerPartRepairDisassembleDataManager
				.updateCustomerPartRepairDisassembleData(newValues, itemID);
	}

	/**
	 * 删除 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		customerPartRepairDisassembleDataManager
				.deleteCustomerPartRepairDisassembleData(itemID);
	}

	/**
	 * 条件查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<CustomerPartRepairDisassembleData> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		if (null != values.get("getByItemID")) {
			String itemID = (String) values.get("getByItemID");
			return customerPartRepairDisassembleDataManager
					.getCustomerPartRepairDisassembleDataByItemID(itemID);
		}
		return null;
	}

}
