package com.skynet.spms.action.stockServiceBusiness.stockCheckBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockCheckBusiness.StockCheckManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheck;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockCheckBusiness.StockCheckItem;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：盘点业务相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockCheckDatasourceAction implements DataSourceAction<StockCheck>{
	@Autowired
	private StockCheckManager stockCheckManager;
	@Autowired
	private StockTaskManager stockTaskManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"stockCheck_dataSource"};
	}

	/**
	 * 新增盘点业务相关信息
	 * @param stockCheck
	 */
	@Override
	public void insert(StockCheck stockCheck) {
		List<StockCheckItem> stockCheckItemList = OneToManyTools.ConvertToEntity(stockCheck.getStockCheckItemList(), StockCheckItem.class);
		stockCheck.setStockCheckItemList(stockCheckItemList);
		
		stockCheckManager.saveStockCheck(stockCheck);
	}

	/**
	 * 更新盘点业务相关信息
	 * @param newValues
	 * @param number
	 * @return 盘点业务相关信息
	 */
	@Override
	public StockCheck update(Map newValues, String number) {
		if (!newValues.containsKey("type")) {
			// 更新盘点计划
			StockCheck stockCheck = new StockCheck();
			BeanPropUtil.fillEntityWithMap(stockCheck, newValues);

			List<StockCheckItem> stockCheckItemList = OneToManyTools.ConvertToEntity(stockCheck.getStockCheckItemList(), StockCheckItem.class);
			stockCheck.setStockCheckItemList(stockCheckItemList);

			return stockCheckManager.saveStockCheck(stockCheck);	
		} else {
			String stockCheckID = newValues.get("stockCheckID").toString();
			// 下达盘点任务
			stockTaskManager.insertStockCountTask(stockCheckID);
			return null;
		}
	}
	
	/**
	 * 删除盘点业务相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockCheckManager.deleteEntity(number, StockCheck.class);
	}

	/**
	 * 查询盘点业务相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 盘点业务相关信息
	 */
	@Override
	public List<StockCheck> doQuery(Map values, int startRow, int endRow) {
		return stockCheckManager.getStockCheck(values, 0, -1);
	}

	/**
	 * 获取所有盘点业务信息
	 * @param startRow
	 * @param endRow
	 * @return 盘点业务信息
	 */
	@Override
	public List<StockCheck> getList(int startRow, int endRow) {
		return stockCheckManager.getStockCheck(null, 0, -1);
	}
}