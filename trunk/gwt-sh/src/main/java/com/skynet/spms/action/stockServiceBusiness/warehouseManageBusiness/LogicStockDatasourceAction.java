package com.skynet.spms.action.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.LogicStockManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;

/**
 * 描述：逻辑库相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class LogicStockDatasourceAction implements DataSourceAction<LogicStock>{
	
	private Logger log=LoggerFactory.getLogger(LogicStockDatasourceAction.class);
	
	@Autowired
	private LogicStockManager logicStockManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"logicStock_dataSource"};
	}
	
	/**
	 * 新增逻辑库相关信息
	 * @param logicStock
	 */
	@Override
	public void insert(LogicStock logicStock) {
		logicStockManager.insertLogicStock(logicStock);
	}

	/**
	 * 更新逻辑库相关信息
	 * @param newValues
	 * @param number
	 * @return 逻辑库相关信息
	 */
	@Override
	public LogicStock update(Map newValues, String number) {
		return (LogicStock) logicStockManager.updateEntity(newValues, number, LogicStock.class);
	}

	/**
	 * 删除逻辑库相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		logicStockManager.deleteEntity(number, LogicStock.class);
	}

	/**
	 * 查询逻辑库相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 逻辑库相关信息
	 */
	@Override
	public List<LogicStock> doQuery(Map values, int startRow, int endRow) {
		return logicStockManager.getLogicStock(values, startRow, endRow);
	}

	/**
	 * 获取所有逻辑库信息
	 * @param startRow
	 * @param endRow
	 * @return 逻辑库信息
	 */
	@Override
	public List<LogicStock> getList(int startRow, int endRow) {
		return logicStockManager.list(0, -1, LogicStock.class);
	}
}
