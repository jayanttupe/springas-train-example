package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.DeliveryOrderItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

/**
 * 描述：发货业务明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class DeliveryOrderItemsDatasourceAction implements DataSourceAction<PickingListPartItems>{
	@Autowired
	private DeliveryOrderItemsManager deliveryOrderItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"deliveryOrderItems_dataSource"};
	}

	/**
	 * 新增发货业务明细相关信息
	 * @param pickingListPartItems
	 */
	@Override
	public void insert(PickingListPartItems pickingListPartItems) {
		deliveryOrderItemsManager.insertEntity(pickingListPartItems);
	}

	/**
	 * 更新发货业务明细相关信息
	 * @param newValues
	 * @param number
	 * @return 发货业务明细相关信息
	 */
	@Override
	public PickingListPartItems update(Map newValues, String number) {
		if (newValues != null && newValues.containsKey("isPicking") && newValues.get("isPicking").equals("1")) {
			return null;
		} else {
			return (PickingListPartItems) deliveryOrderItemsManager.updateEntity(newValues, number, PickingListPartItems.class);	
		}
	}

	/**
	 * 删除发货业务明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		deliveryOrderItemsManager.deleteEntity(number, PickingListPartItems.class);
	}

	/**
	 * 查询发货业务明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 发货业务明细相关信息
	 */
	@Override
	public List<PickingListPartItems> doQuery(Map values, int startRow, int endRow) {
		return deliveryOrderItemsManager.getDeliveryOrderItems(values, startRow, endRow);
	}

	/**
	 * 获取所有发货业务明细信息
	 * @param startRow
	 * @param endRow
	 * @return 发货业务明细信息
	 */
	@Override
	public List<PickingListPartItems> getList(int startRow, int endRow) {
		return deliveryOrderItemsManager.getDeliveryOrderItems(null, startRow, endRow);
	}
}