package com.skynet.spms.client.gui.partcatalog.technicalCatalog.optionalData;


import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomSelectItem;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedEvent;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
public class OptionalDataPanel extends VLayout {
	
	private OptionalDataListGrid optionalDataListGrid;
	private SelectItem sltPartNumber;
	private HLayout buttonPanel;
	
	public OptionalDataPanel(final boolean editable) {
		//这个事用来做什么的？
		sltPartNumber = new CustomSelectItem(
				null, 
				"partCatalog.technical", 
				"partIndex_dataSource", 
				"manufacturerPartNumber", 
				"manufacturerPartNumber", 
				"manufacturerPartNumber",
				"partNumber");
		
		optionalDataListGrid = new OptionalDataListGrid(editable);	
		this.addMember(optionalDataListGrid);
		
		optionalDataListGrid.addRecordClickHandler(new RecordClickHandler() {	
			@Override
			public void onRecordClick(RecordClickEvent event) {
				optionalDataListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
				
			}
		});
		
     	buttonPanel = new BtnsHLayout();
     	buttonPanel.setHeight(30);
		final IButton newButton = new IButton(ConstantsUtil.buttonConstants.newButton());	
		newButton.setIcon("icons/add_list.png");
		newButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(optionalDataListGrid.getPartIndexId() == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectIndex());
					return;
				}
				
				optionalDataListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("partIndexId", optionalDataListGrid.getPartIndexId());
				optionalDataListGrid.startEditingNew(map);	
				
				/*DataSourceTool dataSourceTool = new DataSourceTool();
				dataSourceTool.onCreateDataSource("partCatalog.technical","partIndex_dataSource", new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
						
						sltPartNumber = new SelectItem();
						sltPartNumber.setOptionDataSource(dataSource);
						sltPartNumber.setValueField("manufacturerPartNumber");
						optionalDataListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
						
						Map<String, String> map = new LinkedHashMap<String, String>();
						map.put("partIndexId", optionalDataListGrid.getPartIndexId());
						optionalDataListGrid.startEditingNew(map);					
					}
				});*/
				
			};
		});
		
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				optionalDataListGrid.saveAllEdits();
			}
		});	
		
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				optionalDataListGrid.discardAllEdits();				
			}
		});
		
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(optionalDataListGrid.getSelection().length == 0){
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}else{
					boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
					if (isDel){
						optionalDataListGrid.removeSelectedData();
				     } 
				}	
			}
		});
	
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
		optionalDataListGrid.setPartIndexId(partIndexId);
		Criteria criteria = new Criteria("partIndexId",partIndexId);
		optionalDataListGrid.fetchData(criteria);
		
	}
	public void clearData(){
		optionalDataListGrid.setPartIndexId(null);
		optionalDataListGrid.setData(new Record[0]);
	}

}