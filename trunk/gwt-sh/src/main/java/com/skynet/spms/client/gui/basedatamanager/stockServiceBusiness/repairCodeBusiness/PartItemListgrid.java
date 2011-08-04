package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PartItemListgrid extends ListGrid {

	public PartItemListgrid(){
		
	}
	public void drawPartItemListgrid(){
		setShowAllRecords(true);
		setCanEdit(false);
		setCellHeight(22);

		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		// 件号
		ListGridField partNumberFiled = new ListGridField("partsInventoryRecord.partNumber");
		fieldList.add(partNumberFiled);
		// 序列号/批号
		ListGridField repairCodeTypeFiled = new ListGridField("partsInventoryRecord.partSerialNumber");
		fieldList.add(repairCodeTypeFiled);
		//货位编号
		ListGridField cargoSpaceNumberFiled = new ListGridField("partsInventoryRecord.cargoSpaceNumber");
		fieldList.add(cargoSpaceNumberFiled);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
        setFields(fields);
		
	}

}
