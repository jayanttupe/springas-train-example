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

public class ShippingAddressPanel extends VLayout{
	private HLayout buttonPanel;
	private ListGrid shippingAddressListGrid;
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
		shippingAddressListGrid.fetchData(criteria);
	}
	public void clearData(){
		this.enterpriseId = null;
		shippingAddressListGrid.setData(new Record[0]);
		}
	public ShippingAddressPanel() {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise", "shippingAddress_dataSource", new PostDataSourceInit() {
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
		
		createShippingAddressListGrid(dataSource);
		addMember(shippingAddressListGrid);
	}
	
	public void createShippingAddressListGrid(DataSource dataSource){
		shippingAddressListGrid = new ListGrid();
		shippingAddressListGrid.setDataSource(dataSource);
		shippingAddressListGrid.setCanEdit(true);
		shippingAddressListGrid.setAutoSaveEdits(false);
		shippingAddressListGrid.setSelectionType(SelectionStyle.SIMPLE);
		shippingAddressListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		shippingAddressListGrid.setCellHeight(22); 
		shippingAddressListGrid.setShowRowNumbers(true);
        
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		/*ListGridField lgfItemNumber = new ListGridField("itemNumber");
		fieldList.add(lgfItemNumber);*/
		ListGridField lgfAddress = new ListGridField("address");
		fieldList.add(lgfAddress);
		ListGridField lgfPostCode = new ListGridField("postCode");
		fieldList.add(lgfPostCode);
		ListGridField lgfIsDefault = new ListGridField("default");
		fieldList.add(lgfIsDefault);
		ListGridField lgfConsignor = new ListGridField("consignor");
		fieldList.add(lgfConsignor);
		ListGridField lgfContactMan = new ListGridField("contactMan");
		fieldList.add(lgfContactMan);
		ListGridField lgfContactWay = new ListGridField("contactWay");
		fieldList.add(lgfContactWay);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
		shippingAddressListGrid.setFields(fields);
		
		shippingAddressListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				//SC.say(event.getColNum()+"");
				if(event.getColNum()!=1){
					shippingAddressListGrid.selectRecords(shippingAddressListGrid.getSelection(), false);
					shippingAddressListGrid.selectRecord(selectedRecord);
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
				
				//shippingAddressListGrid.getField("optionalPartNumber").setEditorType(sltPartNumber);
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("enterpriseId",enterpriseId);
				shippingAddressListGrid.startEditingNew(map);	
							
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
				shippingAddressListGrid.saveAllEdits(new Function(){

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
				shippingAddressListGrid.discardAllEdits();				
			}
		});
		
		final IButton deleteButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteButton.setIcon("icons/delete_list.png");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(shippingAddressListGrid.getSelection().length == 0){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				}else{
					SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
	                    public void execute(Boolean value) {  
	                        if (value != null && value) {  
	                           SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());  
	                           shippingAddressListGrid.removeSelectedData();
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
		this.addMember(shippingAddressListGrid);
		this.addMember(buttonPanel);
	
	}
}

	
	