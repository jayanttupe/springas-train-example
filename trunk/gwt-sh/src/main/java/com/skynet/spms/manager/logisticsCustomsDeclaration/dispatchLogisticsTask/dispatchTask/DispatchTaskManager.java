package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * 分发任务管理相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface DispatchTaskManager extends CommonManager<PickupDeliveryOrder>{

	/**
	 * 获取分发任务管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 分发任务管理信息
	 */
	public List<PickupDeliveryOrder> getDispatchTask(Map values, int startRow, int endRow);
	
	/**
	 * 设置工作状态
	 * @param orderID
	 */
	public void setTaskStatus(String orderID);
}
