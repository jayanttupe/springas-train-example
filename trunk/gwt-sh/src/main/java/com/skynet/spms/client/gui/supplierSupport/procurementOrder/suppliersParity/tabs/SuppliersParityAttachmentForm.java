package com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.tabs;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FileItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同附件
 * 
 * @author tqc
 * 
 */
public class SuppliersParityAttachmentForm extends VLayout {

	public SuppliersParityAttachmentForm() {
		// 构建表格
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth100();
		sectionStack.setHeight(230);

		SectionStackSection section = new SectionStackSection("合同附件");
		section.setCanCollapse(false);
		section.setExpanded(true);
		HLayout h = new HLayout();
		h.setLayoutMargin(10);
		h.setWidth100();
		h.setHeight100();
	

		final ListGrid attachmentGrid = new ListGrid();
		attachmentGrid.setWidth100();
		attachmentGrid.setShowAllRecords(true);
		attachmentGrid.setCellHeight(22);
		attachmentGrid.setMargin(10);

		ListGridField itemNumberField = new ListGridField("itemId", "项号", 40);
		itemNumberField.setCanEdit(false);
		ListGridField titleField = new ListGridField("title", "标题");
		titleField.setCanEdit(false);
		ListGridField descriptionField = new ListGridField("description", "描述");
		descriptionField.setCanEdit(false);
		ListGridField fillNameField = new ListGridField("fileName", "文件名");
		fillNameField.setCanEdit(false);
		ListGridField modifyDateField = new ListGridField("modifyDate",
				"最后修改时间");
		modifyDateField.setCanEdit(false);
		ListGridField operatorField = new ListGridField("operator", "修改人");
		operatorField.setCanEdit(false);

		attachmentGrid.setFields(itemNumberField, titleField, descriptionField,
				fillNameField, modifyDateField, operatorField);

		h.addMember(attachmentGrid);
		section.setItems(h);
		sectionStack.setSections(section);

		// 构建按钮组
		DynamicForm fileUploadBar = new DynamicForm();
		fileUploadBar.setNumCols(8);
		fileUploadBar.setWidth100(); 
		fileUploadBar.setHeight(50);
		fileUploadBar.setIsGroup(true);  
		fileUploadBar.setGroupTitle("文件上传");
		
		FileItem uploadItem = new FileItem("附件");
		
		ButtonItem submitOneBtn = new ButtonItem();
		submitOneBtn.setStartRow(false);
		submitOneBtn.setEndRow(false);
		submitOneBtn.setTitle("上传附件");
		
		FileItem uploadKuFileButton = new FileItem("客户送修确认函");
		uploadKuFileButton.setWidth(200);
		
		ButtonItem submitTwoBtn = new ButtonItem();
		submitTwoBtn.setStartRow(false);
		submitTwoBtn.setEndRow(false);
		submitTwoBtn.setTitle("上传客户送修确认函");
		
		fileUploadBar.setFields(uploadItem,submitOneBtn,uploadKuFileButton,submitTwoBtn);

		addMember(sectionStack);
		addMember(fileUploadBar);

	}

}
