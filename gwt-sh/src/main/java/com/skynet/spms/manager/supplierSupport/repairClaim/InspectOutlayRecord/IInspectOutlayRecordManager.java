package com.skynet.spms.manager.supplierSupport.repairClaim.InspectOutlayRecord;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.supplierSupport.repairClaim.InspectOutlayRecord.InspectOutlayRecord;

/**
 * 供应商检修记录业务接口
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
public interface IInspectOutlayRecordManager {

	/**
	 * 添加供应商检修记录
	 * 
	 * @param
	 * @param o
	 * @return void
	 */
	public void addInspectOutlayRecord(InspectOutlayRecord o);

	/**
	 * 更新供应商检修记录
	 * 
	 * @param
	 * @param newValues
	 * @param
	 * @param itemID
	 * @param
	 * @return
	 * @return InspectOutlayRecord
	 */
	public InspectOutlayRecord updateInspectOutlayRecord(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除供应商检修记录
	 * 
	 * @param
	 * @param itemID
	 * @return void
	 */
	public void deleteInspectOutlayRecord(String itemID);

	/**
	 * 分页查询供应商检修记录
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @return
	 * @return List<InspectOutlayRecord>
	 */
	public List<InspectOutlayRecord> queryInspectOutlayRecordList(int startRow,
			int endRow);

	/**
	 * 根据供应商检修记录编号查询检修记录
	 * 
	 * @param
	 * @param sheetId
	 * @param
	 * @return
	 * @return InspectOutlayRecord
	 */
	public InspectOutlayRecord getInspectOutlayRecordById(String sheetId);

	/**
	 * 根据供应商合同编号查询检修记录
	 * 
	 * @param
	 * @param supplierSupportContractId
	 * @param
	 * @return
	 * @return List<InspectOutlayRecord>
	 */
	public List<InspectOutlayRecord> getInspectOutlayRecordByContractId(
			String supplierSupportContractId);

}
