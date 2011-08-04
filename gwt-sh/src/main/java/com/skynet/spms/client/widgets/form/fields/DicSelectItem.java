package com.skynet.spms.client.widgets.form.fields;

import com.smartgwt.client.widgets.form.fields.SelectItem;

/**
 * 字典下拉列表(针对枚举字典)
 * 
 * @author tqc
 * 
 */

public class DicSelectItem extends SelectItem {

	private String key;

	public void setValueMap(String key) {
		this.key = key;
	}

	public void setDefaultValue(String value) {
		if (key != null && !"".equals(value)) {
		}

	}

}
