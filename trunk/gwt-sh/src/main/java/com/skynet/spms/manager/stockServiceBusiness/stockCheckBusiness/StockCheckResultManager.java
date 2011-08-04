/**
 * 
 */
package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;

/**
 * 盘点结果Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockCheckResultManager extends CommonManager<StockCheck>{

	/**
	 * 获取盘点结果信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点结果信息
	 */
	public List<StockCheck> GetStockCheckResult(Map values, int startRow, int endRow);
	
	/**
	 * 更新盘点记录
	 * @param map
	 * @return 盘点结果信息
	 */
	public StockCheck updateRecord(Map map);
}
