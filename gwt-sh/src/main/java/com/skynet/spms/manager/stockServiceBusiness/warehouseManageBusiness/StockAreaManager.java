package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockArea;

/**
 * 库房区域相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockAreaManager extends CommonManager<StockArea>{

	/**
	 * 获取库房区域信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 库房区域信息
	 */
	public List<StockArea> getStockArea(Map map, int startRow, int endRow);
	
}
