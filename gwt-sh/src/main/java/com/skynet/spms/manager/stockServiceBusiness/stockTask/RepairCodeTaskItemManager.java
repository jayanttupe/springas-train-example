package com.skynet.spms.manager.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.RepairCodeTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 补码任务明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface RepairCodeTaskItemManager extends CommonManager<RepairCodeTaskItem> {

	/**
	 * 获取补码任务明细
	 * @param values
	 * @return 补码任务明细
	 */
	List<RepairCodeTaskItem> getRepairCodeTaskItem(Map<String,Object> values);
	
	/**
	 * 更新补码任务明细信息
	 * @param stockTask
	 * @param repairCodeTaskItemList
	 * @return 补码任务明细信息
	 */
	Boolean updateRepairCodeTaskItem(StockTask stockTask, List<RepairCodeTaskItem> repairCodeTaskItemList);

}