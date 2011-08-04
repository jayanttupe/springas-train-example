package com.skynet.spms.client.gui.basedatamanager.organization.user;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
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
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;


public class UserViewWindow extends BaseWindow {

	    private DynamicForm userform;
	    private DynamicForm formcard;
		public UserViewWindow(String windowTitle, boolean isMax, Rectangle srcRect,
				ListGrid listGrid, String iconUrl) {
			super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		}

		@Override
		protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
			final UserManagerListgrid userManagerListgrid=(UserManagerListgrid)listGrid;
			addCloseClickHandler(new CloseClickHandler() {
				public void onCloseClick(CloseClientEvent event) {
					ShowWindowTools.showCloseWindow(srcRect, UserViewWindow.this, -1);
				}
			});

			final DynamicForm form = new DynamicForm();
			form.setDataSource(userManagerListgrid.getDataSource());
			form.setPadding(5);
			form.setWidth(300);
			form.setColWidths(100,200);
			
			final List<FormItem> itemList = new ArrayList<FormItem>();
			String required = "<font color=red>*</font>";
			DataInfo dataInfo = userManagerListgrid.getDataInfo();
			DataSourceTool dataSourceTool = new DataSourceTool();
			
			//获取所选中的记录数据
			final Record record=userManagerListgrid.getSelectedRecord();
			
			HiddenItem hdnId = new HiddenItem("id");
			hdnId.setValue(record.getAttribute("id"));
			itemList.add(hdnId);
			
			//用户名
			TextItem txtUsername = new TextItem("username");
			txtUsername.setRequired(true);
			txtUsername.setHint(required);
			txtUsername.setWidth(200);
			txtUsername.setValidators(ValidateUtils.isCharValidator());
			txtUsername.setValue(record.getAttribute("username"));
			itemList.add(txtUsername);
			
			//真实姓名
			TextItem txtRealname = new TextItem("realname");
			txtRealname.setWidth(200);
			txtRealname.setValue(record.getAttribute("realname"));
			itemList.add(txtRealname);
			
			//工号
			if(userManagerListgrid.isComacUser()){
				TextItem txtJobNumber = new TextItem("jobNumber");
				txtJobNumber.setWidth(200);
				txtJobNumber.setValue(record.getAttribute("jobNumber"));
				itemList.add(txtJobNumber);
			}
			
			//密码
			PasswordItem txtPassword = new PasswordItem("password");
			txtPassword.setWidth(200);
			txtPassword.setValue(record.getAttribute("password"));
			txtPassword.setHint(required);
			txtPassword.setRequired(true);
			itemList.add(txtPassword);
			//确认密码
			PasswordItem txtPwdConfirm = new PasswordItem();
			txtPwdConfirm.setTitle(ConstantsUtil.organizationConstants.confirmPassword());
			txtPwdConfirm.setHint(required);
			txtPwdConfirm.setRequired(true);
			txtPwdConfirm.setValidators(ValidateUtils.pwdConfirmValidator(txtPwdConfirm.getValueAsString()));
			txtPwdConfirm.setValidateOnExit(true);
			txtPwdConfirm.setWidth(200);
			itemList.add(txtPwdConfirm);
			
			//Email
			TextItem txtEmail = new TextItem("email");
			txtEmail.setWidth(200);
			txtEmail.setValue(record.getAttribute("email"));
			itemList.add(txtEmail);
			
