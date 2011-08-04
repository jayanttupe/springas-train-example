package com.skynet.spms.client.gui.partcatalog.technicalCatalog.technicalData;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class TechnicalDataAddWindow extends BaseWindow {
	public ListGrid technicalDataListGrid;
	public TechnicalDataAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax,srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		//IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, TechnicalDataAddWindow.this, -1);
			}
		});
		setWidth(470);
		setHeight(280);
		setTitle("添加科技数据信息");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				//buttonTouchThis.setTitle("Touch This");
				destroy();
			}
		});
		final DynamicForm form = new DynamicForm();
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setMargin(10);
		form.setNumCols(4);  
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		form.enableHiliting(false);
		
		TextItem tiMaintenancePercent = new TextItem("maintenancePercent", "航线维护百分比");
        TextItem tiMeanShopProcessingTime = new TextItem("meanShopProcessingTime", "车间平均维修时间");
        TextItem tiUnscheduledRemovalRate = new TextItem("unscheduledRemovalRate", "不定期拆换率");
        SelectItem siUnscheduledRemovalRateDecimalCode = new SelectItem("unscheduledRemovalRateDecimalCode", "不定期拆换率小数点代码");  
        siUnscheduledRemovalRateDecimalCode.setValueMap( "A","B","C");  
        SelectItem siShelfLifeCode = new SelectItem("shelfLifeCode", "时寿代码");  
        siShelfLifeCode.setValueMap( "999V999","99V9999","9V99999","V999999");  
        form.setColWidths(120, 300, 150, 300);  
		//form.setDataSource(indexInfoXmlDS);
		form.setFields(tiMaintenancePercent,tiMeanShopProcessingTime,tiUnscheduledRemovalRate,siUnscheduledRemovalRateDecimalCode,siShelfLifeCode);
        this.addMember(form);
        
        SectionStack sectionStack = new SectionStack();
        
        sectionStack.setWidth100();
        SectionStackSection section = new SectionStackSection();
        section.setTitle("时控数据");
        section.setCanCollapse(false);  
        section.setExpanded(true); 
        
		//ListGrid technicalDataListGrid = new ListGrid();
		//setDataSource(supplyItemDS); 
		//setUseAllDataSourceFields(true);
		List<ListGridField> fieldList = new ArrayList<ListGridField>();
		
	
	    ListGridField lgfCycle = new ListGridField("cycle", "周期数");  
	    lgfCycle.setCanEdit(false);
	    fieldList.add(lgfCycle);
                  
        ListGridField lgfUnitCode = new ListGridField("unitCode", "时控类型"); 
        lgfUnitCode.setValueMap( "","","","", "", "","", "", "");
        lgfUnitCode.setCanEdit(false);
        fieldList.add(lgfUnitCode);
        
        ListGridField lgfQuickEngineChangeIndicator = new ListGridField("quickEngineChangeIndicator", "快速换发");  
        lgfQuickEngineChangeIndicator.setCanEdit(false);
	    fieldList.add(lgfQuickEngineChangeIndicator);
	    
        ListGridField lgfTimeCycleCode = new ListGridField("timeCycleCode","时空代码");
        lgfTimeCycleCode.setCanEdit(false);
	    fieldList.add(lgfTimeCycleCode);
        
        ListGridField lgfTimeCycleReferenceCode = new ListGridField("timeCycleReferenceCode","维修代码");
        lgfTimeCycleReferenceCode.setCanEdit(false);
	    fieldList.add(lgfTimeCycleReferenceCode);
	    
	    ListGridField lgfhandling = new ListGridField("handling","处理方法");
	    lgfhandling.setCanEdit(false);
	    fieldList.add(lgfhandling);
	    
        ListGridField lgfDescription = new ListGridField("description","描述");
        lgfDescription.setCanEdit(false);
	    fieldList.add(lgfDescription);
	    
	    
        ListGridField[] fields = new ListGridField[fieldList.size()];
        fieldList.toArray(fields);
        
        
		HLayout buttonPanel = new HLayout(10);
		IButton saveButton = new IButton();
		saveButton.setIcon("icons/save.png");
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.setAlign(Alignment.CENTER);
	    IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.setIcon("icons/remove.png");
	   
	    IButton helpButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
	    helpButton.setIcon("icons/book_help.png");
	    
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);
		
	    saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect, TechnicalDataAddWindow.this, -1);
			}
		});

	    HLayout tileGrid = new HLayout();
		tileGrid.setWidth100();
		tileGrid.setHeight("90%");
		//tileGrid.setTileWidth(150);
		//tileGrid.setTileHeight(150);
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addChild(form);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("10%");
		//buttonGrid.setTileWidth(100);
		//buttonGrid.setTileHeight(100);	
		buttonGrid.setBorder("0px solid #9C9C9C");
		saveButton.setTop("25%");
		buttonGrid.addChild(saveButton);

		
		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
}
