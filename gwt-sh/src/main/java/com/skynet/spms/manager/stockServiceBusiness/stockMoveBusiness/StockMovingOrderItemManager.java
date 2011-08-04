/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTaskItem.BaseTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItems;

/**
 * 移库相关明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockMovingOrderItemManager extends CommonManager<BaseTaskItem>{

	/**
	 * 获取库房信息
	 * @param map
	 * @param startRow
	 * @param endRow
	 * @return 库房信息
	 */
	public List<BaseTaskItem> getStockInfo(Map map, int startRow, int endRow);

}
