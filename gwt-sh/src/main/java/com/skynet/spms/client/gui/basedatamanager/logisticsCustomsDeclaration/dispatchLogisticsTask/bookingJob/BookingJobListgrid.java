package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class BookingJobListgrid extends ListGrid {

	private Logger log = Logger.getLogger("BookingJobListgrid");

	public void drawBookingJobListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 订舱工作编号
		ListGridField bookingJobNumberField = new ListGridField("bookingJobNumber");
		// 物流任务编号
		ListGridField logisticsTasksNumberField = new ListGridField("logisticsTasksNumber");
		// 运代商
		ListGridField carrierNameField = new ListGridField("pickupDeliveryOrder.carrierName");
		// 交货时间
		ListGridField deliveryDateField = new ListGridField("pickupDeliveryOrder.deliveryDate", "交货日期");
		deliveryDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 起动时间
		ListGridField shippedDateField = new ListGridField("pickupDeliveryOrder.shippedDate", "起运日期");
		shippedDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 起动时间
		ListGridField arrivalDateField = new ListGridField("pickupDeliveryOrder.arrivalDate", "到货日期");
		shippedDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 交货时间
		ListGridField pickupDateField = new ListGridField("pickupDeliveryOrder.pickupDate", "提货日期");
		deliveryDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 起运口岸（港）
		ListGridField portOfShipmentField = new ListGridField("portOfShipment");
		// 目的口岸（港）
		ListGridField portOfDestinatField = new ListGridField("portOfDestinat");
		// 操作人员
		ListGridField createByField = new ListGridField("createBy");
		// 修改时间
		ListGridField createDateField = new ListGridField("createDate");
		// 订舱状态
		ListGridField bookingMemoStatusField = new ListGridField("bookingStatus");
		
		bookingJobNumberField.setCanFilter(true);
		logisticsTasksNumberField.setCanFilter(true);
		carrierNameField.setCanFilter(true);
		deliveryDateField.setCanFilter(true);
		shippedDateField.setCanFilter(true);
		arrivalDateField.setCanFilter(true);
		pickupDateField.setCanFilter(true);
		portOfShipmentField.setCanFilter(true);
		portOfDestinatField.setCanFilter(true);
		createByField.setCanFilter(true);
		createDateField.setCanFilter(true);
		bookingMemoStatusField.setCanFilter(true);

		setFields(bookingJobNumberField
				,logisticsTasksNumberField
				,carrierNameField
				,deliveryDateField
				,shippedDateField
				,arrivalDateField
				,pickupDateField
				,portOfShipmentField
				,portOfDestinatField
				,createByField
				,createDateField
				,bookingMemoStatusField
				);
		
		setCellHeight(22);
	}

}
