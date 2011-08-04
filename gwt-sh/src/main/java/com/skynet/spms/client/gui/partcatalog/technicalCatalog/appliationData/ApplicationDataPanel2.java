package com.skynet.spms.client.gui.partcatalog.technicalCatalog.appliationData;


import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
public class ApplicationDataPanel2 extends VLayout {
	
	private ApplicationDataListGrid applicationDataListGrid;
	private HLayout buttonPanel;
	public ApplicationDataPanel2(final boolean editable) {
		
		applicationDataListGrid = new ApplicationDataListGrid(editable);
		
		this.addMember(applicationDataListGrid);
    	
     	buttonPanel = new BtnsHLayout();
     	buttonPanel.setHeight(30);
		final IButton newButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		newButton.setIcon("icons/add_list.png");
		newButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(applicationDataListGrid.getPartIndexId() == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectIndex());
					return;
				}
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("partIndexId", applicationDataListGrid.getPartIndexId()	);
				applicationDataListGrid.startEditingNew(map);	
			}
		});
		
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				applicationDataListGrid.saveAllEdits();
			}
		});	
		
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				applicationDataListGrid.discardAllEdits();
			}
		});
		
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(applicationDataListGrid.getSelection().length == 0){
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}else{
					boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
					if (isDel){
						applicationDataListGrid.removeSelectedData();
				     } 
				}
				
		}});
	
		buttonPanel.addMember(newButton);  
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(deleteButton);
		this.addMember(buttonPanel);
		
		if(!editable){
			buttonPanel.setVisible(false);
		}
	}
	
	public void fetchData(String partIndexId){
		applicationDataListGrid.setPartIndexId(partIndexId);
		Criteria criteria = new Criteria("partIndexId",partIndexId);
		applicationDataListGrid.fetchData(criteria);
		
	}
	public void clearData(){
		applicationDataListGrid.setPartIndexId(null);
		//Criteria criteria = new Criteria("partIndexId","clear");
		//applicationDataListGrid.fetchData(criteria);
		applicationDataListGrid.setData(new Record[0]);
	}

}