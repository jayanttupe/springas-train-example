package com.skynet.spms.client.gui.basedatamanager.organization.usergroup;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
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
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserGroupModifyWindow extends BaseWindow {

	public UserGroupModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		UserGroupListgrid userGroupListgrid = (UserGroupListgrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, UserGroupModifyWindow.this, -1);
			}
		});
		
		final DynamicForm form = new DynamicForm();
		form.setDataSource(userGroupListgrid.getDataSource());
		form.setPadding(5);
		form.setWidth(400);
		form.setColWidths(100,300);
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		String required = "<font color=red>*</font>";
		Record record = userGroupListgrid.getSelectedRecord();
		
		//用户组ID
		HiddenItem hidGroupId = new HiddenItem("id");
		hidGroupId.setValue(record.getAttribute("id"));
		itemList.add(hidGroupId);
		//用户组名称 
		TextItem txtGroupName = new TextItem("groupName");
		txtGroupName.setWidth(200);
		txtGroupName.setHint(required);
		txtGroupName.setRequired(true);
		txtGroupName.setValue(record.getAttribute("groupName"));
		itemList.add(txtGroupName);
		
        //用户组描述 
		TextAreaItem txtDescription = new TextAreaItem("description");  
		txtDescription.setWidth(280);  
		txtDescription.setRowSpan(3);
		txtDescription.setValue(record.getAttribute("description"));
		txtDescription.setHint(required);
		txtDescription.setRequired(true);
		itemList.add(txtDescription);
		
		FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        
        form.setFields(items);
	    
      //按钮条
        HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(40);
        IButton saveButton = new IButton();
        saveButton.setIcon("icons/save.png"); 
        saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(form.validate()){
				form.saveData();
				
				SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							ShowWindowTools.showCloseWindow(srcRect, UserGroupModifyWindow.this, -1);
						}
					}
				});
				//ShowWindowTools.showCloseWindow(srcRect, UserGroupModifyWindow.this, -1);
			}
			  }
		});
	    
	    IButton cancelButton = new IButton();
	    cancelButton.setIcon("icons/remove.png"); 
	    cancelButton.setTitle(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.reset();
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
		tileGrid.addChild(form);
		
		HLayout  buttonGrid = new HLayout();
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
