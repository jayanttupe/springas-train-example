package com.skynet.spms.client.gui.customerplatform.buybackSheetService.ui;

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
		ListGridField field2 = new ListGridField("requisitionSheetNumber"/*,
				"申请单编号"*/);
		ListGridField field3 = new ListGridField(
				"m_CustomerIdentificationCode.code"/*, "客户名称"*/);
		ListGridField field4 = new ListGridField("createDate"/*, "申请日期"*/);
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		
		field4.setDateFormatter(DateDisplayFormat.TOSERIALIZEABLEDATE);
		ListGridField field5 = new ListGridField("linkman"/*, "联系人"*/);
		ListGridField field6 = new ListGridField("contactWay"/*, "联系方式"*/);
		ListGridField field7 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus", ui.getB()
						.listTitlePublishStatus());
		
		ListGridField field9 = new ListGridField("m_BussinessStatusEntity.status", ui.getB()
		.listTitleBusStatus());
		
		ListGridField field8 = new ListGridField("contractBasisDesc"/*, "合同依据描述"*/);
		setFields( field2, field3, field4, field5, field6, field7,field9,
				field8);
	}
}
