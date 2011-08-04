/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class WaitReceivingSheetListgrid extends ListGrid {

	private Logger log = Logger.getLogger("WaitReceivingSheetListgrid");

	public void drawWaitReceivingSheetListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 提货指令单ID 
		ListGridField orderIDField = new ListGridField("orderID");
		orderIDField.setHidden(true);
		// 提货指令单编号
		ListGridField orderNumberField = new ListGridField("orderNumber");
		// 业务类型
		ListGridField businessTypeField = new ListGridField("businessType");
		// 合同编号
		ListGridField contractNumberField = new ListGridField("contractNumber");
		// 运单号
		ListGridField mainWayBillField = new ListGridField("mainWayBill");
		// 物流操作人员
		ListGridField logisticsCreateByField = new ListGridField("logisticsCreateBy");
		// 物流操作日期
		ListGridField logisticsCreateDateField = new ListGridField("logisticsCreateDate");
		logisticsCreateDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		orderIDField.setCanFilter(true);
		orderNumberField.setCanFilter(true);
		businessTypeField.setCanFilter(true);
		contractNumberField.setCanFilter(true);
		mainWayBillField.setCanFilter(true);
		logisticsCreateByField.setCanFilter(true);
		logisticsCreateDateField.setCanFilter(true);

		setFields(orderIDField
				,orderNumberField
				,businessTypeField
				,contractNumberField
				,mainWayBillField
				,logisticsCreateByField
				,logisticsCreateDateField
				);
		
		setCellHeight(22);
	}

}
