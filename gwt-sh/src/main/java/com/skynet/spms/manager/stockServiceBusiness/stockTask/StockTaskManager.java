package com.skynet.spms.manager.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.spmsdd.TaskType;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 所有手持机相关项目Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockTaskManager extends CommonManager<StockTask> {

	/**
	 * 获取相关任务
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 相关任务信息
	 */
	List<StockTask> getStockTask(Map values, int startRow, int endRow);

	/**
	 * 新增上架任务信息
	 * @param inStockRecordIDs
	 */
	void insertShelveTask(String[] inStockRecordIDs);

	/**
	 * 新增配料单任务信息
	 * @param pickinglistIDs
	 */
	void insertPickingTask(String[] pickinglistIDs);

	/**
	 * 新增发卡任务信息
	 * @param inStockRecordIDs
	 */
	void insertSendCardTask(String[] inStockRecordIDs);

	/**
	 * 更新任务状态
	 * @param stockTask
	 */
	void updateStockTaskStatus(StockTask stockTask);

	/**
	 * 根据标签唯一序列号查找任务明细
	 * @param tagIdentifierCode
	 * @return
	 */
	List<StockTask> getTaskListByTagIdentifierCode(String tagIdentifierCode);

	/**
	 * 新增装箱任务
	 * @param pickinglistIDs
	 */
	void insertPackingTask(String[] pickinglistIDs);
	
	/**
	 * 新增补码任务
	 * @param repairCodeIDs
	 */
	void insertRepairCodeTask(String[] repairCodeIDs);

	/**
	 * 根据类型获取任务
	 * @param type
	 * @return 任务
	 */
	List<StockTask> getStockTaskByType(TaskType type);

	/**
	 * 生成盘点任务
	 * @param stockCheckID
	 */
	void insertStockCountTask(String stockCheckID);
}