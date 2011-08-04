package com.skynet.spms.action.logisticsCustomsDeclaration.pickupDeliveryOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssignManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssign;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：物流任务分派相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickupDeliveryTaskAssignDataSourceAction implements DataSourceAction<PickupDeliveryTaskAssign> {

	@Autowired
	private PickupDeliveryTaskAssignManager pickupTaskAssignManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"pickupDeliveryTaskAssign_dataSource"};
	}

	/**
	 * 新增物流任务分派相关信息
	 * @param pickupTaskAssign
	 */
	@Override
	public void insert(PickupDeliveryTaskAssign pickupTaskAssign) {
		
	}
	
	/**
	 * 更新物流任务分派相关信息
	 * @param newValues
	 * @param itemID
	 * @return 物流任务分派相关信息
	 */
	@Override
	public PickupDeliveryTaskAssign update(Map newValues, String itemID) {
		if (newValues.containsKey("publish") && newValues.get("publish").toString().equals("publish")) {
			String orderID = newValues.get("orderID").toString();
			pickupTaskAssignManager.publishPickupDelivery(orderID);	
		} else {
			ArrayList taskRecordList = (ArrayList)newValues.get("taskRecords");
			List<PickupDeliveryTaskAssign> newPickupDeliveryTaskAssignList =
				OneToManyTools.ConvertToEntity(taskRecordList, PickupDeliveryTaskAssign.class);

			pickupTaskAssignManager.savePickupDeliveryTaskAssign(newPickupDeliveryTaskAssignList);	
		}
		
		return null;
	}

	/**
	 * 删除物流任务分派相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		pickupTaskAssignManager.deleteEntity(itemID,PickupDeliveryTaskAssign.class);
	}
	
	/**
	 * 查询物流任务分派相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流任务分派相关信息
	 */
	@Override
	public List<PickupDeliveryTaskAssign> doQuery(Map values, int startRow, int endRow) {
		return pickupTaskAssignManager.getPickupDeliveryTaskAssign(values, startRow, endRow);
	}

	/**
	 * 获取所有物流任务分派信息
	 * @param startRow
	 * @param endRow
	 * @return 物流任务分派信息
	 */
	@Override
	public List<PickupDeliveryTaskAssign> getList(int startRow, int endRow) {
		return pickupTaskAssignManager.getPickupDeliveryTaskAssign(null, startRow, endRow);
	}
}
