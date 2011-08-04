package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 库房相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockRoomManager extends CommonManager<StockRoom>{

	/**
	 * 保存库房相关信息
	 * @param stockRoom
	 * @return 库房信息
	 */
	public StockRoom saveStockRoom(StockRoom stockRoom);
	
	/**
	 * 获取库房相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 库房相关信息
	 */
	public List<StockRoom> getStockRoom(Map values, int startRow, int endRow);
	
}
