package com.skynet.spms.client.gui.partcatalog.technicalCatalog.customsClearanceData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class CustomsClearanceDataAddWindow extends BaseWindow {
 
		private AttachmentListGrid attachmentListGrid;
		private DataSource customsDS;
		private DataInfo customsDataInfo;
		private Record selectedRecord;
		private DynamicForm customsForm;
		private DynamicForm exportForm;
		private DynamicForm importForm;
		
		public CustomsClearanceDataAddWindow(String windowTitle, boolean isMax,
				Rectangle srcRect, ListGrid listGrid, String iconUrl) {
			super(windowTitle, isMax,srcRect, listGrid, iconUrl);
		}

		protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
			attachmentListGrid = (AttachmentListGrid)listGrid;
			customsDS = (DataSource)attachmentListGrid.getObject("customsDS");
			customsDataInfo = (DataInfo)attachmentListGrid.getObject("customsDataInfo");
			selectedRecord = (Record)attachmentListGrid.getObject("selectedRecord");
			addCloseClickHandler(new CloseClickHandler() {
				public void onCloseClick(CloseClientEvent event) {
					ShowWindowTools.showCloseWindow(srcRect, CustomsClearanceDataAddWindow.this, -1);
				}
			});
			
			//报关数据表单
			customsForm = new DynamicForm();
			customsForm.setDataSource(customsDS); 
			customsForm.setWidth(590);
			customsForm.setPadding(5);
			customsForm.setNumCols(4);  
			customsForm.setColWidths(120, 150, 120, 200);  
			
			final List<FormItem> customsList = new ArrayList<FormItem>();

			//报关数据ID
			HiddenItem hdnId = new HiddenItem("id");
			//hdnId.setValue(selectedRecord.getAttribute("id"));
			customsList.add(hdnId);
			
			//商品编码
			TextItem txtHsCode = new TextItem("hsCode");
			txtHsCode.setWidth(150);
			//txtHsCode.setValue(selectedRecord.getAttribute("hsCode"));
			customsList.add(txtHsCode);
			
			//备件用途
		    TextAreaItem txaPurpose = new TextAreaItem("purpose");
		    txaPurpose.setWidth(280);  
		    txaPurpose.setRowSpan(2);
		    //txaPurpose.setValue(selectedRecord.getAttribute("purpose"));
		    customsList.add(txaPurpose);
		   
		      
		    //国际商品编码
			SelectItem sltIcCode  = (SelectItem)customsDataInfo.getFieldInfoByName("m_InternationalCommodityCode").generFormField();
			sltIcCode.setWidth(150);
			//sltIcCode.setValue(selectedRecord.getAttribute("m_InternationalCommodityCode"));
			customsList.add(sltIcCode);
			
	        FormItem[] customsItems = new FormItem[customsList.size()];
	        customsList.toArray(customsItems);
	        customsForm.setFields(customsItems);
	        setFormValues(customsForm, selectedRecord);
	        
	        //关联报关数据表单按钮条
			HLayout custButtonPanel = new BtnsHLayout();
			custButtonPanel.setHeight(30);
			IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
			saveButton.setIcon("icons/save.png");
			saveButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					customsForm.saveData(new DSCallback() {
						@Override
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							customsForm.rememberValues();
							Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
							selectedRecord = response.getData()[0];		
							setFormValues(exportForm, selectedRecord);
							setFormValues(importForm, selectedRecord);
							/*exportForm.getItem("id").setValue(selectedRecord.getAttribute("id"));
							exportForm.getItem("m_ExportRestraints.id").setValue(selectedRecord.getAttribute("m_ExportRestraints.id"));
							exportForm.getItem("m_ExportRestraints.exportRestraints").setValue(selectedRecord.getAttribute("m_ExportRestraints.exportRestraints"));
							exportForm.getItem("m_ExportRestraints.description").setValue(selectedRecord.getAttribute("m_ExportRestraints.description"));
							
							importForm.getItem("id").setValue(selectedRecord.getAttribute("id"));
							importForm.getItem("m_ImportRestraints.id").setValue(selectedRecord.getAttribute("m_ImportRestraints.id"));
							importForm.getItem("m_ImportRestraints.importRestraints").setValue(selectedRecord.getAttribute("m_ImportRestraints.importRestraints"));
							importForm.getItem("m_ExportRestraints.description").setValue(selectedRecord.getAttribute("m_ExportRestraints.description"));*/
						}
					});
					
				}
			});
			
		    IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		    cancelButton.setIcon("icons/remove.png");
		    cancelButton.addClickHandler(new ClickHandler() {	
				@Override
				public void onClick(ClickEvent event) {
					customsForm.reset();	
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
		    
		    custButtonPanel.addMember(saveButton);
		    custButtonPanel.addMember(cancelButton);
		    custButtonPanel.addMember(helpButton);
		    
		    final TabSet tabSet = new TabSet();
		    //tabSet.setHeight("77%");
			
			Tab exportTab = new Tab("出口限制");
			exportTab.setPane(getExportPanel());
			tabSet.addTab(exportTab);
			
			Tab importTab = new Tab("进口限制");
			importTab.setPane(getImportPanel());
			tabSet.addTab(importTab);
			
			VLayout vLayout = new VLayout(5);
			vLayout.addMember(customsForm); 
			vLayout.addMember(custButtonPanel);
			vLayout.addMember(tabSet);
			
			return vLayout;
		}
		
		private Canvas getExportPanel(){
			//出口限制表单
			exportForm = new DynamicForm();
			exportForm.setDataSource(customsDS);
			exportForm.setWidth(590);
			exportForm.setPadding(5);
			exportForm.setNumCols(4);  
			exportForm.setColWidths(120, 150, 120, 200);  
			
			final List<FormItem> exportList = new ArrayList<FormItem>();

			////报关数据ID
			HiddenItem hdncustomsId = new HiddenItem("id");
			//hdncustomsId.setValue(selectedRecord.getAttribute("id"));
			exportList.add(hdncustomsId);
			
			//出口限制ID
			HiddenItem hdnExportId = new HiddenItem("m_ExportRestraints.id");
			//hdnExportId.setValue(selectedRecord.getAttribute("m_ExportRestraints.id"));
			exportList.add(hdnExportId);
			
			//是否出口限制
		    RadioGroupItem radExportRestraints = new RadioGroupItem("m_ExportRestraints.exportRestraints");  
		    radExportRestraints.setWidth(150);
		    radExportRestraints.setVertical(false);
		    LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
		    boolValueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
		    boolValueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
		    radExportRestraints.setValueMap(boolValueMap);
		    //radExportRestraints.setValue(selectedRecord.getAttribute("m_ExportRestraints.exportRestraints"));
		    exportList.add(radExportRestraints);
		    
		    //描述
		    TextAreaItem txaExportDescription = new TextAreaItem("m_ExportRestraints.description");
		    txaExportDescription.setWidth(280);  
		    txaExportDescription.setRowSpan(2);
		    //txaExportDescription.setValue(selectedRecord.getAttribute("m_ExportRestraints.description"));
		    exportList.add(txaExportDescription);
		    
		   
	        FormItem[] exportItems = new FormItem[exportList.size()];
	        exportList.toArray(exportItems);
	        exportForm.setFields(exportItems);
	        setFormValues(exportForm, selectedRecord);
	        
	        //关联出口限制表单按钮条
			HLayout exportButtonPanel = new BtnsHLayout();
			exportButtonPanel.setHeight(30);
			IButton saveExportButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
			saveExportButton.setWidth(80);
			saveExportButton.setIcon("icons/save.png");
			saveExportButton.addClickHandler(new ClickHandler() {	
				@Override
				public void onClick(ClickEvent event) {
					exportForm.saveData(new DSCallback() {		
						@Override
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							exportForm.rememberValues();
							Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
						}
					});	
				}
			});
		    IButton cancelExportButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		    cancelExportButton.setWidth(80);
		    cancelExportButton.setIcon("icons/remove.png");
		    cancelExportButton.addClickHandler(new ClickHandler() {			
				@Override
				public void onClick(ClickEvent event) {
					exportForm.reset();
				}
			});
		    exportButtonPanel.addMember(saveExportButton);
		    exportButtonPanel.addMember(cancelExportButton);
		    
	        VLayout pane = new VLayout(5);
		    pane.addMember(exportForm);
		    pane.addMember(exportButtonPanel);
		    pane.addMember(attachmentListGrid);
			return pane;
		}
		
		private Canvas getImportPanel(){
			//进口限制表单
			importForm = new DynamicForm();
			importForm.setDataSource(customsDS);
			importForm.setWidth(590);
			importForm.setPadding(5);
			importForm.setNumCols(4); 
			importForm.setColWidths(120, 150, 120, 200); 
			
			final List<FormItem> importList = new ArrayList<FormItem>();
			
			////报关数据ID
			HiddenItem hdncustomsId = new HiddenItem("id");
			importList.add(hdncustomsId);
			//进口限制ID
			HiddenItem hdnImportId = new HiddenItem("m_ImportRestraints.id");
			importList.add(hdnImportId);
			
			//是否进口限制
		    RadioGroupItem radImportRestraints = new RadioGroupItem("m_ImportRestraints.importRestraints");  
		    radImportRestraints.setWidth(150);
		    radImportRestraints.setVertical(false);
		    LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
		    boolValueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
		    boolValueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
		    radImportRestraints.setValueMap(boolValueMap);
		    importList.add(radImportRestraints);
		    
		    //描述
		    TextAreaItem txaImportDescription = new TextAreaItem("m_ImportRestraints.description");
		    txaImportDescription.setWidth(280);  
		    txaImportDescription.setRowSpan(2);
		    importList.add(txaImportDescription);
		    
	        FormItem[] importItems = new FormItem[importList.size()];
	        importList.toArray(importItems);
	        importForm.setFields(importItems);
	        setFormValues(importForm, selectedRecord);
	        
	        //关联进口限制表单按钮条
			HLayout importButtonPanel = new BtnsHLayout();
			importButtonPanel.setHeight(30);
			IButton saveImportButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
			saveImportButton.setWidth(80);
			saveImportButton.setIcon("icons/save.png");
			saveImportButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					importForm.saveData(new DSCallback() {		
						@Override
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							importForm.rememberValues();
							Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
						}
					});
				}
			});
		    IButton cancelImportButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		    cancelImportButton.setWidth(80);
		    cancelImportButton.setIcon("icons/remove.png");
		    cancelImportButton.addClickHandler(new ClickHandler() {			
				@Override
				public void onClick(ClickEvent event) {
					importForm.reset();
				}
			});
		    importButtonPanel.addMember(saveImportButton);
		    importButtonPanel.addMember(cancelImportButton);
		       
	        VLayout pane2 = new VLayout(5);
	        pane2.addMember(importForm);
	        pane2.addMember(importButtonPanel);
	        AttachmentListGrid importAttachmentListGrid = new AttachmentListGrid();    
	        pane2.addMember(importAttachmentListGrid);
			return pane2;
		}
		
		private void setFormValues(DynamicForm form, Record record){
			for(FormItem item : form.getFields()){
				String name = item.getName();
				item.setValue(record.getAttribute(name));
			}
		}
}