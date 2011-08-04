package com.skynet.common.strtemplate;

import java.util.Date;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import com.skynet.common.strtemplate.render.DateRender;
import com.skynet.common.strtemplate.render.DecimalRender;
import com.skynet.common.strtemplate.render.IntegerRender;

public class TemplateGroup {

	private final StringTemplateGroup templateGroup;
	
	TemplateGroup(StringTemplateGroup group){
		this.templateGroup=group;
		
		templateGroup.registerRenderer(Date.class, new DateRender());
		templateGroup.registerRenderer(Float.class, new DecimalRender());
		templateGroup.registerRenderer(Integer.class, new IntegerRender());
		templateGroup.registerRenderer(Double.class, new DecimalRender());
		templateGroup.registerRenderer(Long.class, new IntegerRender());

	}
	
	public void addDateFormat(String name, String format) {
		((TypeAttributeRender) templateGroup.getAttributeRenderer(Date.class))
				.addFormatWithType(name, format);
	}

	public void addFloatFormat(String name,String format) {
		((TypeAttributeRender) templateGroup.getAttributeRenderer(Float.class))
		.addFormatWithType(name, format);
		((TypeAttributeRender) templateGroup.getAttributeRenderer(Double.class))
		.addFormatWithType(name, format);

	}

	public void addIntFormat(String name,String format) {
		((TypeAttributeRender) templateGroup.getAttributeRenderer(Integer.class))
		.addFormatWithType(name, format);
		((TypeAttributeRender) templateGroup.getAttributeRenderer(Long.class))
		.addFormatWithType(name, format);
	}
	
	public TemplateTool getTemplateTool(String templateName){
		
		StringTemplate tpl = templateGroup.getInstanceOf(templateName);

		return new TemplateTool(tpl);
	}
	
}
