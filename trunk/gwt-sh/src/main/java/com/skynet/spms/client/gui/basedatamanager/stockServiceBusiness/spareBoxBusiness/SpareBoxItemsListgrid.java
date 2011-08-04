package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.spareBoxBusiness;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SpareBoxItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PackingListPartItemsListgrid");

	public void drawSpareBoxItemsListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);

		// 件号
		ListGridField partNumberField = new ListGridField("partNumber","件号");
		// 序号，批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber","序号，批号");
		// 关键字
		ListGridField keywordField = new ListGridField("keyword","关键字");
		// 制造商
		ListGridField manufacturerField = new ListGridField("manufacturer","制造商");
		// 数量
		ListGridField quantityField = new ListGridField("quantity","数量");
		// 单位
		ListGridField unitField = new ListGridField("unit","单位");
		// 备件状况
		ListGridField partStatusField = new ListGridField("partStatus","备件状况");
		// 库房编号
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber","库房编号");
		// 货位编号
		ListGridField cargoSpaceNumberField = new ListGridField("cargoSpaceNumber","货位编号");
		// 剩余寿命%
		ListGridField remainLifeField = new ListGridField("remainLife","剩余寿命%");
		// 状态
		ListGridField stateField = new ListGridField("state","状态");
		// 备注
		ListGridField remarkField = new ListGridField("remark","备注");
		
		
		setFields(partNumberField
				,partSerialNumberField
				,keywordField
				,manufacturerField
				,quantityField
				,unitField
				,partStatusField
				,stockRoomNumberField
				,cargoSpaceNumberField
				,remainLifeField
				,stateField
				,remarkField	
				);
		
		setCellHeight(22);
	}
}
