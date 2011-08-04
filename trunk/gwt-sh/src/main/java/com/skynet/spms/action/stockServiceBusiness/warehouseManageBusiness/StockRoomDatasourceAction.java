package com.skynet.spms.action.stockServiceBusiness.warehouseManageBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.warehouseManageBusiness.StockRoomManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockArea;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：库房相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockRoomDatasourceAction implements DataSourceAction<StockRoom>{
	@Autowired
	private StockRoomManager stockRoomManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"stockRoom_dataSource"};
	}
	
	/**
	 * 新增库房相关信息
	 * @param stockRoom
	 */
	@Override
	public void insert(StockRoom stockRoom) {
		List<LogicStock> newLogicStockList = OneToManyTools.ConvertToEntity(stockRoom.getLogicStock(), LogicStock.class);
		stockRoom.setLogicStock(newLogicStockList);
		
		List<StockArea> newStockAreaList = OneToManyTools.ConvertToEntity(stockRoom.getStockArea(), StockArea.class);
		stockRoom.setStockArea(newStockAreaList);
		
		stockRoomManager.saveStockRoom(stockRoom);
	}

	/**
	 * 更新库房相关信息
	 * @param newValues
	 * @param number
	 * @return 库房相关信息
	 */
	@Override
	public StockRoom update(Map newValues, String number) {
		StockRoom stockRoom = new StockRoom();
		BeanPropUtil.fillEntityWithMap(stockRoom, newValues);
		
		List<LogicStock> newLogicStockList = OneToManyTools.ConvertToEntity(stockRoom.getLogicStock(), LogicStock.class);
		stockRoom.setLogicStock(newLogicStockList);
		
		List<StockArea> newStockAreaList = OneToManyTools.ConvertToEntity(stockRoom.getStockArea(), StockArea.class);
		stockRoom.setStockArea(newStockAreaList);

		return stockRoomManager.saveStockRoom(stockRoom);
	}

	/**
	 * 删除库房相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockRoomManager.deleteEntity(number, StockRoom.class);
	}

	/**
	 * 查询库房相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 库房相关信息
	 */
	@Override
	public List<StockRoom> doQuery(Map values, int startRow, int endRow) {
		return stockRoomManager.getStockRoom(values, 0, -1);
	}

	/**
	 * 获取所有库房信息
	 * @param startRow
	 * @param endRow
	 * @return 库房信息
	 */
	@Override
	public List<StockRoom> getList(int startRow, int endRow) {
		return stockRoomManager.getStockRoom(null, 0, -1);
	}
}
