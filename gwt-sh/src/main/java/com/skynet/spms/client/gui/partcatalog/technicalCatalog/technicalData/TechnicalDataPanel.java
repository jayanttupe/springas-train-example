package com.skynet.spms.client.gui.partcatalog.technicalCatalog.technicalData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Window;
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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;


public class TechnicalDataPanel extends VLayout {
	private TimeCyclesDataListGrid timeCyclesDataListGrid;
	private DynamicForm form;
	private HLayout buttonPanel;
	private HLayout buttonPanel2;
	public TechnicalDataPanel(final boolean editable){
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("partCatalog.technical", "technicalData_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				initPanel(dataSource,dataInfo,editable);
			}
		});
	}
	public void initPanel(DataSource dataSource,DataInfo dataInfo,boolean editable){
		setHeight(400);
		
		
		form = new DynamicForm();
		form.setDataSource(dataSource);
		
		form.setHeight(50);
		form.setWidth(760);
		form.setPadding(5);
		form.setNumCols(8);
		form.setColWidths(120, 100,40,100, 160, 100,40,100); 
		form.setAlign(Alignment.LEFT);	
		
		LinkedHashMap<String, String> boolValueMap = new LinkedHashMap<String, String>();
	    boolValueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
	    boolValueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
		
		List<FormItem> itemList = new ArrayList<FormItem>();
		
		//主键ID
		HiddenItem hdnId = new HiddenItem("id");
		itemList.add(hdnId);
		
		//是否时寿件
		CheckboxItem chkTimeCycles = new CheckboxItem("timeCycles");
		chkTimeCycles.setWidth(100);
		chkTimeCycles.setValue(false);
		itemList.add(chkTimeCycles);
		
		//是否时控件
		CheckboxItem chkTimeControl = new CheckboxItem("timeControl");
		chkTimeControl.setWidth(100);
		chkTimeControl.setValue(false);
		itemList.add(chkTimeControl);
		
		//货架寿命
		final TextItem txtShelfLife = new TextItem("shelfLife");
		txtShelfLife.setWidth(100);
		txtShelfLife.setValidateOnChange(true);
		itemList.add(txtShelfLife);
		
		//货架寿命单位
		SelectItem sltLifeUnitCode = (SelectItem)dataInfo.getFieldInfoByName("shelfLifeUnitCode").generFormField();
		sltLifeUnitCode.setWidth(100);
		sltLifeUnitCode.setDefaultToFirstOption(true);
		itemList.add(sltLifeUnitCode);

		/*RadioGroupItem rgiIsTimeCycles = new RadioGroupItem("timeCycles");
		rgiIsTimeCycles.setValueMap(boolValueMap);
		rgiIsTimeCycles.setVertical(false);
		rgiIsTimeCycles.setColSpan(3);
		rgiIsTimeCycles.setWidth(120);
	    itemList.add(rgiIsTimeCycles);*/
	    
		/*//货架周期类型代码
		SelectItem sltShelfLifeCode = (SelectItem)dataInfo.getFieldInfoByName("m_ShelfLifeCode").generFormField(); 
		sltShelfLifeCode.setDefaultToFirstOption(true);
		itemList.add(sltShelfLifeCode);*/
		
		//提示文本：可靠性数据参考值
	    HeaderItem refDataHeader = new HeaderItem();
	    refDataHeader.setDefaultValue(ConstantsUtil.partCatalogConstants.reliabilityReferenceValue());
	    refDataHeader.setColSpan(8);
	    itemList.add(refDataHeader);
		
		//平均维修间隔
		final TextItem txtMtbm = new TextItem("mtbm");
		txtMtbm.setWidth(100);
		txtMtbm.setValidateOnChange(true);
		itemList.add(txtMtbm);
		
		//平均维修间隔单位代码
		SelectItem sltMtbmUnitCode = (SelectItem)dataInfo.getFieldInfoByName("mtbmUnitCode").generFormField();
		sltMtbmUnitCode.setWidth(100);
		sltMtbmUnitCode.setDefaultToFirstOption(true);
		itemList.add(sltMtbmUnitCode);
		
		//不定期拆换率
        TextItem txtUnscheduledRemovalRate = new TextItem("unscheduledRemovalRate");
        txtUnscheduledRemovalRate.setColSpan(3);
        txtUnscheduledRemovalRate.setValidateOnChange(true);
        itemList.add(txtUnscheduledRemovalRate);
		
		//平均非计划拆卸间隔
		TextItem txtMtbur = new TextItem("mtbur");
		txtMtbur.setWidth(100);
		txtMtbur.setValidateOnChange(true);
		itemList.add(txtMtbur);
		
		//平均非计划拆卸间隔单位代码
		SelectItem sltMtburUnitCode = (SelectItem)dataInfo.getFieldInfoByName("mtburUnitCode").generFormField();
		sltMtburUnitCode.setWidth(100);
		sltMtburUnitCode.setDefaultToFirstOption(true);
		itemList.add(sltMtburUnitCode);
		
		/*//航线维护百分比
		TextItem txtMaintenancePercent = new TextItem("maintenancePercent");
		itemList.add(txtMaintenancePercent);*/
        
        //不定期拆换率小数点代码
        SelectItem sltUnscheduledRemovalRateDecimalCode = (SelectItem)dataInfo.getFieldInfoByName("m_UnscheduledRemovalRateDecimalCode").generFormField();
        sltUnscheduledRemovalRateDecimalCode.setDefaultToFirstOption(true);
        sltUnscheduledRemovalRateDecimalCode.setColSpan(3);
        itemList.add(sltUnscheduledRemovalRateDecimalCode);
        
        FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items); 
		form.setFields(items);
		
        this.addMember(form);
        
        
        //按钮条
        buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(30);
        
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(form.getItem("id").getValue() == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectIndex());
					return ;
				}
				form.saveData(new DSCallback() {			
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						form.rememberValues();
						Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
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
		IButton helpButton = new IButton(ConstantsUtil.buttonConstants.helpButton());
		helpButton.setIcon("icons/book_help.png");
		helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);
		this.addMember(buttonPanel);
		
		
		//时控数据ListGrid
		SectionStack sectionStack = new SectionStack();
        sectionStack.setWidth100();
        sectionStack.setHeight100();
        SectionStackSection section = new SectionStackSection();
        section.setTitle(ConstantsUtil.partCatalogConstants.timeCyclesData());
        section.setCanCollapse(false);  
        section.setExpanded(true);
        //editable表示时控是否可编辑
        timeCyclesDataListGrid = new TimeCyclesDataListGrid(editable);
	    section.setItems(timeCyclesDataListGrid);
	    sectionStack.setSections(section);
	    
	    HLayout wrapperGrid = new HLayout();
		wrapperGrid.setWidth100();
		wrapperGrid.setHeight100();
		wrapperGrid.addChild(sectionStack);
		this.addMember(wrapperGrid);
	    
	    //按钮条
	    buttonPanel2 = new BtnsHLayout();
        buttonPanel2.setHeight(30);
		IButton newTcButton = new IButton(ConstantsUtil.buttonConstants.newButton());
		newTcButton.setIcon("icons/add_list.png");
		newTcButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String partTechnicalDataId = (String)form.getItem("id").getValue();
				if(partTechnicalDataId == null){
					Window.alert(ConstantsUtil.partCatalogConstants.alertForSelectIndex());
					return ;
				}
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("partTechnicalDataId", partTechnicalDataId);
				timeCyclesDataListGrid.startEditingNew(map);
				
			}
		});
		IButton saveTcButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveTcButton.setIcon("icons/save.png");
		saveTcButton.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				timeCyclesDataListGrid.saveAllEdits();
				
			}
		});
		IButton cancelTcButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelTcButton.setIcon("icons/remove.png");
		cancelTcButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				timeCyclesDataListGrid.discardAllEdits();
				
			}
		});	
		IButton deleteTcButton = new IButton(ConstantsUtil.buttonConstants.deleteButton());
		deleteTcButton.setIcon("icons/delete_list.png");
		deleteTcButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(timeCyclesDataListGrid.getSelection().length == 0){
					Window.alert(ConstantsUtil.commonConstants.alertSelectForDelete());
				}else{
					boolean isDel = Window.confirm(ConstantsUtil.commonConstants.ConfirmForDelete());
					if (isDel){
						timeCyclesDataListGrid.removeSelectedData();
				     } 
				}		
			}
		});
		buttonPanel2.addMember(newTcButton);
		buttonPanel2.addMember(saveTcButton);
		buttonPanel2.addMember(cancelTcButton);
		buttonPanel2.addMember(deleteTcButton);
		/*HLayout wrapper2 = new HLayout();
		wrapper2.setWidth100();
		wrapper2.setHeight100();
		this.addMember(wrapper2); */
		
		this.addMember(buttonPanel2);
		
		if(!editable){
			buttonPanel.setVisible(false);
			buttonPanel2.setVisible(false);
		}
	}
	
	public void fetchData(final String partTechnicalDataId){
		Criteria criteria = new Criteria("id",partTechnicalDataId);
		form.fetchData(criteria,new DSCallback() {
			
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				Record selectedRecord = response.getData()[0];
				form.getItem("id").setValue(selectedRecord.getAttribute("id"));
				form.getItem("timeCycles").setValue(selectedRecord.getAttributeAsBoolean("timeCycles"));
				form.getItem("timeControl").setValue(selectedRecord.getAttributeAsBoolean("timeControl"));
				//form.getItem("m_ShelfLifeCode").setValue(selectedRecord.getAttribute("m_ShelfLifeCode"));
				form.getItem("shelfLife").setValue(selectedRecord.getAttribute("shelfLife"));
				form.getItem("shelfLifeUnitCode").setValue(selectedRecord.getAttribute("shelfLifeUnitCode"));
				form.getItem("mtbm").setValue(selectedRecord.getAttribute("mtbm"));
				form.getItem("mtbmUnitCode").setValue(selectedRecord.getAttribute("mtbmUnitCode"));
				form.getItem("mtbur").setValue(selectedRecord.getAttribute("mtbur"));
				form.getItem("mtburUnitCode").setValue(selectedRecord.getAttribute("mtburUnitCode"));
				form.getItem("unscheduledRemovalRate").setValue(selectedRecord.getAttribute("unscheduledRemovalRate"));
				form.getItem("m_UnscheduledRemovalRateDecimalCode").setValue(selectedRecord.getAttribute("m_UnscheduledRemovalRateDecimalCode"));
				Criteria criteria2 = new Criteria("partTechnicalDataId",partTechnicalDataId);
				timeCyclesDataListGrid.fetchData(criteria2);
			}
		});
	}
	public void clearFormValues(){
		form.clearValues();
		//Criteria criteria2 = new Criteria("partTechnicalDataId","clear");
		//timeCyclesDataListGrid.fetchData(criteria2);
		timeCyclesDataListGrid.setData(new Record[0]);
	}
}
