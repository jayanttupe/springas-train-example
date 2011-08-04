package com.skynet.spms.client.gui.basedatamanager.organization.department;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class DepartmentInfoPanel extends ShowcasePanel {

	 private static final String DESCRIPTION = "部门基础信息维护 ";
	 
	 private DepartmentInfoTreeGrid departmentInfoTreeGrid;
	 private DepartmentInfoButtonToolBar departmentInfoButtonToolBar;
	 private Label lblEnterprise;
	 private Label lblDepartment;
	 
	 public static class Factory implements PanelFactory {

	        private String id;

	        public Canvas create() {
	        	DepartmentInfoPanel panel = new DepartmentInfoPanel();
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
	
	@Override
	public Canvas getViewPanel() {
		// 主Layout
		VLayout mainPanelLayout = new VLayout();
		
		VLayout barGridLayout = new VLayout();
		barGridLayout.setHeight("40%");
		barGridLayout.setShowResizeBar(true);
	
		//数据表格
		departmentInfoTreeGrid = new DepartmentInfoTreeGrid();
		//按钮面板（增删改查等按钮）
		departmentInfoButtonToolBar = new DepartmentInfoButtonToolBar(
				"organization.enterprise.department",departmentInfoTreeGrid);
		
		barGridLayout.addMember(departmentInfoButtonToolBar);
		barGridLayout.addMember(departmentInfoTreeGrid.getGridLayout());
		
		//中间的所选企业，所选部门提示
		HLayout tipLayout = new HLayout(5);
		tipLayout.setHeight(20);
		
		Label lblEnterpriseTip = new Label("所选企业：");
		lblEnterpriseTip.setWidth(60);
		Label lblDeptTip = new Label("所选部门：");
		lblDeptTip.setWidth(60);
		
		lblEnterprise = new Label();
		lblEnterprise.setWidth(150);
		lblDepartment = new Label();
		lblDepartment.setWidth(150);
		tipLayout.addMember(lblEnterpriseTip);
		tipLayout.addMember(lblEnterprise);
		tipLayout.addMember(lblDeptTip);
		tipLayout.addMember(lblDepartment);
		
		final TabSet tabSet = new TabSet();
		final Tab dutyTab = new Tab(ConstantsUtil.organizationConstants.duty(),"pieces/16/star_green.png");
		final DutyPanel dutyPanel = new DutyPanel();
		dutyTab.setPane(dutyPanel);
		tabSet.addTab(dutyTab);
		
		departmentInfoTreeGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
			@Override
			public void onSelectionChanged(SelectionEvent event) {
					Record selectedRecord = departmentInfoTreeGrid.getSelectedRecord();
					Tab selectedTab = tabSet.getSelectedTab();
					refreshTab(selectedTab, selectedRecord);
			}
		});
		
		departmentInfoTreeGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				Record selectedRecord = event.getRecord();
				departmentInfoTreeGrid.selectRecords(departmentInfoTreeGrid.getSelection(), false);
				departmentInfoTreeGrid.selectRecord(selectedRecord);
				
			}
		});
		
		tabSet.addTabSelectedHandler(new TabSelectedHandler() {	
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = tabSet.getSelectedTab();
				Record selectedRecord = departmentInfoTreeGrid.getSelectedRecord();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		
		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(tipLayout);
		mainPanelLayout.addMember(tabSet);
		
		return mainPanelLayout;
	}
	
	private void refreshTab(Tab selectedTab, Record selectedRecord){
		Canvas panel = selectedTab.getPane();
		if(departmentInfoTreeGrid.getSelection().length == 1){
			lblDepartment.setContents(selectedRecord.getAttribute("department"));
		}else{
			lblDepartment.setContents("");
		}
		if(departmentInfoTreeGrid.getEnterpriseInfoTree().getSelection().length == 1){
			Record enterpriseRecord = departmentInfoTreeGrid.getEnterpriseInfoTree().getSelectedRecord();
			lblEnterprise.setContents(enterpriseRecord.getAttribute("abbreviation"));
		}else{
			lblEnterprise.setContents("");
		}
		
		if(panel instanceof DutyPanel){
			DutyPanel dp = (DutyPanel)panel;
			if(departmentInfoTreeGrid.getSelection().length == 1){
				dp.fetchData(selectedRecord.getAttribute("id"));
			}else{
				dp.clearData();
			}
			
		}
	}
	
    public String getIntro() {
        return DESCRIPTION;
    }


}
