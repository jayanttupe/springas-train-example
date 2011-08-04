package com.skynet.spms.client.widgets.form.fields;

import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class ReadOnlyItem extends TextItem {

	public ReadOnlyItem() {
		super();
		this.setAttribute("readOnly", true);
		this.setTextBoxStyle("readOnlyColor");
	}


}
