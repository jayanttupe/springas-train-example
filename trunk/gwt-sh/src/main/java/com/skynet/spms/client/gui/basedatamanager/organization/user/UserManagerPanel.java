package com.skynet.spms.client.gui.basedatamanager.organization.user;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class UserManagerPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "用户管理信息维护页面";

	private ToolStrip userManagerToolBar;
	private UserManagerListgrid userManagerListgrid;
	private UserInfoEditPanel userInfoEditPanel;
	private RoleInfoPanel roleInfoPanel; 
	private IDCardEditPanel idCardEditPanel;
	private VLayout mainPanelLayout;
	private Label labelUser;
	 
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "用户管理模块";
		private String id;

		public Canvas create() {
			UserManagerPanel panel = new UserManagerPanel();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}

	}

	public Canvas getViewPanel() {
		// 设置数据源
		String modeName = "organization.userinfo";
		String dsName = "user_dataSource";

		// 主Layout
		mainPanelLayout = new VLayout();
		
		VLayout barGridLayout = new VLayout();
		barGridLayout.setHeight("40%");
		barGridLayout.setShowResizeBar(true);
		barGridLayout.setLayoutTopMargin(0);

		// 主列表面板
		userManagerListgrid = new UserManagerListgrid();
		userManagerListgrid.setComacUser(true);
		userManagerToolBar = new UserManagerButtonToolBar(userManagerListgrid,modeName);
		
		barGridLayout.addMember(userManagerToolBar);
		barGridLayout.addMember(userManagerListgrid);

		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				userManagerListgrid.setDataInfo(dataInfo);
				userManagerListgrid.setDataSource(dataSource);
				userManagerListgrid.fetchData();
				userManagerListgrid.drawUserManagerListgrid();
			}
		});
		
		//中间的所选企业，所选部门提示
		HLayout tipLayout = new HLayout(5);
		tipLayout.setHeight(20);
		
		Label labelUserTip = new Label("所选用户：");
		labelUserTip.setWidth(60);
			
		labelUser = new Label();
		labelUser.setWidth(150);
		tipLayout.addMember(labelUserTip);
		tipLayout.addMember(labelUser);

		
		// ------------------tabs主容器--------
		final TabSet childNodeTab = new TabSet();
		childNodeTab.setHeight("60%");
		
		//用户信息选项卡
		Tab userInfoTab = new Tab(ConstantsUtil.organizationConstants.userInfo(),"pieces/16/star_green.png");
		userInfoEditPanel = new UserInfoEditPanel();
		userInfoTab.setPane(userInfoEditPanel);
		childNodeTab.addTab(userInfoTab);
		//角色信息
		Tab roleTab = new Tab(ConstantsUtil.organizationConstants.roleCard(),"pieces/16/star_green.png");
		roleInfoPanel = new RoleInfoPanel();
		roleTab.setPane(roleInfoPanel);
		childNodeTab.addTab(roleTab);
		
		//身份识别卡选项卡
		Tab idCardTab = new Tab(ConstantsUtil.organizationConstants.idCard(),"pieces/16/star_green.png");
		idCardEditPanel = new IDCardEditPanel();
		idCardTab.setPane(idCardEditPanel);
		childNodeTab.addTab(idCardTab);
		
		
		userManagerListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				c.addCriteria("filter","filter");
				userManagerListgrid.fetchData(c,new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						userManagerListgrid.selectRecords(userManagerListgrid.getSelection(), false);
						Tab selectedTab = childNodeTab.getSelectedTab();
						ListGridRecord selectedRecord = userManagerListgrid.getSelectedRecord();
						refreshTab(selectedTab, selectedRecord);
					}
				});
			}
		});
		userManagerListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					userManagerListgrid.selectRecords(userManagerListgrid.getSelection(), false);
					userManagerListgrid.selectRecord(selectedRecord);
				}else if(userManagerListgrid.getSelection().length == 1){
					selectedRecord = userManagerListgrid.getSelection()[0];
					userManagerListgrid.scrollToRow(userManagerListgrid.getRecordIndex(selectedRecord));
				}
				
				Tab selectedTab = childNodeTab.getSelectedTab();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		childNodeTab.addTabSelectedHandler(new TabSelectedHandler() {
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = childNodeTab.getSelectedTab();
				ListGridRecord selectedRecord = userManagerListgrid.getSelectedRecord();
				refreshTab(selectedTab, selectedRecord);
			}
		});

		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(tipLayout);
		mainPanelLayout.addMember(childNodeTab);
		return mainPanelLayout;

	}
	private void refreshTab(Tab selectedTab, Record selectedRecord){
		Canvas panel = selectedTab.getPane();
		
		if(userManagerListgrid.getSelection().length == 1){
			String abbreviation = selectedRecord.getAttribute("username");
			labelUser.setContents(abbreviation);
		}else{
			labelUser.setContents("");
		}
		//用户信息
		if(panel instanceof UserInfoEditPanel){
			UserInfoEditPanel uiePanel = (UserInfoEditPanel)panel;
			if(userManagerListgrid.getSelection().length == 1){
				String userInfoId = selectedRecord.getAttribute("m_UserInformation.id");
				uiePanel.fetchData(userInfoId);
			}else{
				uiePanel.clearFormValues();
			}
		}
		//角色信息
		if(panel instanceof RoleInfoPanel){
			RoleInfoPanel rolePanel = (RoleInfoPanel)panel;
			if(userManagerListgrid.getSelection().length == 1){
				String userId = selectedRecord.getAttribute("id");
				rolePanel.fetchData(userId);
			}else{
				rolePanel.clearData();
			}
		}
		
		//身份识别卡
		if(panel instanceof IDCardEditPanel){
			IDCardEditPanel idcePanel = (IDCardEditPanel)panel;
			if(userManagerListgrid.getSelection().length == 1){
				String cardId = selectedRecord.getAttribute("m_IDCard.id");
				idcePanel.fetchData(cardId);
			}else{
				idcePanel.clearFormValues();
			}
		}
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
