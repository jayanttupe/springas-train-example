package com.skynet.spms.action.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItems;

/**
 * 描述：移库记录明细信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockMovingRecordItemsDatasourceAction implements DataSourceAction<StockMovingRecordItems>{
	@Autowired
	private StockMovingRecordItemsManager stockMovingRecordItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"stockMovingRecordItems_dataSource"};
	}

	/**
	 * 新增移库记录明细相关信息处理
	 * @param stockMovingRecordItems
	 */
	@Override
	public void insert(StockMovingRecordItems stockMovingRecordItems) {
		stockMovingRecordItemsManager.InsertStockMovingRecordItems(stockMovingRecordItems);
	}

	/**
	 * 更新移库记录明细相关信息
	 * @param newValues
	 * @param number
	 * @return 移库记录明细相关信息
	 */
	@Override
	public StockMovingRecordItems update(Map newValues, String number) {
		return (StockMovingRecordItems) stockMovingRecordItemsManager.updateEntity(newValues, number, StockMovingRecordItems.class);
	}

	/**
	 * 删除移库记录明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockMovingRecordItemsManager.deleteEntity(number, StockMovingRecordItems.class);
	}

	/**
	 * 查询移库记录明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 移库记录明细相关信息
	 */
	@Override
	public List<StockMovingRecordItems> doQuery(Map values, int startRow, int endRow) {
		return stockMovingRecordItemsManager.getStockMovingRecordItems(values, startRow, endRow);
	}

	/**
	 * 获取所有移库记录明细信息
	 * @param startRow
	 * @param endRow
	 * @return 移库记录明细相关信息
	 */
	@Override
	public List<StockMovingRecordItems> getList(int startRow, int endRow) {
		return stockMovingRecordItemsManager.findbyall();
	}
}