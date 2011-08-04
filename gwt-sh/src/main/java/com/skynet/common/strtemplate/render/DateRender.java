package com.skynet.common.strtemplate.render;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRender extends AbstractRender{

	@Override
	protected String getDefaultFormat() {
		
		return "yyyy/MM/dd";
	}

	@Override
	protected Format getFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	@Override
	protected boolean validType(Object obj) {
		return obj instanceof Date;
	}

}
