package com.skynet.spms.client.gui.customerService.commonui;

import java.util.List;

import com.skynet.spms.client.gui.contractManagement.common.ContractTreeDS;
import com.skynet.spms.client.gui.contractManagement.tag.Tag;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.contractManagement.tag.TagManager;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

/**
 * 合同条款
 * 
 * @author tqc
 * 
 */
public class ContractProvisionPanel extends VLayout {
	
	private DynamicForm form=new DynamicForm();
	
	final TextAreaItem contentEnItem = new TextAreaItem();
	
	final TextAreaItem contentZhItem = new TextAreaItem();
	
	private DynamicForm contractForm;
	
	private TagEnum tagType;

	public ContractProvisionPanel(DynamicForm contractForm,TagEnum tagType) {
		this.contractForm=contractForm;
		this.tagType=tagType;
		addMember(_init());
	}

	public Canvas _init() {
		HLayout wrap = new HLayout();
		final TreeGrid treeGrid = new TreeGrid();
		treeGrid.setLoadDataOnDemand(false);
		treeGrid.setWidth100();
		treeGrid.setHeight100();
		treeGrid.setNodeIcon("icons/16/person.png");
		treeGrid.setFolderIcon("icons/16/person.png");
		treeGrid.setShowOpenIcons(false);
		treeGrid.setShowDropIcons(false);
		treeGrid.setClosedIconSuffix("");
		treeGrid.setCanFreezeFields(true);
		treeGrid.setCanReparentNodes(true);
		TreeGridField nameField = new TreeGridField("title_en", 150);
		nameField.setFrozen(true);
		treeGrid.setFields(nameField);
		ContractTreeDS treeDs = new ContractTreeDS("RepairInspectClaimContractTemplate.xml",
				System.currentTimeMillis() + "");
		treeGrid.setDataSource(treeDs);
		form.setDataSource(treeDs);
		treeGrid.fetchData();

		//替换tag
		treeGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				form.editRecord(treeGrid.getSelectedRecord());
				String en=treeGrid.getSelectedRecord().getAttribute("content_en");
				String zh=treeGrid.getSelectedRecord().getAttribute("content_zh");
				List<Tag> tags=TagManager.getInstance().getTags(tagType);
				for (Tag tag : tags) {
					if(en.indexOf("{"+tag.getTagKey()+"}")!=-1){//hits tag
						//Find value from the form
						FormItem item=contractForm.getItem(tag.getTagKey());
						//Replace the tags
						if(item!=null){
							String value=item.getDisplayValue();
							en=en.replaceAll("{"+tag.getTagKey()+"}",value);
							contentEnItem.setValue(en);
						}
					}
					if(zh.indexOf("{"+tag.getTagKey()+"}")!=-1){//hits tag
						//Find value from the form
						FormItem item=contractForm.getItem(tag.getTagKey());
						//Replace the tags
						if(item!=null){
							String value=item.getDisplayValue();
							zh=zh.replaceAll("{"+tag.getTagKey()+"}", value);
							contentZhItem.setValue(zh);
						}
					}
					
				}
				
			
			}
		});

		VLayout bottomLeft = new VLayout();
		bottomLeft.setWidth("30%");
		bottomLeft.setBorder("1px solid #E8E8E8");
		bottomLeft.setShowResizeBar(true);
		bottomLeft.addMember(treeGrid);

		VLayout bottomRight = new VLayout();
		bottomRight.setBorder("1px solid #E8E8E8");
		bottomRight.setWidth("70%");
		bottomRight.addMember(buildForm());

		wrap.addMember(bottomLeft);
		wrap.addMember(bottomRight);
		
		return wrap;

	}
	
	private DynamicForm buildForm() {
		form.setTop(5);
		TextItem titleEnItem = new TextItem();
		titleEnItem.setName("title_en");
		titleEnItem.setTitle("英文标题");
		titleEnItem.setRequired(true);
		titleEnItem.setWidth("100%");
		TextItem titleZhItem = new TextItem();
		titleZhItem.setName("title_zh");
		titleZhItem.setTitle("中文标题");
		titleZhItem.setRequired(true);
		titleZhItem.setWidth("100%");
		
		contentEnItem.setName("content_en");
		contentEnItem.setTitle("英文内容");
		contentEnItem.setRequired(true);
		contentEnItem.setWidth("100%");
		
		contentZhItem.setName("content_zh");
		contentZhItem.setTitle("中文内容");
		contentZhItem.setRequired(true);
		contentZhItem.setWidth("100%");
		form.setFields(titleEnItem, titleZhItem, contentEnItem, contentZhItem);
		return form;
	}



}