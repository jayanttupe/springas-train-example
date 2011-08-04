package com.skynet.spms.client.gui.basedatamanager.organization.usergroup;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class UserGroupViewWindow extends Window {

	private DetailViewer detailView; 
	
	public UserGroupViewWindow(final UserGroupListgrid userGroupListgrid){
		
		setWidth(360);
		setTitle("查看用户");
		setShowMinimizeButton(false);
		setIsModal(true);
		setHeight(310);
		setShowModalMask(true);
		//setEdgeMarginSize(20);
		//setMembersMargin(20);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				destroy();
			}
		});
		
		detailView = new DetailViewer();
		detailView.setDataSource(userGroupListgrid.getDataSource());
		detailView.setShowComplexFields(true);
		detailView.setShowDetailFields(true);
		//detailView.setEdgeMarginSize(20);
		detailView.setMargin(20);
		ListGridRecord[] record = userGroupListgrid.getSelection();
        ListGridRecord[] showList = new ListGridRecord[record.length]; 

        detailView.setData(record);
        
		addItem(detailView);
		
	}
}
