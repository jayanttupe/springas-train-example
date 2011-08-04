package com.skynet.spms.client.gui.partcatalog.technicalCatalog.appliationData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ApplicationDataPanel extends VLayout {
	private DynamicForm form;
	private HLayout buttonPanel;
	private Record selectedRecord; 
	
	public ApplicationDataPanel(final boolean editable) {
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical","applicationData_dataSource", new PostDataSourceInit() {
					
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				initPanel(dataSource,dataInfo);
				if(!editable){
					buttonPanel.setVisible(false);
				}
			}
		});
	}
	
	public void initPanel(DataSource dataSource,DataInfo dataInfo) {
		form = new DynamicForm();
		form.setDataSource(dataSource);
		form.setWidth(780);
		form.setPadding(5);
		form.setNumCols(4);  
		form.setColWidths(120, 200, 160, 300);  
		//form.setAlign(Alignment.LEFT);	
		//String requiredHint = "<font color=red>*</font>";
		LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
	    boolValueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
	    boolValueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
				
	    List<FormItem> itemList = new ArrayList<FormItem>();
		
		//主键ID
		HiddenItem hdnId = new HiddenItem("id");
		itemList.add(hdnId);
		
		//单机装机数量
		final TextItem tiTotalQuantityPerAircraftEngine = new TextItem("totalQuantityPerAircraftEngine","单机装机数量");
		tiTotalQuantityPerAircraftEngine.setWidth(150);
		itemList.add(tiTotalQuantityPerAircraftEngine);
	    
		//快速转发
		RadioGroupItem rgiQuickEngineChangeIndicator = new RadioGroupItem("quickEngineChange","快速转发");
		rgiQuickEngineChangeIndicator.setValueMap(boolValueMap);
		rgiQuickEngineChangeIndicator.setVertical(false);
		//rgiQuickEngineChangeIndicator.setColSpan(2);
		rgiQuickEngineChangeIndicator.setWidth(120);
	    itemList.add(rgiQuickEngineChangeIndicator);

		//订货点
		TextItem reorderLine = new TextItem("reorderLine","订货点");
		reorderLine.setWidth(150);
		itemList.add(reorderLine);
	
		//ETOPS
	    RadioGroupItem rgiETOPSFlightIndicator = new RadioGroupItem("etops","ETOPS");
	    rgiETOPSFlightIndicator.setValueMap(boolValueMap);
	    rgiETOPSFlightIndicator.setVertical(false);
	    rgiETOPSFlightIndicator.setWidth(120);
	    itemList.add(rgiETOPSFlightIndicator);
        //安全库存点
		TextItem  safeStockLine= new TextItem("safeStockLine","安全库存点");
		safeStockLine.setWidth(150);
		itemList.add(safeStockLine);
		
		//航线维护备注说明
	    TextAreaItem textMiscellaneousText = new TextAreaItem("miscellaneousText","航线维护备注说明");
	    textMiscellaneousText.setWidth(300);  
	    textMiscellaneousText.setRowSpan(3);
	    itemList.add(textMiscellaneousText);
	    
	    //再订货量
		TextItem reorderQuantity = new TextItem("reorderQuantity","再订货量");
		reorderQuantity.setWidth(150);
		itemList.add(reorderQuantity);
		
		//出口控制分类号码
		TextItem sltExportControlClassificationCode = new TextItem("m_ExportControlClassificationCode","出口控制分类号码");
		sltExportControlClassificationCode.setWidth(150);
		itemList.add(sltExportControlClassificationCode);
		
		/*//单位
		SelectItem sltUnitOfMeasureCode = new SelectItem("m_UnitOfMeasureCode","单位");
		sltUnitOfMeasureCode.setWidth(80);
		sltUnitOfMeasureCode.setDefaultToFirstOption(true);
		itemList.add(sltUnitOfMeasureCode);*/
	    
	    /*//发动机维护代码
	    //SelectItem sltEngineLevelOfMaintenanceCode = (SelectItem)dataInfo.getFieldInfoByName("m_EngineLevelOfMaintenanceCode").generFormField(); 
	    SelectItem sltEngineLevelOfMaintenanceCode = new SelectItem("m_EngineLevelOfMaintenanceCode","发动机维护代码");
	    sltEngineLevelOfMaintenanceCode.setWidth(120);
		itemList.add(sltEngineLevelOfMaintenanceCode);
		
		//维修代码
		//SelectItem sltMaintenanceOverhaulRepairCode = (SelectItem)dataInfo.getFieldInfoByName("m_MaintenanceOverhaulRepairCode").generFormField(); 
	    SelectItem sltMaintenanceOverhaulRepairCode = new SelectItem("m_MaintenanceOverhaulRepairCode","维修代码");
		sltMaintenanceOverhaulRepairCode.setWidth(120);
		itemList.add(sltMaintenanceOverhaulRepairCode);*/
		
	    FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items); 
		form.setFields(items);
		this.addMember(form);
        
		
		buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(form.getItem("id").getValue() == null){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
					return ;
				}
				form.validate();
				form.saveData(new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						selectedRecord = response.getData()[0];
						form.rememberValues();
						SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess());
						}
				});
			}
		});	
		
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				form.reset();	
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
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);	
		
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.setHeight100();
		this.addMember(wrapper); 
		
	    this.addMember(buttonPanel);       
	}
	public void fetchData(String applicationDataId){
		Criteria criteria = new Criteria("id",applicationDataId);
		form.fetchData(criteria,new DSCallback() {	
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				selectedRecord = response.getData()[0];
				form.getItem("id").setValue(selectedRecord.getAttribute("id"));
				
				form.getItem("totalQuantityPerAircraftEngine").setValue(selectedRecord.getAttribute("totalQuantityPerAircraftEngine"));
				//form.getItem("m_UnitOfMeasureCode").setValue(selectedRecord.getAttribute("m_UnitOfMeasureCode"));
				form.getItem("quickEngineChange").setValue(selectedRecord.getAttribute("quickEngineChange"));
				form.getItem("m_ETOPSFlightIndicator").setValue(selectedRecord.getAttribute("m_ETOPSFlightIndicator"));
				form.getItem("m_ExportControlClassificationCode").setValue(selectedRecord.getAttribute("m_ExportControlClassificationCode"));
				form.getItem("reorderLine").setValue(selectedRecord.getAttribute("reorderLine"));
				form.getItem("safeStockLine").setValue(selectedRecord.getAttribute("safeStockLine"));
				form.getItem("reorderQuantity").setValue(selectedRecord.getAttribute("reorderQuantity"));
				form.getItem("miscellaneousText").setValue(selectedRecord.getAttribute("miscellaneousText"));
			}
		});
	}
	
	public void clearFormValues(){
		form.clearValues();
	}	
}
