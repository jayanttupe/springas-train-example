package com.skynet.spms.action.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;

/**
 * 描述：物流装箱相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickupDeliveryVanningDataSourceAction implements DataSourceAction<PickupDeliveryVanning> {

	@Autowired
	private PickupDeliveryVanningManager pickupVanningManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"pickupDeliveryVanning_dataSource"};
	}

	/**
	 * 新增物流装箱相关信息
	 * @param pickupOrder
	 */
	@Override
	public void insert(PickupDeliveryVanning pickupOrder) {
		pickupVanningManager.insertEntity(pickupOrder);
	}
	
	/**
	 * 更新物流装箱相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流装箱相关信息
	 */
	@Override
	public PickupDeliveryVanning update(Map newValues, String itemID) {
		return (PickupDeliveryVanning) pickupVanningManager.updateEntity(newValues, itemID, PickupDeliveryVanning.class);
	}

	/**
	 * 删除物流装箱相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		pickupVanningManager.deleteEntity(itemID,PickupDeliveryVanning.class);
	}
	
	/**
	 * 查询物流装箱相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流装箱相关信息
	 */
	@Override
	public List<PickupDeliveryVanning> doQuery(Map values, int startRow, int endRow) {
		return pickupVanningManager.getPickupDeliveryVanning(values, startRow, endRow);
	}

	/**
	 * 获取所有物流装箱信息
	 * @param startRow
	 * @param endRow
	 * @return 物流装箱信息
	 */
	@Override
	public List<PickupDeliveryVanning> getList(int startRow, int endRow) {
		return pickupVanningManager.getPickupDeliveryVanning(null, startRow, endRow);
	}
}
