package com.skynet.spms.client.gui.contractManagement.template;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.skynet.spms.client.gui.base.i18n.ContractModuleConst;
import com.skynet.spms.client.gui.contractManagement.common.ContractTreeDS;
import com.skynet.spms.client.service.contractManagement.provision.ContractProvisionService;
import com.skynet.spms.client.service.contractManagement.provision.ContractProvisionServiceAsync;
import com.skynet.spms.client.vo.contractManagement.Provision;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;

public class ProvisionAddWindow extends Window {
	
	private ContractModuleConst ui=GWT.create(ContractModuleConst.class);

	private LayoutDynamicForm form = new LayoutDynamicForm();

	public ProvisionAddWindow(final TreeGrid treeGrid,
			final ComboBoxItem selectItem) {
		this.setWidth(600);
		this.setHeight(500);
		this.setShowMinimizeButton(false);
		this.setAlign(Alignment.CENTER);
		this.setAlign(VerticalAlignment.CENTER);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.centerInPage();

		Button onlySaveBtn = new Button(ui.btnSave());
		onlySaveBtn.setPadding(6);
		onlySaveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate()) {
					Provision p = new Provision();
					Record record = treeGrid.getSelectedRecord();
					if (record == null) {
						p.setParentId("root");
					} else {
						p.setParentId(record.getAttribute("id"));
					}
					p.setTempType(selectItem.getSelectedRecord()
							.getAttributeAsString("id"));
					p.setTitle_en(form.getValueAsString("title_en"));
					p.setTitle_zh(form.getValueAsString("title_zh"));
					p.setContent_en(form.getValueAsString("content_en"));
					p.setContent_zh(form.getValueAsString("content_zh"));

					ContractProvisionServiceAsync service = GWT
							.create(ContractProvisionService.class);
					service.addProvision(p, new AsyncCallback<Void>() {

						public void onSuccess(Void arg0) {
							SC.say(ui.msgAddOpSuccess());
							ContractTreeDS treeDs = new ContractTreeDS(selectItem
									.getSelectedRecord().getAttributeAsString("fileName"),
									System.currentTimeMillis() + "");
							treeGrid.setDataSource(treeDs);
							treeGrid.fetchData();
							treeDs.destroy();
							destroy();
						}

						public void onFailure(Throwable arg0) {
							SC.say("fail");
						}
					});

				}
			}
		});

		Button cancelBtn = new Button(ui.btnCancel());
		cancelBtn.setPadding(6);
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		VLayout v = new VLayout();
		HLayout hlForm = new HLayout();
		hlForm.setWidth100();
		hlForm.setLayoutTopMargin(30);
		hlForm.setAlign(Alignment.CENTER);
		hlForm.setLayoutLeftMargin(15);

		// 获得明细表单
		buildForm();
		hlForm.addMember(form);

		// 图片
		HLayout hlImg = new HLayout();
		hlImg.setWidth("30%");
		Image img = new Image();
		hlImg.addMember(img);
		hlForm.addMember(hlImg);
		v.addMember(hlForm);
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

	private void buildForm() {
		final ContractModuleConst uii18=GWT.create(ContractModuleConst.class);
		form.setTop(5);
		form.setWidth("70%");
		TextItem titleEnItem = new TextItem();
		titleEnItem.setName("title_en");
		titleEnItem.setTitle(uii18.title_en());
		titleEnItem.setRequired(true);

		TextItem titleZhItem = new TextItem();
		titleZhItem.setName("title_zh");
		titleZhItem.setTitle(uii18.title_zh());
		titleZhItem.setRequired(true);

		TextAreaItem contentEnItem = new TextAreaItem();
		contentEnItem.setName("content_en");
		contentEnItem.setTitle(uii18.content_en());
		contentEnItem.setRequired(true);

		TextAreaItem contentZhItem = new TextAreaItem();
		contentZhItem.setName("content_zh");
		contentZhItem.setTitle(uii18.content_zh());
		contentZhItem.setRequired(true);

		form.setFields(titleEnItem, titleZhItem, contentEnItem, contentZhItem);

		this.addMember(form);
	}

}
