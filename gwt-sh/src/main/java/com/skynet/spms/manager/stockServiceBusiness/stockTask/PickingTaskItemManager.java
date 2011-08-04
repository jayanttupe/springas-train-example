package com.skynet.spms.manager.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PickingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.ShelvingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 配料单Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickingTaskItemManager extends CommonManager<PickingTaskItem> {

	/**
	 * 获取配料单任务明细
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 配料单任务明细
	 */
	List<PickingTaskItem> getPickingTaskItem(Map values, int startRow, int endRow);
	
	/**
	 * 更新配料单任务明细
	 * @param stockTask
	 * @param pickingTaskItemList
	 * @return 配料单任务明细
	 */
	Boolean updatePickingTaskItem(StockTask stockTask, List<PickingTaskItem> pickingTaskItemList);

}