package com.skynet.spms.action.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrderManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：费用记录管理相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickupDeliveryOrderDataSourceAction implements DataSourceAction<PickupDeliveryOrder> {

	@Autowired
	private PickupDeliveryOrderManager pickupDeliveryOrderManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"pickupDeliveryOrder_dataSource"};
	}

	/**
	 * 新增费用记录管理相关信息
	 * @param pickupOrder
	 */
	@Override
	public void insert(PickupDeliveryOrder pickupOrder) {
		// 获取物流货物的箱信息
		List<PickupDeliveryVanning> newPickupOrder = OneToManyTools.ConvertToEntity(pickupOrder.getPickupDeliveryVanningList(), PickupDeliveryVanning.class);
		pickupOrder.setPickupDeliveryVanningList(newPickupOrder);
		
		pickupDeliveryOrderManager.savePickupDeliveryOrder(pickupOrder);
	}
	
	/**
	 * 更新费用记录管理相关信息
	 * @param newValues
	 * @param itemID
	 * @return 费用记录管理相关信息
	 */
	@Override
	public PickupDeliveryOrder update(Map newValues, String itemID) {
		PickupDeliveryOrder pickupOrder = new PickupDeliveryOrder();		
		BeanPropUtil.fillEntityWithMap(pickupOrder, newValues);

		// 获取物流货物的箱信息
		List<PickupDeliveryVanning> newPickupOrder = OneToManyTools.ConvertToEntity(pickupOrder.getPickupDeliveryVanningList(), PickupDeliveryVanning.class);
		pickupOrder.setPickupDeliveryVanningList(newPickupOrder);
		
		return pickupDeliveryOrderManager.savePickupDeliveryOrder(pickupOrder);
	}

	/**
	 * 删除费用记录管理相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		pickupDeliveryOrderManager.deleteEntity(itemID,PickupDeliveryOrder.class);
	}
	
	/**
	 * 查询费用记录管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 费用记录管理相关信息
	 */
	@Override
	public List<PickupDeliveryOrder> doQuery(Map values, int startRow, int endRow) {
		return pickupDeliveryOrderManager.getPickupDeliveryOrder(values, startRow, endRow);
	}

	/**
	 * 获取所有费用记录管理信息
	 * @param startRow
	 * @param endRow
	 * @return 费用记录管理信息
	 */
	@Override
	public List<PickupDeliveryOrder> getList(int startRow, int endRow) {
		return pickupDeliveryOrderManager.getPickupDeliveryOrder(null, startRow, endRow);
	}
}
