package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.discardServiceBusiness;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class DiscardServiceBusinessListgrid extends ListGrid {

	private Logger log = Logger.getLogger("DiscardServiceBusinessListgrid");

	public void drawDiscardServiceBusinessListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 申请单号
		ListGridField discardReportNumberFiled = new ListGridField("discardReportNumber");
		// 申请日期
		ListGridField applicationDateFiled = new ListGridField("applicationDate");
		applicationDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		// 单位
		ListGridField unitMeasureCodeFiled = new ListGridField("unitMeasureCode");
		// 残值(单价)
		ListGridField remainderUnitPriceFiled = new ListGridField("remainderUnitPrice");
		// 购入日期
		ListGridField buyDataFiled = new ListGridField("buyData");
		buyDataFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 报废类型
		ListGridField discardTypesFiled = new ListGridField("discardTypes");
		// 处理去向
		ListGridField disposeDescribeFiled = new ListGridField("disposeDescribe");
		// 状态 
		ListGridField stateFiled = new ListGridField("state");
		// 申请人
		ListGridField applicantFiled = new ListGridField("applicant");

		discardReportNumberFiled.setCanFilter(true);
		applicationDateFiled.setCanFilter(true);
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		quantityFiled.setCanFilter(true);
		unitMeasureCodeFiled.setCanFilter(true);
		remainderUnitPriceFiled.setCanFilter(true);
		buyDataFiled.setCanFilter(true);
		discardTypesFiled.setCanFilter(true);
		disposeDescribeFiled.setCanFilter(true);
		stateFiled.setCanFilter(true);
		applicantFiled.setCanFilter(true);
		
		setFields(discardReportNumberFiled
				,applicationDateFiled
				,partNumberFiled
				,partSerialNumberFiled
				,quantityFiled
				,unitMeasureCodeFiled
				,remainderUnitPriceFiled
				,buyDataFiled
				,discardTypesFiled
				,disposeDescribeFiled
				,stateFiled
				,applicantFiled
				);
		setCellHeight(22);
	}
}