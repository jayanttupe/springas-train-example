package com.skynet.spms.manager.supplierSupport.procurement.SafetyStockStrategy;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.supplierSupport.procurement.SafetyStockStrategy.SafetyStockStrategy;

public interface SafetyStockStrategyManager {
	public void addSafetyStockStrategy(SafetyStockStrategy o);

	public void deleteSafetyStockStrategy(String id);

	public SafetyStockStrategy updateSafetyStockStrategy(
			Map<String, Object> newValues, String itemID);

	public List<SafetyStockStrategy> querySafetyStockStrategyList(int startRow,
			int endRow);

}
