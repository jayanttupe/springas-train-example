package com.skynet.spms.action.stockServiceBusiness.inStockRoomBusiness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.PickupOrderItemsManager;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReceivingSheetItemsManager;
import com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;

/**
 * 描述：收料单历史记录明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ReceivingSheetItemsDatasourceAction implements
		DataSourceAction<ReceivingSheetItems> {
	
	@Autowired
	private PickupOrderItemsManager pickupOrderItemsManager;

	@Autowired
	private ReceivingSheetItemsManager receivingSheetItemsManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "receivingSheetItems_dataSource" };
	}

	/**
	 * 新增收料单历史记录明细相关信息
	 * @param entity
	 */
	public void insert(ReceivingSheetItems entity) {
		receivingSheetItemsManager.insertEntity(entity);
	}

	/**
	 * 更新收料单历史记录明细相关信息
	 * @param newValues
	 * @param number
	 * @return 收料单历史记录明细相关信息
	 */
	@Override
	public ReceivingSheetItems update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除收料单历史记录明细相关信息
	 * @param id
	 */
	@Override
	public void delete(String id) {
		receivingSheetItemsManager.deleteReceivingSheetItems(id);
	}

	/**
	 * 获取所有收料单历史记录明细信息
	 * @param startRow
	 * @param endRow
	 * @return 收料单历史记录明细信息
	 */
	@Override
	public List<ReceivingSheetItems> getList(int startRow, int endRow) {
		return receivingSheetItemsManager.list(0, -1, ReceivingSheetItems.class);
	}

	/**
	 * 查询收料单历史记录明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 收料单历史记录明细相关信息
	 */
	@Override
	public List<ReceivingSheetItems> doQuery(Map values, int startRow, int endRow) {
		String strType = "";
		if (values.containsKey("type")) {
			strType = values.get("type").toString();
		}
		
		if (strType.equals("order")) {
			List<ReceivingSheetItems> newReceivingSheetItemsList = new ArrayList();
			List<ReceivingSheetItems> receivingSheetItemsList = new ArrayList();
			// 根据指令ID取得收料指令明细数据
			String orderID = values.get("orderID").toString();
			Map newMap = new HashMap();
			newMap.put("orderID", orderID);
			receivingSheetItemsList = pickupOrderItemsManager.getPickupOrderItems(values, startRow,endRow);
			
			for (int i = 0; i < receivingSheetItemsList.size(); i++) {
				ReceivingSheetItems receivingSheetItems = receivingSheetItemsList.get(i);
				
				if (receivingSheetItems.getIsSerial() == true) {
					int qty = Integer.valueOf(receivingSheetItems.getQuantity());
					for (int j = 0; j < qty; j++) {
						ReceivingSheetItems newReceivingSheetItems = new ReceivingSheetItems();
						// 项号
						newReceivingSheetItems.setItemNumber(receivingSheetItems.getItemNumber());
						// 件号
						newReceivingSheetItems.setPartNumber(receivingSheetItems.getPartNumber());
						// 件名称
						newReceivingSheetItems.setPartName(receivingSheetItems.getPartName());
						// 数量
						newReceivingSheetItems.setQuantity(1);
						// 单位
						newReceivingSheetItems.setPartUnit(receivingSheetItems.getPartUnit());
						// 备件类型
						newReceivingSheetItems.setPartType(receivingSheetItems.getPartType());
						// 装箱单号
						newReceivingSheetItems.setPackingListNumber(receivingSheetItems.getPackingListNumber());
						// 分箱号
						newReceivingSheetItems.setBoxNO(receivingSheetItems.getBoxNO());
						// 是否序号控制
						newReceivingSheetItems.setIsSerial(receivingSheetItems.getIsSerial());
						newReceivingSheetItemsList.add(newReceivingSheetItems);
					}
				} else {
					ReceivingSheetItems newReceivingSheetItems = new ReceivingSheetItems();
					// 项号
					newReceivingSheetItems.setItemNumber(receivingSheetItems.getItemNumber());
					// 件号
					newReceivingSheetItems.setPartNumber(receivingSheetItems.getPartNumber());
					// 件名称
					newReceivingSheetItems.setPartName(receivingSheetItems.getPartName());
					// 数量
					newReceivingSheetItems.setQuantity(receivingSheetItems.getQuantity());
					// 单位
					newReceivingSheetItems.setPartUnit(receivingSheetItems.getPartUnit());
					// 备件类型
					newReceivingSheetItems.setPartType(receivingSheetItems.getPartType());
					// 装箱单号
					newReceivingSheetItems.setPackingListNumber(receivingSheetItems.getPackingListNumber());
					// 分箱号
					newReceivingSheetItems.setBoxNO(receivingSheetItems.getBoxNO());
					// 是否序号控制
					newReceivingSheetItems.setIsSerial(receivingSheetItems.getIsSerial());
					newReceivingSheetItemsList.add(newReceivingSheetItems);
				}
			}
			
			return newReceivingSheetItemsList;
		} else {
			// 根据收料单ID取得收料单明细数据
			return receivingSheetItemsManager.getReceivingSheetItems(values, 0, -1);
		}
	}
}
