package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;

/**
 * 收料单历史记录明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ReceivingSheetItemsManager extends
		CommonManager<ReceivingSheetItems> {

	/**
	 * 删除收料单历史记录明细信息
	 * @param receivingSheetItemsID
	 * @return 收料单历史记录明细信息
	 */
	public ReceivingSheetItems deleteReceivingSheetItems(String receivingSheetItemsID);

	/**
	 * 获取收料单历史记录明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 收料单历史记录明细信息
	 */
	public List<ReceivingSheetItems> getReceivingSheetItems(Map values, int startRow, int endRow);
}
