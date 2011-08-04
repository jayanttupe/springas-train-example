package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class BusinessScopeAccountBookItemsListgrid extends ListGrid {

	private Logger log = Logger
			.getLogger("BusinessScopeAccountBookItemsListgrid");

	public void drawBusinessScopeAccountBookItemsListgrid() {
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setCanEdit(false);
		setShowRowNumbers(true);

		// 商品编码
		ListGridField hsCodeFiled = new ListGridField("hsCode");
		// 中文关键字
		ListGridField keywordFiled = new ListGridField("keyword");
		// 备案计量单位
		ListGridField registerUnitMeasureCodeFiled = new ListGridField(
				"registerUnitMeasureCode");
		// 法定计量单位
		ListGridField legalUnitMeasureCodeFiled = new ListGridField(
				"legalUnitMeasureCode");
		// 备案日期
		ListGridField registerDateFiled = new ListGridField("registerDate");
		registerDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 描述
		ListGridField describeFiled = new ListGridField("describe");

		setFields(hsCodeFiled, keywordFiled, registerUnitMeasureCodeFiled,
				legalUnitMeasureCodeFiled, registerDateFiled, describeFiled);
		setCellHeight(22);
	}
}