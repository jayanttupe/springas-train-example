package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItems;

/**
 * 提货发货指令明细业务接口
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
public interface IPickupDeliveryVanningItemsManager {

	/**
	 * 添加提货发货指令明细
	 * 
	 * @param
	 * @param o
	 * @return void
	 */
	public void addPickupDeliveryVanningItems(PickupDeliveryVanningItems o);

	/**
	 * 更新提货发货指令明细
	 * 
	 * @param
	 * @param newValues
	 * @param
	 * @param itemID
	 * @param
	 * @return
	 * @return PickupDeliveryVanningItems
	 */
	public PickupDeliveryVanningItems updatePickupDeliveryVanningItems(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除提货发货指令明细
	 * 
	 * @param
	 * @param itemID
	 * @return void
	 */
	public void deletePickupDeliveryVanningItems(String itemID);

	/**
	 * 分页查询提货发货指令明细
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @param orderId
	 * @param
	 * @return
	 * @return List<PickupDeliveryVanningItems>
	 */
	public List<PickupDeliveryVanningItems> queryPickupDeliveryVanningItemsList(
			int startRow, int endRow, String orderId);

	/**
	 * 根据指令查询提货发货指令明细
	 * 
	 * @param
	 * @param sheetId
	 * @param
	 * @return
	 * @return PickupDeliveryVanningItems
	 */
	public PickupDeliveryVanningItems getPickupDeliveryVanningItemsById(
			String sheetId);

}
