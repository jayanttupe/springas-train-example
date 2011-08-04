package com.skynet.spms.client.gui.base;

import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ItemRender implements CellFormatter {

	private String clsName;

	public ItemRender(String clsName) {
		this.clsName = clsName;
	}

	@Override
	public String format(Object value, ListGridRecord record, int rowNum,
			int colNum) {
		return EnumTool.getIn18Value(clsName, value.toString());
	}

}
