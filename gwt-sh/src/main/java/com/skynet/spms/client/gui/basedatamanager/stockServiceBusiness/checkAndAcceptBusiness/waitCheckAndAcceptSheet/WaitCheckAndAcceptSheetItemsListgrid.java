/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class WaitCheckAndAcceptSheetItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("WaitCheckAndAcceptSheetItemsListgrid");

	public void drawWaitCheckAndAcceptSheetItemsListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SIMPLE);
		setCanEdit(false);

		// 项号 
		ListGridField numberFiled = new ListGridField("receivingSheetItemNumber");
		// 件号
		ListGridField piecesFiled = new ListGridField("partNumber");
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		// 序号/批号
		ListGridField partSerialFiled = new ListGridField("partSerialNumber");
		// 生产日期
		ListGridField dateFiled = new ListGridField("productiondate");
		dateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 备件类型
		ListGridField typeFiled = new ListGridField("partType");
		// 箱号
		ListGridField packingFiled = new ListGridField("packingNumber");
		
		setFields(numberFiled
				,piecesFiled
				,quantityFiled
				,partSerialFiled
				,typeFiled
				,dateFiled
				,packingFiled);
		setCellHeight(22);
	}
}
