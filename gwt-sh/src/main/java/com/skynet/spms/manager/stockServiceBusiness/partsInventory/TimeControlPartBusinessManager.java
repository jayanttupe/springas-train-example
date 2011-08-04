package com.skynet.spms.manager.stockServiceBusiness.partsInventory;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

/**
 * 时控件相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface TimeControlPartBusinessManager extends CommonManager<PartsInventoryRecord>{
	
	/**
	 * 获取所有时控件的信息
	 * @param startRow
	 * @param endRow
	 * @return 时控件相关信息
	 */
	public List<PartsInventoryRecord> getTimeControlPartBusiness(Map values, int startRow, int endRow);

}