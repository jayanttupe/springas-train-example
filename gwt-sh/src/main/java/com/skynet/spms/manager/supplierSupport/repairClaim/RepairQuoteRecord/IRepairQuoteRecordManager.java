package com.skynet.spms.manager.supplierSupport.repairClaim.RepairQuoteRecord;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord.RepairQuoteRecord;

/**
 * 供应商检修报价
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
public interface IRepairQuoteRecordManager {

	/**
	 * 添加供应商检修报价
	 * 
	 * @param
	 * @param o
	 * @return void
	 */
	public void addRepairQuoteRecord(RepairQuoteRecord o);

	/**
	 * 更新供应商检修报价
	 * 
	 * @param
	 * @param newValues
	 * @param
	 * @param itemID
	 * @param
	 * @return
	 * @return RepairQuoteRecord
	 */
	public RepairQuoteRecord updateRepairQuoteRecord(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除供应商检修报价
	 * 
	 * @param
	 * @param itemID
	 * @return void
	 */
	public void deleteRepairQuoteRecord(String itemID);

	/**
	 * 分页查询供应商检修报价
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @return
	 * @return List<RepairQuoteRecord>
	 */
	public List<RepairQuoteRecord> queryRepairQuoteRecordList(int startRow,
			int endRow);

	/**
	 * 根据编号查询供应商检修报价
	 * 
	 * @param
	 * @param sheetId
	 * @param
	 * @return
	 * @return RepairQuoteRecord
	 */
	public RepairQuoteRecord getRepairQuoteRecordById(String sheetId);

	/**
	 * 根据供应商合同编号查询供应商检修报价
	 * 
	 * @param
	 * @param contractID
	 * @param
	 * @return
	 * @return List<RepairQuoteRecord>
	 */
	public List<RepairQuoteRecord> getRepairQuoteRecordByContractID(
			String contractID);

}
