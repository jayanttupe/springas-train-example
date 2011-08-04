package com.skynet.spms.action.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseOutStockManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.BondedWarehouseOutStock;

/**
 * 描述：保税库出库记录维护相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class BondedWarehouseOutStockByIsCustomsDatasourceAction implements DataSourceAction<BondedWarehouseOutStock>{
	@Autowired
	
	private BondedWarehouseOutStockManager bondedWarehouseOutStockManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"bondedWarehouseOutStockByIsCustoms_dataSource"};
	}

	/**
	 * 新增保税库出库记录
	 * @param businessScopeAccountBook
	 */
	@Override
	public void insert(BondedWarehouseOutStock businessScopeAccountBook) {
	}

	/**
	 * 更新保税库出库记录相关信息
	 * @param newValues
	 * @param number
	 * @return 保税库出库记录相关信息
	 */
	@Override
	public BondedWarehouseOutStock update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除保税库出库记录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询保税库出库相关记录
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 保税库出库记录
	 */
	@Override
	public List<BondedWarehouseOutStock> doQuery(Map values, int startRow, int endRow) {
		return bondedWarehouseOutStockManager.getBondedWarehousOutStocksByIsCustoms(values, 0, -1);
	}

	/**
	 * 获取所有保税库出库记录
	 * @param startRow
	 * @param endRow
	 * @return 保税库出库记录
	 */
	@Override
	public List<BondedWarehouseOutStock> getList(int startRow, int endRow) {
		return bondedWarehouseOutStockManager.getBondedWarehousOutStocksByIsCustoms(null, endRow, endRow);
	}
}