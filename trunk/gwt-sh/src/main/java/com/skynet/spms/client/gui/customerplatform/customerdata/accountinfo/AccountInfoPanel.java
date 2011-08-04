package com.skynet.spms.client.gui.customerplatform.customerdata.accountinfo;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.organization.user.IDCardEditPanel;
import com.skynet.spms.client.gui.basedatamanager.organization.user.UserInfoEditPanel;
import com.skynet.spms.client.gui.basedatamanager.organization.user.UserManagerButtonToolBar;
import com.skynet.spms.client.gui.basedatamanager.organization.user.UserManagerListgrid;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class AccountInfoPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "用户管理信息维护页面";
	private String currentUsername;
	private ToolStrip userManagerToolBar;
	private UserManagerListgrid userManagerListgrid;
	private UserInfoEditPanel userInfoEditPanel;
	private VLayout mainPanelLayout;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "用户管理模块";
		private String id;

		public Canvas create() {
			AccountInfoPanel panel = new AccountInfoPanel();
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
		//获取用户
		currentUsername = UserTools.getCurrentUserName();
		// 设置Feature
		String custModuleName = "customerplat.customerData.accountInfo";
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
		userManagerListgrid.setComacUser(false);
		userManagerToolBar = new UserManagerButtonToolBar(userManagerListgrid,custModuleName);
		
		barGridLayout.addMember(userManagerToolBar);
		barGridLayout.addMember(userManagerListgrid);

		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(final DataSource dataSource,final DataInfo dataInfo) {
				//根据用户名获取用户记录
				Criteria criteria = new Criteria("username",currentUsername);
				dataSource.fetchData(criteria, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						Record record = response.getData()[0];
						//获取用户所属客户企业ID
						String customerId = record.getAttribute("m_BaseEnterpriseInformation.id");
						userManagerListgrid.setEnterpriseId(customerId);
						userManagerListgrid.setDataInfo(dataInfo);
						userManagerListgrid.setDataSource(dataSource);
						Criteria c = new Criteria("m_BaseEnterpriseInformation.id",customerId);
						c.addCriteria("filter", "filter");
						userManagerListgrid.fetchData(c);
						userManagerListgrid.drawUserManagerListgrid();
						userManagerListgrid.getField("jobNumber").setCanFilter(false);
						userManagerListgrid.getField("m_BaseEnterpriseInformation.abbreviation").setCanFilter(false);
						userManagerListgrid.getField("m_DepartmentInformation.department").setCanFilter(false);
						userManagerListgrid.getField("m_Duty.dutyName").setCanFilter(false);
					}
				});
				
			}
		});
		
		// ------------------tabs主容器--------
		final TabSet childNodeTab = new TabSet();
		childNodeTab.setHeight("60%");
		
		//用户信息选项卡
		Tab userInfoTab = new Tab(ConstantsUtil.organizationConstants.userInfo(),"pieces/16/star_green.png");
		userInfoEditPanel = new UserInfoEditPanel();
		userInfoTab.setPane(userInfoEditPanel);
		childNodeTab.addTab(userInfoTab);
		
		userManagerListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				c.addCriteria("m_BaseEnterpriseInformation.id", userManagerListgrid.getEnterpriseId());
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
		mainPanelLayout.addMember(childNodeTab);
		return mainPanelLayout;

	}
	private void refreshTab(Tab selectedTab, Record selectedRecord){
		Canvas panel = selectedTab.getPane();
		
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
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
