package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.allocationBillBusiness;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AllocationGoodListgrid extends ListGrid {

	private Logger log = Logger.getLogger("AllocationGoodListgrid");

	public void drawAllocationGoodListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SIMPLE);
		setCanEdit(false);

		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		// 关键字
		ListGridField keyWordFiled = new ListGridField("keyWord");
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		// 单位
		ListGridField unitFiled = new ListGridField("unit");
		// 备件状态
		ListGridField stateFiled = new ListGridField("state");
		// 单价
		ListGridField unitPriceFiled = new ListGridField("unitPrice");
		// 计划调入日期
		ListGridField planDateFiled = new ListGridField("planDate");
		planDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 收货库房
		ListGridField inWareHouseFiled = new ListGridField("inWareHouse");
		// 发货库房
		ListGridField outWareHouseFiled = new ListGridField("outWareHouse");
		// 是否时寿 
		ListGridField isLifeLimitFiled = new ListGridField("isLifeLimit");

		setFields(partNumberFiled
				,keyWordFiled
				,partSerialNumberFiled
				,quantityFiled
				,unitFiled
				,stateFiled
				,unitPriceFiled
				,planDateFiled
				,inWareHouseFiled
				,outWareHouseFiled
				,isLifeLimitFiled
				);
		setCellHeight(22);
	}
}