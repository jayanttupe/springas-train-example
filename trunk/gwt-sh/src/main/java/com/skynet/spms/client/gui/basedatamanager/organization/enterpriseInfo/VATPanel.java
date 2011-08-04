package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import java.util.ArrayList;
import java.util.List;

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
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class VATPanel extends VLayout{

	private DynamicForm editorForm;
	private HLayout buttonPanel;
	
	public void fetchData(String vatId){
		Criteria criteria = new Criteria("id",vatId);
		editorForm.fetchData(criteria, new DSCallback() {
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				Record record = response.getData()[0];
				editorForm.getItem("id").setValue(record.getAttribute("id"));
				editorForm.getItem("vatIdentificationNumber").setValue(record.getAttribute("vatIdentificationNumber"));
				editorForm.getItem("vatAddressTelephone").setValue(record.getAttribute("vatAddressTelephone"));
				editorForm.getItem("vatEnterpriseName").setValue(record.getAttribute("vatEnterpriseName"));
				editorForm.getItem("vatBankInformation").setValue(record.getAttribute("vatBankInformation"));
			}
		});
		/*String enterpriseId = selectedEnterprise.getAttribute("id");
		editorForm.getItem("enterpriseId").setValue(enterpriseId);
		
		String vid = selectedEnterprise.getAttribute("m_VAT.id");
		if(vid != null){
			vatId = vid;
		}
		if(vatId != null){
			Criteria criteria = new Criteria("id",vatId);
			criteria.addCriteria("enterpriseId", enterpriseId);
			editorForm.fetchData(criteria);
			Record record = editorForm.getDataSource().getCacheData()[0];
			editorForm.getItem("enterpriseId").setValue(record.getAttribute("enterpriseId"));
			editorForm.getItem("id").setValue(record.getAttribute("id"));
			editorForm.getItem("vatIdentificationNumber").setValue(record.getAttribute("vatIdentificationNumber"));
			editorForm.getItem("vatAddressTelephone").setValue(record.getAttribute("vatAddressTelephone"));
			editorForm.getItem("vatEnterpriseName").setValue(record.getAttribute("vatEnterpriseName"));
			editorForm.getItem("vatBankInformation").setValue(record.getAttribute("vatBankInformation"));
		}*/
		
	}
	public void clearFormValues(){
		editorForm.clearValues();
	}
	public VATPanel() {

		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise", "vat_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				
				initPanel(dataSource);
			}
		});
	}
	private void initPanel(DataSource dataSource){
		setWidth100();
		editorForm = new DynamicForm();
		editorForm.setMargin(5);
		editorForm.setNumCols(4);
		editorForm.setWidth(740);
		editorForm.setColWidths(120,200,120,280);
		editorForm.setDataSource(dataSource);
		
		String requiredHint = "<font color=red>*</font>";
		
		List<FormItem> itemList = new ArrayList<FormItem>();
		
		/*final HiddenItem hdnEnterpriseId = new HiddenItem("enterpriseId");
		itemList.add(hdnEnterpriseId);*/
		
		HiddenItem hdnId = new HiddenItem("id");
		itemList.add(hdnId);
		//增值纳税识别号
		TextItem txtIdentificationNumber = new TextItem("vatIdentificationNumber");
		txtIdentificationNumber.setWidth(200);
		txtIdentificationNumber.setHint(requiredHint);
		txtIdentificationNumber.setRequired(true);
		itemList.add(txtIdentificationNumber);
		
		//增值地址及电话
		TextAreaItem txtVatAddressTelephone = new TextAreaItem("vatAddressTelephone");
		txtVatAddressTelephone.setWidth(280);
		txtVatAddressTelephone.setRowSpan(3);
		txtVatAddressTelephone.setHint(requiredHint);
		txtVatAddressTelephone.setRequired(true);
		itemList.add(txtVatAddressTelephone);
		
		//增值税企业名称
		TextItem txtVatEnterpriseName = new TextItem("vatEnterpriseName");
		txtVatEnterpriseName.setWidth(200);
		txtVatEnterpriseName.setHint(requiredHint);
		txtVatEnterpriseName.setRequired(true);
		itemList.add(txtVatEnterpriseName);
		
		//增值税银行信息
		TextItem txtVatBankInformation = new TextItem("vatBankInformation");
		txtVatBankInformation.setWidth(200);
		txtVatBankInformation.setHint(requiredHint);
		txtVatBankInformation.setRequired(true);
		itemList.add(txtVatBankInformation);
		
		FormItem[] items = new FormItem[itemList.size()];
		itemList.toArray(items);
		editorForm.setFields(items);
	
		buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(editorForm.getItem("id").getValue() == null){
					 SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
							
						//Window.alert(ConstantsUtil.organizationConstants.alertForSelectEnterprise());
					return ;
				}
				editorForm.validate();
				editorForm.saveData(new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						editorForm.rememberValues();
						
						SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess());
						
						//Window.alert(ConstantsUtil.commonConstants.alertForSaveSuccess());
					}
				});
			}
		});	
		IButton cancelButton = new IButton(ConstantsUtil.buttonConstants.cancelButton());
		cancelButton.setIcon("icons/remove.png");
		cancelButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				editorForm.reset();
				
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
		
		this.addMember(editorForm);
		this.addMember(wrapper);  
		this.addMember(buttonPanel);

	}
	
}
