package com.skynet.spms.action.order;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.IPickupDeliveryVanningManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;

/**
 * 装箱控制层
 * 
 * @author taiqichao
 * @version
 * @Date Jul 12, 2011
 */
@Component
public class PickupDeliveryVanningAction implements
		DataSourceAction<PickupDeliveryVanning> {

	@Resource
	IPickupDeliveryVanningManager manager;

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "PickupDeliveryVanning_datasource" };
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(PickupDeliveryVanning item) {
		manager.addPickupDeliveryVanning(item);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<PickupDeliveryVanning> getList(int startRow, int endRow) {
		return manager.queryPickupDeliveryVanningList(startRow, endRow, "");
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public PickupDeliveryVanning update(Map<String, Object> newValues,
			String itemID) {
		return manager.updatePickupDeliveryVanning(newValues, itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		manager.deletePickupDeliveryVanning(itemID);
	}

	/**
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<PickupDeliveryVanning> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		String orderId = (String) values.get("orderID");
		return manager
				.queryPickupDeliveryVanningList(startRow, endRow, orderId);
	}

}
