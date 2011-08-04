package com.skynet.spms.client.gui.basedatamanager.organization.role;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RoleModifyQuotaWindow extends BaseWindow {

	private RoleForAssignListgrid roleListGrid;
	
	public RoleModifyQuotaWindow(String windowTitle, boolean isMax, Rectangle srcRect, String iconUrl) {
		super(windowTitle, isMax, srcRect, null, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, RoleModifyQuotaWindow.this, -1);
			}
		});

		// 设置数据源
		String moduleName = "organization.userGroup";
		String dsName = "role_dataSource";
		roleListGrid = new RoleForAssignListgrid();
		roleListGrid.setHeight100();
		roleListGrid.setWidth100();
		
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName, dsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				roleListGrid.setDataSource(dataSource);
				roleListGrid.fetchData();
				roleListGrid.drawRoleForAssignListgrid(true, true);
			}
		});
		
		roleListGrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				c.addCriteria("filter","filter");
				roleListGrid.fetchData(c);
			}
		});

        //按钮条
		HLayout buttonPanel = new BtnsHLayout();
     	buttonPanel.setHeight(24);
		
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				roleListGrid.saveAllEdits();
				
				SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							ShowWindowTools.showCloseWindow(srcRect, RoleModifyQuotaWindow.this, -1);
						}
					}
				});
			}
		});	
		
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				roleListGrid.discardAllEdits();
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
	    
	    VLayout tileGrid = new VLayout();
	    tileGrid.setPadding(5);
		tileGrid.setWidth100();
		tileGrid.setHeight("92%");
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addMember(roleListGrid);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("8%");
		buttonGrid.setBorder("0px solid #9C9C9C");
		buttonGrid.addMember(buttonPanel);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
}
