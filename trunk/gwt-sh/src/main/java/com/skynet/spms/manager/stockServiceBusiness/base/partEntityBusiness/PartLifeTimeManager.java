package com.skynet.spms.manager.stockServiceBusiness.base.partEntityBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;
import com.skynet.spms.schedule.ScheduledJob;

/**
 * 备件时寿Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PartLifeTimeManager extends CommonManager<PartsInventoryRecord>{

	/**
	 * 获取备件时寿实体
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件时寿实体
	 */
	public List<PartsInventoryRecord> getPartLifeTime(Map values, int startRow, int endRow);

}