package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItems;

/**
 * 物流装箱相关明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickupDeliveryVanningItemsManager extends CommonManager<PickupDeliveryVanningItems>{

	/**
	 * 获取物流装箱相关明细
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流装箱相关明细
	 */
	public List<PickupDeliveryVanningItems> getPickupDeliveryVanningItems(Map values, int startRow, int endRow);
}
