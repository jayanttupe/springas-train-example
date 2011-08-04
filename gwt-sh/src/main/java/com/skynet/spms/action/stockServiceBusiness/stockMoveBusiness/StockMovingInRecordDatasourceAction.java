package com.skynet.spms.action.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecord;

/**
 * 描述：移库记录相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockMovingInRecordDatasourceAction implements DataSourceAction<StockMovingRecord>{
	@Autowired
	private StockMovingRecordManager stockMovingRecordManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"stockMovingInRecord_dataSource"};
	}

	/**
	 * 新增移库记录相关信息
	 * @param stockMovingRecord
	 */
	@Override
	public void insert(StockMovingRecord stockMovingRecord) {
		stockMovingRecordManager.insertEntity(stockMovingRecord);
	}

	/**
	 * 更新移库记录相关信息
	 * @param newValues
	 * @param number
	 * @return 移库记录相关信息
	 */
	@Override
	public StockMovingRecord update(Map newValues, String number) {
		return stockMovingRecordManager.updateRecord(newValues);
	}

	/**
	 * 删除移库记录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockMovingRecordManager.deleteEntity(number, StockMovingRecord.class);
	}

	/**
	 * 查询移库记录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 移库记录相关信息
	 */
	@Override
	public List<StockMovingRecord> doQuery(Map values, int startRow, int endRow) {
		return stockMovingRecordManager.getAllByCondition(values, 0, -1);
	}

	/**
	 * 获取所有移库记录信息
	 * @param startRow
	 * @param endRow
	 * @return 移库记录信息
	 */
	@Override
	public List<StockMovingRecord> getList(int startRow, int endRow) {
		return stockMovingRecordManager.findbystate("移库中","已移入");
	}
}