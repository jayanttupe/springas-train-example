package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;

/**
 * 提货指令Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickupOrderManager extends CommonManager<ReceivingSheet> {

	/**
	 * 获取提货指令信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 提货指令信息
	 */
	public List<ReceivingSheet> getPickupOrder(Map values, int startRow, int endRow);
}
