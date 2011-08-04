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
import com.smartgwt.client.data.DataSourceField;
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

public class RecipeInvoiceAddressPanel extends VLayout{
	private HLayout buttonPanel;
	private ListGrid recipeInvoiceAddressListGrid;
	private String enterpriseId;
	
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public void fetchData(String enterpriseId){
		this.enterpriseId = enterpriseId;
		Criteria criteria = new Criteria("enterpriseId", enterpriseId);
		recipeInvoiceAddressListGrid.fetchData(criteria);
		
	}
	public void clearData(){
		this.enterpriseId = null;
		recipeInvoiceAddressListGrid.setData(new Record[0]);
		
	}
	public RecipeInvoiceAddressPanel() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise", "recipeInvoiceAddress_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				LinkedHashMap<String,String> valueMap = new LinkedHashMap<String,String>();
				valueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
				valueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
				DataSourceField isdefaultField = dataSource.getField("default");
				isdefaultField.setValueMap(valueMap);
				initPanel(dataSource);
			}
		});
	}
	private void initPanel(DataSource dataSource){
		setWidth100();
		setMembersMargin(5);
		this.setCanDrag(false);
		
		createRecipeInvoiceAddressListGrid(dataSource);
		addMember(recipeInvoiceAddressListGrid);
	}
	
	public void createRecipeInvoiceAddressListGrid(DataSource dataSource){
		recipeInvoiceAddressListGrid = new ListGrid();
		recipeInvoiceAddressListGrid.setDataSource(dataSource);
		recipeInvoiceAddressListGrid.setCanEdit(true);
		recipeInvoiceAddressListGrid.setAutoSaveEdits(false);
		recipeInvoiceAddressListGrid.setSelectionType(SelectionStyle.SIMPLE);
		recipeInvoiceAddressListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		recipeInvoiceAddressListGrid.setCellHeight(22); 
		recipeInvoiceAddressListGrid.setShowRowNumbers(true);
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		/*ListGridField lgfItemNumber = new ListGridField("itemNumber");
		fieldList.add(lgfItemNumber);*/
		ListGridField lgfAddress = new ListGridField("address");
		fieldList.add(lgfAddress);
		ListGridField lgfPostCode = new ListGridField("postCode");
		fieldList.add(lgfPostCode);
		ListGridField lgfIsDefault = new ListGridField("default");
		fieldList.add(lgfIsDefault);
		ListGridField lgfRecipient = new ListGridField("recipient");
		fieldList.add(lgfRecipient);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
		recipeInvoiceAddressListGrid.setFields(fields);
		
		recipeInvoiceAddressListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=1){
					recipeInvoiceAddressListGrid.selectRecords(recipeInvoiceAddressListGrid.getSelection(), false);
					recipeInvoiceAddressListGrid.selectRecord(selectedRecord);
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
				
				//dutyListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("enterpriseId",enterpriseId);
				recipeInvoiceAddressListGrid.startEditingNew(map);	
							
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
				recipeInvoiceAddressListGrid.saveAllEdits(new Function(){

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
				recipeInvoiceAddressListGrid.discardAllEdits();				
			}
		});
		
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(recipeInvoiceAddressListGrid.getSelection().length == 0){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}else{
					SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
	                    public void execute(Boolean value) {  
	                        if (value != null && value) {  
	                           SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());  
	                           recipeInvoiceAddressListGrid.removeSelectedData();
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
		this.addMember(recipeInvoiceAddressListGrid);
		this.addMember(buttonPanel);
	
	}
}
