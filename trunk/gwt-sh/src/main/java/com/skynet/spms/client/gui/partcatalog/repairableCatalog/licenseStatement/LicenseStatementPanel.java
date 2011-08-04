package com.skynet.spms.client.gui.partcatalog.repairableCatalog.licenseStatement;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LicenseStatementPanel extends VLayout{

	private DynamicForm form;
	public LicenseStatementPanel(){
		initPanel();
	}
	
	public void fetchData(Record selectedRecord){
		form.getItem("id").setValue(selectedRecord.getAttribute("m_BasicInformation.id"));
	}
	public void initPanel(){
		form = new DynamicForm();
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setMargin(10);
		form.setNumCols(1);  
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		
		HiddenItem hdnId = new HiddenItem("id");
		
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
		
		form.setColWidths(120, 300, 150, 300);  
		//form.setDataSource(indexInfoXmlDS);
		form.setFields(tiLicenseNumber,tiAuthorizedInstitutions,diLicenseeDate,diStopDate);
		
		this.addMember(form);
		
		HLayout buttonPanel = new HLayout();
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		IButton helpButton = new IButton();
	    helpButton.setIcon("book_help.png");
	    helpButton.setTitle(ConstantsUtil.buttonConstants.helpButton());
	    helpButton.addClickHandler(new ClickHandler() {
						@Override
			public void onClick(
					com.smartgwt.client.widgets.events.ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	    buttonPanel.addMember(saveButton);
	    buttonPanel.addMember(cancelButton);
	    buttonPanel.addMember(helpButton);
		
		
		this.addMember(buttonPanel);
		
	}
	
}
