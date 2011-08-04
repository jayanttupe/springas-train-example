package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

import java.util.logging.Logger;

import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.ItemRender;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ShippingJobListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ShippingJobListgrid");

	public void drawShippingJobListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 起运工作编号
		ListGridField shippingJobNumberField = new ListGridField("shippingJobNumber");
		// 物流任务编号
		ListGridField logisticsTasksNumberField = new ListGridField("logisticsTasksNumber");
		// 运代商
		ListGridField carrierNameField = new ListGridField("pickupDeliveryOrder.carrierName");
		// 主运单号
		ListGridField mainWayBillField = new ListGridField("mainWayBill", "主运单号");
		// 分运单号
		ListGridField houseWayBillField = new ListGridField("houseWayBill", "分运单号");
		// 运输方式
		ListGridField transportationCodeField = new ListGridField("pickupDeliveryOrder.transportationCode");
		transportationCodeField.setCellFormatter(new ItemRender(EnumTool.TRANSPORTATIONCODE));
		// 起运口岸
		ListGridField portOfShipmentField = new ListGridField("portOfShipment");
		// 目的口岸
		ListGridField portOfDestinatField = new ListGridField("portOfDestinat");
		// 起运日期
		ListGridField shipmentDateField = new ListGridField("shipmentDate");
		shipmentDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 预计到达日期
		ListGridField estimatedTimeOfArrivalField = new ListGridField("estimatedTimeOfArrival");
		estimatedTimeOfArrivalField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 起运状态
		ListGridField shippingStatusFiled=new ListGridField("shippingStatus");
        
		shippingJobNumberField.setCanFilter(true);
		logisticsTasksNumberField.setCanFilter(true);
		carrierNameField.setCanFilter(true);
		mainWayBillField.setCanFilter(true);
		houseWayBillField.setCanFilter(true);
		transportationCodeField.setCanFilter(true);
		portOfShipmentField.setCanFilter(true);
		portOfDestinatField.setCanFilter(true);
		shipmentDateField.setCanFilter(true);
		estimatedTimeOfArrivalField.setCanFilter(true);
		shippingStatusFiled.setCanFilter(true);
		
		setFields(shippingJobNumberField,
				logisticsTasksNumberField,
				carrierNameField,
				mainWayBillField,
				houseWayBillField,
				transportationCodeField,
				portOfShipmentField,
				portOfDestinatField,
				shipmentDateField,
				estimatedTimeOfArrivalField,
				shippingStatusFiled
				);
		setCellHeight(22);
	}

}
