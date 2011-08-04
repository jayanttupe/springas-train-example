package com.skynet.spms.manager.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.SendCardTaskItem;

/**
 * 发卡任务明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface SendCardTaskItemManager extends CommonManager<SendCardTaskItem> {

	/**
	 * 获取发卡任务明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 发卡任务明细信息
	 */
	List<SendCardTaskItem> getSendCardTaskItem(Map values, int startRow, int endRow);

}