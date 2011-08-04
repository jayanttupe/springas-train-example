package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * 提货发货指令业务接口
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
public interface IPickupDeliveryOrderManager {
	/**
	 * 添加提货发货指令
	 * 
	 * @param
	 * @param o
	 * @return void
	 */
	public void addPickupDeliveryOrder(PickupDeliveryOrder o);

	/**
	 * 更新提货发货指令
	 * 
	 * @param
	 * @param newValues
	 * @param
	 * @param itemID
	 * @param
	 * @return
	 * @return PickupDeliveryOrder
	 */
	public PickupDeliveryOrder updatePickupDeliveryOrder(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除提货发货指令
	 * 
	 * @param
	 * @param itemID
	 * @return void
	 */
	public void deletePickupDeliveryOrder(String itemID);

	/**
	 * 分页查询提货发货指令
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @param type
	 * @param
	 * @param businessType
	 * @param
	 * @return
	 * @return List<PickupDeliveryOrder>
	 */
	public List<PickupDeliveryOrder> queryPickupDeliveryOrderList(int startRow,
			int endRow, String type, String businessType);

	/**
	 * 根据编号查询提货发货指令
	 * 
	 * @param
	 * @param sheetId
	 * @param
	 * @return
	 * @return PickupDeliveryOrder
	 */
	public PickupDeliveryOrder getPickupDeliveryOrderById(String sheetId);

}
