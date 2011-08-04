package com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SalesRequisitionSheetListGrid extends FilterListGrid{

	private DataSource quotationDs;

	public DataSource getSalesInquiryDs() {
		return quotationDs;
	}

	public void setSalesInquiryDs(DataSource quotationDs) {
		this.quotationDs = quotationDs;
	}

	public SalesRequisitionSheetListGrid() {
           
	}

	public void drawGrid() {
		this.setShowRowNumbers(false);
		//留言
		ListGridField field1 = new ListGridField("field1");
		field1.setHidden(true);
		Utils.setMessageFieldWidth(field1);
		
		//订单编号
		ListGridField field2 = new ListGridField("requisitionSheetNumber");

		//优先级
		ListGridField field3 = new ListGridField("m_Priority");

		//客户名称
		ListGridField field4 = new ListGridField("m_CustomerIdentificationCode.code");

		//总金额
		ListGridField field6 = new ListGridField("totalAmount");

		//申请时间
		ListGridField field7 = new ListGridField("createDate");
		field7.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		//客户订单号
		ListGridField field8 = new ListGridField("customerPurchasingOrderNumber");

		//发布状态
		ListGridField field9 = new ListGridField("m_BussinessPublishStatusEntity.m_PublishStatus");
		
		//业务状态
		ListGridField field10 = new ListGridField("m_BussinessStatusEntity.status");
		
		field1.setCanFilter(true);
		field2.setCanFilter(true);
		field3.setCanFilter(true);
		field4.setCanFilter(true);
		field6.setCanFilter(true);
		field7.setCanFilter(true);
		field8.setCanFilter(true);
		field9.setCanFilter(true);
		field10.setCanFilter(true);

		this.setFields(field1, field2, field3, field4,  field6, field7,
				field8, field9,field10);

	}

}
