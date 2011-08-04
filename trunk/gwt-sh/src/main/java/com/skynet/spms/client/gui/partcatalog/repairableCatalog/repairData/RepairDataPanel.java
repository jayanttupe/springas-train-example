package com.skynet.spms.client.gui.partcatalog.repairableCatalog.repairData;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RepairDataPanel extends VLayout{
	public RepairDataListGrid repairDataListGrid;
	public RepairDataPanel() {
		drawrepairData();
	}
	
	public void drawrepairData() {  
		
		repairDataListGrid = new RepairDataListGrid();
        this.addMember(repairDataListGrid);
    	
		HLayout buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		final IButton newButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		newButton.setIcon("icons/add_list.png");
		newButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(repairDataListGrid.getPartIndexId() == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectIndex());
					return;
				}
				RepairDataAddWindow repairDataAddWindow = new RepairDataAddWindow(
						ConstantsUtil.partCatalogConstants.addRepairData(), false,
						newButton.getPageRect(), repairDataListGrid, "showwindow/part_add_01.png");
				ShowWindowTools.showWondow(newButton.getPageRect(), repairDataAddWindow, -1);
				
			}
		});
		
		final IButton saveButton = new IButton(ConstantsUtil.buttonConstants.editButton());
		saveButton.setIcon("icons/edit_form.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (repairDataListGrid.getSelection().length != 1) {
					Window.alert(ConstantsUtil.commonConstants.alertSelectForModify());
				}else{
					RepairDataModifyWindow repairDataModifyWindow = new RepairDataModifyWindow(
							ConstantsUtil.partCatalogConstants.modifyRepairData(), false,
							saveButton.getPageRect(), repairDataListGrid, "showwindow/part_modify_01.png");
					ShowWindowTools.showWondow(saveButton.getPageRect(), repairDataModifyWindow, -1);
				}
			}
		});
		
		/*IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//applicationDataListGrid.discardAllEdits();
			}
		});*/
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if (repairDataListGrid.getSelection().length != 0) {
					boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
					if (isDel)
						repairDataListGrid.removeSelectedData();
				} else {
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}
			}
		});
		
		/*IButton helpButton = new IButton(ConstantsUtil.buttonConstants.viewButton());
		IButton repaiRauthorizationButton = new IButton("维修授权");*/
		
		buttonPanel.addMember(newButton);
		buttonPanel.addMember(saveButton);
		//buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(deleteButton);
		/*buttonPanel.addMember(helpButton);
		buttonPanel.addMember(repaiRauthorizationButton);*/
		
		this.addMember(buttonPanel);
	
	}
	
	public void fetchData(String partIndexId){
		repairDataListGrid.setPartIndexId(partIndexId);
		Criteria criteria = new Criteria("partIndexId", partIndexId);
		repairDataListGrid.fetchData(criteria);
	}
	public void clearData(){
		repairDataListGrid.setPartIndexId(null);
		Criteria criteria = new Criteria("partIndexId", "clear");
		repairDataListGrid.fetchData(criteria);
	}
	
}
