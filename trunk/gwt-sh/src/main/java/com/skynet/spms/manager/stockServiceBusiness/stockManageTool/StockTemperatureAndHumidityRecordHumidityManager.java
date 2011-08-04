package com.skynet.spms.manager.stockServiceBusiness.stockManageTool;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity.StockTemperatureAndHumidityRecord;

/**
 * 库房温湿度信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockTemperatureAndHumidityRecordHumidityManager extends CommonManager<StockTemperatureAndHumidityRecord>{
	
	/**
	 * 获取库房温度湿度信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 库房温湿度信息
	 */
	public List<StockTemperatureAndHumidityRecord> getStockTemperatureAndHumidityRecord(Map values, int startRow, int endRow);

}