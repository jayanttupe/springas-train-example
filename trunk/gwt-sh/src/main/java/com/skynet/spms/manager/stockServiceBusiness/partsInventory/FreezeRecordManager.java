package com.skynet.spms.manager.stockServiceBusiness.partsInventory;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.freezeRecord.FreezeRecord;

/**
 * 备件冻结信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface FreezeRecordManager extends CommonManager<FreezeRecord>{
	
	/**
	 * 获取备件冻结信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件冻结信息
	 */
	public List<FreezeRecord> getFreezeRecord(Map values, int startRow, int endRow);

	/**
	 * 保存备件冻结信息
	 * @param freezeRecord
	 * @return 备件冻结信息
	 */
	public FreezeRecord saveFreezeRecord(FreezeRecord freezeRecord);
	
	/**
	 * 解除备件冻结状态
	 * @param freezeRecordID
	 * @param partsInventoryID
	 */
	public void releaseFreezeRecord(String freezeRecordID, String partsInventoryID);
}