			//所属企业 
			if(userManagerListgrid.isComacUser()){
				final SelectItem sltEnterprise = new SelectItem("enterpriseId");
				dataSourceTool.onCreateDataSource("organization.enterprise", "baseEnterprise_dataSource", new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
						sltEnterprise.setOptionDataSource(dataSource);
						sltEnterprise.setValueField("id");
						sltEnterprise.setDisplayField("abbreviation");
						sltEnterprise.setDefaultToFirstOption(true);
					}
				});
				sltEnterprise.addChangedHandler(new ChangedHandler() {
					@Override
					public void onChanged(ChangedEvent event) {
						form.clearValue("departmentId");
						form.clearValue("dutyId");
					}
				});
				sltEnterprise.setWidth(200);
				sltEnterprise.setValue(record.getAttribute("m_BaseEnterpriseInformation.id"));
				itemList.add(sltEnterprise);
				
				//所属部门
				final SelectItem sltDepartment = new SelectItem("departmentId"){
					@Override
					protected Criteria getPickListFilterCriteria() {
						String enterpriseId = (String)sltEnterprise.getValue();
						if(enterpriseId == null){
							enterpriseId = "clear";
						}
						Criteria criteria = new Criteria("enterpriseId",enterpriseId);
						return criteria;
					}
				};
				dataSourceTool.onCreateDataSource("organization.enterprise.department", "department_dataSource", new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
						sltDepartment.setOptionDataSource(dataSource);
						sltDepartment.setValueField("id");
						sltDepartment.setDisplayField("department");
						//sltDepartment.setDefaultToFirstOption(true);
					}
				});
				sltDepartment.addChangedHandler(new ChangedHandler() {
					@Override
					public void onChanged(ChangedEvent event) {
						form.clearValue("dutyId");
					}
				});
				sltDepartment.setWidth(200);
				sltDepartment.setAllowEmptyValue(true);
				sltDepartment.setValue(record.getAttribute("m_DepartmentInformation.id"));
				itemList.add(sltDepartment);
				
				//职务
				final SelectItem sltDuty = new SelectItem("dutyId"){
					@Override
					protected Criteria getPickListFilterCriteria() {
						String departmentId = (String)sltDepartment.getValue();
						if(departmentId == null){
							departmentId = "clear";
						}
						Criteria criteria = new Criteria("departmentId",departmentId);
						return criteria;
					}
				};
				dataSourceTool.onCreateDataSource("organization.enterprise.department", "duty_dataSource", new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
						sltDuty.setOptionDataSource(dataSource);
						sltDuty.setValueField("id");
						sltDuty.setDisplayField("dutyName");
						//sltDuty.setDefaultToFirstOption(true);
					}
				});
				sltDuty.setWidth(200);
				sltDuty.setAllowEmptyValue(true);
				sltDuty.setValue(record.getAttribute("m_Duty.id"));
				itemList.add(sltDuty);
			}else{
				final SelectItem sltEnterprise = new SelectItem("enterpriseId");
				dataSourceTool.onCreateDataSource("organization.enterprise", "baseEnterprise_dataSource", new PostDataSourceInit() {
					@Override
					public void doPostOper(final DataSource dataSource, DataInfo dataInfo) {
						Criteria criteria = new Criteria("enterpriseId", userManagerListgrid.getEnterpriseId());
						sltEnterprise.setOptionDataSource(dataSource);
						sltEnterprise.setOptionCriteria(criteria);
						sltEnterprise.setValueField("id");
						sltEnterprise.setDisplayField("abbreviation");
						sltEnterprise.setDefaultToFirstOption(true);
						
					}
				});
				sltEnterprise.setWidth(200);
				sltEnterprise.setValue(record.getAttribute("m_BaseEnterpriseInformation.id"));
				itemList.add(sltEnterprise);
			}
			
			
			//启用状态
			SelectItem sltEnableStatus = (SelectItem)dataInfo.getFieldInfoByName("m_EnableStatus").generFormField();
			sltEnableStatus.setWidth(200);
			sltEnableStatus.setValue(record.getAttribute("m_EnableStatus"));
			itemList.add(sltEnableStatus);
			
			FormItem[] items = new FormItem[itemList.size()];
	        itemList.toArray(items);
	        form.setFields(items);
	        
	        
	        //----------------------------------------------------------------------
		    
	        
	    		userform = new DynamicForm();
	    		userform.setDataSource(userManagerListgrid.getDataSource());
	    		userform.setWidth(680);
	    		userform.setPadding(5);
	    		userform.setNumCols(4);  
	    		userform.setColWidths(120,200,160,200);
	    		//String required = "<font color=red>*</font>";
	    		
	    		final List<FormItem> useritemList = new ArrayList<FormItem>();
	    		//用户信息ID
	    		HiddenItem userhdnId = new HiddenItem("id"); 
	    		useritemList.add(userhdnId);
	    		//性别
	    		RadioGroupItem radSex = new RadioGroupItem("sex");
	    		radSex.setWidth(150);
	    		radSex.setVertical(false);
	    		LinkedHashMap<String,String> sexMap = new LinkedHashMap<String,String>();
	    		sexMap.put("0",ConstantsUtil.organizationConstants.male());
	    		sexMap.put("1", ConstantsUtil.organizationConstants.female());
	    		radSex.setValueMap(sexMap);
	    		radSex.setDefaultValue("0");
	    		useritemList.add(radSex);
	    		//联系地址
	    		TextItem txtContactAddresses = new TextItem("contactAddresses");
	    		txtContactAddresses.setHint(required);
	    		txtContactAddresses.setRequired(true);
	    		txtContactAddresses.setWidth(280);
	    		useritemList.add(txtContactAddresses);
	    		//手机
	    		TextItem txtMobile = new TextItem("mobile");
	    		txtMobile.setValidators(ValidateUtils.mobileValidator(txtMobile.getValueAsString()));
	    		txtMobile.setValidateOnExit(true);
	    		txtMobile.setHint(required);
	    		txtMobile.setRequired(true);
	    		txtMobile.setWidth(200);
	    		useritemList.add(txtMobile);
	    		//备注
	    		TextAreaItem txtRemark = new TextAreaItem("remark");	
	    		txtRemark.setWidth(280);
	    		txtRemark.setRowSpan(3);
	    		useritemList.add(txtRemark);
	    		//传真
	    		TextItem txtFax = new TextItem("fax");
	    		txtFax.setValidators(ValidateUtils.faxValidator(txtFax.getValueAsString()));
	    		txtFax.setValidateOnExit(true);
	    		txtFax.setHint(required);
	    		txtFax.setRequired(true);
	    		txtFax.setWidth(200);
	    		useritemList.add(txtFax);
	    		//电话
	    		TextItem txtTel = new TextItem("telephone");
	    		txtTel.setValidators(ValidateUtils.telValidator(txtTel.getValueAsString()));
	    		txtTel.setValidateOnExit(true);
	    		txtTel.setHint(required);
	    		txtTel.setRequired(true);
	    		txtTel.setWidth(200);
	    		useritemList.add(txtTel);
	    		
	    		FormItem[] useritems = new FormItem[useritemList.size()];
	            useritemList.toArray(useritems);
	            userform.setFields(useritems); 

	    	   // this.addMember(userform);
	            formcard = new DynamicForm();
	    		formcard.setDataSource(userManagerListgrid.getDataSource());
	    		formcard.setWidth(350);
	    		formcard.setPadding(5);  
	    		formcard.setColWidths(150,200);

	    		final List<FormItem> itemListcard = new ArrayList<FormItem>();
	    		//身份识别卡ID
	    		HiddenItem hdnIdcard = new HiddenItem("id"); 
	    		itemList.add(hdnIdcard);
	    		//身份识别卡编号
	    		TextItem txtCardNumber = new TextItem("idCardNumber");
	    		txtCardNumber.setWidth(200);
	    		itemList.add(txtCardNumber);
	    		//有效日期
	    		DateItem datExpiryDate = new DateItem("expiryDate");
	    		datExpiryDate.setUseTextField(true);
	    		datExpiryDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATETIME);
	    		itemList.add(datExpiryDate);
	    		
	    		FormItem[] itemscard = new FormItem[itemList.size()];
	            itemList.toArray(itemscard);
	            formcard.setFields(itemscard); 
	            
	            VLayout vLayout = new VLayout();
				vLayout.addMember(form);
				vLayout.addMember(userform);
				vLayout.addMember(formcard);
				return vLayout;

	    	}	    
	}



