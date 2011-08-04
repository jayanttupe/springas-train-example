package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.deliveryOrder;
//指令信息列表
import java.util.logging.Logger;

import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.ItemRender;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class DeliveryOrderListgrid extends ListGrid {

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
		// 发货日期
		ListGridField deliveryDateField = new ListGridField("deliveryDate", "发货日期");
		deliveryDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 起运日期
		ListGridField shippedDateField = new ListGridField("shippedDate");
		shippedDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 业务人员
		ListGridField createByField = new ListGridField("createBy");
		// 业务状态
		ListGridField statusField = new ListGridField("statusName", "业务状态");

		orderNumberField.setCanFilter(true);
		descriptionField.setCanFilter(true);
		priorityField.setCanFilter(true);
		contractNumberField.setCanFilter(true);
		deliveryDateField.setCanFilter(true);
		shippedDateField.setCanFilter(true);
		createByField.setCanFilter(true);
		statusField.setCanFilter(true);
		
		setFields(orderNumberField
				,descriptionField
				,priorityField
				,contractNumberField
				,deliveryDateField
				,shippedDateField
				,createByField
				,statusField
				);
		
		setCellHeight(22);
	}
}
