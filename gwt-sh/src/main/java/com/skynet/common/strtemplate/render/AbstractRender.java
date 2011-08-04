package com.skynet.common.strtemplate.render;

import java.text.Format;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.skynet.common.strtemplate.TypeAttributeRender;


public abstract class AbstractRender implements TypeAttributeRender {

	private final String format;

	public AbstractRender() {
		format = getDefaultFormat();
	}

	protected abstract String getDefaultFormat();

	protected abstract Format getFormat(String pattern);

	protected abstract boolean validType(Object obj);

	@Override
	public String toString(Object o) {
		Format pattern = getFormat(format);
		return pattern.format(o);
	}

	private Map<String, String> formatMap = Collections.synchronizedMap(new HashMap<String, String>());

	@Override
	public String toString(Object o, String formatName) {
		String format = formatMap.get(formatName);
		Format pattern = null;
		if (StringUtils.isBlank(format)) {
			pattern = getFormat(formatName);
		} else {
			pattern = getFormat(format);
		}
		if (validType(o)) {
			return pattern.format(o);
		} else {
			return String.valueOf(o);
		}
	}

	@Override
	public void addFormatWithType(String type, String format) {
		formatMap.put(type, format);
		return ;
	}

}
