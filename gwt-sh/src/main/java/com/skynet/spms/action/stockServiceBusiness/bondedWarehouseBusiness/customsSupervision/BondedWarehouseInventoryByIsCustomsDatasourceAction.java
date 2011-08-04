package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision;

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
public class BondedWarehouseInventoryByIsCustomsDatasourceAction implements DataSourceAction<BondedWarehouseInStock>{
	@Autowired
	private BondedWarehouseInStockManager bondedWarehouseInStockManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"bondedWarehouseInventoryStockByIsCustoms_dataSource"};
	}

	/**
	 * 新增库房保税库在库记录
	 * @param businessScopeAccountBook
	 */
	@Override
	public void insert(BondedWarehouseInStock businessScopeAccountBook) {
	}

	/**
	 * 更新保税库在库记录相关信息
	 * @param newValues
	 * @param number
	 * @return 保税库在库记录相关信息
	 */
	@Override
	public BondedWarehouseInStock update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除保税库在库记录
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询保税库在库相关记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库在库记录
	 */
	@Override
	public List<BondedWarehouseInStock> doQuery(Map values, int startRow, int endRow) {
		return bondedWarehouseInStockManager.getInStocksByIsCustoms(values, 0, -1);
	}

	/**
	 * 获取所有保税库在库记录
	 * @param startRow
	 * @param endRow
	 * @return 保税库在库记录
	 */
	@Override
	public List<BondedWarehouseInStock> getList(int startRow, int endRow) {
		return bondedWarehouseInStockManager.getInStocksByIsCustoms(null, startRow, endRow);
	}
}