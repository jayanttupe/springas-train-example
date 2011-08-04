package com.skynet.spms.client.gui.customerService.salesService.salesInquirySheet;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 询价单列表
 * 
 * @author tqcs
 * 
 */
public class SalesInquiryListGrid extends ListGrid {
	private I18n ui = new I18n();

	public SalesInquiryListGrid() {
		this.setWidth100();
		this.setMargin(5);
		this.setCanRemoveRecords(true);
		this.setRemoveFieldTitle(ui.getB().btnRemove());
		this.setCellHeight(22);
		this.setShowFilterEditor(true);
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);
		this.setCanEdit(false);
	}

	/**
	 * 绘制表格列
	 */
	public void drawGrid() {
		ListGridField field1 = new ListGridField("message", "留言");
		// 询价单编号
		ListGridField field2 = new ListGridField("inquirySheetNumber");
		field2.setCanFilter(true);
		// 客户名称
		ListGridField field3 = new ListGridField(
				"m_CustomerIdentificationCode.code");
		field3.setCanFilter(true);
		// 询价日期
		ListGridField field4 = new ListGridField("createDate");
		field4.setType(ListGridFieldType.DATE);
		field4.setShowGroupSummary(true);
		field4.setShowGridSummary(false);
		field4.setSummaryFunction(SummaryFunctionType.MAX);
		field4.setCanFilter(true);
		// 联系人
		ListGridField field5 = new ListGridField("linkman");
		field5.setCanFilter(true);
		// 联系方式
		ListGridField field6 = new ListGridField("contactInformation");
		field6.setCanFilter(true);
		// 是否发布
		ListGridField field7 = new ListGridField(
				"m_BussinessStatusEntity.status");
		field7.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				return formatter(value);
			}
		});
		field7.setCanFilter(true);
		// 是否报价
		ListGridField field8 = new ListGridField(
				"m_QuotationStatusEntity.m_QuotationStatus");
		field8.setCanFilter(true);
		field8.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String defortValue = "<font color='red'>未报价</font>";
				if (value == null) {
					return defortValue;
				} else {
					try {
						if (value.equals("didNotOffer")) {
							return defortValue;
						} else if (value.equals("alreadyOffer")) {
							return "全部报价";
						} else if (value.equals("partsOffer")) {
							return "部分报价";
						}
						return defortValue;
					} catch (Exception e) {
						return value.toString();
					}
				}
			}
		});
		this.setFields(field1, field2, field3, field4, field5, field6, field7,
				field8);

	}

	private String formatter(Object value) {
		String deforeValue = "<font color='red'>已新建</font>";
		if (value == null) {
			return deforeValue;
		} else {
			try {
				if (value.equals("EntityStatusMonitor.created")) {
					return deforeValue;
				} else if (value.equals("EntityStatusMonitor.submited")) {
					return "已提交";
				} else if (value.equals("EntityStatusMonitor.refused")) {
					return "已打回";
				} else if (value.equals("EntityStatusMonitor.accepted")) {
					return "已确认";
				} else if (value.equals("EntityStatusMonitor.dispatched")) {
					return "已分派";
				} else if (value.equals("EntityStatusMonitor.processed")) {
					return "已处理";
				} else if (value.equals("EntityStatusMonitor.closed")) {
					return "已关闭";
				}
				return deforeValue;
			} catch (Exception e) {
				return value.toString();
			}
		}
	}
}
