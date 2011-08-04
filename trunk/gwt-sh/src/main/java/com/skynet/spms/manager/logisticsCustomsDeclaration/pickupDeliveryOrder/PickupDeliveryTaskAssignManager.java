package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssign;

/**
 * 物流任务分派Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickupDeliveryTaskAssignManager extends CommonManager<PickupDeliveryTaskAssign>{

	/**
	 * 获取任务分派相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 任务分派相关信息
	 */
	public List<PickupDeliveryTaskAssign> getPickupDeliveryTaskAssign(Map values, int startRow, int endRow);

	/**
	 * 保存任务分派相关信息
	 * @param newPickupDeliveryTaskAssignList
	 */
	public void savePickupDeliveryTaskAssign(List<PickupDeliveryTaskAssign> newPickupDeliveryTaskAssignList);

	/**
	 * 发布提发货指令
	 * @param orderID
	 */
	public void publishPickupDelivery(String orderID);
}
