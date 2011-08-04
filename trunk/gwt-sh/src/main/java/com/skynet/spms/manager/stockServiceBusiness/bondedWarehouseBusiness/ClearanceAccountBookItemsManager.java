/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook.clearanceAccountBookItems.ClearanceAccountBookItems;

/**
 * 通关电子帐册明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */

public interface ClearanceAccountBookItemsManager extends
		CommonManager<ClearanceAccountBookItems> {

	/**
	 * 新增通关电子帐册明细
	 * @param clearanceAccountBookItems
	 */
	public void insertClearanceAccountBookItems(
			ClearanceAccountBookItems clearanceAccountBookItems);

	/**
	 * 获取通关电子帐册明细
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 通关电子帐册明细
	 */
	public List<ClearanceAccountBookItems> getClearanceAccountBookItems(
			Map map, int startRow, int endRow);
}
