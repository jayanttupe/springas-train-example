package com.skynet.spms.action.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheckItem;

/**
 * 描述：盘点相关明细信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockCheckItemDatasourceAction implements DataSourceAction<StockCheckItem>{
	@Autowired
	private StockCheckItemManager stockCheckItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"stockCheckItem_dataSource"};
	}

	/**
	 * 新增盘点相关明细信息
	 * @param stockCheckItem
	 */
	@Override
	public void insert(StockCheckItem stockCheckItem) {
		stockCheckItemManager.insertEntity(stockCheckItem);
	}

	/**
	 * 更新盘点相关明细信息
	 * @param newValues
	 * @param number
	 * @return 盘点相关明细信息
	 */
	@Override
	public StockCheckItem update(Map newValues, String number) {
		return (StockCheckItem) stockCheckItemManager.updateEntity(newValues, number, StockCheckItem.class);
	}

	/**
	 * 删除盘点相关明细信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockCheckItemManager.deleteEntity(number, StockCheckItem.class);
	}

	/**
	 * 查询盘点相关明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点相关明细信息
	 */
	@Override
	public List<StockCheckItem> doQuery(Map values, int startRow, int endRow) {
		if (values.containsKey("StockData")) {
			return stockCheckItemManager.getStockData(values, startRow, endRow);	
		} else {
			return stockCheckItemManager.getStockCheckItem(values, startRow, endRow);	
		}
	}

	/**
	 * 获取所有盘点明细信息
	 * @param startRow
	 * @param endRow
	 * @return 盘点明细信息
	 */
	@Override
	public List<StockCheckItem> getList(int startRow, int endRow) {
		return stockCheckItemManager.getStockCheckItem(null, startRow, endRow);
	}
}