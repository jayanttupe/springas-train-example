package com.skynet.spms.client.gui.partcatalog.repairableCatalog.licenseStatement;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
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
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LicenseStatementAddWindow extends BaseWindow{

	public LicenseStatementAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		IndexInfoListGrid indexInfoListGrid=(IndexInfoListGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, LicenseStatementAddWindow.this, -1);
			}
		});
	
		setWidth(470);
		setHeight(280);
		setTitle("新建维护许可证信息");
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
		//form.setDataSource(appliactionDataPanel.getDataSource());
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setMargin(10);
		form.setNumCols(4);  
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		form.enableHiliting(false);
	
		//许可证编号字段未找到
		TextItem tiLicenseNumber = new TextItem("licenseNumber","许可证编号");
		
		TextItem tiAuthorizedInstitutions = new TextItem("authorizedInstitutions","授权机构");
		
		//授权日期字段未找到
		DateItem diLicenseeDate = new DateItem("licenseeDate", "授权日期");  
		diLicenseeDate.setUseTextField(true);  
		diLicenseeDate.setUseMask(true);  
		
		//终止日期字段未找到
		DateItem diStopDate = new DateItem("stopDate", "终止日期");  
		diStopDate.setUseTextField(true);  
		diStopDate.setUseMask(true);
	
		HLayout buttonPanel = new HLayout(10);
		IButton saveButton = new IButton();
		saveButton.setIcon("icons/save.png");
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.setAlign(Alignment.CENTER);
	    IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.setIcon("icons/remove.png");
	    
	    IButton helpButton = new IButton();
	    helpButton.setIcon("book_help.png");
	    helpButton.setTitle(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
			}
		});
	    buttonPanel.addMember(saveButton);
	    buttonPanel.addMember(cancelButton);
	    buttonPanel.addMember(helpButton);
		
	   saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect, LicenseStatementAddWindow.this, -1);
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
