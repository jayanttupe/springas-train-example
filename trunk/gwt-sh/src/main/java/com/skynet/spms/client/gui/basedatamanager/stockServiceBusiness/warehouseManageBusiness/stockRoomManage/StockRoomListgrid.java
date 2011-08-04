package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockRoomListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockRoomListgrid");
	private DataInfo dataInfo;
	
	public DataInfo getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}
	
	public void drawStockRoomListgrid()
	{
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 备件中心位置
		ListGridField partCentreLocationFiled = new ListGridField("partCentreLocation");
		// 库房编号
		ListGridField numberFiled = new ListGridField("stockRoomNumber");
		// 中文名称
		ListGridField stockRoomChineseNameFiled = new ListGridField("stockRoomChineseName");
		// 库房类型
		ListGridField stockRoomTypeFiled = new ListGridField("stockRoomType");
		// 库房地址
		ListGridField addressFiled = new ListGridField("address");
		// 电话 
		ListGridField teleFiled = new ListGridField("telephone");
		// 备注
		ListGridField remarkFiled = new ListGridField("remark");
		
		partCentreLocationFiled.setCanFilter(true);
		numberFiled.setCanFilter(true);
		stockRoomChineseNameFiled.setCanFilter(true);
		stockRoomTypeFiled.setCanFilter(true);
		addressFiled.setCanFilter(true);
		teleFiled.setCanFilter(true);;
		remarkFiled.setCanFilter(true);

		setFields(numberFiled
				,stockRoomChineseNameFiled
				,stockRoomTypeFiled
				,partCentreLocationFiled
				,addressFiled
				,teleFiled
				,remarkFiled
				);
		setCellHeight(22);
	}
}
