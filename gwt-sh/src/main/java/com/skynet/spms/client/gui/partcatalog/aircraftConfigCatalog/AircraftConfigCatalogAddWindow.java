package com.skynet.spms.client.gui.partcatalog.aircraftConfigCatalog;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class AircraftConfigCatalogAddWindow extends BaseWindow{

	public AircraftConfigCatalogAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax,srcRect, listGrid, iconUrl);
	}
	
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		AircraftConfigCatalogListGrid aircraftConfigCatalogListGrid=(AircraftConfigCatalogListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, AircraftConfigCatalogAddWindow.this, -1);
			}
		});
		
		final DynamicForm form = new DynamicForm();
		form.setDataSource(aircraftConfigCatalogListGrid.getDataSource());
		form.setPadding(5);
		form.setWidth(300);
		form.setColWidths(100,200);
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		DataInfo dataInfo = aircraftConfigCatalogListGrid.getDataInfo();
		
		//适用机型
		 SelectItem siAircraftModelIdentifier = (SelectItem)dataInfo.getFieldInfoByName("m_AircraftModelIdentifier").generFormField();  
		 siAircraftModelIdentifier.setDefaultToFirstOption(true);
		 siAircraftModelIdentifier.setWidth(200);
		 itemList.add(siAircraftModelIdentifier);
		 
		//飞机尾号
		 TextItem siAircraftTailNumber = new TextItem("aircraftTailNumber");
		 siAircraftTailNumber.setValidators(ValidateUtils.StringLenValidator(1, 6));
		 siAircraftTailNumber.setRequired(true);
		 siAircraftTailNumber.setWidth(200);
	     itemList.add(siAircraftTailNumber);

		//飞机注册号
		 TextItem siAircraftRegistrationNumber = new TextItem("aircraftRegistrationNumber");
		 siAircraftRegistrationNumber.setValidators(ValidateUtils.StringLenValidator(1, 6));
		 siAircraftRegistrationNumber.setRequired(true);
		 siAircraftRegistrationNumber.setWidth(200);
		 itemList.add(siAircraftRegistrationNumber);
	     
		//国籍 
	     SelectItem siCountryCode = (SelectItem)dataInfo.getFieldInfoByName("m_CountryCode").generFormField();  
	     siCountryCode.setDefaultToFirstOption(true);
	     siCountryCode.setWidth(200);
	     itemList.add(siCountryCode);

	   //飞机运营人
		 TextItem tiOperator = new TextItem("operator");
		 tiOperator.setRequired(true);
		 tiOperator.setWidth(200);
	     itemList.add(tiOperator);
	     
	   //飞机所有人
		 TextItem tiOwner = new TextItem("owner");
		 tiOwner.setRequired(true);
		 tiOwner.setWidth(200);
	     itemList.add(tiOwner);
	     
	   //出厂时间
	     DateItem datFactoryDate = new DateItem("factoryDate");
	     datFactoryDate.setWidth(200);
	     itemList.add(datFactoryDate);
	     
	   //注册时间
	     DateItem datRegistrationDate = new DateItem("registrationDate");
	     datRegistrationDate.setWidth(200);
	     itemList.add(datRegistrationDate);

        FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);
        this.addMember(form);
        
        HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(30);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(form.validate()){
                   form.saveData();
                   clear();
				ShowWindowTools.showCloseWindow(srcRect, AircraftConfigCatalogAddWindow.this, -1);
			}
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
	
}
