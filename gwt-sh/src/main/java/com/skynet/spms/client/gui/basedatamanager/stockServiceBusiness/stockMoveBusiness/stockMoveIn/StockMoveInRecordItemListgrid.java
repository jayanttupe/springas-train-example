/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveIn;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class StockMoveInRecordItemListgrid extends ListGrid{

	private Logger log = Logger.getLogger("StockMoveRecordItemListgrid");
	
	public void drawStockMoveItemsManageListgrid(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		
		setSelectionType(SelectionStyle.SIMPLE);
        setEditByCell(true);
		
        // 移库单号 
		ListGridField numberFiled = new ListGridField("stockMovingNumber");
		numberFiled.setHidden(true);
		numberFiled.setCanEdit(false);
		// 项号
		ListGridField movingOutStockRoomField = new ListGridField("stockMovingNumberItem");	
		movingOutStockRoomField.setCanEdit(false);
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		partNumberField.setCanEdit(false);
		// 序号
		ListGridField partSerialField = new ListGridField("partSerialNumber");
		partSerialField.setCanEdit(false);
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		quantityField.setCanEdit(false);
		// 单位
		ListGridField unitMeasureCodeField = new ListGridField("unitMeasureCode");
		unitMeasureCodeField.setCanEdit(false);
		// 备件状态
		ListGridField statusField = new ListGridField("status");
		statusField.setCanEdit(false);
		// 生产日期
		ListGridField dateOfManufactureField = new ListGridField("dateOfManufacture");
		dateOfManufactureField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		dateOfManufactureField.setCanEdit(false);
		// 价格
		ListGridField unitPriceField = new ListGridField("unitPrice");
		unitPriceField.setCanEdit(false);
		// 总金额
		ListGridField totalAmountField = new ListGridField("totalAmount");
		totalAmountField.setCanEdit(false);
		// 移出货位编号
		ListGridField movingOutFreightField = new ListGridField("movingOutFreightLotNumber");
		// 移入货位编号
		ListGridField movingInFreightField = new ListGridField("movingInFreightLotNumber");
		movingInFreightField.setCanEdit(false);
		// 状态
		ListGridField stateField = new ListGridField("state");
		stateField.setCanEdit(false);
		
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
				,movingInFreightField
				,stateField
				,numberFiled
		);
	}
	
}
