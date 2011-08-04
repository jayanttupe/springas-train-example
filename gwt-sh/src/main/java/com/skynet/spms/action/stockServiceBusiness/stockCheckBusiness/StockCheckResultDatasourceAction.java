package com.skynet.spms.action.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckResultManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;

/**
 * 描述：盘点结果相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockCheckResultDatasourceAction implements DataSourceAction<StockCheck>{
	@Autowired
	private StockCheckResultManager stockCheckResultManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "stockCheckResult_dataSource" };
	}

	/**
	 * 新增盘点结果相关信息
	 * @param item
	 */
	@Override
	public void insert(StockCheck item) {
	}

	/**
	 * 更新盘点结果相关信息
	 * @param newValues
	 * @param itemID
	 * @return 盘点结果相关信息
	 */
	@Override
	public StockCheck update(Map<String, Object> newValues, String itemID) {
		return stockCheckResultManager.updateRecord(newValues);
	}

	/**
	 * 删除盘点结果相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
	}

	/**
	 * 查询盘点结果相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点结果相关信息
	 */
	@Override
	public List<StockCheck> doQuery(Map<String, Object> values, int startRow, int endRow) {
		return stockCheckResultManager.GetStockCheckResult(values, startRow, endRow);
	}

	/**
	 * 获取所有盘点结果信息
	 * @param startRow
	 * @param endRow
	 * @return 盘点结果信息
	 */
	@Override
	public List<StockCheck> getList(int startRow, int endRow) {
		return stockCheckResultManager.GetStockCheckResult(null, startRow, endRow);
	}
}
