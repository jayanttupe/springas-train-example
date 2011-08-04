package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecordManage;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class InStockRecordManageListgrid extends ListGrid {

	private Logger log = Logger.getLogger("InStockRecordManageListgrid");

	public void drawInStockRecordManageListgrid() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		// 件描述
		ListGridField partNameField = new ListGridField("partName");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		// 单位
		ListGridField unitOfMeasureField = new ListGridField("unitOfMeasure");
		// 入库日期
		ListGridField inStockDateField = new ListGridField("inStockDate");
		inStockDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 实际货位
		ListGridField cargoSpaceNumberField = new ListGridField("cargoSpaceNumber");
		// 收料单号
		ListGridField receivingNumberField = new ListGridField("receivingSheetNumber");
		// 验收单号
		ListGridField checkAndAcceptSheetNumberField = new ListGridField("checkAndAcceptSheetNumber");
		// 合同编号
		ListGridField contractNumberField = new ListGridField("contratNumber");

		partNumberField.setCanFilter(true);
		partSerialNumberField.setCanFilter(true);
		partNameField.setCanFilter(true);
		quantityField.setCanFilter(true);
		unitOfMeasureField.setCanFilter(true);
		inStockDateField.setCanFilter(true);
		cargoSpaceNumberField.setCanFilter(true);
		receivingNumberField.setCanFilter(true);
		checkAndAcceptSheetNumberField.setCanFilter(true);
		contractNumberField.setCanFilter(true);

		setFields( partNumberField
				, partSerialNumberField
				, partNameField
				, quantityField
				, unitOfMeasureField
				, inStockDateField
				, cargoSpaceNumberField
				, receivingNumberField
				, checkAndAcceptSheetNumberField
				, contractNumberField
			    );
		setCellHeight(22);
	}
}