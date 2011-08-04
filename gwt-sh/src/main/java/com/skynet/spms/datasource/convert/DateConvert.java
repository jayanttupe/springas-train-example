package com.skynet.spms.datasource.convert;

import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class DateConvert implements Converter {
	
	public Object convert(Class arg0, Object arg1) {
		if(arg1 instanceof java.util.Date){
			return arg1;
		}
		String date = (String) arg1;
		if (date == null || date.trim().length() == 0) {
			return null;
		}
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return df.parse(date.trim());
		} catch (Exception e) {
			return null;
		}
	}

}
