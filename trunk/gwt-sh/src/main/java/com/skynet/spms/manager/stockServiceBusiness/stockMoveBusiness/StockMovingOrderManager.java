/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 移库相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockMovingOrderManager extends CommonManager<BaseTask>{
	
	/**
	 * 通过状态查找移库信息
	 * @return 移库信息
	 */
	public List<BaseTask> findbystate();
	
	/**
	 * 获取移库相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 移库信息
	 */
    public List<BaseTask> getStockMovingOrder(Map values, int startRow, int endRow);

}