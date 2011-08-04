package com.skynet.spms.client.gui.portal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.basedatamanager.organization.user.UserManagerListgrid;
import com.skynet.spms.client.service.UserMessageService;
import com.skynet.spms.client.service.UserMessageServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LeaveMsgWindow extends BaseWindow {

	private UserMessageServiceAsync userMessageService = GWT.create(UserMessageService.class);
	private UserManagerListgrid userManagerListgrid;
	public LeaveMsgWindow(String windowTitle, boolean isMax, Rectangle srcRect,
			 String iconUrl) {
		super(windowTitle, isMax, srcRect, null, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, LeaveMsgWindow.this, -1);
			}
		});
		
		// 设置数据源
		String modeName = "organization.userinfo";
		String dsName = "user_dataSource";
		userManagerListgrid = new UserManagerListgrid();
		userManagerListgrid.setHeight("45%");
		userManagerListgrid.setWidth100();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				userManagerListgrid.setDataInfo(dataInfo);
				userManagerListgrid.setDataSource(dataSource);
				userManagerListgrid.fetchData();
				userManagerListgrid.drawSimpleListgrid();
			}
		});
		
		userManagerListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				c.addCriteria("filter","filter");
				userManagerListgrid.fetchData(c);
			}
		});
		userManagerListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					userManagerListgrid.selectRecords(userManagerListgrid.getSelection(), false);
					userManagerListgrid.selectRecord(selectedRecord);
				}
			}
		});
		
		final DynamicForm form = new DynamicForm();
		form.setWidth100();
		form.setHeight("45%");
		form.setColWidths("100%",0);
		
		final TextAreaItem taiMsg = new TextAreaItem();
		taiMsg.setShowTitle(false);
		taiMsg.setWidth("100%");
		taiMsg.setHeight("100%");
        form.setFields(taiMsg);

        //按钮条
        HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(24);
        IButton saveButton = new IButton();
        saveButton.setIcon("icons/save.png");  
        saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(userManagerListgrid.getSelection().length != 1){
					Window.alert(ConstantsUtil.organizationConstants.alertForSelectUser());
					return;
				}
				String msg = (String)taiMsg.getValue();
				if(msg == null || msg.trim().equals("")){
					Window.alert(ConstantsUtil.organizationConstants.alertForInputMessage());
					return;
				}
				String username = userManagerListgrid.getSelectedRecord().getAttribute("username");
				userMessageService.addMessage(username, msg,new AsyncCallback<Void>() {		
					@Override
					public void onSuccess(Void arg0) {
						ShowWindowTools.showCloseWindow(srcRect, LeaveMsgWindow.this, -1);
					}
					@Override
					public void onFailure(Throwable arg0) {
						Window.alert(arg0.getLocalizedMessage());
					}
				});
				
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
	    
	    Label lblUsers = new Label();
	    lblUsers.setHeight(24);
	    lblUsers.setContents(ConstantsUtil.organizationConstants.userList());
	    Label lblMsg = new Label();
	    lblMsg.setHeight(24);
	    lblMsg.setContents(ConstantsUtil.organizationConstants.messageContent());
	    
	    VLayout tileGrid = new VLayout();
	    tileGrid.setPadding(5);
		tileGrid.setWidth100();
		tileGrid.setHeight("94%");
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addMember(lblUsers);
		tileGrid.addMember(userManagerListgrid);
		tileGrid.addMember(lblMsg);
		tileGrid.addMember(form);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("6%");
		buttonGrid.setBorder("0px solid #9C9C9C");
		buttonGrid.addMember(buttonPanel);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
}
