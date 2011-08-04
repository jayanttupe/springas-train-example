package com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;

/**
 * 收料单Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface ReceivingSheetManager extends CommonManager<ReceivingSheet> {

	/**
	 * 保存收料单信息
	 * @param receivingSheet
	 * @return 收料单信息
	 */
	public ReceivingSheet saveReceivingSheet(ReceivingSheet receivingSheet);

	/**
	 * 获取收料单信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 收料单信息
	 */
	public List<ReceivingSheet> getReceivingSheet(Map values, int startRow, int endRow);
}
