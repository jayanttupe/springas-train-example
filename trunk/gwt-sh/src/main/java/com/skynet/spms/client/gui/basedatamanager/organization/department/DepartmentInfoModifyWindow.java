package com.skynet.spms.client.gui.basedatamanager.organization.department;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DepartmentInfoModifyWindow extends BaseWindow {
	public DepartmentInfoModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		DepartmentInfoTreeGrid departmentInfoTreeGrid =(DepartmentInfoTreeGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, DepartmentInfoModifyWindow.this, -1);
			}
		});
		final DynamicForm formBase = new DynamicForm();
		formBase.setDataSource(departmentInfoTreeGrid.getDataSource());
		formBase.setWidth100();
		formBase.setHeight100();
		//formBase.setBorder("1px solid blue");
		formBase.setPadding(5);
		String requiredHint = "<font color=red>*</font>";
		
		List<FormItem> itemList = new ArrayList<FormItem>();
		Record selectedDepartment = departmentInfoTreeGrid.getSelectedRecord();
		Record selectedEnterprise = departmentInfoTreeGrid.getEnterpriseInfoTree().getSelectedRecord();
		
		HiddenItem hdnId = new HiddenItem("id");
		hdnId.setValue(selectedDepartment.getAttribute("id"));
		itemList.add(hdnId);
		//部门名称
		TextItem txtDepartment = new TextItem("department");
		txtDepartment.setValue(selectedDepartment.getAttribute("department"));
		txtDepartment.setHint(requiredHint);
		txtDepartment.setRequired(true);
		itemList.add(txtDepartment);
		//所属企业
		final SelectItem sltEnterpriseId = new SelectItem("enterpriseId");
		sltEnterpriseId.setOptionDataSource(departmentInfoTreeGrid.getEnterpriseInfoTree().getDataSource());
		sltEnterpriseId.setValueField("id");
		sltEnterpriseId.setDisplayField("abbreviation");
		sltEnterpriseId.setValue(selectedDepartment.getAttribute("enterpriseId"));
		sltEnterpriseId.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				formBase.clearValue("parentId");
			}
		});
		sltEnterpriseId.setDefaultToFirstOption(true);
		itemList.add(sltEnterpriseId);
		//上级部门
		final SelectItem sltParentId = new SelectItem("parentId"){
			@Override
			protected Criteria getPickListFilterCriteria() {
				String enterpriseId = (String)sltEnterpriseId.getValue();
				Criteria criteria = new Criteria("enterpriseId",enterpriseId);
				criteria.addCriteria("deleted",false);
				return criteria;
			}
		};
		sltParentId.setOptionDataSource(departmentInfoTreeGrid.getDataSource());
		sltParentId.setDisplayField("department");
		sltParentId.setRequired(false);
		sltParentId.setAddUnknownValues(true);
		sltParentId.setAllowEmptyValue(true);
		sltParentId.setEmptyDisplayValue(ConstantsUtil.commonConstants.none());
		sltParentId.setValue(selectedDepartment.getAttribute("parentId"));
		itemList.add(sltParentId);
		//备注信息
		TextAreaItem txtRemark = new TextAreaItem("remark");  
		txtRemark.setValue(selectedDepartment.getAttribute("remark"));
		//txtRemark.setHint(requiredHint);
		txtRemark.setWidth(280);  
		txtRemark.setRowSpan(3);
		itemList.add(txtRemark);
		
		FormItem[] items = new FormItem[itemList.size()];
	    itemList.toArray(items);
	    formBase.setFields(items);
	    
	    //按钮条
	    HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(40);
	    
	    IButton saveButton = new IButton();
	    saveButton.setIcon("icons/save.png"); 
	    saveButton.setWidth(80);
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.setAlign(Alignment.CENTER);
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(formBase.validate()){
				formBase.saveData();
				 SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (value.equals(true)) {
								//SC.say("删除成功！！");
								ShowWindowTools.showCloseWindow(srcRect, DepartmentInfoModifyWindow.this, -1);
							}
						}
					});
				
				}
			}
		});  
	    
	    
	    IButton cancelButton = new IButton();
	    cancelButton.setIcon("icons/remove.png");  
	    cancelButton.setTitle(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				formBase.reset();
			}
		});
	    
	    IButton helpButton = new IButton();
	    helpButton.setIcon("icons/book_help.png");  
	    helpButton.setTitle(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
	    
	    buttonPanel.addMember(saveButton);
	    buttonPanel.addMember(cancelButton);
	    buttonPanel.addMember(helpButton);
	    
	    HLayout tileGrid = new HLayout();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");
		//tileGrid.setTileWidth(150);
		//tileGrid.setTileHeight(150);
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addChild(formBase);
		
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("10%");
		//buttonGrid.setTileWidth(100);
		//buttonGrid.setTileHeight(100);	
		buttonGrid.setBorder("0px solid #9C9C9C");
		buttonGrid.addChild(buttonPanel);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid);
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
}
