package com.skynet.spms.client.gui.finance.format;

import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PayApplyCheckFormat implements CellFormatter {

	@Override
	public String format(Object value, ListGridRecord record, int rowNum,
			int colNum) {
		// TODO Auto-generated method stub
		if("0".equals(value.toString())){
			return "<font color='blue'>未提交审核</font>";
		}else if("1".equals(value.toString())){
			return "<font color='green'>审核通过</font>";
		}else if("3".equals(value.toString())){
			return "<font color='#FF9933'>已提交审批</font>";
		}else
		    return "<font color='red'>审核未通过</font>";
	}

}
