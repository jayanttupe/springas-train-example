package com.skynet.spms.client.gui.basedatamanager.organization.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserInfoEditPanel extends VLayout{
	
	private DynamicForm form;
	private HLayout buttonPanel;
	
	public UserInfoEditPanel(){
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.userinfo", "userInformation_dataSource", new PostDataSourceInit() {
			
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				initPanel(dataSource);
			}
		});
	}
	public void initPanel(DataSource dataSource){
		form = new DynamicForm();
		form.setDataSource(dataSource);
		form.setWidth(680);
		form.setPadding(5);
		form.setNumCols(4);  
		form.setColWidths(120,200,160,200);
		String required = "<font color=red>*</font>";
		
		final List<FormItem> itemList = new ArrayList<FormItem>();
		//用户信息ID
		HiddenItem hdnId = new HiddenItem("id"); 
		itemList.add(hdnId);
		//性别
		RadioGroupItem radSex = new RadioGroupItem("sex");
		radSex.setWidth(150);
		radSex.setVertical(false);
		LinkedHashMap<String,String> sexMap = new LinkedHashMap<String,String>();
		sexMap.put("0",ConstantsUtil.organizationConstants.male());
		sexMap.put("1", ConstantsUtil.organizationConstants.female());
		radSex.setValueMap(sexMap);
		radSex.setDefaultValue("0");
		itemList.add(radSex);
		//联系地址
		TextItem txtContactAddresses = new TextItem("contactAddresses");
		txtContactAddresses.setHint(required);
		txtContactAddresses.setRequired(true);
		txtContactAddresses.setWidth(280);
		itemList.add(txtContactAddresses);
		//手机
		TextItem txtMobile = new TextItem("mobile");
		txtMobile.setValidators(ValidateUtils.mobileValidator(txtMobile.getValueAsString()));
		txtMobile.setValidateOnExit(true);
		txtMobile.setHint(required);
		txtMobile.setRequired(true);
		txtMobile.setWidth(200);
		itemList.add(txtMobile);
		//备注
		TextAreaItem txtRemark = new TextAreaItem("remark");	
		txtRemark.setWidth(280);
		txtRemark.setRowSpan(3);
		itemList.add(txtRemark);
		//传真
		TextItem txtFax = new TextItem("fax");
		txtFax.setValidators(ValidateUtils.faxValidator(txtFax.getValueAsString()));
		txtFax.setValidateOnExit(true);
		txtFax.setHint(required);
		txtFax.setRequired(true);
		txtFax.setWidth(200);
		itemList.add(txtFax);
		//电话
		TextItem txtTel = new TextItem("telephone");
		txtTel.setValidators(ValidateUtils.telValidator(txtTel.getValueAsString()));
		txtTel.setValidateOnExit(true);
		txtTel.setHint(required);
		txtTel.setRequired(true);
		txtTel.setWidth(200);
		itemList.add(txtTel);
		
		FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items); 
        
    	buttonPanel = new BtnsHLayout();
		buttonPanel.setHeight(30);
		IButton saveButton = new IButton(ConstantsUtil.buttonConstants.saveButton());
		saveButton.setIcon("icons/save.png"); 
		saveButton.addClickHandler(new ClickHandler() {	
			@Override
			public void onClick(ClickEvent event) {
				if(form.getItem("id").getValue() == null){
					SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
					return ;
				}
				form.validate();
				form.saveData(new DSCallback() {	
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						form.rememberValues();
						SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess());
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
		buttonPanel.addMember(saveButton);
		buttonPanel.addMember(cancelButton);
		buttonPanel.addMember(helpButton);	
	    
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.setHeight100();
		
		
	    this.addMember(form);
	    this.addMember(wrapper);  
	    this.addMember(buttonPanel); 
	    
	}
	
	public void fetchData(String userInfoId){
		Criteria criteria = new Criteria("id",userInfoId);
		form.fetchData(criteria, new DSCallback() {
			@Override
			public void execute(DSResponse response, Object rawData, DSRequest request) {
				Record record = response.getData()[0];
				setFormValues(form,record);
			}
		});
	}
	private void setFormValues(DynamicForm form, Record record){
		for(FormItem item : form.getFields()){
			String name = item.getName();
			item.setValue(record.getAttribute(name));
		}
	}
	public void clearFormValues(){
		form.clearValues();
	}	
}
