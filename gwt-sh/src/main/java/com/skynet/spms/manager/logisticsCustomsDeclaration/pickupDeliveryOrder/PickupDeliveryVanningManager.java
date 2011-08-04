package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;

/**
 * 物流装箱相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickupDeliveryVanningManager extends CommonManager<PickupDeliveryVanning>{

	/**
	 * 获取物流装箱相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流装箱相关信息
	 */
	public List<PickupDeliveryVanning> getPickupDeliveryVanning(Map values, int startRow, int endRow);
}
