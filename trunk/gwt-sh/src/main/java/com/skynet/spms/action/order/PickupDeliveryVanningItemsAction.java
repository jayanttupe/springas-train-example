package com.skynet.spms.action.order;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.IPickupDeliveryVanningItemsManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningItems;

/**
 * 提货发货指令明细控制层
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
@Component
public class PickupDeliveryVanningItemsAction implements
		DataSourceAction<PickupDeliveryVanningItems> {

	@Resource
	IPickupDeliveryVanningItemsManager manager;

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "PickupDeliveryVanningItems_datasource" };
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(PickupDeliveryVanningItems item) {
		manager.addPickupDeliveryVanningItems(item);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<PickupDeliveryVanningItems> getList(int startRow, int endRow) {
		return manager
				.queryPickupDeliveryVanningItemsList(startRow, endRow, "");
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public PickupDeliveryVanningItems update(Map<String, Object> newValues,
			String itemID) {
		return manager.updatePickupDeliveryVanningItems(newValues, itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deletePickupDeliveryVanningItems(itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<PickupDeliveryVanningItems> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		// 根据指令查询明细
		if (values.get("orderId") != null) {
			String orderId = (String) values.get("orderId");
			return manager.queryPickupDeliveryVanningItemsList(startRow,
					endRow, orderId);
		}
		return null;

	}

}
