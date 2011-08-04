package com.skynet.spms.client.gui.partcatalog.technicalCatalog.customsClearanceData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CustomsClearanceDataPanel extends VLayout {
	private DynamicForm customsForm;
	AttachmentListGrid attachmentListGrid;
	public CustomsClearanceDataPanel(final boolean editable) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical", "customsClearance_dataSource", new PostDataSourceInit() {	
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
				boolValueMap.put("true", ConstantsUtil.commonConstants.choiceYes());
				boolValueMap.put("false", ConstantsUtil.commonConstants.choiceNo());
				dataSource.getField("m_ExportRestraints.exportRestraints").setValueMap(boolValueMap);
				dataSource.getField("m_ImportRestraints.importRestraints").setValueMap(boolValueMap);
				initPanel(dataSource,dataInfo,editable);
				attachmentListGrid = new AttachmentListGrid();
			}
		});
	}
	
	public void fetchData(String customsClearanceDataId){
		Criteria criteria = new Criteria("id",customsClearanceDataId);
		customsForm.fetchData(criteria,new DSCallback() {	
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				Record selectedRecord = response.getData()[0];		
				attachmentListGrid.addObject("selectedRecord", selectedRecord);
				
				customsForm.getItem("id").setValue(selectedRecord.getAttribute("id"));
				customsForm.getItem("hsCode").setValue(selectedRecord.getAttribute("hsCode"));
				customsForm.getItem("purpose").setValue(selectedRecord.getAttribute("purpose"));
				customsForm.getItem("m_InternationalCommodityCode").setValue(selectedRecord.getAttribute("m_InternationalCommodityCode"));
				customsForm.getItem("m_ExportRestraints.exportRestraints").setValue(selectedRecord.getAttribute("m_ExportRestraints.exportRestraints"));
				customsForm.getItem("m_ImportRestraints.importRestraints").setValue(selectedRecord.getAttribute("m_ImportRestraints.importRestraints"));
			}
		});		
	}
	public void clearFormValues(){
		customsForm.clearValues();
	}
	
	public void initPanel(DataSource dataSource,DataInfo dataInfo ,boolean editable) {
		final DataInfo customsDataInfo = dataInfo;
		//报关数据表单
		customsForm = new DynamicForm();
		customsForm.setDataSource(dataSource); 
		customsForm.setWidth(680);
		customsForm.setPadding(5);
		customsForm.setNumCols(4);  
		customsForm.setColWidths(120, 200, 160, 200);  

		final List<FormItem> customsList = new ArrayList<FormItem>();
		//报关数据ID
		HiddenItem hdnId = new HiddenItem("id");
		customsList.add(hdnId);
		//商品编码
		TextItem txtHsCode = new TextItem("hsCode");
		txtHsCode.setAttribute("readOnly", true);
		
		txtHsCode.setWidth(200);
		customsList.add(txtHsCode);
		//备件用途
	    TextAreaItem txaPurpose = new TextAreaItem("purpose");
	    txaPurpose.setWidth(280);  
	    txaPurpose.setRowSpan(4);  
	    customsList.add(txaPurpose);
	      
	    //国际商品编码
	    TextItem txtIcCode  = new TextItem("m_InternationalCommodityCode");
	    txtIcCode.setWidth(200);
		customsList.add(txtIcCode);
		
		//是否出口限制
		TextItem txtExportRestraints  = new TextItem("m_ExportRestraints.exportRestraints");
		txtExportRestraints.setWidth(200);
		customsList.add(txtExportRestraints);
		//是否进口限制
		TextItem txtImportRestraints  = new TextItem("m_ImportRestraints.importRestraints");
		txtImportRestraints.setWidth(200);
		customsList.add(txtImportRestraints);
		 
        FormItem[] customsItems = new FormItem[customsList.size()];
        customsList.toArray(customsItems);
        customsForm.setFields(customsItems); 
	    this.addMember(customsForm);
		
		HLayout buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		final IButton editButton = new IButton(ConstantsUtil.buttonConstants.editButton());
		editButton.setIcon("icons/edit_form.png");
		editButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(customsForm.getItem("id").getValue() == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectIndex());
					return;
				}	
				attachmentListGrid.addObject("customsDS", customsForm.getDataSource());
				attachmentListGrid.addObject("customsDataInfo",customsDataInfo);
				attachmentListGrid.addObject("cid", customsForm.getItem("id").getValue());
				
				CustomsClearanceDataAddWindow useWindow = new CustomsClearanceDataAddWindow(
						ConstantsUtil.partCatalogConstants.editCustomsClearanceData(), 
						true, editButton.getPageRect(), 
						attachmentListGrid, "showwindow/part_modify_01.png");
				ShowWindowTools.showWondow(editButton.getPageRect(), useWindow, -1);
				
				useWindow.addCloseClickHandler(new CloseClickHandler() {	
					@Override
					public void onCloseClick(CloseClientEvent event) {
						fetchData((String)customsForm.getItem("id").getValue());
						
					}
				});
			}
		});
		
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				//form.reset();
				
			}
		});
		
		IButton helpButton = new IButton();
	    helpButton.setIcon("icons/book_help.png");
	    helpButton.setTitle(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		buttonPanel.addMember(editButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);	
		
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.setHeight100();
		this.addMember(wrapper);  
		
		this.addMember(buttonPanel);
		if(!editable){
			buttonPanel.setVisible(false);
		}
	}
}
