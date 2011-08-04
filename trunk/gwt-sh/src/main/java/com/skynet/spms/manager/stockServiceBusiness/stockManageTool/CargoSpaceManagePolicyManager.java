package com.skynet.spms.manager.stockServiceBusiness.stockManageTool;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy.CargoSpaceManagePolicy;

/**
 * 货位策略信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface CargoSpaceManagePolicyManager extends CommonManager<CargoSpaceManagePolicy>{
	
	/**
	 * 获取货位策略信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 货位策略信息
	 */
	public List<CargoSpaceManagePolicy> getCargoSpaceManagePolicy(Map values, int startRow, int endRow);

}