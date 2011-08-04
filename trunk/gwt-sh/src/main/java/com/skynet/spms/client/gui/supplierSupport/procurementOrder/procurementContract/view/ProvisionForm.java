package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.view;

import com.skynet.spms.client.gui.contractManagement.common.ContractTreeDS;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

/**
 * 条例配置
 * 
 * @author gqr
 * 
 */
public class ProvisionForm extends HLayout {
	private DynamicForm form=new DynamicForm();
	public ProvisionForm() {
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
		ContractTreeDS treeDs = new ContractTreeDS("ProcurementContract.xml",
				System.currentTimeMillis() + "");
		treeGrid.setDataSource(treeDs);
		form.setDataSource(treeDs);
		treeGrid.fetchData();

		treeGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				form.editRecord(treeGrid.getSelectedRecord());
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
		TextItem titleZhItem = new TextItem();
		titleZhItem.setName("title_zh");
		titleZhItem.setTitle("中文标题");
		titleZhItem.setRequired(true);
		TextAreaItem contentEnItem = new TextAreaItem();
		contentEnItem.setName("content_en");
		contentEnItem.setTitle("英文内容");
		contentEnItem.setRequired(true);
		TextAreaItem contentZhItem = new TextAreaItem();
		contentZhItem.setName("content_zh");
		contentZhItem.setTitle("中文内容");
		contentZhItem.setRequired(true);
		form.setFields(titleEnItem, titleZhItem, contentEnItem, contentZhItem);
		return form;
	}

}
