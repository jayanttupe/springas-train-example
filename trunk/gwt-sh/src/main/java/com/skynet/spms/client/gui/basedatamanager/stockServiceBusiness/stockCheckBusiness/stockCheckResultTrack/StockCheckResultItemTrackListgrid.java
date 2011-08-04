package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheckResultTrack;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockCheckResultItemTrackListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockCheckResultItemTrackListgrid");

	public  ListGridField stockFiled;

	public void drawStockCheckResultItemManageListgrid() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		// 盘点编号 
		stockFiled = new ListGridField("checkNumber");
		stockFiled.setHidden(true);
		stockFiled.setCanEdit(false);
		// 项号
		ListGridField itemNoFiled = new ListGridField("itemnumber");
		itemNoFiled.setCanEdit(false);
		// 货位编号
		ListGridField freightLotNumberFiled = new ListGridField("freightLotNumber");
		itemNoFiled.setCanEdit(false);
		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		partNumberFiled.setCanEdit(false);
		// 序号/批次号
		ListGridField partSerialFiled = new ListGridField("partSerialNumber");
		partSerialFiled.setCanEdit(false);
		// 关键字
		ListGridField keywordFiled = new ListGridField("keyword");
		keywordFiled.setCanEdit(false);
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		quantityFiled.setCanEdit(false);
		// 单位
		ListGridField UnitOfMeasureFiled = new ListGridField("UnitOfMeasure");
		UnitOfMeasureFiled.setCanEdit(false);
		// 盘存数量
		ListGridField checkQuantityFiled = new ListGridField("checkQuantity");
		checkQuantityFiled.setCanEdit(true);
		// 盘存单位
		ListGridField checkUnitsFiled = new ListGridField("checkUnits");
		checkUnitsFiled.setCanEdit(true);
		// 盘点结果
		ListGridField checkResultFiled = new ListGridField("checkResult");
		checkResultFiled.setCanEdit(false);
		// 盘点日期
		ListGridField checkDateField = new ListGridField("checkDate");
		checkDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		checkDateField.setCanEdit(false);
		// 盘点人
		ListGridField checkManField = new ListGridField("checkMan");
		checkManField.setCanEdit(false);
		
		setFields(stockFiled
				, itemNoFiled
				, freightLotNumberFiled
				, partNumberFiled,partSerialFiled
				, keywordFiled
				, quantityFiled
				, UnitOfMeasureFiled
				, checkQuantityFiled
				, checkUnitsFiled
				, checkResultFiled
				, checkDateField
				, checkManField);
		setCellHeight(22);
	}
}
