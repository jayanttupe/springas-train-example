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

public class ConsigneeAddressPanel extends VLayout{
	private HLayout buttonPanel;
	private ListGrid consigneeAddressListGrid;
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
		consigneeAddressListGrid.fetchData(criteria);
		
	}
	
	public void clearData(){
		this.enterpriseId = null;
		consigneeAddressListGrid.setData(new Record[0]);
			}
	public ConsigneeAddressPanel() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise", "consigneeAddress_dataSource", new PostDataSourceInit() {
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
		
		createConsigneeAddressListGrid(dataSource);
		addMember(consigneeAddressListGrid);
	}
	
	public void createConsigneeAddressListGrid(DataSource dataSource){
		consigneeAddressListGrid = new ListGrid();
		consigneeAddressListGrid.setDataSource(dataSource);
		consigneeAddressListGrid.setCanEdit(true);
		consigneeAddressListGrid.setAutoSaveEdits(false);
		consigneeAddressListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		consigneeAddressListGrid.setSelectionType(SelectionStyle.SIMPLE);
		consigneeAddressListGrid.setCellHeight(22); 
		consigneeAddressListGrid.setShowRowNumbers(true);
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		/*ListGridField lgfItemNumber = new ListGridField("itemNumber");
		fieldList.add(lgfItemNumber);*/
		ListGridField lgfAddress = new ListGridField("address");
		fieldList.add(lgfAddress);
		ListGridField lgfPostCode = new ListGridField("postCode");
		fieldList.add(lgfPostCode);
		ListGridField lgfIsDefault = new ListGridField("default");
		fieldList.add(lgfIsDefault);
		ListGridField lgfConsignor = new ListGridField("recipient");
		fieldList.add(lgfConsignor);
		ListGridField lgfContactMan = new ListGridField("contactMan");
		fieldList.add(lgfContactMan);
		ListGridField lgfContactWay = new ListGridField("contactWay");
		fieldList.add(lgfContactWay);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
		consigneeAddressListGrid.setFields(fields);
		
		consigneeAddressListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=1){
					consigneeAddressListGrid.selectRecords(consigneeAddressListGrid.getSelection(), false);
					consigneeAddressListGrid.selectRecord(selectedRecord);
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
					//hdnEnterpriseId.setValue(enterpriseId);
					//dutyListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
					Map<String, String> map = new LinkedHashMap<String, String>();
					map.put("enterpriseId", enterpriseId);
					consigneeAddressListGrid.startEditingNew(map);	
								
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
					consigneeAddressListGrid.saveAllEdits(new Function(){

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
					consigneeAddressListGrid.discardAllEdits();				
				}
			});
			
			final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
			deleteButton.setIcon("icons/delete_list.png");
			deleteButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if(consigneeAddressListGrid.getSelection().length == 0){
						SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
					}else{
						SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
		                    public void execute(Boolean value) {  
		                        if (value != null && value) {  
		                           SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());  
		                           consigneeAddressListGrid.removeSelectedData();
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
			this.addMember(consigneeAddressListGrid);
			this.addMember(buttonPanel);
	}
}
	/*private VLayout createFormLayout(DataSource dataSource) {
		editorForm = new DynamicForm();
		editorForm.setDataSource(dataSource);
		editorForm.setIsGroup(true);
		editorForm.setGroupTitle(ConstantsUtil.commonConstants.editFormTitle());
		 String requiredHint = "<font color=red>*</font>";
		
		List<FormItem> itemList = new ArrayList<FormItem>();
		
		final HiddenItem hdnEnterpriseId = new HiddenItem("enterpriseId");
		hdnEnterpriseId.setValue(enterpriseId);
		itemList.add(hdnEnterpriseId);
		
		HiddenItem hdnId = new HiddenItem("id");
		itemList.add(hdnId);
       //项号
		TextItem txtItemNumber = new TextItem("itemNumber");
		txtItemNumber.setHint(requiredHint);
		txtItemNumber.setRequired(true);
		itemList.add(txtItemNumber);
		//详细地址
		TextItem txtAddress = new TextItem("address");
		txtAddress.setHint(requiredHint);
		txtAddress.setRequired(true);
		itemList.add(txtAddress);
		//邮政编码
		TextItem txtPostCode = new TextItem("postCode");
		txtPostCode.setHint(requiredHint);
		txtPostCode.setRequired(true);
		txtPostCode.setValidateOnExit(true);
		txtPostCode.setValidators(ValidateUtils.ZipCodeValidator(txtPostCode.getValueAsString()));
		itemList.add(txtPostCode);
		//是否默认地址
		SelectItem sltIsDefault = new SelectItem("default");
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		valueMap.put("true", ConstantsUtil.commonConstants.choiceYes());
		valueMap.put("false", ConstantsUtil.commonConstants.choiceNo());
		sltIsDefault.setValueMap(valueMap);
		sltIsDefault.setDefaultValue("false");
		itemList.add(sltIsDefault);
		//默认发货人
		TextItem txtRecipient = new TextItem("recipient");
		txtRecipient.setHint(requiredHint);
		txtRecipient.setRequired(true);
		itemList.add(txtRecipient);
		//联系人
		TextItem txtContactMan = new TextItem("contactMan");
		itemList.add(txtContactMan);
		//联系方式
		TextItem txtContactWay = new TextItem("contactWay");
		itemList.add(txtContactWay);
		
		FormItem[] items = new FormItem[itemList.size()];
		itemList.toArray(items);
		editorForm.setFields(items);
		
		IButton newButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		newButton.setIcon("icons/add_list.png");
		newButton.setWidth(60);
		newButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(enterpriseId == null){
					Window.alert(ConstantsUtil.organizationConstants.alertForSelectEnterprise());
					return;
				}
				editorForm.editNewRecord();
				hdnEnterpriseId.setValue(enterpriseId);
				editorForm.setGroupTitle(
						ConstantsUtil.commonConstants.editFormTitle() 
						+ " - "
						+ ConstantsUtil.buttonConstants.newButton());
			}
		});
	
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png"); 
		saveButton.setWidth(60);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(hdnEnterpriseId.getValue() == null){
					Window.alert(ConstantsUtil.organizationConstants.alertForSelectEnterprise());
					return;
				}
				editorForm.validate();
				editorForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						editorForm.setGroupTitle(
								ConstantsUtil.commonConstants.editFormTitle() 
								+ " - "
								+ ConstantsUtil.buttonConstants.modifyButton());
						consigneeAddressListGrid.selectRecords(consigneeAddressListGrid.getSelection(), false);
					}
				});
			}
		});

		IButton removeButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		removeButton.setIcon("icons/remove.png");  
		removeButton.setWidth(60);
		removeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(consigneeAddressListGrid.getSelection().length < 1){
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}else if(Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete())){
					consigneeAddressListGrid.removeSelectedData();
					editorForm.editNewRecord();
					hdnEnterpriseId.setValue(enterpriseId);
					editorForm.setGroupTitle(
							ConstantsUtil.commonConstants.editFormTitle() 
							+ " - "
							+ ConstantsUtil.buttonConstants.newButton());
				}
			}
		});

		VLayout editorLayout = new VLayout(5);
		editorLayout.setAlign(VerticalAlignment.TOP);
		editorLayout.addMember(editorForm);
		
		HLayout buttonPanel = new HLayout(10);
		buttonPanel.addMember(newButton);
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(removeButton);
		editorLayout.addMember(buttonPanel);
		editorLayout.setWidth(280);

		return editorLayout;
	}*/
//}
