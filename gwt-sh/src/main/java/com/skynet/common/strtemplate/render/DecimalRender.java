package com.skynet.common.strtemplate.render;

import java.text.DecimalFormat;
import java.text.Format;

public class DecimalRender extends AbstractRender{

	@Override
	protected Format getFormat(String pattern) {
		return new DecimalFormat(pattern);
	}

	@Override
	protected boolean validType(Object obj) {
		return obj instanceof Float ||
				obj instanceof Double;
	}

	@Override
	protected String getDefaultFormat() {
		return "#,###.00";
	}

}
