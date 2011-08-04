package com.skynet.spms.action.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckResultItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheckItem;

/**
 * 描述：盘点结果明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockCheckResultItemDatasourceAction implements DataSourceAction<StockCheckItem>{
	@Autowired
	private StockCheckResultItemManager stockCheckResultItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"stockCheckResultItem_dataSource"};
	}

	/**
	 * 新增盘点结果明细相关信息
	 * @param stockCheckItem
	 */
	@Override
	public void insert(StockCheckItem stockCheckItem) {
		stockCheckResultItemManager.insertEntity(stockCheckItem);
	}

	/**
	 * 更新盘点结果明细相关信息
	 * @param newValues
	 * @param number
	 * @return 盘点结果明细相关信息
	 */
	@Override
	public StockCheckItem update(Map newValues, String number) {
		return (StockCheckItem) stockCheckResultItemManager.updateEntity(newValues, number, StockCheckItem.class);
	}

	/**
	 * 删除盘点结果明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockCheckResultItemManager.deleteEntity(number, StockCheckItem.class);
	}

	/**
	 * 查询盘点结果明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点结果明细相关信息
	 */
	@Override
	public List<StockCheckItem> doQuery(Map values, int startRow, int endRow) {
		return stockCheckResultItemManager.getStockCheckItem(values);
	}

	/**
	 * 获取所有盘点结果明细信息
	 * @param startRow
	 * @param endRow
	 * @return 盘点结果明细信息
	 */
	@Override
	public List<StockCheckItem> getList(int startRow, int endRow) {
		return stockCheckResultItemManager.getStockCheckItem(null);
	}
}