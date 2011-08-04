package com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AircraftConfigCatalogModifyWindow extends BaseWindow{
	
	public AircraftConfigCatalogModifyWindow(String windowTitle, boolean isMax,
			final	Rectangle srcRect, ListGrid listGrid, String iconUrl) {
			super(windowTitle, isMax,srcRect, listGrid, iconUrl);
		}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid=(AircraftConfigCatalogListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, AircraftConfigCatalogModifyWindow.this, -1);
			}
		});
		final DynamicForm form = new DynamicForm();	
		form.setDataSource(aircraftConfigCatalogListGrid.getDataSource());
		form.setPadding(5);
		form.setWidth(300);
		form.setColWidths(100,200);
				
		final List<FormItem> itemList = new ArrayList<FormItem>();
		DataInfo dataInfo = aircraftConfigCatalogListGrid.getDataInfo();
	
		//获取所选中的记录数据
		final Record record=aircraftConfigCatalogListGrid.getSelectedRecord();
		
		HiddenItem hdnId = new HiddenItem("id");
		hdnId.setValue(record.getAttribute("id"));
		itemList.add(hdnId);
		
		//适用机型
		SelectItem siAircraftModelIdentifier = (SelectItem)dataInfo.getFieldInfoByName("m_AircraftModelIdentifier").generFormField();  
		siAircraftModelIdentifier.setWidth(200);
		siAircraftModelIdentifier.setValue(record.getAttribute("m_AircraftModelIdentifier"));
		itemList.add(siAircraftModelIdentifier);
		
       //飞机尾号
		TextItem tiAircraftTailNumber = new TextItem("aircraftTailNumber");  
		tiAircraftTailNumber.setWidth(200);
		tiAircraftTailNumber.setValue(record.getAttribute("aircraftTailNumber"));
		tiAircraftTailNumber.setValidators(ValidateUtils.StringLenValidator(1, 6));
		tiAircraftTailNumber.setRequired(true);
		itemList.add(tiAircraftTailNumber);
        
		//飞机注册号
		TextItem tiAircraftRegistrationNumber = new TextItem("aircraftRegistrationNumber");  
		tiAircraftRegistrationNumber.setWidth(200);
		tiAircraftRegistrationNumber.setValue(record.getAttribute("aircraftRegistrationNumber"));
		tiAircraftRegistrationNumber.setValidators(ValidateUtils.StringLenValidator(1, 6));
		tiAircraftRegistrationNumber.setRequired(true);
		itemList.add(tiAircraftRegistrationNumber);
		
		//国籍
		SelectItem sltNationality = (SelectItem)dataInfo.getFieldInfoByName("m_CountryCode").generFormField();  
		sltNationality.setWidth(200);
		sltNationality.setValue(record.getAttribute("m_CountryCode"));
		itemList.add(sltNationality);
		
		//飞机运营人
		TextItem tiOperator = new TextItem("operator");  
		tiOperator.setWidth(200);
		tiOperator.setValue(record.getAttribute("operator"));
		itemList.add(tiOperator);
		
		//飞机所有人
		TextItem tiOwner = new TextItem("owner");  
		tiOwner.setWidth(200);
		tiOwner.setValue(record.getAttribute("owner"));
		itemList.add(tiOwner);
		
		//出厂时间
		final DateItem datFactoryDate = new DateItem("factoryDate");
		datFactoryDate.setWidth(200);
		datFactoryDate.setValue(record.getAttributeAsDate("factoryDate"));
		itemList.add(datFactoryDate);
		
		//注册时间
		DateItem datRegistrationDate = new DateItem("registrationDate");  
		datRegistrationDate.setWidth(200);
		datRegistrationDate.setValue(record.getAttributeAsDate("registrationDate"));
		itemList.add(datRegistrationDate);
		
        FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);
        //setFormValues(form, record);
		
        //按钮条
        HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(30);
        IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
        saveButton.setIcon("icons/save.png");
	    saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect, AircraftConfigCatalogModifyWindow.this, -1);
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
		buttonGrid.addChild(buttonPanel);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
	
	private void setFormValues(DynamicForm form, Record record){
		for(FormItem item : form.getFields()){
			String name = item.getName();
			item.setValue(record.getAttribute(name));
		}
	}
	
}
