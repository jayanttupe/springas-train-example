package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.DeliveryOrderManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;

/**
 * 描述：发货业务相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class DeliveryOrderDatasourceAction implements DataSourceAction<PickingList>{
	@Autowired
	private DeliveryOrderManager deliveryOrderManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"deliveryOrder_dataSource"};
	}

	/**
	 * 新增发货业务相关信息
	 * @param pickingList
	 */
	@Override
	public void insert(PickingList pickingList) {
		deliveryOrderManager.insertEntity(pickingList);
	}

	/**
	 * 更新发货业务相关信息
	 * @param newValues
	 * @param number
	 * @return 发货业务相关信息
	 */
	@Override
	public PickingList update(Map newValues, String number) {
		return (PickingList) deliveryOrderManager.updateEntity(newValues, number, PickingList.class);
	}

	/**
	 * 删除发货业务相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		deliveryOrderManager.deleteEntity(number, PickingList.class);
	}

	/**
	 * 查询发货业务相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 发货业务相关信息
	 */
	@Override
	public List<PickingList> doQuery(Map values, int startRow, int endRow) {
		return deliveryOrderManager.getDeliveryOrder(values, startRow, endRow);
	}

	/**
	 * 获取所有发货业务信息
	 * @param startRow
	 * @param endRow
	 * @return 发货业务信息
	 */
	@Override
	public List<PickingList> getList(int startRow, int endRow) {
		return deliveryOrderManager.getDeliveryOrder(null, startRow, endRow);
	}
}