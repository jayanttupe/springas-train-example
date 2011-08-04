package com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheckItem;

/**
 * 盘点结果明细Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockCheckResultItemManager extends CommonManager<StockCheckItem>{
	
	/**
	 * 获取盘点结果明细
	 * @param values
	 * @return 盘点结果明细
	 */
	public List<StockCheckItem> getStockCheckItem(Map values);
	
}