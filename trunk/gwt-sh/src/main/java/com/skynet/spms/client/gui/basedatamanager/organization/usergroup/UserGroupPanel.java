package com.skynet.spms.client.gui.basedatamanager.organization.usergroup;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserGroupPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "用户组管理信息数据维护";
	
    private UserGroupButtonPanl userGroupButtonPanl;  
    private UserGroupListgrid userGroupListgrid;
    
    //主Layout
    private HLayout mainPanelLayout;

    
  
    public static class Factory implements PanelFactory {
    	
    	private String DESCRIPTION = "用户组管理模块";
        private String id;

        public Canvas create() {
        	UserGroupPanel panel = new UserGroupPanel();
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
	  	//获取数据源
	  	String moduleName = "organization.userGroup";
	  	String dsName = "userGroup_dataSource";
	    //主Layout
		mainPanelLayout = new HLayout();
		
		VLayout barGridLayout = new VLayout();
		barGridLayout.setHeight("100%");
		barGridLayout.setShowResizeBar(true);
		
		//数据表格
        userGroupListgrid = new UserGroupListgrid();
        //按钮面板（增删改查等按钮）
        userGroupButtonPanl = new UserGroupButtonPanl(userGroupListgrid);
        
        barGridLayout.addMember(userGroupButtonPanl);
		barGridLayout.addMember(userGroupListgrid);
        
        DataSourceTool dataSourceTool = new DataSourceTool();
        dataSourceTool.onCreateDataSource(moduleName, dsName, new PostDataSourceInit() {	
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				userGroupListgrid.setDataSource(dataSource);
				userGroupListgrid.fetchData();
				userGroupListgrid.drawUserGroupListgrid();
			}
		});
        
        userGroupListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria c = event.getCriteria();
				c.addCriteria("filter","filter");
				userGroupListgrid.fetchData(c);
			}
		});
        userGroupListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					userGroupListgrid.selectRecords(userGroupListgrid.getSelection(), false);
					userGroupListgrid.selectRecord(selectedRecord);
				}else if(userGroupListgrid.getSelection().length == 1){
					selectedRecord = userGroupListgrid.getSelection()[0];
					userGroupListgrid.scrollToRow(userGroupListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
        
	    mainPanelLayout.addMember(barGridLayout);
		
	 return mainPanelLayout;
  }


   public String getIntro() {
        return DESCRIPTION;
   }

}
