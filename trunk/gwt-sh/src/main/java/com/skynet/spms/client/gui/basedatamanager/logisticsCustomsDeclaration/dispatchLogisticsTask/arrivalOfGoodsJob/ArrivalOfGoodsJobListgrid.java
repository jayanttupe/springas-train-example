package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ArrivalOfGoodsJobListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ArrivalOfGoodsJobListgrid");

	public void drawArrivalOfGoodsJobListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 物流任务编号
		ListGridField logisticsTasksNumberFiled = new ListGridField("logisticsTasksNumber");
		// 到达工作编号
		ListGridField arrivalOfGoodsNumberFiled = new ListGridField("arrivalOfGoodsNumber");
		// 到达日期
		ListGridField arrivalOfGoodsDateFiled = new ListGridField("arrivalOfGoodsDate");
		arrivalOfGoodsDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 联系人
		ListGridField contacterFiled=new ListGridField("contacter");
		// 运输班次
		ListGridField numberOfRunsFiled=new ListGridField("numberOfRuns");
		// 到达口岸
		ListGridField portOfDestinatFiled=new ListGridField("portOfDestinat");		
		//备注
		ListGridField memoField = new ListGridField("memo");
		
		logisticsTasksNumberFiled.setCanFilter(true);
		arrivalOfGoodsNumberFiled.setCanFilter(true);
		arrivalOfGoodsDateFiled.setCanFilter(true);
		contacterFiled.setCanFilter(true);
		numberOfRunsFiled.setCanFilter(true);
		portOfDestinatFiled.setCanFilter(true);
		memoField.setCanFilter(true);
		
		
		setFields(logisticsTasksNumberFiled
				,arrivalOfGoodsNumberFiled
				,arrivalOfGoodsDateFiled
				,contacterFiled
				,numberOfRunsFiled
				,portOfDestinatFiled
				,memoField
				);
		setCellHeight(22);
	}

}
