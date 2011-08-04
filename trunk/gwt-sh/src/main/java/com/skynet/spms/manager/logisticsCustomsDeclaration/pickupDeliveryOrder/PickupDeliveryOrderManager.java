package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * 物流提发货指令Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickupDeliveryOrderManager extends CommonManager<PickupDeliveryOrder>{

	/**
	 * 获取物流提发货指令相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流提发货指令相关信息
	 */
	public List<PickupDeliveryOrder> getPickupDeliveryOrder(Map values, int startRow, int endRow);

	/**
	 * 保存物流提发货指令相关信息
	 * @param pickupOrder
	 * @return 物流提发货指令相关信息
	 */
	public PickupDeliveryOrder savePickupDeliveryOrder(PickupDeliveryOrder pickupOrder);
}
