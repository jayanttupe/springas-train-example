package com.skynet.spms.client.gui.partcatalog.supplierPriceCatalog.partSupplierPrice;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomSelectItem;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
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
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class PartSupplierPriceModifyWindow extends BaseWindow {

	public PartSupplierPriceModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax,srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, final ListGrid listGrid) {
		PartSupplierPriceListGrid partSupplierPriceListGrid=(PartSupplierPriceListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, PartSupplierPriceModifyWindow.this, -1);
			}
		});
		
		final DynamicForm form = new DynamicForm();
		form.setDataSource(partSupplierPriceListGrid.getDataSource());
		form.setPadding(5);
		form.setWidth(380);
		form.setColWidths(100,280);
		String required = "<font color=red>*</font>";	
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		
		final Record record = partSupplierPriceListGrid.getSelectedRecord();
		
		HiddenItem hdnId = new HiddenItem("id");
		hdnId.setValue(record.getAttribute("id"));
		itemList.add(hdnId);
		
		final SelectItem sltPartIndexId = new CustomSelectItem(
        		"partIndexId", 
        		"partCatalog.technical", 
        		"partIndex_dataSource", 
        		"id", 
        		"manufacturerPartNumber",
        		"manufacturerPartNumber",
        		"partNumber");
		sltPartIndexId.setPickListWidth(400);
        sltPartIndexId.setHint(required);
        sltPartIndexId.setWidth(200);
        sltPartIndexId.setValue(record.getAttribute("partIndexId"));
        itemList.add(sltPartIndexId);
        
        
      //供应商代码
        final SelectItem sltSupplierCode = new CustomSelectItem(
        		"supplierCodeId",
        		"partCatalog.supplierPrice",
        		"supplierCodeForm_dataSource",
        		"supplierCodeId",
        		"supplierCode",
        		"supplierCode",
        		"supplierName");
        sltSupplierCode.setPickListWidth(400);
        sltSupplierCode.setHint(required);
        sltSupplierCode.setWidth(200);
        sltSupplierCode.setValue(record.getAttribute("supplierCodeId"));
		itemList.add(sltSupplierCode);
		
        //发布版本号
        TextItem txtReleaseVersion = new TextItem("releaseVersion");
        txtReleaseVersion.setHint(required);
        txtReleaseVersion.setRequired(true);
        txtReleaseVersion.setWidth(200);
        txtReleaseVersion.setValue(record.getAttribute("releaseVersion"));
        itemList.add(txtReleaseVersion);
        
        //版次   不可修改
        TextItem lgfEdition = new TextItem("edition");
        lgfEdition.setWidth(200);
        lgfEdition.setValue(record.getAttribute("edition"));
        lgfEdition.setDisabled(true);
        itemList.add(lgfEdition);
		
        //备注信息
	    TextAreaItem taiRemark=new TextAreaItem("remark");
	    taiRemark.setWidth(280);  
		taiRemark.setRowSpan(3); 
		taiRemark.setValue(record.getAttribute("remark"));
	    itemList.add(taiRemark);

		FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);
        this.addMember(form);
        
        HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(40);
        IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
        saveButton.setIcon("icons/save.png");
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(form.validate()){
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect,PartSupplierPriceModifyWindow.this, -1);
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