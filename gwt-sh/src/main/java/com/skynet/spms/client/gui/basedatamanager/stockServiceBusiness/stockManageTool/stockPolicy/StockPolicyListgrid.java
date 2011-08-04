package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockPolicy;

import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockPolicyListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockPolicyListgrid");
	private DataInfo dataInfo;
	
	public DataInfo getDataInfo() {
		return dataInfo;
	}

	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}
	public void drawStockPolicyListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 库存策略编号
		ListGridField stockPolicyNumberFiled = new ListGridField("stockPolicyNumber");
		// 库存策略名称
		ListGridField stockRoomNumberFiled = new ListGridField("stockPolicyName");
		// 业务类型
		ListGridField businessTypeFiled = new ListGridField("businessType");
		// 备件类别
		ListGridField backTypeFiled = new ListGridField("backType");	
		// 起始货位编号
		ListGridField startCargoSpaceNumberFiled = new ListGridField("stockRoomNumber");
		// 结束货位编号
		ListGridField endCargoSpaceNumberFiled = new ListGridField("stockAreaCode");
		// 备件中心位置代码
		ListGridField partCenterCodeFiled = new ListGridField("enableDate");

		stockPolicyNumberFiled.setCanFilter(true);
		stockRoomNumberFiled.setCanFilter(true);
		businessTypeFiled.setCanFilter(true);
		backTypeFiled.setCanFilter(true);
		startCargoSpaceNumberFiled.setCanFilter(true);
		endCargoSpaceNumberFiled.setCanFilter(true);
		partCenterCodeFiled.setCanFilter(true);
		
		setFields(stockPolicyNumberFiled
				,stockRoomNumberFiled
				,businessTypeFiled
				,backTypeFiled
				,startCargoSpaceNumberFiled
				,endCargoSpaceNumberFiled
				,partCenterCodeFiled
				);
		setCellHeight(22);
	}
}