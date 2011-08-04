package com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog.attachment;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordDropEvent;
import com.smartgwt.client.widgets.grid.events.RecordDropHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AttachmentPanel extends VLayout{

	public ListGrid attachmentListGrid;
	public AttachmentPanel() {
		/*DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				// TODO Auto-generated method stub
				
			}
		});*/
		drawaccessory();
	}

	public void drawaccessory() {  
		
		attachmentListGrid = new ListGrid();
		//setDataSource(supplyItemDS); 
		//setUseAllDataSourceFields(true);
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		
		ListGridField lgfItemNumber = new ListGridField("itemNumber", "项号"); 
		lgfItemNumber.setCanEdit(false);
        fieldList.add(lgfItemNumber);
	
        
		ListGridField lgfTitle = new ListGridField("title", "标题"); 
		lgfTitle.setCanEdit(false);
        fieldList.add(lgfTitle);
	
		ListGridField lgfDescription = new ListGridField("description", "描述"); 
		lgfDescription.setCanEdit(false);
        fieldList.add(lgfDescription);
	
	
        
		ListGridField lgfFileName = new ListGridField("fileName", "文件名称"); 
		lgfFileName.setCanEdit(false);
        fieldList.add(lgfFileName);
        
		ListGridField lgfModifyDate = new ListGridField("modifyDate", "修改日期"); 
		lgfModifyDate.setCanEdit(false);
        fieldList.add(lgfModifyDate);
	
     
		ListGridField lgfModifyBy = new ListGridField("modifyBy", "修改人"); 
		lgfModifyBy.setCanEdit(false);
        fieldList.add(lgfModifyBy);
	
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);

        attachmentListGrid.setAutoFetchData(true);
        attachmentListGrid.setFields(fields);  
        attachmentListGrid.setShowFilterEditor(true);
        attachmentListGrid.setCanRemoveRecords(true);
        attachmentListGrid.setRemoveFieldTitle("删除");
        attachmentListGrid.addRecordDropHandler(new RecordDropHandler() {
			
			@Override
			public void onRecordDrop(RecordDropEvent event) {
				// TODO Auto-generated method stub
		         Window.alert("sssss");
				
			}
			
		});
        
        
        attachmentListGrid.setShowAllRecords(true);
        attachmentListGrid.setSelectionType(SelectionStyle.SIMPLE);  
        attachmentListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        //attachmentListGrid.setDataSource(indexInfoXmlDS);
        attachmentListGrid.setCanEdit(true);   
        attachmentListGrid.setCanDragRecordsOut(true); 
        attachmentListGrid.setShowAllRecords(true);
        attachmentListGrid.setHoverWidth(200);   
        attachmentListGrid.setHoverHeight(20); 
        attachmentListGrid. setCellHeight(22);   
        this.addMember(attachmentListGrid);
    	
        
        
        HLayout buttonPanel = new HLayout(10);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		IButton removeButton = new IButton("移除");
		
		IButton modifyButton = new IButton("修改");
		IButton showButton = new IButton("查看");
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(removeButton);
		buttonPanel.addMember(modifyButton);
		buttonPanel.addMember(showButton);
		this.addMember(buttonPanel);
    }  
	
}
