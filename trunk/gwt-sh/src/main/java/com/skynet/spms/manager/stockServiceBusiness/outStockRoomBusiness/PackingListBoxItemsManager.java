package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.PackingListBoxItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.packingList.packingListPartItems.PackingListPartItems;

/**
 * 装箱单明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PackingListBoxItemsManager extends CommonManager<PackingListBoxItems>{

	/**
	 * 获取装箱单明细信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 装箱单明细信息
	 */
	public List<PackingListBoxItems> getPackingListBoxItems(Map map, int startRow, int endRow);

	/**
	 * 判断是否装箱
	 * @param rFIDTagUUIDs
	 * @return 是否装箱
	 */
	public boolean isExistPackingBox(String[] rFIDTagUUIDs);
}