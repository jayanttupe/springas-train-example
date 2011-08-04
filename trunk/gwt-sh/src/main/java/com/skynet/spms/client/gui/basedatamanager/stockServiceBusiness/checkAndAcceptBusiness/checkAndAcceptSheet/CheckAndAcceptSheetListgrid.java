package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet;

import com.skynet.spms.client.entity.DataInfo;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CheckAndAcceptSheetListgrid extends ListGrid {

	private DataInfo dataInfo;
	
	public DataInfo getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}
	
	public void drawCheckAndAcceptSheetManagerListgrid(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");

		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setShowFilterEditor(true);
		setCanEdit(false);

		// 检验单编号
		ListGridField checkAndAcceptSheetNumberField = new ListGridField("checkAndAcceptSheetNumber");
		checkAndAcceptSheetNumberField.setWidth(100);
		// 合同编号
		ListGridField contratNumberField = new ListGridField("contratNumber");
		contratNumberField.setWidth(100);
		// 收料单编号
		ListGridField receivingSheetNumberField = new ListGridField("receivingSheetNumber");
		receivingSheetNumberField.setWidth(100);
		// 业务类型
		ListGridField businessTypeField = new ListGridField("businessType");
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		partNumberField.setWidth(105);
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		partSerialNumberField.setWidth(100);
		// 供应商
		ListGridField providerNameField = new ListGridField("providerName");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		quantityField.setWidth(50);
		// 单位
		ListGridField unitField = new ListGridField("unit");
		unitField.setWidth(50);
		// 检验人
		ListGridField checkUserField = new ListGridField("checkUser");
		// 检验日期
		ListGridField checkDateField = new ListGridField("checkDate");
		checkDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 验收结论 
		ListGridField checkResultField = new ListGridField("checkResult");
		
		checkAndAcceptSheetNumberField.setCanFilter(true);
		contratNumberField.setCanFilter(true);
		receivingSheetNumberField.setCanFilter(true);
		businessTypeField.setCanFilter(true);
		partNumberField.setCanFilter(true);
		partSerialNumberField.setCanFilter(true);
		providerNameField.setCanFilter(true);
		quantityField.setCanFilter(true);
		unitField.setCanFilter(true);
		checkUserField.setCanFilter(true);
		checkUserField.setCanFilter(true);
		checkResultField.setCanFilter(true);

		setFields(checkAndAcceptSheetNumberField
				,contratNumberField
				,receivingSheetNumberField
				,businessTypeField
				,partNumberField
				,partSerialNumberField
				,providerNameField
				,quantityField
				,unitField
				,checkUserField
				,checkDateField
				,checkResultField
				);
		
		setCellHeight(22);
	}
}
