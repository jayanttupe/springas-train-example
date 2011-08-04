package com.skynet.spms.client.gui.contractManagement.common;

import com.skynet.spms.client.model.IModelLocator;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;

public class TagModelLocator implements IModelLocator<TagModelLocator> {
	private static TagModelLocator instance;

	private TagModelLocator() {
	}

	public static TagModelLocator getInstance() {
		if (instance == null) {
			instance = new TagModelLocator();
		}
		return instance;
	}
	
	public TextAreaItem zhItem;
	
	public TextAreaItem enItem;


}
