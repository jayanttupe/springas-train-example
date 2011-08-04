package com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;

/**
 * 配料单明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface PickingListPartItemsManager extends CommonManager<PickingListPartItems>{

	/**
	 * 获取配料单明细信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 配料单明细信息
	 */
	public List<PickingListPartItems> getPickingListPartItems(Map map, int startRow, int endRow);

	/**
	 * 保存配料单明细信息
	 * @param newValues
	 */
	public void savePickingListPartItems(Map newValues);
}