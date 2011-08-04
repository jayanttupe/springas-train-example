package com.skynet.spms.client.gui.base;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.fields.DateItem;

public class CustomDateItem extends DateItem {

	public CustomDateItem(String name, String title) {
        setName(name);
		setTitle(title);
        setAttribute("editorType", "DateItem");
        
		setUseTextField(true);
		setDisplayFormat(DateDisplayFormat.TOJAPANSHORTDATE);
	}

	public CustomDateItem(String name) {
        setName(name);
        setAttribute("editorType", "DateItem");
        
		setUseTextField(true);
		setDisplayFormat(DateDisplayFormat.TOJAPANSHORTDATE);
	}
}
