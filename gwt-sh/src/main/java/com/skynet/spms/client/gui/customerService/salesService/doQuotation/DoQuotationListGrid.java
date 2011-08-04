package com.skynet.spms.client.gui.customerService.salesService.doQuotation;

import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.skynet.spms.client.gui.base.Utils;
public class DoQuotationListGrid extends FilterListGrid {

	public DoQuotationListGrid() {

	}

	public void drawGrid() {
		this.setShowRowNumbers(false);
		//留言
		ListGridField field1 = new ListGridField("field1");
		Utils.setMessageFieldWidth(field1);
		field1.setHidden(true);
		//询价单编号
		ListGridField field2 = new ListGridField("inquirySheetNumber");
		field2.setCanFilter(true);
		
		//客户名称
		ListGridField field3 = new ListGridField(
				"m_CustomerIdentificationCode.code");
		field3.setCanFilter(true);

		//联系人
		ListGridField field4 = new ListGridField("linkman");
		field4.setCanFilter(true);

		//联系方式"
		ListGridField field6 = new ListGridField("contactInformation");
		field6.setCanFilter(true);

		//询价日期
		ListGridField field7 = new ListGridField("createDate");
		field7.setCanFilter(true);
		field7.setType(ListGridFieldType.DATE);
		field7.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		//是否报价
		ListGridField field8 = new ListGridField(
				"m_QuotationStatusEntity.m_QuotationStatus");
		field8.setCanFilter(true);
		
		//发布状态
		ListGridField field10 = new ListGridField(
		"m_BussinessPublishStatusEntity.m_PublishStatus");
		field10.setCanFilter(true);
	
		//发布状态
		ListGridField field11 = new ListGridField(
		"m_BussinessStatusEntity.status");
		field10.setCanFilter(true);	

		//备注
		ListGridField field9 = new ListGridField("remark");
		field9.setCanFilter(true);

		this.setFields(field1, field2, field3, field4, field6, field7, field8,field10,field11,
				field9);
		

	}

}
