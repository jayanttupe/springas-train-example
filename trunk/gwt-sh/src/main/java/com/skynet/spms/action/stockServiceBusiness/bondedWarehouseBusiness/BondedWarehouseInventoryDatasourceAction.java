package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseInStockManager;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BusinessScopeAccountBookManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseInStock;

/**
 * 描述：保税库在库记录相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BondedWarehouseInventoryDatasourceAction implements DataSourceAction<BondedWarehouseInStock>{
	@Autowired
	private BondedWarehouseInStockManager bondedWarehouseInStockManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"bondedWarehouseInventoryStock_dataSource"};
	}

	/**
	 * 新增保税库在库记录相关信息
	 * @param businessScopeAccountBook
	 */
	@Override
	public void insert(BondedWarehouseInStock businessScopeAccountBook) {
		bondedWarehouseInStockManager.insertEntity(businessScopeAccountBook);
	}

	/**
	 * 更新保税库在库记录相关信息
	 * @param newValues
	 * @param number
	 * @return 保税库在库记录相关信息
	 */
	@Override
	public BondedWarehouseInStock update(Map newValues, String number) {
		return (BondedWarehouseInStock) bondedWarehouseInStockManager.updateEntity(newValues, number, BondedWarehouseInStock.class);
	}

	/**
	 * 删除保税库在库记录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		bondedWarehouseInStockManager.deleteEntity(number, BondedWarehouseInStock.class);
	}

	/**
	 * 查询保税库在库记录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库在库记录信息
	 */
	@Override
	public List<BondedWarehouseInStock> doQuery(Map values, int startRow, int endRow) {
		return bondedWarehouseInStockManager.getInStocks(values, 0, -1);
	}

	/**
	 * 获取所有保税库在库记录信息
	 * @param startRow
	 * @param endRow
	 * @return 保税库在库记录信息
	 */
	@Override
	public List<BondedWarehouseInStock> getList(int startRow, int endRow) {
		return bondedWarehouseInStockManager.getInStocks(null, startRow, endRow);
	}
}