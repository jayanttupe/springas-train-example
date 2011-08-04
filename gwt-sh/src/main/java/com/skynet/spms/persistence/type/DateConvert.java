package com.skynet.spms.persistence.type;

import java.text.SimpleDateFormat;
import org.apache.commons.beanutils.Converter;

/**
 * 日期转换器
 * 
 * @author tqc
 * 
 */
public class DateConvert implements Converter {
	
	public Object convert(Class arg0, Object arg1) {
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
