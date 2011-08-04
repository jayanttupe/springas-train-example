package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport;

import java.util.logging.Logger;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class NonconformingReportListgrid extends ListGrid {

	private Logger log = Logger.getLogger("NonconformingReportListgrid");
	DataSource userDataSource;

	public DataSource getUserDataSource() {
		return userDataSource;
	}

	public NonconformingReportListgrid() {
		
	}
	
	public void drawNonconformingReportListgrid(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 不合格品报告编号
		ListGridField nonconformingReportNumberField = new ListGridField("nonconformingReportNumber");
		// 合同编号
		ListGridField contratNumberField = new ListGridField("contratNumber");
		// 检验单号
		ListGridField checkAndAcceptSheetNumberField = new ListGridField("checkAndAcceptSheetNumber");
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 供货单位
		ListGridField supplyUnitField = new ListGridField("supplyUnit");
		// 处理日期
		ListGridField treatmentDateField = new ListGridField("treatmentDate");
		treatmentDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 状态
		ListGridField stateField = new ListGridField("state");
		// 处理人
		ListGridField transactorField=new ListGridField("transactor");
		// 检验人 
		ListGridField identifierField=new ListGridField("identifier");
		// 检验日期
		ListGridField surveyDateField=new ListGridField("surveyDate");
		surveyDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		nonconformingReportNumberField.setCanFilter(true);
		checkAndAcceptSheetNumberField.setCanFilter(true);
		identifierField.setCanFilter(true);
		surveyDateField.setCanFilter(true);
		contratNumberField.setCanFilter(true);
		supplyUnitField.setCanFilter(true);
		partNumberField.setCanFilter(true);
		partSerialNumberField.setCanFilter(true);
		transactorField.setCanFilter(true);
		treatmentDateField.setCanFilter(true);
		stateField.setCanFilter(true);
		
		setFields(nonconformingReportNumberField
				,checkAndAcceptSheetNumberField	
				,identifierField
				,surveyDateField
				,contratNumberField
				,supplyUnitField
				,partNumberField
				,partSerialNumberField
				,transactorField										
				,treatmentDateField
				,stateField
				);
		setCellHeight(22);
	}
}
