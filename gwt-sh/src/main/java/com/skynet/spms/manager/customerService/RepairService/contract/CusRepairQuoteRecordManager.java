package com.skynet.spms.manager.customerService.RepairService.contract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerRepairQuoteRecord;
/**
 * 送修报价业务接口
 * @author   taiqichao
 * @version  
 * @Date 	 2011-7-11
 */
public interface CusRepairQuoteRecordManager {
	
	/**
	 * 添加送修报价
	 *
	 * @param  @param o   
	 * @return void
	 */
	public void addRepairQuoteRecord(CustomerRepairQuoteRecord o);
	/**
	 * 更新送修报价
	 *
	 * @param  @param newValues
	 * @param  @param itemID
	 * @param  @return   
	 * @return CustomerRepairQuoteRecord
	 */
	public CustomerRepairQuoteRecord updateRepairQuoteRecord(Map<String, Object> newValues, String itemID);

	public void deleteRepairQuoteRecord(String itemID);

	public List<CustomerRepairQuoteRecord> queryRepairQuoteRecordList(int startRow, int endRow);
	
	public CustomerRepairQuoteRecord getRepairQuoteRecordById(String sheetId);
	
	public List<CustomerRepairQuoteRecord> getRepairQuoteRecordByContractID(String contractID);

}
