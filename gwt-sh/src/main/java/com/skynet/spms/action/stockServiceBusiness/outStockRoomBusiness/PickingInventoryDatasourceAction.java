package com.skynet.spms.action.stockServiceBusiness.outStockRoomBusiness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.outStockRoomBusiness.PickingListPartItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.partsInventory.PartsInventoryRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.outStockRoomBusiness.pickingList.pickingListPartItems.PickingListPartItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;
import com.skynet.spms.web.form.PickingInventoryForm;

/**
 * 描述：备件目录相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PickingInventoryDatasourceAction implements DataSourceAction<PickingInventoryForm>{
	@Autowired
	private PartsInventoryRecordManager partsInventoryRecordManager;
	@Autowired
	private PickingListPartItemsManager pickingListPartItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"pickingInventory_dataSource"};
	}

	/**
	 * 新增备件目录相关信息
	 * @param pickingInventoryForm
	 */
	@Override
	public void insert(PickingInventoryForm pickingInventoryForm) {
		PickingListPartItems pickingListPartItems = pickingInventoryForm.getPickingListPartItems();
		pickingListPartItemsManager.insertEntity(pickingListPartItems);
	}

	/**
	 * 更新备件目录相关信息
	 * @param newValues
	 * @param number
	 * @return 备件目录相关信息
	 */
	@Override
	public PickingInventoryForm update(Map newValues, String number) {
		pickingListPartItemsManager.savePickingListPartItems(newValues);
		
		return null;
	}

	/**
	 * 删除备件目录相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询备件目录相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 备件目录相关信息
	 */
	@Override
	public List<PickingInventoryForm> doQuery(Map values, int startRow, int endRow) {
		List<PickingInventoryForm> pickingInventoryForms = new ArrayList<PickingInventoryForm>();
		
		Map partsInventoryMap = new HashMap();
		partsInventoryMap.put("partNumber", values.get("partNumber"));
		List<PartsInventoryRecord> partsInventoryRecords =
			partsInventoryRecordManager.getPartsInventoryRecord(partsInventoryMap, startRow, endRow);
		for (PartsInventoryRecord partsInventoryRecord : partsInventoryRecords)
		{
			Map pickingListPartItemsMap = new HashMap();
			List<PickingListPartItems> pickingListPartItemsList = new ArrayList<PickingListPartItems>();
			if (values.get("pickingListID") != null &&
					!values.get("pickingListID").toString().equals(""))
			{
				// 配料单ID
				pickingListPartItemsMap.put("pickingListID", values.get("pickingListID"));
				// 件号
				pickingListPartItemsMap.put("partNumber", partsInventoryRecord.getPartNumber());
				// 序号/批号
				pickingListPartItemsMap.put("partSerialNumber", partsInventoryRecord.getPartSerialNumber());
				// 取得配料明细相关的数据
				pickingListPartItemsList =
					pickingListPartItemsManager.getPickingListPartItems(pickingListPartItemsMap, startRow, endRow);
			}

			PickingInventoryForm pickingInventoryForm = new PickingInventoryForm();
			pickingInventoryForm.setPartsInventoryRecord(partsInventoryRecord);
			if (pickingListPartItemsList != null && pickingListPartItemsList.size() > 0)
			{
				pickingInventoryForm.setPickingListPartItems(pickingListPartItemsList.get(0));
			}
			pickingInventoryForms.add(pickingInventoryForm);
		}
		
		return pickingInventoryForms;
	}

	/**
	 * 获取所有备件目录信息
	 * @param startRow
	 * @param endRow
	 * @return 备件目录信息
	 */
	@Override
	public List<PickingInventoryForm> getList(int startRow, int endRow) {
		List<PickingInventoryForm> pickingInventoryForms = new ArrayList();
		return pickingInventoryForms;
	}
}