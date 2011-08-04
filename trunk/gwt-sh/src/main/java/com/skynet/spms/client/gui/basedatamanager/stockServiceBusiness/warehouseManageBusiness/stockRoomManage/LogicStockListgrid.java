package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

import java.util.logging.Logger;

import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class LogicStockListgrid extends ListGrid {

	private Logger log = Logger.getLogger("LogicStockListgrid");

	public void drawLogicStockListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setEditEvent(ListGridEditEvent.CLICK);
		
		// 库房ID
		ListGridField stockRoomIDFiled = new ListGridField("stockRoomID");
		stockRoomIDFiled.setHidden(true);
		// 逻辑库代码
		ListGridField logicStockCodeFiled = new ListGridField("logicStockCode");
		logicStockCodeFiled.setRequired(true);
		logicStockCodeFiled.setValidators(ValidateUtils.logicValidator(logicStockCodeFiled.getValueField()));
		logicStockCodeFiled.setValidateOnChange(true);
		// 逻辑库名称
		ListGridField logicStockNameFiled = new ListGridField("logicStockName");
		// 逻辑库描述
		ListGridField memoFiled = new ListGridField("memo");

		setFields(stockRoomIDFiled
				,logicStockCodeFiled
				,logicStockNameFiled
				,memoFiled
				);
		setCellHeight(22);
	}
}
