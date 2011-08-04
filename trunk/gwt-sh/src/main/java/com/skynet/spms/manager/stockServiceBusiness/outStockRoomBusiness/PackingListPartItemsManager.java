package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

/**
 * 装箱单备件明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PackingListPartItemsManager extends CommonManager<PackingListPartItems>{

	/**
	 * 获取装箱单备件明细信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 装箱单备件明细信息
	 */
	public List<PackingListPartItems> getPackingListPartItems(Map map, int startRow, int endRow);

	/**
	 * 保存装箱单备件明细信息
	 * @param map
	 */
	public void savePackingListPartItems(Map map);
}