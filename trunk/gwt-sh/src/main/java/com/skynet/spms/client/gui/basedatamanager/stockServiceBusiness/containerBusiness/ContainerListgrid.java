package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.containerBusiness;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ContainerListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ContainerListgrid");

	public void drawContainerEntityListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 容器编号 
		ListGridField containerNumberFiled = new ListGridField("containerNumber");
		// 所属库房
		ListGridField stockRoomIDFiled = new ListGridField("stockRoomID");
		stockRoomIDFiled.setHidden(true);
		// 所属库房名称
		ListGridField stockRoomNameFiled = new ListGridField("stockRoomName");
		// 容器类型
		ListGridField containerTypeFiled = new ListGridField("containerType");
		// 材质
		ListGridField containerMaterialFiled=new ListGridField("containerMaterial");
		// 备注
		ListGridField remarkField = new ListGridField("remark");
		
		containerNumberFiled.setCanFilter(true);
		stockRoomIDFiled.setCanFilter(true);
		stockRoomNameFiled.setCanFilter(true);
		containerTypeFiled.setCanFilter(true);
		containerMaterialFiled.setCanFilter(true);
		remarkField.setCanFilter(true);
		
		
		setFields(containerNumberFiled
				,stockRoomIDFiled
				,stockRoomNameFiled
				,containerTypeFiled
				,containerMaterialFiled
				,remarkField
				);
		setCellHeight(22);
	}

}
