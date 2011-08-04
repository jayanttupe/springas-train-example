package com.skynet.spms.client.gui.basedatamanager.organization.department;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class DutyPanel extends VLayout{
	private HLayout buttonPanel;
	private ListGrid dutyListGrid;
	//private DynamicForm editorForm;
	private String departmentId;
	
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public void fetchData(String departmentId){
		this.departmentId = departmentId;
		Criteria criteria = new Criteria("departmentId", departmentId);
		dutyListGrid.fetchData(criteria);
	}
	public void clearData(){
		//上部分无选择，清空当前数据；
		departmentId=null;
		dutyListGrid.setData(new Record[0]);
	}
	public DutyPanel() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise.department", "duty_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				createDutyListGrid(dataSource);
				//initPanel(dataSource);
			}
		});
	}
		
	public void createDutyListGrid(DataSource dataSource){
		dutyListGrid = new ListGrid();
		//dutyListGrid.setShowFilterEditor(true);
		dutyListGrid.setDataSource(dataSource);
		dutyListGrid.setCanEdit(true);
		dutyListGrid.setAutoSaveEdits(false);
		dutyListGrid.setSelectionType(SelectionStyle.SIMPLE);
		dutyListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		dutyListGrid.setCellHeight(22); 
		dutyListGrid.setShowRowNumbers(true);
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		ListGridField lgfDutyName = new ListGridField("dutyName");
		fieldList.add(lgfDutyName);
		ListGridField lgfDutyLevel = new ListGridField("dutyLevel");
		fieldList.add(lgfDutyLevel);
		ListGridField lgfDutyDescription = new ListGridField("dutyDescription");
		fieldList.add(lgfDutyDescription);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
		dutyListGrid.setFields(fields);
		
		dutyListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=1){
					dutyListGrid.selectRecords(dutyListGrid.getSelection(), false);
					dutyListGrid.selectRecord(selectedRecord);
				}
				}
		});
		
		buttonPanel = new BtnsHLayout();
     	buttonPanel.setHeight(30);
		final IButton newButton = new IButton(ConstantsUtil.buttonConstants.newButton());	
		newButton.setIcon("icons/add_list.png");
		newButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(departmentId == null){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
					return;
				}
				
				//dutyListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("departmentId",departmentId);
				dutyListGrid.startEditingNew(map);	
							
			};
		});
		
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			/*public void onClick(ClickEvent event) {
				dutyListGrid.saveAllEdits();
				SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess());
			}
		});	*/
		public void onClick(ClickEvent event) {
			if(departmentId == null){
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}else{
				dutyListGrid.saveAllEdits(new Function(){

					@Override
					public void execute() {
						SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess());
						
					}
				});
			}	
		}
	});
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dutyListGrid.discardAllEdits();				
			}
		});
		
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(dutyListGrid.getSelection().length == 0){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}else{
					SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
	                    public void execute(Boolean value) {  
	                        if (value != null && value) {  
	                           SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());  
	                           dutyListGrid.removeSelectedData();
	                           //删除表单
	                        } else {  
	                           // labelAnswer.setContents("Cancel");  
	                        }  
	                    }  
	                }); 
								}	
			}
		});
	
		buttonPanel.addMember(newButton);  
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(deleteButton);
		this.addMember(dutyListGrid);
		this.addMember(buttonPanel);
	
	}
}

