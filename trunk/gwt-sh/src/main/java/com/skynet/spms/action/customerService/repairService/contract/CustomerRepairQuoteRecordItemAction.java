package com.skynet.spms.action.customerService.repairService.contract;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.RepairService.contract.CusRepairQuoteRecordItemManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerRepairQuoteRecordItem;

/**
 * 修理记录明细
 * 
 * @author tqc
 */
@Component
public class CustomerRepairQuoteRecordItemAction implements
		DataSourceAction<CustomerRepairQuoteRecordItem> {

	@Resource
	CusRepairQuoteRecordItemManager manager;
	/**
	 * 绑定数据源
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "customerRepairQuteRecordItem_datasource_B" };
	}
	/**
	 * 插入
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(CustomerRepairQuoteRecordItem item) {
		manager.addRepairQuoteRecordItem(item);
	}
	/**
	 * 分页查询
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<CustomerRepairQuoteRecordItem> getList(int startRow, int endRow) {
		return manager.queryRepairQuoteRecordItemList(startRow, endRow);
	}
	/**
	 * 更新
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map, java.lang.String)
	 */
	public CustomerRepairQuoteRecordItem update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateRepairQuoteRecordItem(newValues, itemID);
	}
	/**
	 * 删除
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteRepairQuoteRecordItem(itemID);
	}
	/**
	 * 高级查询 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map, int, int)
	 */
	public List<CustomerRepairQuoteRecordItem> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		if ("getByID".equals(values.get("key"))) {
			String repairQutoReocdId = (String) values.get("repairQutoReocdId");
			return manager.queryRepairQuoteRecordItemListByRecordId(startRow,
					endRow, repairQutoReocdId);
		}
		return null;
	}

}
