package com.skynet.common.strtemplate.render;

import java.text.DecimalFormat;
import java.text.Format;

public class IntegerRender extends AbstractRender {

	@Override
	protected String getDefaultFormat() {
		return "####";
	}

	@Override
	protected Format getFormat(String pattern) {
		return new DecimalFormat(pattern);
	}

	@Override
	protected boolean validType(Object obj) {
		return obj instanceof Integer ||
		obj instanceof Long;
	}

}
