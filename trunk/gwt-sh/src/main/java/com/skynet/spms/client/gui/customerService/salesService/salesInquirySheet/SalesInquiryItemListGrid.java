package com.skynet.spms.client.gui.customerService.salesService.salesInquirySheet;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 询价明细列表
 * 
 * @author tqc
 * 
 */
public class SalesInquiryItemListGrid extends ListGrid {
	private I18n ui = new I18n();

	public SalesInquiryItemListGrid() {
		this.setCanRemoveRecords(true);
		this.setRemoveFieldTitle(ui.getB().btnRemove());
		this.setCanEdit(false);
		
		//this.setSelectionAppearance(SelectionAppearance.CHECKBOX);
	}

	public void drawGrid() {
		// ListGridField fileld1 = new ListGridField("itemNumber", "询价单编号");
		// fileld1.setCanEdit(false);
		ListGridField fileld1 = new ListGridField("itemNumber");//项号
		fileld1.setCanFilter(true);

		ListGridField fileld2 = new ListGridField("partNumber");//件号
		fileld2.setCanFilter(true);

		ListGridField fileld10 = new ListGridField("itemNumber","备件描述");//备件描述
		fileld10.setCanFilter(true);

		
		ListGridField fileld3 = new ListGridField("m_ManufacturerCode");//制造商代码
		fileld3.setCanFilter(true);

		ListGridField fileld4 = new ListGridField("m_Priority");//工作任务的优先级*
		fileld4.setCanFilter(true);
		fileld4.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				if (value == null) {
					return null;
				} else {
					try {
						if (value.equals("Priority.AOG")) {
							return "飞机停场紧急模式";
						} else if (value.equals("Priority.routine")) {
							return "正常工作任务模式";
						} else if (value.equals("Priority.expedite")) {
							return "加急工作任务模式";
						}
						return null;
					} catch (Exception e) {
						return value.toString();
					}

				}
			}
		});

		ListGridField fileld5 = new ListGridField("quantity");//数量
		fileld5.setType(ListGridFieldType.INTEGER);
		fileld5.setCanFilter(true);

		ListGridField fileld6 = new ListGridField("m_UnitOfMeasureCode");//单位代码
		fileld6.setCanFilter(true);
		fileld6.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				if (value == null) {
					return null;
				} else {
					try {
						if (value.equals("UnitOfMeasureCode.EA")) {
							return "个";
						} 
						return null;
					} catch (Exception e) {
						return value.toString();
					}

				}
			}
		});

		ListGridField fileld7 = new ListGridField("partDeliveryDate");//备件交货日期
		fileld7.setCanFilter(true);

		ListGridField fileld8 = new ListGridField("remark");//备注
		fileld8.setCanFilter(true);

		ListGridField fileld9 = new ListGridField(
				"m_QuotationStatusEntity.m_QuotationStatus");//是否报价
		fileld9.setCanFilter(true);
		fileld9.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String defortValue="<font color='red'>未报价</font>";
				if (value == null) {
					return defortValue;
				} else {
					try {
						if (value.equals("QuotationStatusEntity.didNotOffer")) {
							return defortValue;
						} else if (value.equals("QuotationStatusEntity.alreadyOffer")) {
							return "已报价";
						}
						return defortValue;
					} catch (Exception e) {
						return value.toString();
					}
				}
			}
		});
		setFields(fileld1, fileld2, fileld10,fileld3, fileld4, fileld5, fileld6,
				fileld7, fileld8, fileld9);
		setCellHeight(22);
	}

}
