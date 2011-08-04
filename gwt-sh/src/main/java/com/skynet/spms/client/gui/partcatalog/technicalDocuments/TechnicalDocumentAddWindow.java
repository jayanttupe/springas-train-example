package com.skynet.spms.client.gui.partcatalog.technicalDocuments;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class TechnicalDocumentAddWindow extends BaseWindow{

	public TechnicalDocumentAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax,srcRect, listGrid, iconUrl);
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, final ListGrid listGrid) {
		TechnicalDocumentListGrid technicalDocumentListGrid=(TechnicalDocumentListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, TechnicalDocumentAddWindow.this, -1);
			}
		});
		final DynamicForm form = new DynamicForm();
		form.setDataSource(technicalDocumentListGrid.getDataSource());
		form.setPadding(5);
		form.setWidth(300);
		form.setColWidths(100, 200); 
		String requiredHint = "<font color=red>*</font>";
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		//存储位置
		TextItem tiLocation = new TextItem("location");
		tiLocation.setWidth(200);
		tiLocation.setHint(requiredHint);
		tiLocation.setRequired(true);
        itemList.add(tiLocation);
		//技术出版物
        DataInfo dataInfo = technicalDocumentListGrid.getDataInfo();
		SelectItem sltTechnicalPublishType = (SelectItem)dataInfo.getFieldInfoByName("m_TechnicalPublishType").generFormField();  
		sltTechnicalPublishType.setDefaultToFirstOption(true);
		sltTechnicalPublishType.setWidth(200);
		itemList.add(sltTechnicalPublishType);
	
        FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);
        
        HLayout buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
        IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
        saveButton.setIcon("icons/save.png");
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(form.validate()){
					form.saveData();
					form.clear();
					ShowWindowTools.showCloseWindow(srcRect,TechnicalDocumentAddWindow.this, -1);
			
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
		tileGrid.setBorder("0px solid #9C9C9C");
		tileGrid.addChild(form);
		
		HLayout buttonGrid = new HLayout();
		buttonGrid.setWidth100();
		buttonGrid.setHeight("10%");	
		buttonGrid.setBorder("0px solid #9C9C9C");
		buttonGrid.addChild(buttonPanel);

		VLayout vLayout = new VLayout();
		vLayout.addMember(tileGrid); 
		vLayout.addMember(buttonGrid);
		return vLayout;
	}
}
