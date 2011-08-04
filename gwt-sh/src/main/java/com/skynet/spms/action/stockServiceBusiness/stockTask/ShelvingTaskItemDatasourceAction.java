package com.skynet.spms.action.stockServiceBusiness.stockTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.ShelvingTaskItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.ShelvingTaskItem;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.StockTask;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：上架任务管理明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ShelvingTaskItemDatasourceAction implements
		DataSourceAction<ShelvingTaskItem> {
	@Autowired
	private ShelvingTaskItemManager shelvingTaskItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "shelvingTaskItem_dataSource" };
	}

	/**
	 * 新增上架任务管理明细相关信息
	 * @param shelvingTaskItem
	 */
	@Override
	public void insert(ShelvingTaskItem shelvingTaskItem) {
	}

	/**
	 * 更新上架任务管理明细相关信息
	 * @param newValues
	 * @param number
	 * @return null
	 */
	@Override
	public ShelvingTaskItem update(Map newValues, String number) {
		// 条码标签唯一编号
		ArrayList<String> barcodeTagUUIDList = (ArrayList<String>)newValues.get("barcodeTagUUIDs");
		// RFID标签唯一序列号
		ArrayList<String> tagIdentifierCodeList = (ArrayList<String>)newValues.get("tagIdentifierCodes");
		// 件号
		ArrayList<String> partNumberList = (ArrayList<String>)newValues.get("partNumbers");
		// 序号/批号
		ArrayList<String> partSerialNumberList = (ArrayList<String>)newValues.get("partSerialNumbers");
		// 货位编号
		ArrayList<String> cargoSpaceNumbersList = (ArrayList<String>)newValues.get("cargoSpaceNumbers");

		String[] barcodeTagUUIDs = null;
		String[] tagIdentifierCodes = null;
		String[] partNumbers = null;
		String[] partSerialNumbers = null;
		String[] cargoSpaceNumbers = null;
		if (barcodeTagUUIDList != null && barcodeTagUUIDList.size() > 0) {
			barcodeTagUUIDs = new String[barcodeTagUUIDList.size()];
			barcodeTagUUIDs = barcodeTagUUIDList.toArray(barcodeTagUUIDs);
		}
		if (tagIdentifierCodeList != null && tagIdentifierCodeList.size() > 0) {
			tagIdentifierCodes = new String[tagIdentifierCodeList.size()];
			tagIdentifierCodes = tagIdentifierCodeList.toArray(tagIdentifierCodes);
		}
		if (partNumberList != null && partNumberList.size() > 0) {
			partNumbers = new String[partNumberList.size()];
			partNumbers = partNumberList.toArray(partNumbers);
		}
		if (partSerialNumberList != null && partSerialNumberList.size() > 0) {
			partSerialNumbers = new String[partSerialNumberList.size()];
			partSerialNumbers = partSerialNumberList.toArray(partSerialNumbers);
		}
		if (cargoSpaceNumbersList != null && cargoSpaceNumbersList.size() > 0) {
			cargoSpaceNumbers = new String[cargoSpaceNumbersList.size()];
			cargoSpaceNumbers = cargoSpaceNumbersList.toArray(cargoSpaceNumbers);
		}
		
		StockTask stockTask = new StockTask();
		stockTask.setTaskNo(newValues.get("taskNo").toString());
		
		List<ShelvingTaskItem> shelvingTaskItemList = new ArrayList<ShelvingTaskItem>();
		for (int i = 0; i < partNumbers.length; i++) {
			ShelvingTaskItem shelvingTaskItem = new ShelvingTaskItem();
			if (barcodeTagUUIDs != null && barcodeTagUUIDs.length > i) {
				// 条码标签唯一编号
				shelvingTaskItem.setBarcodeTagUUID(barcodeTagUUIDs[i]);
			}
			if (tagIdentifierCodes != null && tagIdentifierCodes.length > i) {
				// RFID标签唯一序列号
				shelvingTaskItem.setTagIdentifierCode(tagIdentifierCodes[i]);
			}
			// 件号
			shelvingTaskItem.setPartNumber(partNumbers[i]);
			// 序号/批号
			shelvingTaskItem.setPartSerialNumber(partSerialNumbers[i]);
			// 货位编号
			shelvingTaskItem.setCargoSpaceNumber(cargoSpaceNumbers[i]);
			shelvingTaskItemList.add(shelvingTaskItem);
		}
		
		shelvingTaskItemManager.updateShelvingTaskItem(stockTask, shelvingTaskItemList);
		return null;
	}

	/**
	 * 删除上架任务管理明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询上架任务管理明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 上架任务管理明细相关信息
	 */
	@Override
	public List<ShelvingTaskItem> doQuery(Map values, int startRow, int endRow) {
		return shelvingTaskItemManager.getShelvingTaskItem(values, startRow, endRow);
	}

	/**
	 * 获取所有上架任务管理明细信息
	 * @param startRow
	 * @param endRow
	 * @return 上架任务管理明细信息
	 */
	@Override
	public List<ShelvingTaskItem> getList(int startRow, int endRow) {
		return shelvingTaskItemManager.getShelvingTaskItem(null, startRow, endRow);
	}
}