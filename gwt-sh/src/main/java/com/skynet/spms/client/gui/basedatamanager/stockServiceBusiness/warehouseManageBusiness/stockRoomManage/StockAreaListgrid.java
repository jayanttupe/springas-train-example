package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

import java.util.logging.Logger;

import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StockAreaListgrid extends ListGrid {

	private Logger log = Logger.getLogger("StockAreaListgrid");

	public void drawStockAreaListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setEditEvent(ListGridEditEvent.CLICK);

		// 区域代码
		ListGridField areaCodeFiled = new ListGridField("areaCode");
		areaCodeFiled.setRequired(true);
		areaCodeFiled.setValidators(ValidateUtils.capitalValidator(areaCodeFiled.getValueField()));
		areaCodeFiled.setValidateOnChange(true);
		// 区域名称
		ListGridField areaNameFiled = new ListGridField("areaName");
		// 区域描述
		ListGridField areaDescriptionFiled = new ListGridField("areaDescription");

		setFields(areaCodeFiled
				,areaNameFiled
				,areaDescriptionFiled
				);
		setCellHeight(22);
	}
}
