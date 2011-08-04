package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingList;

/**
 * 提货指令明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickupOrderItemsManager extends CommonManager<ReceivingSheetItems> {

	/**
	 * 获取提货指令明细
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 提货指令明细
	 */
	public List<ReceivingSheetItems> getPickupOrderItems(Map values, int startRow, int endRow);
}