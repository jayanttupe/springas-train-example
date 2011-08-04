package com.skynet.spms.client.gui.customerplatform.guaranteeForm.ui;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 担保索赔申请表格
 * 
 * @author fl
 * 
 */
public class GuaranteeGrid extends BaseListGrid {
	private I18n ui = new I18n();

	public GuaranteeGrid() {
		// 设置删除按钮
	}

	public void drawGrid() {
		ListGridField field1 = new ListGridField("message", "留言");
		Utils.setMessageFieldWidth(field1);
		ListGridField field2 = new ListGridField("requisitionSheetNumber"/*,
				"申请单编号"*/);
		ListGridField field3 = new ListGridField(
				"m_CustomerIdentificationCode.code"/*, "客户名称"*/);
		field3.setCanFilter(true);
		ListGridField field4 = new ListGridField("requisitionDate"/*, "申请日期"*/);
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		
		ListGridField field5 = new ListGridField("linkman"/*, "联系人"*/);
		field5.setWidth(60);
		ListGridField field6 = new ListGridField("contactWay"/*, "联系方式"*/);
		ListGridField field7 = new ListGridField(
				"m_BussinessPublishStatusEntity.m_PublishStatus", ui.getB()
						.listTitlePublishStatus());
		
//		ListGridField field9 = new ListGridField(
//		"m_BussinessStatusEntity.status");
//		field9.setCanFilter(true);
		
		ListGridField field8 = new ListGridField("contractBasisDesc"/*, "合同依据描述"*/);
		setFields(field1, field2, field3, field4, field5, field6, field7,
				field8);
	}
}
