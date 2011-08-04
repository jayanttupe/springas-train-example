package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 回购合同表格
 * 
 * @author fl
 * 
 */
public class BuyBackPactGrid extends BaseListGrid {
	public BuyBackPactGrid() {
		drawGrid();
	}

	public void drawGrid() {
		ListGridField field1 = new ListGridField("message"/*, "留言" */);  
		Utils.setMessageFieldWidth(field1);
		// 启动可渲染
		ListGridField field2 = new ListGridField("contractNumber"/* , "合同编号" */);
		field2.setCanFilter(true);
		ListGridField field3 = new ListGridField(
				"customerIdentificationCode.code"/* , "客户名称" */);
		field3.setCanFilter(true);
		ListGridField field4 = new ListGridField("createDate"/* , "创建日期" */);
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		field4.setCanFilter(true);
		ListGridField field5 = new ListGridField("extendedValueTotalAmount"/*
																			 * ,
																			 * "合同总金额"
																			 */);
		field5.setCanFilter(true);
		ListGridField field6 = new ListGridField("deliveryPeriod"/* , "回购交货期(天)" */);
		field6.setCanFilter(true);
		ListGridField field7 = new ListGridField("m_InternationalCurrencyCode"/*
																			 * ,
																			 * "币种"
																			 */);
		field7.setCanFilter(true);
		ListGridField field8 = new ListGridField("createBy"/* , "制定人" */);
		field8.setCanFilter(true);
		ListGridField field9 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus"/* , "发布状态" */);
		field9.setCanFilter(true);
		ListGridField field10 = new ListGridField(
				"m_BussinessStatusEntity.status"/* , "业务状态" */);
		field10.setCanFilter(true);
		ListGridField field11 = new ListGridField("auditStatus"/* , "审批结果" */);
		field11.setCanFilter(true);
		setFields(field1,field2, field3, field4, field5, field6, field7, field8,
				field9, field10, field11);
		setCellHeight(22);
	}
}
