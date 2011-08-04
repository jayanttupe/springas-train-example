package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssignManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssign;

/**
 * 描述：物流任务分派相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class DispatchTaskAssignDataSourceAction implements DataSourceAction<PickupDeliveryTaskAssign> {

	@Autowired
	private PickupDeliveryTaskAssignManager pickupDeliveryTaskAssignManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"dispatchTaskAssign_dataSource"};
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
	 * @return
	 */
	@Override
	public PickupDeliveryTaskAssign update(Map newValues, String itemID) {
		return null;
	}

	/**
	 * 删除物流任务分派相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		pickupDeliveryTaskAssignManager.deleteEntity(itemID,PickupDeliveryTaskAssign.class);
	}
	
	/**
	 * 查询物流任务分派相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 物流任务分派信息
	 */
	@Override
	public List<PickupDeliveryTaskAssign> doQuery(Map values, int startRow, int endRow) {
		return pickupDeliveryTaskAssignManager.getPickupDeliveryTaskAssign(values, startRow, endRow);
	}

	/**
	 * 获取所有物流任务分派信息
	 * @param startRow
	 * @param endRow
	 * @return 物流任务分派信息
	 */
	@Override
	public List<PickupDeliveryTaskAssign> getList(int startRow, int endRow) {
		return pickupDeliveryTaskAssignManager.getPickupDeliveryTaskAssign(null, startRow, endRow);
	}
}
