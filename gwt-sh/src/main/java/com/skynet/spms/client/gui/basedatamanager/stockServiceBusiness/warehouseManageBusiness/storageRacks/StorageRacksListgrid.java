package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.storageRacks;

import java.util.logging.Logger;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StorageRacksListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StorageRacksListgrid");
	DataSource userDataSource;

	public DataSource getUserDataSource() {
		return userDataSource;
	}

	public StorageRacksListgrid() {

	}
	
	public void drawStorageRacksListgrid(){
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCellHeight(22);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 库房名称
		ListGridField stockRoomChineseNameField = new ListGridField("stockRoomChineseName");
		// 货架编号
		ListGridField storageRacksCodeField = new ListGridField("storageRacksCode");
		// 货架形式
		ListGridField storageRacksTypeCodeField = new ListGridField("storageRacksTypeCode");
		// 货架区域号
		ListGridField storageRacksAreaCodeField = new ListGridField("storageRacksAreaCode");
		// 货架号
		ListGridField storageRacksNumberField = new ListGridField("storageRacksNumber");

		stockRoomChineseNameField.setCanFilter(true);
		storageRacksCodeField.setCanFilter(true);
		storageRacksTypeCodeField.setCanFilter(true);
		storageRacksAreaCodeField.setCanFilter(true);
		storageRacksNumberField.setCanFilter(true);
		
		setFields(stockRoomChineseNameField
				,storageRacksCodeField
				,storageRacksTypeCodeField
				,storageRacksAreaCodeField
				,storageRacksNumberField
				);
	}
}
