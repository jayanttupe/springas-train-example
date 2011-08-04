package com.skynet.spms.client.gui.basedatamanager.organization.department;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class DepartmentInfoViewWindow extends Window {

	private DetailViewer detailView; 
	
	public DepartmentInfoViewWindow(final DepartmentInfoTreeGrid departmentInfoTreeGrid){
		
		setWidth(430);
		setHeight(270);
		setTitle("查看部门信息");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);

		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				destroy();
			}
		});
		
		detailView = new DetailViewer();
		detailView.setDataSource(departmentInfoTreeGrid.getDataSource());
		detailView.setShowComplexFields(true);
		detailView.setShowDetailFields(true);
		//detailView.setEdgeMarginSize(20);
		detailView.setMargin(20);
		/*ListGridRecord[] record = EnterpriseInforTreeGrid.getSelection();
		TreeGridField[] showList = new TreeGridField[record.length]; */

        //detailView.setData(record);
        
		addItem(detailView);
	      
	}
}
