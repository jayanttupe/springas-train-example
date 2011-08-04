package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;

/**
 * 盘点业务Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockCheckManager extends CommonManager<StockCheck>{
	
	/**
	 * 获取盘点相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点相关信息
	 */
	public List<StockCheck> getStockCheck(Map values, int startRow, int endRow);
	
	/**
	 * 保存盘点信息
	 * @param stockCheck
	 * @return 盘点相关信息
	 */
	StockCheck saveStockCheck(StockCheck stockCheck);
}