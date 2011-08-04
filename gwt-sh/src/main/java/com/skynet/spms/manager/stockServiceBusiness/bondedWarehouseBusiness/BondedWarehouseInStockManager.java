package com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseInStock;

/**
 * 保税库入库记录Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */

public interface BondedWarehouseInStockManager extends
		CommonManager<BondedWarehouseInStock> {

	/**
	 * 发送至海关监管
	 * @param map
	 * @return 保税库入库记录
	 */
	public BondedWarehouseInStock updateCustoms(Map map);

	/**
	 * 获得未被删除的数据
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库入库记录
	 */
	public List<BondedWarehouseInStock> getBondedWarehouseInStocks(Map values,
			int startRow, int endRow);

	/**
	 * 获取库存记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库入库记录
	 */
	public List<BondedWarehouseInStock> getInStocks(Map values, int startRow,
			int endRow);

	/**
	 * 获取为海关监管未被删除的数据
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库入库记录
	 */
	public List<BondedWarehouseInStock> getBondedWarehouseInStocksByIsCustoms(
			Map values, int startRow, int endRow);

	/**
	 * 获取为海关监管的库存记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库入库记录
	 */
	public List<BondedWarehouseInStock> getInStocksByIsCustoms(Map values,
			int startRow, int endRow);
}