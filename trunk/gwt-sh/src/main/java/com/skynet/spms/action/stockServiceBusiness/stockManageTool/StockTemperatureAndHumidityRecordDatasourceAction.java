package com.skynet.spms.action.stockServiceBusiness.stockManageTool;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockManageTool.StockTemperatureAndHumidityRecordHumidityManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity.StockTemperatureAndHumidityRecord;

/**
 * 描述：库房温湿度相关信息查询
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockTemperatureAndHumidityRecordDatasourceAction implements DataSourceAction<StockTemperatureAndHumidityRecord>{
	@Autowired
	private StockTemperatureAndHumidityRecordHumidityManager stockTemperatureAndHumidityRecordHumidityManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"stockTemperatureAndHumidityRecord_dataSource"};
	}
	
	/**
	 * 新增库房温湿度相关信息
	 * @param stockTemperatureAndHumidityRecord
	 */
	@Override
	public void insert(StockTemperatureAndHumidityRecord stockTemperatureAndHumidityRecord) {
		stockTemperatureAndHumidityRecordHumidityManager.insertEntity(stockTemperatureAndHumidityRecord);
	}
	
	/**
	 * 更新库房温湿度相关信息
	 * @param newValues
	 * @param number
	 * @return 库房温湿度相关信息
	 */
	@Override
	public StockTemperatureAndHumidityRecord update(Map newValues, String number) {
		return (StockTemperatureAndHumidityRecord) stockTemperatureAndHumidityRecordHumidityManager.updateEntity(newValues, number, StockTemperatureAndHumidityRecord.class);
	}
	
	/**
	 * 删除库房温湿度相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockTemperatureAndHumidityRecordHumidityManager.deleteEntity(number, StockTemperatureAndHumidityRecord.class);
	}
	
	/**
	 * 查询库房温湿度相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 库房温湿度相关信息
	 */ 
	@Override
	public List<StockTemperatureAndHumidityRecord> doQuery(Map values, int startRow, int endRow) {
		return stockTemperatureAndHumidityRecordHumidityManager.getStockTemperatureAndHumidityRecord(values, 0, -1);
	}
	
	/**
	 * 获取所有库房温湿度信息
	 * @param startRow
	 * @param endRow
	 * @return 库房温湿度信息
	 */
	@Override
	public List<StockTemperatureAndHumidityRecord> getList(int startRow, int endRow) {
		return stockTemperatureAndHumidityRecordHumidityManager.getStockTemperatureAndHumidityRecord(null, 0, -1);
	}
}