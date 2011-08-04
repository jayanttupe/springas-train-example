package com.skynet.spms.action.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 描述：所有手持机相关项目处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockTaskDatasourceAction implements
		DataSourceAction<StockTask> {
	@Autowired
	private StockTaskManager stockTaskManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "stockTask_dataSource" };
	}

	/**
	 * 新增手持机相关项目
	 * @param stockTask
	 */
	@Override
	public void insert(StockTask stockTask) {
	}

	/**
	 * 更新手持机相关项目
	 * @param newValues
	 * @param number
	 * @return null
	 */
	@Override
	public StockTask update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除手持机相关项目
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询手持机相关项目
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 手持机相关项目
	 */
	@Override
	public List<StockTask> doQuery(Map values, int startRow, int endRow) {
		return stockTaskManager.getStockTask(values, startRow, endRow);
	}

	/**
	 * 获取所有手持机相关项目
	 * @param startRow
	 * @param endRow
	 * @return 手持机相关项目
	 */
	@Override
	public List<StockTask> getList(int startRow, int endRow) {
		return stockTaskManager.getStockTask(null, startRow, endRow);
	}
}