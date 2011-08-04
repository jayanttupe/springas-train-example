package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class RepairCodeListgrid extends ListGrid {

	private DataInfo dataInfo;
	private boolean passByNew;
	
	public boolean isPassByNew() {
		return passByNew;
	}
	public void setPassByNew(boolean passByNew) {
		this.passByNew = passByNew;
	}
	public DataInfo getDataInfo() {
		return dataInfo;
	}
	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}
	public RepairCodeListgrid(){
		
	}
	public void drawRepairCodeListgrid(){
		setShowAllRecords(false);
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
		setCellHeight(22);

		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		// 任务编号
		ListGridField taskNumberFiled = new ListGridField("taskNumber");
		fieldList.add(taskNumberFiled);
		// 补码类型 
		ListGridField repairCodeTypeFiled = new ListGridField("repairCodeType");
		fieldList.add(repairCodeTypeFiled);
		// 补码原因
		ListGridField repairCodeReasonFiled = new ListGridField("repairCodeReason");
		fieldList.add(repairCodeReasonFiled);
		// 补码种类
		ListGridField repairTypeFiled=new ListGridField("repairType");
		fieldList.add(repairTypeFiled);
		//任务是否下达
		ListGridField sendStatusFiled=new ListGridField("sendStatus");
		fieldList.add(sendStatusFiled);
		// 备注
		ListGridField remarkField = new ListGridField("remark");
		fieldList.add(remarkField);
		
		taskNumberFiled.setCanFilter(true);
		repairCodeTypeFiled.setCanFilter(true);
		repairCodeReasonFiled.setCanFilter(true);
		repairTypeFiled.setCanFilter(true);
		sendStatusFiled.setCanFilter(true);
		remarkField.setCanFilter(true);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
        setFields(fields);
		
	}

}
