package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseOutStock;

/**
 * 保税库出库记录Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */

public interface BondedWarehouseOutStockManager extends
		CommonManager<BondedWarehouseOutStock> {

	/**
	 * 发送至海关监管
	 * @param map
	 * @return 保税库出库记录
	 */
	public BondedWarehouseOutStock updateCustoms(Map map);

	/**
	 * 获得未被删除的数据
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库出库记录
	 */
	public List<BondedWarehouseOutStock> getBondedWarehousOutStocks(Map values,
			int startRow, int endRow);

	/**
	 * 获得海关监管的数据
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库出库记录
	 */
	public List<BondedWarehouseOutStock> getBondedWarehousOutStocksByIsCustoms(
			Map values, int startRow, int endRow);

}