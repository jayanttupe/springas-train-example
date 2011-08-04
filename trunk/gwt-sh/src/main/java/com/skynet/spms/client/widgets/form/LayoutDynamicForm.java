package com.skynet.spms.client.widgets.form;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;

/**
 * 自定义Form 用于FormItem 标题布局
 * 
 * @author Tony FANG
 * 
 */
public class LayoutDynamicForm extends DynamicForm {
	private Alignment titleAlign = Alignment.RIGHT;// 默认Title右布局

	public LayoutDynamicForm() {
		this.setCellPadding(5);
		this.setMargin(5);

	}

	public Alignment getTitleAlign() {
		return titleAlign;
	}

	public void setTitleAlign(Alignment titleAlign) {
		this.titleAlign = titleAlign;
	}

	@Override
	public void setFields(FormItem... fields) {
		// TODO Auto-generated method stub
		super.setFields(fields);
		// 循环设置item属性
		for (FormItem formItem : fields) {
			formItem.setTitleAlign(titleAlign);
			formItem.setWidth("100%");
		}
	}

}
