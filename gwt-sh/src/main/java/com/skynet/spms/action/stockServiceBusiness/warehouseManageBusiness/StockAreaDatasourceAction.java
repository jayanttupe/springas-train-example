package com.skynet.spms.action.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StockAreaManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockArea;

/**
 * 描述：库房区域相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockAreaDatasourceAction implements DataSourceAction<StockArea>{
	@Autowired
	private StockAreaManager stockRoomManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"stockArea_dataSource"};
	}
	
	/**
	 * 新增库房区域相关信息
	 * @param stockRoom
	 */
	@Override
	public void insert(StockArea stockRoom) {
	}

	/**
	 * 更新库房区域相关信息
	 * @param newValues
	 * @param number
	 * @return
	 */
	@Override
	public StockArea update(Map newValues, String number) {
		return null;
	}
	
	/**
	 * 删除库房区域相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockRoomManager.deleteEntity(number, StockArea.class);
	}

	/**
	 * 查询库房区域相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 库房区域相关信息
	 */
	@Override
	public List<StockArea> doQuery(Map values, int startRow, int endRow) {
		return stockRoomManager.getStockArea(values, 0, -1);
	}

	/**
	 * 获取所有库房区域信息
	 * @param startRow
	 * @param endRow
	 * @return 库房区域信息
	 */
	@Override
	public List<StockArea> getList(int startRow, int endRow) {
		return stockRoomManager.getStockArea(null, 0, -1);
	}
}
