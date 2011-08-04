package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CargoItemListgrid extends ListGrid {

	public CargoItemListgrid(){
		
	}
	public void drawCargoItemListgrid(){
		setShowAllRecords(true);
		setCanEdit(false);
		setCellHeight(22);

		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		// 货位编号 
		ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpace.cargoSpaceNumber");
		fieldList.add(cargoSpaceNumberFiled);
		//货位尺寸
		ListGridField storageSizeTypeFiled = new ListGridField("cargoSpace.storageSizeType");
		fieldList.add(storageSizeTypeFiled);
		//承重
		ListGridField storageWeightFiled = new ListGridField("cargoSpace.storageWeight");
		fieldList.add(storageWeightFiled);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
        setFields(fields);
		
	}

}
