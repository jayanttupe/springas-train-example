package com.skynet.spms.manager.stockServiceBusiness.stockManageTool;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockPolicy.StockPolicy;

/**
 * 库存策略Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StockPolicyManager extends CommonManager<StockPolicy>{

	/**
	 * 获取库存策略相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 库存策略相关信息
	 */
	public List<StockPolicy> getStockPolicy(Map values, int startRow, int endRow);
}