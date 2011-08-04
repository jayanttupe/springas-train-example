package com.skynet.spms.action.stockServiceBusiness.stockTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockCheckTaskItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockCheckTaskItem;

/**
 * 描述：盘点任务明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockCheckTaskItemDatasourceAction implements
		DataSourceAction<StockCheckTaskItem> {
	@Autowired
	private StockCheckTaskItemManager stockCheckTaskItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "stockCheckTaskItem_dataSource" };
	}

	/**
	 * 新增盘点任务明细相关信息
	 * @param shelvingTaskItem
	 */
	@Override
	public void insert(StockCheckTaskItem shelvingTaskItem) {
	}

	/**
	 * 更新盘点任务明细相关信息
	 * @param newValues
	 * @param number
	 * @return null
	 */
	@Override
	public StockCheckTaskItem update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除盘点任务明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询盘点任务明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点任务明细相关信息
	 */
	@Override
	public List<StockCheckTaskItem> doQuery(Map values, int startRow, int endRow) {
		return stockCheckTaskItemManager.getStockCheckTaskItem(values, startRow, endRow);
	}

	/**
	 * 获取所有盘点任务明细信息
	 * @param startRow
	 * @param endRow
	 * @return 盘点任务明细信息
	 */
	@Override
	public List<StockCheckTaskItem> getList(int startRow, int endRow) {
		return stockCheckTaskItemManager.getStockCheckTaskItem(null, startRow, endRow);
	}
}