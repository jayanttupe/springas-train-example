package com.skynet.spms.manager.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockCheckTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 盘点任务明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockCheckTaskItemManager extends CommonManager<StockCheckTaskItem> {

	/**
	 * 获取盘点任务明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点任务明细信息
	 */
	List<StockCheckTaskItem> getStockCheckTaskItem(Map values, int startRow, int endRow);
	
	/**
	 * 更新盘点任务明细信息
	 * @param stockTask
	 * @param stockCheckTaskItemList
	 * @return 盘点任务明细信息
	 */
	Boolean updateStockCheckTaskItem(StockTask stockTask, List<StockCheckTaskItem> stockCheckTaskItemList);

}
