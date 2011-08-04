package com.skynet.spms.client.gui.commonOrder;

import com.skynet.spms.client.gui.base.EnumTool;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class FormatUtils {

	/**
	 * 数量
	 * 
	 * @param field
	 */
	public static void quantityFormat(ListGridField field) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				try {
					return value
							+ " "
							+ EnumTool.getIn18Value(EnumTool.UNITOFMEASURECODE,
									record.getAttribute("unit"));
				} catch (Exception e) {
					return value
							+ " "
							+ EnumTool.getIn18Value(EnumTool.UNITOFMEASURECODE,
									record.getAttribute("unit"));
				}
			}
		});
		field.setAlign(Alignment.RIGHT);
	}

	/**
	 * 单价
	 * 
	 * @param field
	 */
	public static void unitPriceFormat(ListGridField field) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				return value
						+ "  "
						+ EnumTool.getIn18Value(
								EnumTool.INTERNATIONALCURRENCYCODE,
								record.getAttribute("currency"));
			}
		});
		field.setAlign(Alignment.RIGHT);
	}

	/**
	 * 总价
	 * 
	 * @param field
	 */
	public static void totalPriceFormt(ListGridField field) {
		field.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (null == value) {
					Integer quantity = record.getAttributeAsInt("quantity");// 数量
					Float unitPrice = record
							.getAttributeAsFloat("unitPriceAmount");// 单价
					if (null != quantity && null != unitPrice) {
						return quantity
								* unitPrice
								+ "  "
								+ EnumTool.getIn18Value(
										EnumTool.INTERNATIONALCURRENCYCODE,
										record.getAttribute("currency"));
					} else {
						return "0";
					}
				} else {
					return value
							+ " "
							+ EnumTool.getIn18Value(
									EnumTool.INTERNATIONALCURRENCYCODE,
									record.getAttribute("currency"));
				}
			}
		});
		field.setAlign(Alignment.RIGHT);
	}

}
