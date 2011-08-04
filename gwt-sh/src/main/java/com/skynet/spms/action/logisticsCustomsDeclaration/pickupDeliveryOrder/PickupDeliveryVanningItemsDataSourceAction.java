package com.skynet.spms.action.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItemsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItems;

/**
 * 描述：物流装箱相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickupDeliveryVanningItemsDataSourceAction implements DataSourceAction<PickupDeliveryVanningItems> {

	@Autowired
	private PickupDeliveryVanningItemsManager pickupVanningItemsManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"pickupDeliveryVanningItems_dataSource"};
	}

	/**
	 * 新增物流装箱相关信息
	 * @param pickupOrder
	 */
	@Override
	public void insert(PickupDeliveryVanningItems pickupOrder) {
		pickupVanningItemsManager.insertEntity(pickupOrder);
	}
	
	/**
	 * 更新物流装箱相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流装箱相关信息
	 */
	@Override
	public PickupDeliveryVanningItems update(Map newValues, String itemID) {
		return (PickupDeliveryVanningItems) pickupVanningItemsManager.updateEntity(newValues, itemID, PickupDeliveryVanningItems.class);
	}

	/**
	 * 删除物流装箱相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		pickupVanningItemsManager.deleteEntity(itemID,PickupDeliveryVanningItems.class);
	}
	
	/**
	 * 查询物流装箱相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流装箱相关信息
	 */
	@Override
	public List<PickupDeliveryVanningItems> doQuery(Map values, int startRow, int endRow) {
		return pickupVanningItemsManager.getPickupDeliveryVanningItems(values, startRow, endRow);
	}

	/**
	 * 获取所有物流装箱信息
	 * @param startRow
	 * @param endRow
	 * @return 物流装箱信息
	 */
	@Override
	public List<PickupDeliveryVanningItems> getList(int startRow, int endRow) {
		return pickupVanningItemsManager.getPickupDeliveryVanningItems(null, startRow, endRow);
	}
}
