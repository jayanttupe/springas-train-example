package com.skynet.spms.manager.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.ShelvingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 上架任务管理明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ShelvingTaskItemManager extends CommonManager<ShelvingTaskItem> {

	/**
	 * 获取上架任务管理明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 上架任务管理明细信息
	 */
	List<ShelvingTaskItem> getShelvingTaskItem(Map values, int startRow, int endRow);
	
	/**
	 * 更新上架任务管理明细信息
	 * @param stockTask
	 * @param shelvingTaskItemList
	 * @return 上架任务管理明细信息
	 */
	Boolean updateShelvingTaskItem(StockTask stockTask, List<ShelvingTaskItem> shelvingTaskItemList);

}