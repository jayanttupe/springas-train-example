package com.skynet.spms.manager.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PackingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PickingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 装箱单任务明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PackingTaskItemManager extends CommonManager<PackingTaskItem> {

	/**
	 * 获取装箱单任务明细
	 * @param values
	 * @param startRow
	 * @param endRow 
	 * @return 装箱单任务明细
	 */
	List<PackingTaskItem> getPackingTaskItem(Map values, int startRow, int endRow);
	
	/**
	 * 更新装箱单任务明细
	 * @param stockTask
	 * @param packingTaskItemList
	 * @return 装箱单任务明细
	 */
	Boolean updatePackingTaskItem(StockTask stockTask, List<PackingTaskItem> packingTaskItemList);

}