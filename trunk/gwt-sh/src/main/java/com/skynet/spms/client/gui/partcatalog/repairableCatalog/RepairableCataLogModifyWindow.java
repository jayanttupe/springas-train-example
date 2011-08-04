package com.skynet.spms.client.gui.partcatalog.repairableCatalog;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedEvent;
import com.smartgwt.client.widgets.form.fields.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RepairableCataLogModifyWindow extends BaseWindow{
	
	public RepairableCataLogModifyWindow(String windowTitle, boolean isMax,
		final	Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax,srcRect, listGrid, iconUrl);
	}
	
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, RepairableCataLogModifyWindow.this, -1);
			}
		});
	
		setWidth(470);
		setHeight(280);
		setTitle("修改备件修理信息");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
	
		final DynamicForm form = new DynamicForm();	
		form.setDataSource(indexInfoListGrid.getDataSource());
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setMargin(10);
		form.setColWidths(150, 300); 
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		form.enableHiliting(false);
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		String required = "<font color=red>*</font>";
		
		//获取所选中的记录数据
		final Record record=indexInfoListGrid.getSelectedRecord();
	
		HiddenItem hdnId = new HiddenItem("id");
		hdnId.setValue(record.getAttribute("id"));
		itemList.add(hdnId);
		
		//原厂商件号
		TextItem txtManufacturerNumber = new TextItem("manufacturerPartNumber");
		txtManufacturerNumber.setWidth(120);
		txtManufacturerNumber.setValue(record.getAttribute("manufacturerPartNumber"));
		txtManufacturerNumber.setHint(required);
        itemList.add(txtManufacturerNumber);
        
        
        //商飞件号
        TextItem txtPartNumber = new TextItem("partNumber"); 
        txtPartNumber.setWidth(120);
        txtPartNumber.setValue(record.getAttribute("partNumber"));
        txtPartNumber.setHint(required);
        itemList.add(txtPartNumber);
        
        
        
        
        
        final SelectItem sltManufacturerCode = new SelectItem("manufacturerCodeId");
        DataSourceTool dataSourceTool = new DataSourceTool();
        dataSourceTool.onCreateDataSource("partCatalog.technical", "manufacturerCodeForm_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				sltManufacturerCode.setOptionDataSource(dataSource);
				sltManufacturerCode.setValueField("manufacturerCodeId");
				sltManufacturerCode.setDisplayField("manufacturerCode");
				ListGridField lgfCode = new ListGridField("manufacturerCode");
				ListGridField lgfName = new ListGridField("manufacturerName");
				sltManufacturerCode.setPickListWidth(400);
				sltManufacturerCode.setPickListFields(lgfCode,lgfName);
				//sltManufacturerCode.setValue(record.getAttribute("manufacturerCodeId"));
			}
		});
        sltManufacturerCode.setHint(required);
        sltManufacturerCode.addDataArrivedHandler(new DataArrivedHandler() {
			
			@Override
			public void onDataArrived(DataArrivedEvent event) {
				sltManufacturerCode.setValue(record.getAttribute("manufacturerCodeId"));
				
				
			}
		});
        itemList.add(sltManufacturerCode);
        
        
        FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);
		
        IButton saveButton = new IButton();
        saveButton.setIcon("icons/save.png");
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.setAlign(Alignment.CENTER);
	    saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				form.saveData();
				
				ShowWindowTools.showCloseWindow(srcRect, RepairableCataLogModifyWindow.this, -1);
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
