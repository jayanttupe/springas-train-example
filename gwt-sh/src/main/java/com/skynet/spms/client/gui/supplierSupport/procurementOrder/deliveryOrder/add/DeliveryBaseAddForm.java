package com.skynet.spms.client.gui.supplierSupport.procurementOrder.deliveryOrder.add;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同基本信息
 * 
 * @author tqc
 * 
 */
public class DeliveryBaseAddForm extends VLayout {

	private LayoutDynamicForm form = new LayoutDynamicForm();

	public DeliveryBaseAddForm() {
		// form.setDataSource(ds);
		// form.editSelectedData(repairRequisitionListGrid);
		form.setNumCols(6);
		form.setCellPadding(2);
		build();
	}

	private void build() {
		// 指令编号
		TextItem item1 = new TextItem();
		item1.setName("item1");
		item1.setValue("业务编号系统自动生成");
		item1.setTitle("指令编号");
		item1.setDisabled(true);

		// 依据合同号
		TextItem item2 = new TextItem();
		item2.setName("item2");
		item2.setDisabled(true);
		item2.setTitle("依据合同号");
		FormItemIcon fi2 = new FormItemIcon();
		fi2.setPrompt("查看");
		item2.setIcons(fi2);

		// 优先级
		SelectItem item3 = new SelectItem();
		item3.setTitle("优先级");
		item3.setName("item3");
		item3.setDisabled(true);

		// 发货日期
		DateItem item4 = new DateItem();
		item4.setTitle("发货日期");
		item4.setName("item4");

		// 指令类型
		SelectItem item5 = new SelectItem();
		item5.setName("item5");
		item5.setTitle("指令类型");

		// 修理厂
		SelectItem item6 = new SelectItem();
		item6.setName("item6");
		item6.setTitle("修理厂");
		item6.setDisabled(true);

		// 已指定运代
		CheckboxItem item7 = new CheckboxItem();
		item7.setName("item7");
		item7.setTitle("已制定运代");

		// 发送方案
		SelectItem item8 = new SelectItem();
		item8.setName("item8");
		item8.setTitle("发送方案");

		// 交货方式
		SelectItem item9 = new SelectItem();
		item9.setName("item9");
		item9.setTitle("交货方式");
		item9.setDisabled(true);

		// 运代商
		TextItem item10 = new TextItem();
		item10.setName("item10");
		item10.setTitle("运代商");

		// 联系人
		TextItem item11 = new TextItem();
		item11.setName("item11");
		item11.setTitle("联系人");

		// 贸易方式
		TextItem item12 = new TextItem();
		item12.setName("item12");
		item12.setTitle("贸易方式");
		item12.setDisabled(true);

		// 联系方式
		TextItem item13 = new TextItem();
		item13.setName("item13");
		item13.setTitle("联系方式");
		item13.setColSpan(3);

		// 运输方式
		TextItem item14 = new TextItem();
		item14.setName("item14");
		item14.setTitle("运输方式");
		item14.setDisabled(false);

		// 指令描述
		TextItem item15 = new TextItem();
		item15.setName("item15");
		item15.setTitle("指令描述");
		item15.setRowSpan(3);
		item15.setColSpan(3);
		item15.setHeight("100%");

		// 指定发送方式
		TextItem item16 = new TextItem();
		item16.setName("item16");
		item16.setTitle("指定发送方式");

		// 报关代理商
		TextItem item17 = new TextItem();
		item17.setName("item17");
		item17.setTitle("报关代理商");

		// 发布人
		TextItem item18 = new TextItem();
		item18.setName("item18");
		item18.setTitle("发布人");
		item18.setDisabled(true);

		form.setFields(item1, item2, item3, item4, item5, item6, item7, item8,
				item9, item10, item11, item12, item13, item14, item15, item16,
				item17, item18);
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

		IButton closeBtn = new IButton("关闭");
		closeBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();

			}
		});
		btnGroup.addMember(btnSave);
		btnGroup.addMember(closeBtn);
		this.addMember(btnGroup);
	}

}
