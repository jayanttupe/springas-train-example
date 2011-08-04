package com.skynet.spms.client.gui.supplierSupport.procurementOrder.deliveryOrder.add;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 收发地址
 * 
 * @author tqc
 * 
 */
public class DeliveryAddressAddForm extends VLayout {

	private LayoutDynamicForm form = new LayoutDynamicForm();

	public DeliveryAddressAddForm() {
		form.setNumCols(6);
		form.setCellPadding(2);
		build();
	}

	private void build() {
		// 发货单位
		TextItem item1 = new TextItem();
		item1.setName("item1");
		item1.setTitle("发货单位");

		// 发货人
		TextItem item2 = new TextItem();
		item2.setName("item2");
		item2.setTitle("发货人");

		// 唛头
		TextItem item3 = new TextItem();
		item3.setName("item3");
		item3.setTitle("唛头");
		item3.setHeight("100%");
		item3.setRowSpan(6);
		item3.setTitleVAlign(VerticalAlignment.TOP);

		// 发货地址
		TextItem item4 = new TextItem();
		item4.setTitle("发货地址");
		item4.setName("item3");
		item4.setColSpan(3);
		item4.setTitleVAlign(VerticalAlignment.TOP);

		// 联系方式
		TextAreaItem item5 = new TextAreaItem();
		item5.setTitle("联系方式");
		item5.setName("item5");
		item5.setColSpan(3);
		item5.setHeight(50);

		// 收货单位
		TextItem item6 = new TextItem();
		item6.setName("item6");
		item6.setTitle("收货单位");

		// 收货人
		TextItem item7 = new TextItem();
		item7.setName("item7");
		item7.setTitle("收货人");

		// 收货地址
		TextItem item8 = new TextItem();
		item8.setName("item8");
		item8.setTitle("收货地址");
		item8.setColSpan(3);

		// 联系方式
		TextAreaItem item9 = new TextAreaItem();
		item9.setName("item9");
		item9.setTitle("联系方式");
		item9.setColSpan(3);
		item9.setHeight(50);

		form.setFields(item1, item2, item3, item4, item5, item6, item7, item8,
				item9);

		this.addMember(form);

		/**操作按钮**/
		BtnsHLayout btnGroup = new BtnsHLayout();
		IButton btnSave = new IButton("保存");
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("ok");
					}
				});
			}
		});

		IButton closeSave = new IButton("关闭");
		closeSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						destroy();
					}
				});
			}
		});

		DynamicForm df1 = new DynamicForm();
		FormItem a = new FormItem();

		DynamicForm df2 = new DynamicForm();
		FormItem b = new FormItem();

		btnGroup.setMembers(btnSave, closeSave);
		this.addMember(btnGroup);
	}

}
