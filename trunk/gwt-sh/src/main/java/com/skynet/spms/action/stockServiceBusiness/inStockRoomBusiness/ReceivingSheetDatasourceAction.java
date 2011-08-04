package com.skynet.spms.action.stockServiceBusiness.inStockRoomBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.inStockRoomBusiness.ReceivingSheetManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheet;
import com.skynet.spms.persistence.entity.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItems;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.logicStockManage.LogicStock;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;
import com.skynet.spms.tools.OneToManyTools;

/**
 * 描述：收料单相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class ReceivingSheetDatasourceAction implements
		DataSourceAction<ReceivingSheet> {

	@Autowired
	private ReceivingSheetManager receivingSheetManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "receivingSheet_dataSource" };
	}

	/**
	 * 新增收料单相关信息
	 * @param receivingSheet
	 */
	@Override
	public void insert(ReceivingSheet receivingSheet) {
		List<ReceivingSheetItems> newReceivingSheetItemsList =
			OneToManyTools.ConvertToEntity(receivingSheet.getReceivingSheetItemsList(), ReceivingSheetItems.class);
		receivingSheet.setReceivingSheetItemsList(newReceivingSheetItemsList);
		
		receivingSheetManager.saveReceivingSheet(receivingSheet);
	}

	/**
	 * 更新收料单相关信息
	 * @param newValues
	 * @param number
	 * @return 收料单相关信息
	 */
	@Override
	public ReceivingSheet update(Map newValues, String number) {
		ReceivingSheet receivingSheet = new ReceivingSheet();
		
		BeanPropUtil.fillEntityWithMap(receivingSheet, newValues);
		List<ReceivingSheetItems> newReceivingSheetItemsList =
			OneToManyTools.ConvertToEntity(receivingSheet.getReceivingSheetItemsList(), ReceivingSheetItems.class);
		receivingSheet.setReceivingSheetItemsList(newReceivingSheetItemsList);

		return receivingSheetManager.saveReceivingSheet(receivingSheet);
	}

	/**
	 * 删除收料单相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		receivingSheetManager.deleteEntity(number, ReceivingSheet.class);
	}

	/**
	 * 查询收料单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 收料单相关信息
	 */
	@Override
	public List<ReceivingSheet> doQuery(Map values, int startRow, int endRow) {
		return receivingSheetManager.getReceivingSheet(values, 0, -1);
	}

	/**
	 * 获取所有收料单信息
	 * @param startRow
	 * @param endRow
	 * @return 收料单信息
	 */
	@Override
	public List<ReceivingSheet> getList(int startRow, int endRow) {
		return receivingSheetManager.getReceivingSheet(null, 0, -1);
	}
}
