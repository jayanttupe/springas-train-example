package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder;
//指令信息列表
import java.util.logging.Logger;

import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.ItemRender;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PickupOrderListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PickupDeliveryOrderListgrid");

	public void drawPickupDeliveryOrderListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 指令编号
		ListGridField orderNumberField = new ListGridField("orderNumber");
		// 描述
		ListGridField descriptionField = new ListGridField("description");
		// 优先级
		ListGridField priorityField = new ListGridField("priority");
		priorityField.setCellFormatter(new ItemRender(EnumTool.PRIORITY));
		// 合同编号
		ListGridField contractNumberField = new ListGridField("contractNumber");
		// 交货日期
		ListGridField arrivalDateField = new ListGridField("arrivalDate", "到货日期");
		arrivalDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 起运日期
		ListGridField pickupDateField = new ListGridField("pickupDate", "提货日期");
		pickupDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 业务人员
		ListGridField createByField = new ListGridField("createBy");
		// 业务状态
		ListGridField statusField = new ListGridField("statusName", "业务状态");

		orderNumberField.setCanFilter(true);
		descriptionField.setCanFilter(true);
		priorityField.setCanFilter(true);
		contractNumberField.setCanFilter(true);
		arrivalDateField.setCanFilter(true);
		pickupDateField.setCanFilter(true);
		createByField.setCanFilter(true);
		statusField.setCanFilter(true);
		
		setFields(orderNumberField
				,descriptionField
				,priorityField
				,contractNumberField
				,arrivalDateField
				,pickupDateField
				,createByField
				,statusField
				);
		
		setCellHeight(22);
	}
}
