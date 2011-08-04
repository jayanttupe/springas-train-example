package com.skynet.spms.action.stockServiceBusiness.partsInventory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.partsInventory.FreezeRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.freezeRecord.FreezeRecord;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 描述：备件冻结相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class FreezeRecordDatasourceAction implements DataSourceAction<FreezeRecord>{
	@Autowired
	private FreezeRecordManager freezeRecordManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"freezeRecord_dataSource"};
	}
	
	/**
	 * 新增备件冻结相关信息
	 * @param freezeRecord
	 */
	@Override
	public void insert(FreezeRecord freezeRecord) {
		freezeRecordManager.saveFreezeRecord(freezeRecord);
	}
	
	/**
	 * 更新备件冻结相关信息
	 * @param newValues
	 * @param number
	 * @return 备件冻结相关信息
	 */
	@Override
	public FreezeRecord update(Map newValues, String number) {
		if (newValues.containsKey("type") && newValues.get("type").equals("release")) {
			// 解除冻结处理
			String freezeRecordID = newValues.get("freezeRecordID").toString();
			String partsInventoryID = newValues.get("partsInventoryID").toString();
			freezeRecordManager.releaseFreezeRecord(freezeRecordID, partsInventoryID);
			return null;
		} else {
			// 冻结处理
			FreezeRecord freezeRecord = new FreezeRecord();		
			BeanPropUtil.fillEntityWithMap(freezeRecord, newValues);
			
			return (FreezeRecord) freezeRecordManager.saveFreezeRecord(freezeRecord);	
		}
	}
	
	/**
	 * 删除备件冻结相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		freezeRecordManager.deleteEntity(number, FreezeRecord.class);
	}
	
	/**
	 * 查询备件冻结相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件冻结相关信息
	 */
	@Override
	public List<FreezeRecord> doQuery(Map values, int startRow, int endRow) {
		return freezeRecordManager.getFreezeRecord(values, 0, -1);
	}
	
	/**
	 * 获取所有备件冻结信息
	 * @param startRow
	 * @param endRow
	 * @return 备件冻结信息
	 */
	@Override
	public List<FreezeRecord> getList(int startRow, int endRow) {
		return freezeRecordManager.getFreezeRecord(null, 0, -1);
	}
}