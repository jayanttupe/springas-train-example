package com.skynet.spms.action.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.PickupOrderItemsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;

/**
 * 描述：提货指令明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickupOrderItemsDatasourceAction implements
		DataSourceAction<ReceivingSheetItems> {

	@Autowired
	private PickupOrderItemsManager pickupOrderItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "pickupOrderItems_dataSource" };
	}

	/**
	 * 新增提货指令明细相关信息
	 * @param receivingSheetItems
	 */
	@Override
	public void insert(ReceivingSheetItems receivingSheetItems) {
	}

	/**
	 * 更新提货指令明细相关信息
	 * @param newValues
	 * @param number
	 * @return 提货指令明细相关信息
	 */
	@Override
	public ReceivingSheetItems update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除提货指令明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		pickupOrderItemsManager.deleteEntity(number, ReceivingSheetItems.class);
	}

	/**
	 * 查询提货指令明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 提货指令明细相关信息
	 */
	@Override
	public List<ReceivingSheetItems> doQuery(Map values, int startRow, int endRow) {
		return pickupOrderItemsManager.getPickupOrderItems(values, startRow, endRow);
	}

	/**
	 * 获取所有提货指令明细信息
	 * @param startRow
	 * @param endRow
	 * @return 提货指令明细信息
	 */
	@Override
	public List<ReceivingSheetItems> getList(int startRow, int endRow) {
		return pickupOrderItemsManager.getPickupOrderItems(null, startRow, endRow);
	}
}