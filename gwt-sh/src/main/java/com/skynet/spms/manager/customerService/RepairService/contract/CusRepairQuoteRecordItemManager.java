package com.skynet.spms.manager.customerService.RepairService.contract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerRepairQuoteRecordItem;

/**
 * 供应商检修记录业务接口
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
public interface CusRepairQuoteRecordItemManager {

	/**
	 * 添加供应商检修记录
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addRepairQuoteRecordItem(CustomerRepairQuoteRecordItem o);

	/**
	 * 更新供应商检修记录
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerRepairQuoteRecordItem
	 */
	public CustomerRepairQuoteRecordItem updateRepairQuoteRecordItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除供应商检修记录
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteRepairQuoteRecordItem(String itemID);

	/**
	 * 分页查询供应商检修记录
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @return
	 * @return List<CustomerRepairQuoteRecordItem>
	 */
	public List<CustomerRepairQuoteRecordItem> queryRepairQuoteRecordItemList(
			int startRow, int endRow);

	/**
	 * 根据编号查询供应商检修记录
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerRepairQuoteRecordItem
	 */
	public CustomerRepairQuoteRecordItem getRepairQuoteRecordItemById(
			String sheetId);

	/**
	 * 根据送修申请单编号查询客户检修记录
	 * 
	 * @param @param startRow
	 * @param @param endRow
	 * @param @param recordId
	 * @param @return
	 * @return List<CustomerRepairQuoteRecordItem>
	 */
	public List<CustomerRepairQuoteRecordItem> queryRepairQuoteRecordItemListByRecordId(
			int startRow, int endRow, String recordId);

}
