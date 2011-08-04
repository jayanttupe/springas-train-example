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

/**
 * 询价单修改窗体
 * 
 * @author tqc
 * 
 */
public class SalesInquiryModifyWindow extends Window {

	private LayoutDynamicForm form = new LayoutDynamicForm();

	public SalesInquiryModifyWindow(final SalesInquiryListGrid listGrid) {
		this.setWidth(600);
		this.setHeight(500);
		this.setTitle("修改询价单");
		this.setShowMinimizeButton(false);
		this.setAlign(Alignment.CENTER);
		this.setAlign(VerticalAlignment.CENTER);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.centerInPage();

		Button modityBtn = new Button("保存");
		modityBtn.setPadding(6);
		modityBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("修改成功!");
						form.clearValues();
						Criteria c = new Criteria();
						c.setAttribute("key", "reload");
						c.setAttribute("r", String.valueOf(Math.random()));
						listGrid.fetchData(c);
						destroy();
					}
				});

			}
		});

		Button cancelBtn = new Button("取消");
		cancelBtn.setPadding(6);
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		/**主布局**/
		HLayout main = new HLayout();

		/**左布局**/
		VLayout leftLayout = new VLayout();
		leftLayout.setWidth("80%");
		leftLayout.setHeight100();
		leftLayout.setLayoutTopMargin(30);
		leftLayout.setLayoutLeftMargin(15);
		leftLayout.setAlign(Alignment.CENTER);

		// 获得明细表单
		form.setWidth("80%");
		buildForm(listGrid);
		leftLayout.addMember(form);

		//(左布局2)操作按钮布局
		HLayout hlayout = new HLayout();
		hlayout.setLayoutTopMargin(30);
		hlayout.setLayoutLeftMargin(10);
		hlayout.setWidth100();
		hlayout.setMargin(5);
		hlayout.setPadding(5);
		hlayout.setAlign(Alignment.LEFT);
		hlayout.addMember(modityBtn);
		hlayout.addMember(cancelBtn);
		hlayout.setWidth("20%");
		leftLayout.addMember(hlayout);

		main.addMember(leftLayout);

		/**右布局**/
		HLayout rightLayout = new HLayout();
		rightLayout.setWidth("20%");

		//图片
		HLayout hlImg = new HLayout();
		Image img = new Image();
		hlImg.addMember(img);

		rightLayout.addMember(hlImg);

		main.addMember(rightLayout);

		this.addItem(main);
	}

	/**
	 * 构建询价表单
	 */
	private void buildForm(SalesInquiryListGrid listGrid) {

		form.setDataSource(listGrid.getDataSource());
		form.reset();
		form.editSelectedData(listGrid);
		form.setNumCols(3);
		form.setTop(5);

		StaticTextItem item1 = new StaticTextItem();
		item1.setAttribute("readOnly", true);
		item1.setName("inquirySheetNumber");
		item1.setTitle("编号");
		item1.setDisabled(false);
		item1.setTitleAlign(Alignment.LEFT);
		item1.setRequired(true);

		SelectItem item2 = new SelectItem();
		item2.setName("m_CustomerIdentificationCode.id");
		item2.setTitle("客户");
		item2.setTitleAlign(Alignment.LEFT);
		CodeRPCTool.bindData(CodeRPCTool.CUSTOMERIDENTIFICATIONCODE, item2);
		
		FormItemIcon fiIcon = new FormItemIcon();
		fiIcon.setPrompt("客户信息");
		item2.setRequired(true);
		item2.setIcons(fiIcon);
		item2.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent event) {

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

		form.setFields(item1, item2, item3, item4, item5);
	}

}
