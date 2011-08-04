package com.skynet.spms.action.stockServiceBusiness.partsInventory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.partsInventory.PartsInventoryRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

/**
 * 描述：备件目录相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PartsInventoryRecordDatasourceAction implements DataSourceAction<PartsInventoryRecord>{
	@Autowired
	private PartsInventoryRecordManager partsInventoryRecordManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"partsInventoryRecord_dataSource"};
	}

	/**
	 * 新增备件目录相关信息
	 * @param partsInventoryRecord
	 */
	@Override
	public void insert(PartsInventoryRecord partsInventoryRecord) {
		partsInventoryRecordManager.insertEntity(partsInventoryRecord);
	}

	/**
	 * 更新备件目录相关信息
	 * @param newValues
	 * @param number
	 * @return 备件目录相关信息
	 */
	@Override
	public PartsInventoryRecord update(Map newValues, String number) {
		return (PartsInventoryRecord) partsInventoryRecordManager.updateEntity(newValues, number, PartsInventoryRecord.class);
	}

	/**
	 * 删除备件目录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		partsInventoryRecordManager.deleteEntity(number, PartsInventoryRecord.class);
	}

	/**
	 * 查询备件目录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件目录相关信息
	 */
	@Override
	public List<PartsInventoryRecord> doQuery(Map values, int startRow, int endRow) {
		if(values.containsKey("repairCodeId")){
			String repairCodeId = (String)values.get("repairCodeId");
			return partsInventoryRecordManager.findByRepairCodeId(repairCodeId);
		}else{
			return partsInventoryRecordManager.getPartsInventoryRecord(values, startRow, endRow);
		}
	}

	/**
	 * 获取所有备件目录信息
	 * @param startRow
	 * @param endRow
	 * @return 备件目录信息
	 */
	@Override
	public List<PartsInventoryRecord> getList(int startRow, int endRow) {
		return partsInventoryRecordManager.getPartsInventoryRecord(null, 0, -1);
	}
}