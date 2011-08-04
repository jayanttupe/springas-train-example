package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;

/**
 * 盘点跟踪业务Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockCheckResultTrackManager extends CommonManager<StockCheck>{

	/**
	 * 获取全部盘点跟踪信息
	 * @param startRow
	 * @param endRow
	 * @return 盘点跟踪信息
	 */
	public List<StockCheck> getStockCheckResultTrack(int startRow, int endRow);
	
	/**
	 * 获取相应的盘点跟踪信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点跟踪信息
	 */
	public List<StockCheck> getStockCheckResultTrackByCondition(Map values, int startRow, int endRow);
	
}