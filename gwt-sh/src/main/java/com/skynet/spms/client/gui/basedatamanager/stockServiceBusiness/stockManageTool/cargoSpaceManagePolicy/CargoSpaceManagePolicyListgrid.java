package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CargoSpaceManagePolicyListgrid extends ListGrid {

	private Logger log = Logger.getLogger("CargoSpaceManagePolicyListgrid");

	public void drawCargoSpaceManagePolicyListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
		
		// 货位策略编号
		ListGridField policyNumberFiled = new ListGridField("policyNumber");
		// 货位编号
		ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber");
		// 货位策略描述
		ListGridField remarkFiled = new ListGridField("remark");
		// 适用件号
		ListGridField storablePartNumberFiled = new ListGridField("storablePartNumber");
		// 状态
		ListGridField statusFiled = new ListGridField("status");
		
		policyNumberFiled.setCanFilter(true);
		cargoSpaceNumberFiled.setCanFilter(true);
		remarkFiled.setCanFilter(true);
		storablePartNumberFiled.setCanFilter(true);
		statusFiled.setCanFilter(true);
		

		setFields(policyNumberFiled
				,cargoSpaceNumberFiled
				,remarkFiled
				,storablePartNumberFiled
				,statusFiled
				);
		setCellHeight(22);
	}
}