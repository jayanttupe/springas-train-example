package com.skynet.spms.action.stockServiceBusiness.stockTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.PickingTaskItemManager;
import com.skynet.spms.persistence.entity.spmsdd.TaskStatus;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PickingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.ShelvingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;

/**
 * 描述：配料单相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickingTaskItemDatasourceAction implements
		DataSourceAction<PickingTaskItem> {
	@Autowired
	private PickingTaskItemManager pickingTaskItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "pickingTaskItem_dataSource" };
	}

	/**
	 * 新增配料单相关信息
	 * @param shelvingTaskItem
	 */
	@Override
	public void insert(PickingTaskItem shelvingTaskItem) {
	}

	/**
	 * 更新配料单相关信息
	 * @param newValues
	 * @param number
	 * @return 配料单相关信息
	 */
	@Override
	public PickingTaskItem update(Map newValues, String number) {
		// 明细件数
		int recordCount = Integer.valueOf(newValues.get("recordCount").toString());
		// 条码标签唯一编号
		ArrayList<String> barcodeTagUUIDsList = (ArrayList<String>)newValues.get("barcodeTagUUIDs");
		// RFID标签唯一序列号
		ArrayList<String> tagIdentifierCodeList = (ArrayList<String>)newValues.get("tagIdentifierCodes");

		// 条码标签唯一编号
		String[] barcodeTagUUIDs = null;
		if (barcodeTagUUIDsList != null && barcodeTagUUIDsList.size() > 0) {
			barcodeTagUUIDs = new String[barcodeTagUUIDsList.size()];
			barcodeTagUUIDs = barcodeTagUUIDsList.toArray(barcodeTagUUIDs);
		}
		// RFID标签唯一序列号
		String[] tagIdentifierCodes = null;
		if (tagIdentifierCodeList != null && tagIdentifierCodeList.size() > 0) {
			tagIdentifierCodes = new String[tagIdentifierCodeList.size()];
			tagIdentifierCodes = tagIdentifierCodeList.toArray(tagIdentifierCodes);
		}
		
		StockTask stockTask = new StockTask();
		stockTask.setBussinessBillNO(newValues.get("pickingListID").toString());
		stockTask.setTaskNo(newValues.get("taskNo").toString());
		stockTask.setTaskStatus(TaskStatus.OVR);
		
		List<PickingTaskItem> pickingTaskItemList = new ArrayList<PickingTaskItem>();
		for (int i = 0; i < recordCount; i++) {
			PickingTaskItem pickingTaskItem = new PickingTaskItem();
			if (barcodeTagUUIDs != null && barcodeTagUUIDs.length > i) {
				// 条码标签唯一编号
				pickingTaskItem.setBarcodeTagUUID(barcodeTagUUIDs[i]);
			}
			if (tagIdentifierCodes != null && tagIdentifierCodes.length > i) {
				// RFID标签唯一序列号
				pickingTaskItem.setTagIdentifierCode(tagIdentifierCodes[i]);
			}
			pickingTaskItemList.add(pickingTaskItem);
		}
		
		pickingTaskItemManager.updatePickingTaskItem(stockTask, pickingTaskItemList);
		return null;
	}

	/**
	 * 删除配料单相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询配料单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 配料单相关信息
	 */
	@Override
	public List<PickingTaskItem> doQuery(Map values, int startRow, int endRow) {
		return pickingTaskItemManager.getPickingTaskItem(values, startRow, endRow);
	}

	/**
	 * 获取所有配料单信息
	 * @param startRow
	 * @param endRow
	 * @return 配料单信息
	 */
	@Override
	public List<PickingTaskItem> getList(int startRow, int endRow) {
		return pickingTaskItemManager.getPickingTaskItem(null, startRow, endRow);
	}
}