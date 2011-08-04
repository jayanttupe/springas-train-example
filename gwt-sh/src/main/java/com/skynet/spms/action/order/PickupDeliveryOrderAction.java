package com.skynet.spms.action.order;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.IPickupDeliveryOrderManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;

/**
 * 提货发货指令控制层
 * 
 * @author tqc
 * 
 */
@Component
public class PickupDeliveryOrderAction implements
		DataSourceAction<PickupDeliveryOrder> {

	@Resource
	IPickupDeliveryOrderManager manager;
	/**
	 * 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "PickupDeliveryOrder_datasource" };
	}
	/**
	 * 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(PickupDeliveryOrder item) {
		manager.addPickupDeliveryOrder(item);
	}
	/**
	 * 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<PickupDeliveryOrder> getList(int startRow, int endRow) {
		return null;
	}
	/**
	 * 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map, java.lang.String)
	 */
	public PickupDeliveryOrder update(Map<String, Object> newValues,
			String itemID) {
		return manager.updatePickupDeliveryOrder(newValues, itemID);
	}
	/**
	 * 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deletePickupDeliveryOrder(itemID);
	}
	/**
	 * 
	 * (non-Javadoc)
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map, int, int)
	 */
	public List<PickupDeliveryOrder> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		String type=(String) values.get("type");
		String businessType=(String)values.get("businessType");
		//1:提货 2:发货
		if("1".equals(type)){
			return manager.queryPickupDeliveryOrderList(startRow, endRow,type,businessType);
		}else{
			return manager.queryPickupDeliveryOrderList(startRow, endRow,type,businessType);
		}
	}

}
