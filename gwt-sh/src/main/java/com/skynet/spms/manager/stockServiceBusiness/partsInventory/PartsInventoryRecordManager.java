package com.skynet.spms.manager.stockServiceBusiness.partsInventory;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

/**
 * 备件目录信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PartsInventoryRecordManager extends CommonManager<PartsInventoryRecord>{

	/**
	 * 更新寿命件的状态
	 */
	public void updateTimeAndLifePartStatus();
	
	/**
	 * 获取备件目录信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件目录信息
	 */
	public List<PartsInventoryRecord> getPartsInventoryRecord(Map values, int startRow, int endRow);
	
	/**
	 * 补码管理使用，根据补码ID查找所要补码的库存备件
	 * @param repairCodeId
	 * @return 备件目录信息
	 */
	public List<PartsInventoryRecord> findByRepairCodeId(String repairCodeId);
}