package com.skynet.spms.manager.supplierSupport.repairClaim.RepairQuoteRecord;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecordItem.RepairQuoteRecordItem;

/**
 * 供应商修理报价明细业务接口
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
public interface IRepairQuoteRecordItemManager {

	/**
	 * 添加供应商修理报价明细
	 * 
	 * @param
	 * @param o
	 * @return void
	 */
	public void addRepairQuoteRecordItem(RepairQuoteRecordItem o);

	/**
	 * 更新供应商修理报价明细
	 * 
	 * @param
	 * @param newValues
	 * @param
	 * @param itemID
	 * @param
	 * @return
	 * @return RepairQuoteRecordItem
	 */
	public RepairQuoteRecordItem updateRepairQuoteRecordItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除供应商修理报价明细
	 * 
	 * @param
	 * @param itemID
	 * @return void
	 */
	public void deleteRepairQuoteRecordItem(String itemID);

	/**
	 * 分页查询供应商修理报价明细
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @return
	 * @return List<RepairQuoteRecordItem>
	 */
	public List<RepairQuoteRecordItem> queryRepairQuoteRecordItemList(
			int startRow, int endRow);

	/**
	 * 根据编号查询供应商修理报价明细
	 * 
	 * @param
	 * @param sheetId
	 * @param
	 * @return
	 * @return RepairQuoteRecordItem
	 */
	public RepairQuoteRecordItem getRepairQuoteRecordItemById(String sheetId);

	/**
	 * 分页查询供应商修理报价明细
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @param recordId
	 * @param
	 * @return
	 * @return List<RepairQuoteRecordItem>
	 */
	public List<RepairQuoteRecordItem> queryRepairQuoteRecordItemListByRecordId(
			int startRow, int endRow, String recordId);

}
