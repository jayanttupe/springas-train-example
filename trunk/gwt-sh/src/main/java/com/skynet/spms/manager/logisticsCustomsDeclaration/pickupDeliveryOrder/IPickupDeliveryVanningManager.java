package com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;

/**
 * 装箱业务接口
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
public interface IPickupDeliveryVanningManager {

	/**
	 * 添加装箱
	 * 
	 * @param
	 * @param o
	 * @return void
	 */
	public void addPickupDeliveryVanning(PickupDeliveryVanning o);

	/**
	 * 更新装箱
	 * 
	 * @param
	 * @param newValues
	 * @param
	 * @param itemID
	 * @param
	 * @return
	 * @return PickupDeliveryVanning
	 */
	public PickupDeliveryVanning updatePickupDeliveryVanning(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除装箱
	 * 
	 * @param
	 * @param itemID
	 * @return void
	 */
	public void deletePickupDeliveryVanning(String itemID);

	/**
	 * 分页查询装箱
	 * 
	 * @param
	 * @param startRow
	 * @param
	 * @param endRow
	 * @param
	 * @param orderID
	 * @param
	 * @return
	 * @return List<PickupDeliveryVanning>
	 */
	public List<PickupDeliveryVanning> queryPickupDeliveryVanningList(
			int startRow, int endRow, String orderID);

	/**
	 * 根据指令查询装箱信息
	 * 
	 * @param
	 * @param sheetId
	 * @param
	 * @return
	 * @return PickupDeliveryVanning
	 */
	public PickupDeliveryVanning getPickupDeliveryVanningById(String sheetId);

}
