package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.spareBoxBusiness;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SpareBoxBusinessListgrid extends ListGrid {

	private Logger log = Logger.getLogger("SpareBoxBusinessListgri");

	public void drawSpareBoxBusinessListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(true);

		// 库房编号
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber","库房编号");
		// 航材包件号
		ListGridField spareBoxPartNumberField = new ListGridField("spareBoxPartNumber","航材包件号");
		// 航材包序号
		ListGridField  spareBoxSerialNumberField= new ListGridField("spareBoxSerialNumber","航材包序号");
		// 描述
		ListGridField  remarkField= new ListGridField("remark","描述");
		// 数量
		ListGridField  quantityField= new ListGridField("quantity","数量");
		// 单位
		ListGridField  unitField= new ListGridField("unit","单位");
		// 货位编号
		ListGridField  cargoSpaceNumberField= new ListGridField("cargoSpaceNumber","货位编号");
		// 打包日期
		ListGridField createDateField= new ListGridField("createDate","打包日期");
		createDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 打包人
		ListGridField  createByField= new ListGridField("createBy","打包人");
		

		stockRoomNumberField.setCanFilter(true);
		spareBoxPartNumberField.setCanFilter(true);
		spareBoxSerialNumberField.setCanFilter(true);
		remarkField.setCanFilter(true);
		quantityField.setCanFilter(true);
		unitField.setCanFilter(true);
		cargoSpaceNumberField.setCanFilter(true);
		createDateField.setCanFilter(true);
		createByField.setCanFilter(true);

		setFields(stockRoomNumberField
				,spareBoxPartNumberField
				,spareBoxSerialNumberField
				,remarkField
				,quantityField
				,unitField
				,cargoSpaceNumberField
				,createDateField
				,createByField
				);

		setCellHeight(22);
	}
}