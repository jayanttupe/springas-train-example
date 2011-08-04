package com.skynet.spms.action.stockServiceBusiness.stockManageTool;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockManageTool.StockPolicyManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockManageTool.stockPolicy.StockPolicy;

/**
 * 描述：库存策略相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockPolicyDatasourceAction implements DataSourceAction<StockPolicy>{
	@Autowired
	private StockPolicyManager stockPolicyManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"stockPolicy_dataSource"};
	}
	
	/**
	 * 新增库存策略相关信息
	 * @param stockPolicy
	 */
	@Override
	public void insert(StockPolicy stockPolicy) {
		stockPolicyManager.insertEntity(stockPolicy);
	}
	
	/**
	 * 更新库存策略相关信息
	 * @param newValues
	 * @param number
	 * @return 库存策略相关信息
	 */
	@Override
	public StockPolicy update(Map newValues, String number) {
		return (StockPolicy) stockPolicyManager.updateEntity(newValues, number, StockPolicy.class);
	}
	
	/**
	 * 删除库存策略相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockPolicyManager.deleteEntity(number, StockPolicy.class);
	}
	
	/**
	 * 查询库存策略相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 库存策略相关信息
	 */
	@Override
	public List<StockPolicy> doQuery(Map values, int startRow, int endRow) {
		return stockPolicyManager.getStockPolicy(values, 0, -1);
	}
	
	/**
	 * 获取所有库存策略信息
	 * @param startRow
	 * @param endRow
	 * @return 库存策略信息
	 */
	@Override
	public List<StockPolicy> getList(int startRow, int endRow) {
		return stockPolicyManager.getStockPolicy(null, 0, -1);
	}
}