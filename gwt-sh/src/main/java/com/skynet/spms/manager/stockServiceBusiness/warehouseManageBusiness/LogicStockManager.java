package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * 逻辑库相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface LogicStockManager extends CommonManager<LogicStock> {

	/**
	 * 新增逻辑库
	 * @param logicStock
	 */
	public void insertLogicStock(LogicStock logicStock);
	
	/**
	 * 获取逻辑库相关信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<LogicStock> getLogicStock(Map map, int startRow, int endRow);
}