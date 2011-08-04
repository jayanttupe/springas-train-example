package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseOutStockManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseOutStock;

/**
 * 描述：保税库出库记录相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BondedWarehouseOutStockDatasourceAction implements DataSourceAction<BondedWarehouseOutStock>{
	@Autowired
	
	private BondedWarehouseOutStockManager bondedWarehouseOutStockManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"bondedWarehouseOutStock_dataSource"};
	}

	/**
	 * 新增保税库出库记录相关信息
	 * @param businessScopeAccountBook
	 */
	@Override
	public void insert(BondedWarehouseOutStock businessScopeAccountBook) {
		bondedWarehouseOutStockManager.insertEntity(businessScopeAccountBook);
	}

	/**
	 * 更新保税库出库记录相关信息
	 * @param newValues
	 * @param number
	 * @return
	 */
	@Override
	public BondedWarehouseOutStock update(Map newValues, String number) {
		return bondedWarehouseOutStockManager.updateCustoms(newValues);
	}

	/**
	 * 删除保税库出库记录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		bondedWarehouseOutStockManager.deleteEntity(number, BondedWarehouseOutStock.class);
	}

	/**
	 * 查询保税库出库记录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库出库记录相关信息
	 */
	@Override
	public List<BondedWarehouseOutStock> doQuery(Map values, int startRow, int endRow) {
		return bondedWarehouseOutStockManager.getBondedWarehousOutStocks(values, 0, -1);
	}
	
	/**
	 * 获取所有保税库出库记录信息
	 * @param startRow
	 * @param endRow
	 * @return 保税库出库记录信息
	 */
	@Override
	public List<BondedWarehouseOutStock> getList(int startRow, int endRow) {
		return bondedWarehouseOutStockManager.getBondedWarehousOutStocks(null, endRow, endRow);
	}
}