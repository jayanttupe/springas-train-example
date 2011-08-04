package com.skynet.common.strtemplate;

import org.antlr.stringtemplate.AttributeRenderer;


public interface TypeAttributeRender extends AttributeRenderer {

	public void addFormatWithType(String type,String format);
}
