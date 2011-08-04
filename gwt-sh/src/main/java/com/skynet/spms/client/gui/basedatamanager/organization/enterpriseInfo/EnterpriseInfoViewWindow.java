package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class EnterpriseInfoViewWindow extends Window {

	private DetailViewer detailView; 
	
	public EnterpriseInfoViewWindow(final EnterpriseInfoTreeGrid enterpriseInfoTreeGrid){
		
		setWidth(930);
		setHeight(570);
		setTitle("查看企业信息");
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
		detailView.setDataSource(enterpriseInfoTreeGrid.getDataSource());
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
