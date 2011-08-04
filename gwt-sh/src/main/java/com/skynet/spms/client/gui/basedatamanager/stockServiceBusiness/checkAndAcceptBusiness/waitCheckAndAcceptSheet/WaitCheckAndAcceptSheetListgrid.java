/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class WaitCheckAndAcceptSheetListgrid extends ListGrid {

	private Logger log = Logger.getLogger("WaitReceivingSheetListgrid");

	public void drawWaitCheckAndAcceptSheetListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 收料单编号 
		ListGridField receivingSheetNumberFiled = new ListGridField("receivingSheetNumber");
		// 业务类型
		ListGridField businessTypeField = new ListGridField("businessType");
		// 指令单编号
		ListGridField orderNumberField = new ListGridField("orderNumber");
		// 合同编号
		ListGridField contractNumberField = new ListGridField("contractNumber");
		// 运单号
		ListGridField mainWayBillField = new ListGridField("mainWayBill");
		// 收料人
		ListGridField receivingUserField = new ListGridField("receivingUser");
		// 收料日期
		ListGridField receivingDateField = new ListGridField("receivingDate");
		receivingDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		receivingSheetNumberFiled.setCanFilter(true);
		businessTypeField.setCanFilter(true);
		orderNumberField.setCanFilter(true);
		contractNumberField.setCanFilter(true);
		mainWayBillField.setCanFilter(true);
		receivingUserField.setCanFilter(true);
		receivingDateField.setCanFilter(true);
		
		setFields(receivingSheetNumberFiled
				,businessTypeField
				,orderNumberField
				,contractNumberField
				,mainWayBillField
				,receivingUserField
				,receivingDateField);

		setCellHeight(22);
	}
	
}

