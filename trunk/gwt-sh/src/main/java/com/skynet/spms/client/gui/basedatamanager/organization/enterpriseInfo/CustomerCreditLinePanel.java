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

public class CustomerCreditLinePanel extends VLayout{
    private HLayout buttonPanel;
	private ListGrid customerCreditLineListGrid;
	private String enterpriseId;
	private DataInfo dataInfo;
	
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public void fetchData(String enterpriseId){
		this.enterpriseId= enterpriseId;
		Criteria criteria = new Criteria("enterpriseId", enterpriseId);
		customerCreditLineListGrid.fetchData(criteria);
		}
	public void clearData(){
		this.enterpriseId = null;
		customerCreditLineListGrid.setData(new Record[0]);
		}
	public CustomerCreditLinePanel() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise", "customerCreditLine_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				CustomerCreditLinePanel.this.dataInfo = dataInfo;
				DataSourceField dsfStartDate = dataSource.getField("startDate");
				DataSourceField dsfExpiryDate = dataSource.getField("expiryDate");
				dsfStartDate.setDisplayFormat(DateDisplayFormat.TOJAPANSHORTDATE);
				dsfExpiryDate.setDisplayFormat(DateDisplayFormat.TOJAPANSHORTDATE);
				initPanel(dataSource);
			}
		});
	}
	private void initPanel(DataSource dataSource){
		setWidth100();
		setMembersMargin(5);
		this.setCanDrag(false);
		
		createCustomerCreditLineListGrid(dataSource);
		addMember(customerCreditLineListGrid);
		}
	
	public void createCustomerCreditLineListGrid(DataSource dataSource){
		customerCreditLineListGrid = new ListGrid();
		customerCreditLineListGrid.setDataSource(dataSource);
		customerCreditLineListGrid.setCanEdit(true);
		customerCreditLineListGrid.setAutoSaveEdits(false);
		customerCreditLineListGrid.setSelectionType(SelectionStyle.SIMPLE);
		customerCreditLineListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		customerCreditLineListGrid.setCellHeight(22); 
		customerCreditLineListGrid.setShowRowNumbers(true);
		
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
		//信用额度
		ListGridField lgfCreditLine = new ListGridField("creditLine");
		fieldList.add(lgfCreditLine);
		//开始日期
		ListGridField lgfStartDate = new ListGridField("startDate");
		lgfStartDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		fieldList.add(lgfStartDate);
		//有效日期
		ListGridField lgfExpiryDate = new ListGridField("expiryDate");
		lgfExpiryDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		fieldList.add(lgfExpiryDate);
		//货币类型
		ListGridField lgfCurrencyCode = new ListGridField("m_InternationalCurrencyCode");
		fieldList.add(lgfCurrencyCode);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
		customerCreditLineListGrid.setFields(fields);
		
		customerCreditLineListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=1){
					customerCreditLineListGrid.selectRecords(customerCreditLineListGrid.getSelection(), false);
					customerCreditLineListGrid.selectRecord(selectedRecord);
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
				customerCreditLineListGrid.startEditingNew(map);	
							
			};
		});
		
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			if(enterpriseId == null){
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}else{
				customerCreditLineListGrid.saveAllEdits(new Function(){

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
				customerCreditLineListGrid.discardAllEdits();				
			}
		});
		
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(customerCreditLineListGrid.getSelection().length == 0){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}else{
					SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
	                    public void execute(Boolean value) {  
	                        if (value != null && value) {  
	                           SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());  
	                           customerCreditLineListGrid.removeSelectedData();
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
		this.addMember(customerCreditLineListGrid);
		this.addMember(buttonPanel);
	
	}
}


	
/*	private VLayout createFormLayout(DataSource dataSource) {
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
		//信用额度
		TextItem txtCreditLine = new TextItem("creditLine");
		txtCreditLine.setHint(requiredHint);
		txtCreditLine.setRequired(true);
		itemList.add(txtCreditLine);
       //国际货币代码
	    SelectItem sltCurrencyCode = (SelectItem)dataInfo.getFieldInfoByName("m_InternationalCurrencyCode").generFormField();
	    sltCurrencyCode.setDefaultToFirstOption(true);
	    itemList.add(sltCurrencyCode);
		//开始日起
		DateItem datStartDate = new DateItem("startDate");
		itemList.add(datStartDate);
     	//有效日期
		DateItem datExpiryDate = new DateItem("expiryDate");
		itemList.add(datExpiryDate);
		
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
						customerCreditLineListGrid.selectRecords(customerCreditLineListGrid.getSelection(), false);
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
				if(customerCreditLineListGrid.getSelection().length < 1){
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}else if(Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete())){
					customerCreditLineListGrid.removeSelectedData();
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
	}
}*/
