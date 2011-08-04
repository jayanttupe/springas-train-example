package com.skynet.spms.action.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.PickupOrderManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;

/**
 * 描述：提货指令相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickupOrderDatasourceAction implements
		DataSourceAction<ReceivingSheet> {

	@Autowired
	private PickupOrderManager pickupOrderManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "pickupOrder_dataSource" };
	}

	/**
	 * 新增提货指令相关信息
	 * @param receivingSheet
	 */
	@Override
	public void insert(ReceivingSheet receivingSheet) {
	}

	/**
	 * 更新提货指令相关信息
	 * @param newValues
	 * @param number
	 * @return 提货指令相关信息
	 */
	@Override
	public ReceivingSheet update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除提货指令相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		pickupOrderManager.deleteEntity(number, ReceivingSheet.class);
	}

	/**
	 * 查询提货指令相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 提货指令相关信息
	 */
	@Override
	public List<ReceivingSheet> doQuery(Map values, int startRow, int endRow) {
		return pickupOrderManager.getPickupOrder(values, startRow, endRow);
	}

	/**
	 * 获取所有提货指令信息
	 * @param startRow
	 * @param endRow
	 * @return 提货指令信息
	 */
	@Override
	public List<ReceivingSheet> getList(int startRow, int endRow) {
		return pickupOrderManager.getPickupOrder(null, startRow, endRow);
	}
}
