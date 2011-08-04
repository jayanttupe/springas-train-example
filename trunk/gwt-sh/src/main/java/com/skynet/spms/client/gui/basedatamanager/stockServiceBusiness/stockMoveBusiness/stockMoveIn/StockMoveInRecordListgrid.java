/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveIn;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class StockMoveInRecordListgrid extends ListGrid{
	
	private Logger log = Logger.getLogger("StockMoveRecordListgrid");
	
	public void drawStockMoveManageListgrid(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
	
		setSelectionType(SelectionStyle.SINGLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
	    // ID
		ListGridField idFiled = new ListGridField("id");
		idFiled.setCanEdit(false);
		idFiled.setHidden(true);
		// 移库单号
		ListGridField numberFiled = new ListGridField("stockMovingNumber");
		numberFiled.setCanEdit(false);
		// 移出库房
		ListGridField movingOutStockRoomField = new ListGridField("movingOutStockRoomNumbers");
		// 移入库房
		ListGridField movingInStockRoomField = new ListGridField("movingInStockRoomNumbers");
		// 总项数
		ListGridField totalItemsField = new ListGridField("totalItemsQuantity");
		// 总金额
		ListGridField totalAmountField = new ListGridField("totalAmount");
		// 状态
		ListGridField stateField = new ListGridField("state");
		// 移库原因
		ListGridField stockMovingReasonField = new ListGridField("stockMovingReason");
		// 批准者 
		ListGridField ratifierField = new ListGridField("ratifier");
		// 批准日期
		ListGridField ratifyDateField = new ListGridField("ratifyDate");
		ratifyDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		
		numberFiled.setCanFilter(true);
		movingOutStockRoomField.setCanFilter(true);
		movingInStockRoomField.setCanFilter(true);
		totalItemsField.setCanFilter(true);
		totalAmountField.setCanFilter(true);
		stateField.setCanFilter(true);
		stockMovingReasonField.setCanFilter(true);
		ratifierField.setCanFilter(true);
		ratifyDateField.setCanFilter(true);
		
		setFields(numberFiled
				,movingOutStockRoomField
				,movingInStockRoomField
				,totalItemsField
				,totalAmountField
				,stateField
				,stockMovingReasonField
				,ratifierField
				,ratifyDateField
				,idFiled
				);
	}
}
