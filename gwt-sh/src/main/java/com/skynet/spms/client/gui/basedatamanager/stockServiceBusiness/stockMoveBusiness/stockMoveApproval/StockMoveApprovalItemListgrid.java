/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveApproval;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class StockMoveApprovalItemListgrid extends ListGrid{

	private Logger log = Logger.getLogger("StockMoveRecordItemListgrid");
	
	public void drawStockMoveItemsManageListgrid(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setCanEdit(true);
		setSelectionType(SelectionStyle.SINGLE);

		// 移库单号
		ListGridField numberFiled = new ListGridField("stockMovingNumber");
		numberFiled.setHidden(true);
		numberFiled.setCanEdit(false);
		// 项号
		ListGridField movingOutStockRoomField = new ListGridField("stockMovingNumberItem");	
		movingOutStockRoomField.setCanEdit(false);
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 序号
		ListGridField partSerialField = new ListGridField("partSerialNumber");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		// 单位
		ListGridField unitMeasureCodeField = new ListGridField("unitMeasureCode");
		// 备件状态
		ListGridField statusField = new ListGridField("status");
		// 生产日期
		ListGridField dateOfManufactureField = new ListGridField("dateOfManufacture");
		dateOfManufactureField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 价格
		ListGridField unitPriceField = new ListGridField("unitPrice");
		// 总金额
		ListGridField totalAmountField = new ListGridField("totalAmount");
		// 移出货位编号
		ListGridField movingOutFreightField = new ListGridField("movingOutFreightLotNumber");
		// 状态
		ListGridField stateField = new ListGridField("state");
		
		setFields(movingOutStockRoomField
				,partNumberField
				,partSerialField
				,quantityField
				,unitMeasureCodeField
				,statusField
				,dateOfManufactureField
				,unitPriceField
				,totalAmountField
				,movingOutFreightField
				,stateField
				,numberFiled
		);
	}
	
}
