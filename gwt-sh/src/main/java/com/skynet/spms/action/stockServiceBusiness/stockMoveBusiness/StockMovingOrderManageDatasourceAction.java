/**
 * 
 */
package com.skynet.spms.action.stockServiceBusiness.stockMoveBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingOrderManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.interfaceBusiness.baseInterfaceBusiness.baseTask.BaseTask;

/**
 * 描述：移库指令相关信息处理
 * 
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockMovingOrderManageDatasourceAction implements
		DataSourceAction<BaseTask> {
	@Autowired
	private StockMovingOrderManager stockMovingOrderManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "stockMovingOrder_dataSource" };
	}

	/**
	 * 新增移库指令相关信息
	 * @param baseTask
	 */
	@Override
	public void insert(BaseTask baseTask) {
		stockMovingOrderManager.insertEntity(baseTask);
	}

	/**
	 * 更新移库指令相关信息
	 * @param newValues
	 * @param number
	 * @return null
	 */
	@Override
	public BaseTask update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除移库指令相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockMovingOrderManager.deleteEntity(number, BaseTask.class);
	}

	/**
	 * 查询移库指令相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 移库指令相关信息
	 */
	@Override
	public List<BaseTask> doQuery(Map values, int startRow, int endRow) {
		return stockMovingOrderManager.getStockMovingOrder(values, 0, -1);
	}

	/**
	 * 获取所有移库指令信息
	 * @param startRow
	 * @param endRow
	 * @return 移库指令信息
	 */
	@Override
	public List<BaseTask> getList(int startRow, int endRow) {
		return stockMovingOrderManager.getStockMovingOrder(null, 0, -1);
	}

}
