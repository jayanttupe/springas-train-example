package com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;

/**
 * 货架相关信息Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface StorageRacks extends CommonManager<CargoSpace>{

	/**
	 * 获取货架相关信息
	 * @param startRow
	 * @param endRow
	 * @return 相关货架信息
	 */
	public List<CargoSpace> getStorageRacks(int startRow, int endRow);
}