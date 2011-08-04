package com.skynet.spms.action.stockServiceBusiness.inStockRoomBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.InStockRecordManager;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.StockTaskManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.inStockRecord.InStockRecord;
import com.skynet.spms.tools.ConvertCodeName;

/**
 * 描述：入库记录管理相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class InStockRecordDatasourceAction implements
		DataSourceAction<InStockRecord> {
	@Autowired
	private InStockRecordManager inStockRecordManager;
	@Autowired
	private StockTaskManager stockTaskManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "inStockRecord_dataSource" };
	}

	/**
	 * 新增入库记录管理相关信息
	 * @param inStockRecord
	 */
	@Override
	public void insert(InStockRecord inStockRecord) {
		inStockRecordManager.updateRecord(inStockRecord);
	}

	/**
	 * 更新入库记录管理相关信息
	 * @param newValues
	 * @param number
	 * @return 入库记录管理相关信息
	 */ 
	@Override
	public InStockRecord update(Map newValues, String number) {
		if (newValues.containsKey("type")) {
			ArrayList<String> inStockRecordIDList = (ArrayList<String>)newValues.get("inStockRecordID");
			String[] inStockRecordIDs = new String[inStockRecordIDList.size()];
			inStockRecordIDs = inStockRecordIDList.toArray(inStockRecordIDs);
			
			if (String.valueOf(newValues.get("type")).equals("order")) {
				// 下达上架指令处理
				stockTaskManager.insertShelveTask(inStockRecordIDs);
				return null;
			}
			if (String.valueOf(newValues.get("type")).equals("sendcard")) {
				// 下达发卡指令处理
				stockTaskManager.insertSendCardTask(inStockRecordIDs);
			}

			return null;
		} else {
			// 设置货位处理
			return (InStockRecord) inStockRecordManager.updateEntity(newValues, number, InStockRecord.class);				
		}
	}

	/**
	 * 删除入库记录管理相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		inStockRecordManager.deleteEntity(number, InStockRecord.class);
	}

	/**
	 * 查询入库记录管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 入库记录管理相关信息
	 */
	@Override
	public List<InStockRecord> doQuery(Map values, int startRow, int endRow) {
		List<InStockRecord> inStockRecordList = inStockRecordManager.getInStockRecord(values, startRow, endRow);
		return inStockRecordList;
	}

	/**
	 * 获取所有入库记录管理信息
	 * @param startRow
	 * @param endRow
	 * @return 入库记录管理信息
	 */
	@Override
	public List<InStockRecord> getList(int startRow, int endRow) {
		List<InStockRecord> inStockRecordList = inStockRecordManager.getInStockRecord(null, startRow, endRow);
		return inStockRecordList;
	}
}