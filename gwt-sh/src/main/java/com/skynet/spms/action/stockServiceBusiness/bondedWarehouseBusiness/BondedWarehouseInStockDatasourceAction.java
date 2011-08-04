package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseInStockManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseInStock;

/**
 * 描述：保税库入库记录相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BondedWarehouseInStockDatasourceAction implements DataSourceAction<BondedWarehouseInStock>{
	@Autowired
	private BondedWarehouseInStockManager bondedWarehouseInStockManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"bondedWarehouseInStock_dataSource"};
	}

	/**
	 * 新增保税库入库记录
	 * @param businessScopeAccountBook
	 */
	@Override
	public void insert(BondedWarehouseInStock businessScopeAccountBook) {
		bondedWarehouseInStockManager.insertEntity(businessScopeAccountBook);
	}

	/**
	 * 更新保税库入库记录
	 * @param newValues
	 * @param number
	 * @return 保税库入库记录
	 */
	@Override
	public BondedWarehouseInStock update(Map newValues, String number) {
		return bondedWarehouseInStockManager.updateCustoms(newValues);
	}

	/**
	 * 删除保税库入库记录
	 * @param number
	 */
	@Override
	public void delete(String number) {
		bondedWarehouseInStockManager.deleteEntity(number, BondedWarehouseInStock.class);
	}

	/**
	 * 查询相关保税库入库记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	@Override
	public List<BondedWarehouseInStock> doQuery(Map values, int startRow, int endRow) {
		return bondedWarehouseInStockManager.getBondedWarehouseInStocks(values, 0, -1);
	}

	/**
	 * 获取所有保税库入库记录
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	@Override
	public List<BondedWarehouseInStock> getList(int startRow, int endRow) {
		return bondedWarehouseInStockManager.getBondedWarehouseInStocks(null, 0, -1);
	}
}