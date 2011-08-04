package com.skynet.spms.action.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask.DispatchTaskManager;
import com.skynet.spms.manager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrderManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryOrder;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanning;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：分发任务管理相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class DispatchTaskDataSourceAction implements DataSourceAction<PickupDeliveryOrder> {

	@Autowired
	private DispatchTaskManager dispatchTaskManager;
	@Autowired
	private PickupDeliveryOrderManager pickupDeliveryOrderManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"dispatchTask_dataSource"};
	}

	/**
	 * 新增分发任务管理相关信息
	 * @param pickupOrder
	 */
	@Override
	public void insert(PickupDeliveryOrder pickupOrder) {
	}
	
	/**
	 * 更新分发任务管理相关信息
	 * @param newValues
	 * @param itemID
	 * @return 分发任务管理相关信息
	 */
	@Override
	public PickupDeliveryOrder update(Map newValues, String itemID) {
		if (newValues.containsKey("orderID") && newValues.containsKey("setStatus")) {
			if (newValues.get("setStatus").toString().equals("deliveryStatus") ||
					newValues.get("setStatus").toString().equals("pickupStatus")) {
				// 设置指令任务状态为已完成
				dispatchTaskManager.setTaskStatus(newValues.get("orderID").toString());
			}
			return null;
		} else {
			PickupDeliveryOrder pickupOrder = new PickupDeliveryOrder();		
			BeanPropUtil.fillEntityWithMap(pickupOrder, newValues);

			// 获取物流货物的箱信息
			List<PickupDeliveryVanning> newPickupOrder = OneToManyTools.ConvertToEntity(pickupOrder.getPickupDeliveryVanningList(), PickupDeliveryVanning.class);
			pickupOrder.setPickupDeliveryVanningList(newPickupOrder);
			
			return pickupDeliveryOrderManager.savePickupDeliveryOrder(pickupOrder);
		}
	}

	/**
	 * 删除分发任务管理相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		dispatchTaskManager.deleteEntity(itemID,PickupDeliveryOrder.class);
	}
	
	/**
	 * 查询分发任务管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 分发任务管理信息
	 */
	@Override
	public List<PickupDeliveryOrder> doQuery(Map values, int startRow, int endRow) {
		return dispatchTaskManager.getDispatchTask(values, startRow, endRow);
	}

	/**
	 * 获取所有分发任务管理信息
	 * @param startRow
	 * @param endRow
	 * @return 分发任务管理信息
	 */
	@Override
	public List<PickupDeliveryOrder> getList(int startRow, int endRow) {
		return dispatchTaskManager.getDispatchTask(null, startRow, endRow);
	}
}
