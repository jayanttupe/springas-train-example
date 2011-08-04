package com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 回购申请单表格
 * 
 * @author fl
 * 
 */
public class BuyBackSheetGrid extends BaseListGrid {
	public I18n ui = new I18n();

	public BuyBackSheetGrid() {
		drawGrid();
	}

	public void drawGrid() {
		ListGridField field1 = new ListGridField("message"/*, "留言" */);  
		Utils.setMessageFieldWidth(field1);
		ListGridField field2 = new ListGridField("requisitionSheetNumber"/*,
				"申请单编号"*/);
		field2.setCanFilter(true);
		
		ListGridField field3 = new ListGridField(
				"m_CustomerIdentificationCode.code"/*, "客户名称"*/);
		field3.setCanFilter(true);
		
		ListGridField field4 = new ListGridField("createDate"/*, "申请日期"*/);
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field4.setCanFilter(true);
		
		ListGridField field5 = new ListGridField("linkman"/*, "联系人"*/);
		field5.setCanFilter(true);
		
		ListGridField field6 = new ListGridField("contactWay"/*, "联系方式"*/);
		field6.setCanFilter(true);
		
		ListGridField field7 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus");
		field7.setCanFilter(true);
		
		ListGridField field9 = new ListGridField("m_BussinessStatusEntity.status", ui.getB()
				.listTitleBusStatus());
		
		ListGridField field8 = new ListGridField("contractBasisDesc"/*, "合同依据描述"*/);
		field8.setCanFilter(true);
		
		setFields(field1, field2, field3, field4, field5, field6, field7,field9,
				field8);
	}
}
