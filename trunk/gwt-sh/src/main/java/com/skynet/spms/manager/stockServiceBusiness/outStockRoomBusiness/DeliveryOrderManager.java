package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;

/**
 * 发货业务Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface DeliveryOrderManager extends CommonManager<PickingList> {

	/**
	 * 获取发货业务相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 发货业务相关信息
	 */
	public List<PickingList> getDeliveryOrder(Map values, int startRow, int endRow);
}
