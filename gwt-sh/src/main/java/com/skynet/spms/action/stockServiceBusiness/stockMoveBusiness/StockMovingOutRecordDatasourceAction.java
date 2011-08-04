package com.skynet.spms.action.stockServiceBusiness.stockMoveBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockMoveBusiness.StockMovingRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockMoveBusiness.StockMovingRecordItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 描述：移库记录相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class StockMovingOutRecordDatasourceAction implements DataSourceAction<StockMovingRecord>{
	@Autowired
	private StockMovingRecordManager stockMovingRecordManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"stockMovingOutRecord_dataSource"};
	}

//	@Override
//	public void insert(StockMovingRecord stockMovingRecord) {
//		stockMovingRecordManager.insertEntity(stockMovingRecord);
//	}
	
	/**
	 * 新增移库记录相关信息
	 * @param stockMovingRecord
	 */
	@Override
	public void insert(StockMovingRecord stockMovingRecord) {
		List<StockMovingRecordItems> newStockMovingRecordItemsList = ConvertToEntity(stockMovingRecord.getStockMovingRecordItems());
		stockMovingRecord.setStockMovingRecordItems(newStockMovingRecordItemsList);
		stockMovingRecordManager.SaveStockMovingRecord(stockMovingRecord);
		}

	/**
	 * 更新移库记录相关信息
	 * @param newValues
	 * @param number
	 * @return
	 */
	@Override
	public StockMovingRecord update(Map newValues, String number) {
		String strType = "";
		if (newValues.containsKey("type")) {
			strType = newValues.get("type").toString();
		}
		
		if (strType.equals("order")) {
			return stockMovingRecordManager.updateRecordstate(newValues);
		}else{
		StockMovingRecord stockMovingRecord = new StockMovingRecord();		
		BeanPropUtil.fillEntityWithMap(stockMovingRecord, newValues);
		List<StockMovingRecordItems> newStockMovingRecordItemsList = ConvertToEntity(stockMovingRecord.getStockMovingRecordItems());
		stockMovingRecord.setStockMovingRecordItems(newStockMovingRecordItemsList);
		return stockMovingRecordManager.SaveStockMovingRecord(stockMovingRecord);
		}
	}

	private List<StockMovingRecordItems> ConvertToEntity(List<StockMovingRecordItems> StockMovingRecordItemsList)
	{
		List<StockMovingRecordItems> newList = new ArrayList<StockMovingRecordItems>();
		
		for (int i = 0; i < StockMovingRecordItemsList.size(); i++)
		{
			Map map = (Map)StockMovingRecordItemsList.get(i);
			Set set = map.entrySet();
			StockMovingRecordItems stockMovingRecordItems = new StockMovingRecordItems();
			BeanPropUtil.fillEntityWithMap(stockMovingRecordItems, map);
	        
			newList.add(stockMovingRecordItems);
		}
		return newList;
	}

	/**
	 * 删除移库记录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		stockMovingRecordManager.deleteEntity(number, StockMovingRecord.class);
	}

	/**
	 * 查询移库记录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 移库记录相关信息
	 */
	@Override
	public List<StockMovingRecord> doQuery(Map values, int startRow, int endRow) {
		return stockMovingRecordManager.getAllByCondition(values, 0, -1);
	}

	/**
	 * 获取移库记录相关信息
	 * @param startRow
	 * @param endRow
	 * @return 移库记录相关信息
	 */
	@Override
	public List<StockMovingRecord> getList(int startRow, int endRow) {
		return stockMovingRecordManager.findbyall();
	}
}