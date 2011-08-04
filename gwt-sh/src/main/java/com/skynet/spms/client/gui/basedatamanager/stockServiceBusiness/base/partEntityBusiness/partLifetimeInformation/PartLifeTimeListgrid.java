package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.base.partEntityBusiness.partLifetimeInformation;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PartLifeTimeListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PartLifetimeInformationListgrid");

	public void drawPartLifetimeInformationListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 件号
		ListGridField partNumberFiled = new ListGridField("partNumber");
		partNumberFiled.setWidth(105);
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
		partSerialNumberFiled.setWidth(100);
		// 制造商代码
		ListGridField manufacturerCodeField = new ListGridField("manufacturerCode");
		// 出厂日期
		ListGridField buildDateField = new ListGridField("buildDate");
		buildDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 件描述
		ListGridField keywordField = new ListGridField("partName", "件描述");
		// 库存结存数量
		ListGridField balanceQuantityFiled = new ListGridField("balanceQuantity");
		// 单位
		ListGridField unitFiled = new ListGridField("unit");
		// 库房编号
		ListGridField stockRoomNumberFiled = new ListGridField("stockRoomNumber");
		// 货位编号
		ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber");
		cargoSpaceNumberFiled.setWidth(105);
		// 备件状况
		ListGridField partStatusFiled = new ListGridField("partStatus");
		// 时寿件状态
		ListGridField lifePartStatusFiled = new ListGridField("lifePartStatus", "时寿件状态");
	
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		manufacturerCodeField.setCanFilter(true); 
		keywordField.setCanFilter(true); 
		balanceQuantityFiled.setCanFilter(true);  
		unitFiled.setCanFilter(true);
		stockRoomNumberFiled.setCanFilter(true); 
		cargoSpaceNumberFiled.setCanFilter(true);  
		partStatusFiled.setCanFilter(true); 
		buildDateField.setCanFilter(true);
		lifePartStatusFiled.setCanFilter(true);

		setFields(partNumberFiled 
				,partSerialNumberFiled
				,manufacturerCodeField
				,buildDateField
				,keywordField
				,balanceQuantityFiled 
				,unitFiled
				,stockRoomNumberFiled
				,cargoSpaceNumberFiled 
				,partStatusFiled
				,lifePartStatusFiled
				);
		setCellHeight(22);
	}
}