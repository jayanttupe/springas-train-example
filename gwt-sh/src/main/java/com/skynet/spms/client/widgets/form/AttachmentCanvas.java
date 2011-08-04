package com.skynet.spms.client.widgets.form;

import com.skynet.spms.client.gui.base.CustomPanel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FileItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同附件
 * 
 * @author tqc
 * 
 */
public class AttachmentCanvas extends VLayout {

	public boolean showCustomersConfirmation = false;//是否显示 上传客户送修确认函

	public boolean showUploadOperator = true;//是否显示 上传容器

	public ListGrid attachmentGrid;//附件显示内容
	public FileItem uploadItem;//上传附件容器

	FileItem uploadKuFileButton;
	DynamicForm fileUploadBar;

	public AttachmentCanvas() {
		// 构建表格

		CustomPanel cp = new CustomPanel();
		cp.getTitleLabel().setContents("附件");

		VLayout v = new VLayout();
		v.setWidth100();
		v.setHeight100();
		v.setLayoutMargin(3);
		//附件

		attachmentGrid = new ListGrid();
		attachmentGrid.setWidth100();
		attachmentGrid.setShowAllRecords(true);
		attachmentGrid.setCanEdit(false);

		ListGridField itemNumberField = new ListGridField("itemId", "项号", 40);
		ListGridField titleField = new ListGridField("title", "标题");
		ListGridField descriptionField = new ListGridField("description", "描述");
		ListGridField fillNameField = new ListGridField("fileName", "文件名");
		ListGridField modifyDateField = new ListGridField("modifyDate",
				"最后修改时间");
		ListGridField operatorField = new ListGridField("operator", "修改人");

		attachmentGrid.setFields(itemNumberField, titleField, descriptionField,
				fillNameField, modifyDateField, operatorField);

		v.addMember(attachmentGrid);

		// 构建按钮容器
		fileUploadBar = new DynamicForm();
		fileUploadBar.setNumCols(8);
		fileUploadBar.setWidth100();
		fileUploadBar.setHeight(50);
		fileUploadBar.setIsGroup(true);
		fileUploadBar.setGroupTitle("文件上传");

		uploadItem = new FileItem("附件");
		ButtonItem submitOneBtn = new ButtonItem();
		submitOneBtn.setStartRow(false);
		submitOneBtn.setEndRow(false);
		submitOneBtn.setTitle("上传附件");
		//显示上传客户送修确认函，默认不显示
		uploadKuFileButton = new FileItem("客户送修确认函");
		uploadKuFileButton.setWidth(200);

		ButtonItem submitTwoBtn = new ButtonItem();
		submitTwoBtn.setStartRow(false);
		submitTwoBtn.setEndRow(false);

		submitTwoBtn.setTitle("上传客户送修确认函");

		//提交
		fileUploadBar.setFields(uploadItem, submitOneBtn, uploadKuFileButton,
				submitTwoBtn);

		v.addMember(fileUploadBar);

		cp.getContentCanvas().addChild(v);
		this.addMember(cp);
	}

	public boolean isShowCustomersConfirmation() {
		return showCustomersConfirmation;
	}

	public void setShowCustomersConfirmation(boolean showCustomersConfirmation) {
		uploadKuFileButton.setVisible(showCustomersConfirmation);
		this.showCustomersConfirmation = showCustomersConfirmation;
	}

	public boolean isShowUploadOperator() {
		return showUploadOperator;
	}

	public void setShowUploadOperator(boolean showUploadOperator) {
		fileUploadBar.setVisible(showUploadOperator);
		this.showUploadOperator = showUploadOperator;
	}
}
