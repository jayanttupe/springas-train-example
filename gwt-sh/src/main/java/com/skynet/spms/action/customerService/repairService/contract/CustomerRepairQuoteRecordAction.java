package com.skynet.spms.action.customerService.repairService.contract;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.RepairService.contract.CusRepairQuoteRecordManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerRepairQuoteRecord;

/**
 *  修理记录
 * @author   taiqichao
 * @version  
 * @Date 	 2011-7-11
 */
@Component
public class CustomerRepairQuoteRecordAction implements
		DataSourceAction<CustomerRepairQuoteRecord> {

	@Resource
	CusRepairQuoteRecordManager manager;
	/**
	 * 绑定数据源
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "customerRepairQuteRecord_datasource_B" };
	}
	/**
	 * 插入
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(CustomerRepairQuoteRecord item) {
		manager.addRepairQuoteRecord(item);
	}
	/**
	 * 分页查询
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<CustomerRepairQuoteRecord> getList(int startRow, int endRow) {
		return manager.queryRepairQuoteRecordList(startRow, endRow);
	}
	/**
	 * 更新 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map, java.lang.String)
	 */
	public CustomerRepairQuoteRecord update(Map<String, Object> newValues,
			String itemID) {
		return manager.updateRepairQuoteRecord(newValues, itemID);
	}
	/**
	 * 删除
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deleteRepairQuoteRecord(itemID);
	}
	/**
	 * 高级查询
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map, int, int)
	 */
	public List<CustomerRepairQuoteRecord> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("getByID".equals(values.get("key"))) {
			String contractId = (String) values.get("contractId");
			return manager.getRepairQuoteRecordByContractID(contractId);
		}else{
			return manager.queryRepairQuoteRecordList(startRow, endRow);
		}
	}

}
