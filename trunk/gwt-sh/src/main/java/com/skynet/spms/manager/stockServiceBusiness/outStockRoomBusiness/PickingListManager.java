package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingList;

/**
 * 配料单信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickingListManager extends CommonManager<PickingList>{
	
	/**
	 * 删除配料单相关信息
	 * @param number
	 */
	public void deletePickingList(String number);

	/**
	 * 获取配料单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 配料单相关信息
	 */
	public List<PickingList> getPickingList(Map values, int startRow, int endRow);
	
	/**
	 * 保存配料单相关信息
	 * @param pickingList
	 * @return 配料单相关信息
	 */
	public PickingList SavePickingList(PickingList pickingList);

	/**
	 * 更新状态
	 * @param pickingListIDs
	 * @param status
	 */
	public void updateStatus(String[] pickingListIDs, String status);
	
}