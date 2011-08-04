package com.skynet.spms.manager.customerService.RepairService.contract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract.CustomerInspectOutlayRecord;

/**
 * 客户送修记录业务接口
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
public interface CusInspectOutlayRecordManager {
	/**
	 * 添加客户送修记录
	 * 
	 * @param @param o
	 * @return void
	 */
	public void addInspectOutlayRecord(CustomerInspectOutlayRecord o);

	/**
	 * 更新 客户送修记录
	 * 
	 * @param @param newValues
	 * @param @param itemID
	 * @param @return
	 * @return CustomerInspectOutlayRecord
	 */
	public CustomerInspectOutlayRecord updateInspectOutlayRecord(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除客户送修记录
	 * 
	 * @param @param itemID
	 * @return void
	 */
	public void deleteInspectOutlayRecord(String itemID);

	/**
	 * 分页查询送修记录
	 * 
	 * @param @param startRow 当前页的索引
	 * @param @param endRow 页大小
	 * @param @return
	 * @return List<CustomerInspectOutlayRecord>
	 */
	public List<CustomerInspectOutlayRecord> queryInspectOutlayRecordList(
			int startRow, int endRow);

	/**
	 * 根据编号查询送修记录
	 * 
	 * @param @param sheetId
	 * @param @return
	 * @return CustomerInspectOutlayRecord
	 */
	public CustomerInspectOutlayRecord getInspectOutlayRecordById(String sheetId);

	/**
	 * 根据合同编号查询送修记录
	 * 
	 * @param @param contractId
	 * @param @return
	 * @return List<CustomerInspectOutlayRecord>
	 */
	public List<CustomerInspectOutlayRecord> getInspectOutlayRecordByContractId(
			String contractId);

}
