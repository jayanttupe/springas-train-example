package com.skynet.spms.client.gui.basedatamanager.organization.user;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.service.CheckUniqueService;
import com.skynet.spms.client.service.CheckUniqueServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserAddWindow extends BaseWindow {

	private CheckUniqueServiceAsync checkUniqueService = GWT.create(CheckUniqueService.class);
	
	public UserAddWindow(String windowTitle, boolean isMax, Rectangle srcRect,
			ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		final UserManagerListgrid userManagerListgrid=(UserManagerListgrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, UserAddWindow.this, -1);
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
		
		//用户名
		final TextItem txtUsername = new TextItem("username");
		txtUsername.setRequired(true);
		txtUsername.setHint(required);
		txtUsername.setWidth(200);
		txtUsername.setValidators(ValidateUtils.isCharValidator());
		itemList.add(txtUsername);
		
		//真实姓名
		TextItem txtRealname = new TextItem("realname");
		txtRealname.setWidth(200);
		itemList.add(txtRealname);
		
		//工号
		if(userManagerListgrid.isComacUser()){
			TextItem txtJobNumber = new TextItem("jobNumber");
			txtJobNumber.setWidth(200);
			itemList.add(txtJobNumber);
		}
		
		//密码
		PasswordItem txtPassword = new PasswordItem("password");
		txtPassword.setWidth(200);
		txtPassword.setHint(required);
		txtPassword.setRequired(true);
		itemList.add(txtPassword);
		//确认密码
		PasswordItem txtPwdConfirm = new PasswordItem();
		txtPwdConfirm.setTitle(ConstantsUtil.organizationConstants.confirmPassword());
		txtPwdConfirm.setValidators(ValidateUtils.pwdConfirmValidator(txtPwdConfirm.getValueAsString()));
		txtPwdConfirm.setValidateOnExit(true);
		txtPwdConfirm.setRequired(true);
		txtPwdConfirm.setHint(required);
		txtPwdConfirm.setWidth(200);
		itemList.add(txtPwdConfirm);
		
		//Email
		TextItem txtEmail = new TextItem("email");
		txtEmail.setWidth(200);
		//txtEmail.setValidators(ValidateUtils.emailValidator(txtEmail.getValueAsString()));
		//txtEmail.setValidateOnExit(true);
		//txtEmail.setHint(required);
		//txtEmail.setRequired(true);
		itemList.add(txtEmail);
		
		//所属企业 
		if(userManagerListgrid.isComacUser()){
			final SelectItem sltEnterprise = new SelectItem("enterpriseId");
			dataSourceTool.onCreateDataSource("organization.enterprise", "baseEnterprise_dataSource", new PostDataSourceInit() {
				@Override
				public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
					dataSource.fetchData();
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
			itemList.add(sltEnterprise);
		}
		
		
		//启用状态
		SelectItem sltEnableStatus = (SelectItem)dataInfo.getFieldInfoByName("m_EnableStatus").generFormField();
		sltEnableStatus.setDefaultToFirstOption(true);
		sltEnableStatus.setWidth(200);
		itemList.add(sltEnableStatus);
		
		FormItem[] items = new FormItem[itemList.size()];
        itemList.toArray(items);
        form.setFields(items);

        //按钮条
        HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(40);
        IButton saveButton = new IButton();
        saveButton.setIcon("icons/save.png");  
        saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String entityClassName = "com.skynet.spms.persistence.entity.organization.userInformation.User";
				String value = txtUsername.getValueAsString();
				checkUniqueService.isUnique(entityClassName, "username", value, new AsyncCallback<Boolean>() {		
					@Override
					public void onSuccess(Boolean unique) {
						if(!unique){
							SC.warn("用户名已使用，请重新输入！");
							return;
						}
						if(form.validate()){
							form.saveData();
							//clear();
							SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
								@Override
								public void execute(Boolean value) {
									if (value.equals(true)) {
										ShowWindowTools.showCloseWindow(srcRect, UserAddWindow.this, -1);
									}
								}
							});
							
						}	
					}
					
					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
	    
	    IButton cancelButton = new IButton();
	    cancelButton.setIcon("icons/remove.png");  
	    cancelButton.setTitle(ConstantsUtil.buttonConstants.cancelButton());
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
