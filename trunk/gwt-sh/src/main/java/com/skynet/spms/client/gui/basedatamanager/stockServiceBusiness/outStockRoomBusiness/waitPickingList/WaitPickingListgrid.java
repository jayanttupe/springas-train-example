/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.waitPickingList;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class WaitPickingListgrid extends ListGrid {

	private Logger log = Logger.getLogger("WaitPickingListgrid");

	public void drawWaitPickingListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 指令单编号
		ListGridField orderNumberField = new ListGridField("orderNumber");
		// 业务类型
		ListGridField businessTypeField = new ListGridField("businessType");
		// 合同编号
		ListGridField contractNumberField = new ListGridField("contractNumber");
		// 收货单位
		ListGridField deliveryField = new ListGridField("companyOfConsignee");
		// 优先级
		ListGridField keywordField = new ListGridField("priority");
		// 交货日期
		ListGridField deliveryDateField = new ListGridField("deliveryDate");
		deliveryDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		orderNumberField.setCanFilter(true);
		businessTypeField.setCanFilter(true);
		contractNumberField.setCanFilter(true);
		deliveryField.setCanFilter(true);
		keywordField.setCanFilter(true);
		deliveryDateField.setCanFilter(true);

		setFields(orderNumberField
				,businessTypeField
				,contractNumberField
				,deliveryField
				,keywordField
				,deliveryDateField
				);
		
		setCellHeight(22);
	}

}
