package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

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
import com.smartgwt.client.types.DateDisplayFormat;
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

public class AirworthinessPanel extends VLayout{

	private ListGrid airworthinessListGrid;
	private HLayout buttonPanel;
	private String enterpriseId;
	private DataInfo dataInfo;
	
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public void fetchData(String enterpriseId){
		this.enterpriseId = enterpriseId;
		Criteria criteria = new Criteria("enterpriseId", enterpriseId);
		airworthinessListGrid.fetchData(criteria);
		}
	public void clearData(){
		this.enterpriseId = null;
		
		airworthinessListGrid.setData(new Record[0]);
		}
	public AirworthinessPanel() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise", "airworthinessInformation_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				AirworthinessPanel.this.dataInfo = dataInfo;
				initPanel(dataSource);
			}
		});
	}
	private void initPanel(DataSource dataSource){
		setWidth100();
		setMembersMargin(5);
		this.setCanDrag(false);
		
		createAirworthinessListGrid(dataSource);
		addMember(airworthinessListGrid);
		}
	
	public void createAirworthinessListGrid(DataSource dataSource){
		airworthinessListGrid = new ListGrid();
		airworthinessListGrid.setDataSource(dataSource);
		airworthinessListGrid.setCanEdit(true);
		airworthinessListGrid.setAutoSaveEdits(false);
		airworthinessListGrid.setSelectionType(SelectionStyle.SIMPLE);
		airworthinessListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		airworthinessListGrid.setCellHeight(22); 
		airworthinessListGrid.setShowRowNumbers(true);
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		//授权编号
		ListGridField lgfAirworthinessNumber = new ListGridField("airworthinessNumber");
		fieldList.add(lgfAirworthinessNumber);
		//签署日期
		ListGridField lgfSigningDate = new ListGridField("signingDate");
		lgfSigningDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		fieldList.add(lgfSigningDate);
		//有效日期
		ListGridField lgfExpiryDate = new ListGridField("expiryDate");
		lgfExpiryDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		fieldList.add(lgfExpiryDate);
		//授权机构
		ListGridField lgfAirworthinessOrg = new ListGridField("m_AirworthinessOrg");
		fieldList.add(lgfAirworthinessOrg);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
		airworthinessListGrid.setFields(fields);
		
		airworthinessListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=1){
					airworthinessListGrid.selectRecords(airworthinessListGrid.getSelection(), false);
					airworthinessListGrid.selectRecord(selectedRecord);
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
				if(enterpriseId == null){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
					return;
				}
				
				//airworthinessListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("enterpriseId",enterpriseId);
				airworthinessListGrid.startEditingNew(map);	
							
			};
		});
		
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			if(enterpriseId == null){
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}else{
				airworthinessListGrid.saveAllEdits(new Function(){

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
				airworthinessListGrid.discardAllEdits();				
			}
		});
		
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(airworthinessListGrid.getSelection().length == 0){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}else{
					SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
	                    public void execute(Boolean value) {  
	                        if (value != null && value) {  
	                           SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());  
	                           airworthinessListGrid.removeSelectedData();
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
		this.addMember(airworthinessListGrid);
		this.addMember(buttonPanel);
	}
}
