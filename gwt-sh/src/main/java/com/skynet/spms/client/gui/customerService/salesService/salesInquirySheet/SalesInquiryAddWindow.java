package com.skynet.spms.client.gui.customerService.salesService.salesInquirySheet;

import com.google.gwt.user.client.ui.Image;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/***
 * 询价单添加窗体
 * 
 * @author tqc
 * 
 */
public class SalesInquiryAddWindow extends Window {

	private LayoutDynamicForm form = new LayoutDynamicForm();

	public SalesInquiryAddWindow(final SalesInquiryListGrid salesInquiryListgrid) {
		this.setWidth(600);
		this.setHeight(500);
		this.setShowMinimizeButton(false);
		this.setAlign(Alignment.CENTER);
		this.setAlign(VerticalAlignment.CENTER);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.centerInPage();
		
		form.setDataSource(salesInquiryListgrid.getDataSource());

		Button onlySaveBtn = new Button("保存");
		onlySaveBtn.setPadding(6);
		onlySaveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate()) {
					form.saveData(new DSCallback() {
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							SC.say("保存成功!");
							form.clearValues();
							Criteria c = new Criteria();
							c.setAttribute("key", "reload");
							c.setAttribute("r", String.valueOf(Math.random()));
							salesInquiryListgrid.fetchData(c);
							destroy();
						}
					});

				} else {

				}

			}
		});

		Button cancelBtn = new Button("取消");
		cancelBtn.setPadding(6);
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});

		// 主布局
		VLayout v = new VLayout();
		
		HLayout hlForm = new HLayout();
		hlForm.setWidth100();
		hlForm.setLayoutTopMargin(30);
		hlForm.setAlign(Alignment.CENTER);
		hlForm.setLayoutLeftMargin(15);
		
		form.setWidth("70%");
		// 获得明细表单
		buildForm();
		hlForm.addMember(form);

		//图片
		HLayout hlImg = new HLayout();
		hlImg.setWidth("30%");
		Image img = new Image();
		hlImg.addMember(img);
		hlForm.addMember(hlImg);

		v.addMember(hlForm);

		// 操作按钮布局
		HLayout hlBtns = new HLayout();
		hlBtns.setLayoutTopMargin(30);
		hlBtns.setLayoutLeftMargin(10);
		hlBtns.setWidth100();
		hlBtns.setMargin(5);
		hlBtns.setPadding(5);
		hlBtns.setAlign(Alignment.LEFT);
		hlBtns.addMember(onlySaveBtn);
		hlBtns.addMember(cancelBtn);
		v.addMember(hlBtns);

		this.addItem(v);
	}

	/**
	 * 构建询价表单
	 */
	private void buildForm() {

		form.setNumCols(3);
		form.setTop(5);
		form.setWidth("70%");

		StaticTextItem item1 = new StaticTextItem();
		item1.setTitle("编号");
		item1.setName("inquirySheetNumber");
		item1.setValue("业务编号系统自动生成");
		item1.setDisabled(false);
		item1.setRequired(true);
		
		SelectItem item2 =new SelectItem();
		item2.setName("m_CustomerIdentificationCode.id");
		item2.setTitle("客户");
		item2.setRequired(true);
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item2);
		
		FormItemIcon fiIcon = new FormItemIcon();
		fiIcon.setPrompt("客户信息");
		item2.setIcons(fiIcon);
		item2.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent event) {
				// TODO Auto-generated method stub

			}
		});

		TextItem item3 = new TextItem();
		item3.setName("linkman");
		item3.setTitle("联系人");
		item3.setTitleAlign(Alignment.LEFT);
		item3.setRequired(true);

		TextAreaItem item4 = new TextAreaItem();
		item4.setName("contactInformation");
		item4.setTitle("联系方式");
		item4.setColSpan(2);
		item4.setWidth("100%");
		item4.setTitleVAlign(VerticalAlignment.TOP);
		item4.setTitleAlign(Alignment.LEFT);
		item4.setRequired(true);

		TextAreaItem item5 = new TextAreaItem();
		item5.setName("remark");
		item5.setTitle("备注");
		item5.setColSpan(2);
		item5.setWidth("100%");
		item5.setTitleVAlign(VerticalAlignment.TOP);
		item5.setTitleAlign(Alignment.LEFT);
		//隐藏属性
		//		HiddenItem hidden1 = new HiddenItem();
		//		hidden1.setName("m_BussinessStatusEntity.status");
		//		hidden1.setTitle("是否发布");
		//		
		//		HiddenItem hidden2 = new HiddenItem();
		//		hidden2.setName("m_QuotationStatusEntity.m_QuotationStatus");
		//		hidden2.setTitle("是否报价");


		form.setFields(item1, item2, item3, item4, item5);
	}

	protected void closeWindow() {
		this.hide();
	}

}
