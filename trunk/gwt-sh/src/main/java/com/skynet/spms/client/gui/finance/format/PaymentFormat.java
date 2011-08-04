package com.skynet.spms.client.gui.finance.format;

import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PaymentFormat implements CellFormatter {

	@Override
	public String format(Object value, ListGridRecord record, int rowNum,
			int colNum) {
		// TODO Auto-generated method stub
		if("Payment.cash".equals(value))
			return "现金";

		return null;
	}

}
