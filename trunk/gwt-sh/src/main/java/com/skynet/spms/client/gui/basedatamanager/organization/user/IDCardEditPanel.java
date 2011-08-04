package com.skynet.spms.client.gui.basedatamanager.organization.user;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class IDCardEditPanel extends VLayout{
	
	private DynamicForm form;
	//private HLayout buttonPanel;
	
	public IDCardEditPanel(){
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.userinfo", "idCard_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				initPanel(dataSource);
			}
		});
	}
	public void initPanel(DataSource dataSource){
		form = new DynamicForm();
		form.setDataSource(dataSource);
		form.setWidth(350);
		form.setPadding(5);  
		form.setColWidths(150,200);

		final List<FormItem> itemList = new ArrayList<FormItem>();
		//身份识别卡ID
		HiddenItem hdnId = new HiddenItem("id"); 
		itemList.add(hdnId);
		//身份识别卡编号
		TextItem txtCardNumber = new TextItem("idCardNumber");
		txtCardNumber.setWidth(200);
		itemList.add(txtCardNumber);
		//有效日期
		DateItem datExpiryDate = new DateItem("expiryDate");
		datExpiryDate.setUseTextField(true);
		datExpiryDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATETIME);
		itemList.add(datExpiryDate);
		
		FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items); 
        
    	/*buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png");  
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(form.getItem("id").getValue() == null){
					Window.alert(ConstantsUtil.organizationConstants.alertForSelectUser());
					return ;
				}
				form.validate();
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
				form.reset();	
			}
		});
		
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);	
	    
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.setHeight100();*/
		
	    this.addMember(form);
	    /*this.addMember(wrapper);  
	    this.addMember(buttonPanel);*/
	    
	}
	
	public void fetchData(String cardId){
		Criteria criteria = new Criteria("id",cardId);
		form.fetchData(criteria, new DSCallback() {
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				Record record = response.getData()[0];
				form.getItem("id").setValue(record.getAttribute("id"));
				form.getItem("idCardNumber").setValue(record.getAttribute("idCardNumber"));
				form.getItem("expiryDate").setValue(record.getAttributeAsDate("expiryDate"));
			}
		});
	}
	public void clearFormValues(){
		form.clearValues();
	}	
}
