package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheckItem;

/**
 * 盘点明细信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockCheckItemManager extends CommonManager<StockCheckItem>{

	/**
	 * 获取库房信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点明细信息
	 */
	List<StockCheckItem> getStockData(Map values, int startRow, int endRow);

	/**
	 * 获取盘点明细信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点明细信息
	 */
	List<StockCheckItem> getStockCheckItem(Map values, int startRow, int endRow);
}