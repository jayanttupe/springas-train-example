package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingList;

/**
 * 装箱单信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PackingListManager extends CommonManager<PackingList>{

	/**
	 * 获取装箱单信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 装箱单信息
	 */
	public List<PackingList> getPackingList(Map values, int startRow, int endRow);

	/**
	 * 更新状态
	 * @param values
	 * @return 装箱单信息
	 */
	public PackingList updateStatus(Map values);
	
	/**
	 * 保存装箱单信息
	 * @param packingList
	 * @return 装箱单信息
	 */
	public PackingList savePackingList(PackingList packingList);
	
}