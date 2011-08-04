package com.skynet.spms.client.gui.basedatamanager.organization.user;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.organization.role.RoleForAssignListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.layout.VLayout;

public class RoleInfoPanel extends VLayout {

	private RoleForAssignListgrid roleListGrid;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void fetchData(String userId){
		this.userId = userId;
		Criteria criteria = new Criteria("userId", userId);
		roleListGrid.fetchData(criteria);
		}
	public void clearData(){
		this.userId = null;
		roleListGrid.setData(new Record[0]);
	}
	public RoleInfoPanel() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.userGroup", "role_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				
				roleListGrid=new RoleForAssignListgrid();
				roleListGrid.setDataSource(dataSource);
				
				roleListGrid.drawRoleForAssignListgrid(false,false);
				addMember(roleListGrid);
			}
		});
	}
	
}

