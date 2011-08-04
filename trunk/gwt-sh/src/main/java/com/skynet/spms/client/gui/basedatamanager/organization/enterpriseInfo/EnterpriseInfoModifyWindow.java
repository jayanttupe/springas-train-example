package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.entity.FieldInfo;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.Alignment;
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
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EnterpriseInfoModifyWindow extends BaseWindow{

	public EnterpriseInfoModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		EnterpriseInfoTreeGrid enterpriseInfoTreeGrid=(EnterpriseInfoTreeGrid)listGrid;
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, EnterpriseInfoModifyWindow.this, -1);
			}
		});
		final DynamicForm formBase = new DynamicForm();
		formBase.setWidth100();
		formBase.setPadding(5);
		formBase.setLayoutAlign(Alignment.LEFT);
		formBase.setDataSource(enterpriseInfoTreeGrid.getDataSource()); 
        formBase.setAutoFocus(false);  
        formBase.setUseAllDataSourceFields(false);   
        formBase.setNumCols(4);  
        formBase.setColWidths(110,140,100,250);   
        String requiredHint = "<font color=red>*</font>";
        LinkedHashMap<String,String> valueMap = new LinkedHashMap<String,String>();
		valueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
		valueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
        
        ListGridRecord selectedEnterprise = enterpriseInfoTreeGrid.getSelectedRecord();
        List<FormItem> itemList = new ArrayList<FormItem>();
        
        //企业Id
        final HiddenItem hdnId = new HiddenItem("id");
        hdnId.setValue(selectedEnterprise.getAttribute("id"));
        itemList.add(hdnId);
        
        //企业简称
		TextItem txtAbbreviation = new TextItem("abbreviation");
		txtAbbreviation.setValue(selectedEnterprise.getAttribute("abbreviation"));
		txtAbbreviation.setHint(requiredHint);
        txtAbbreviation.setRequired(true);
		itemList.add(txtAbbreviation);

		//公司标志//加载企业的图标；
		TextItem txtLogo = new TextItem("logoURL");
		txtLogo.setWidth(120); 
		txtLogo.setHeight(60);  
		txtLogo.setRowSpan(3);
		txtLogo.setValue(selectedEnterprise.getAttribute("logoURL"));
		txtLogo.setHint(requiredHint);
		txtLogo.setRequired(true);
		itemList.add(txtLogo);
		
		//母企业选择
		final SelectItem sltParentEnterprise = new SelectItem("parentId");
		sltParentEnterprise.setOptionDataSource(enterpriseInfoTreeGrid.getDataSource());
		if(!enterpriseInfoTreeGrid.isComacUser()){
			Criteria criteria = new Criteria("enterpriseId", enterpriseInfoTreeGrid.getEnterpriseId());
			sltParentEnterprise.setOptionCriteria(criteria);
		}
		sltParentEnterprise.setValueField("id");
		sltParentEnterprise.setDisplayField("abbreviation");
		sltParentEnterprise.setRequired(false);
		sltParentEnterprise.setAddUnknownValues(true);
		sltParentEnterprise.setAllowEmptyValue(true);
		sltParentEnterprise.setEmptyDisplayValue(ConstantsUtil.commonConstants.none());
		sltParentEnterprise.setValue(selectedEnterprise.getAttribute("parentId"));
		itemList.add(sltParentEnterprise);
		
		
		//母企业名称
		final TextItem txtParentCompany = new TextItem("parentCompany");			
		if(sltParentEnterprise.getValue() == null){
			txtParentCompany.setDisabled(true);
		}else{
			txtParentCompany.setValue(selectedEnterprise.getAttribute("parentCompany"));
		}
		itemList.add(txtParentCompany);
		
		//设置母企业名称的取值
		sltParentEnterprise.addChangedHandler(new ChangedHandler() {	
			@Override
			public void onChanged(ChangedEvent event) {
				String parentId = (String)sltParentEnterprise.getValue();
				String parentName = parentId==null? null : sltParentEnterprise.getDisplayValue();
				boolean disable = parentId==null? true : false;
				txtParentCompany.setValue(parentName);
				txtParentCompany.setDisabled(disable);
			}
		});
		
		//联系人
		TextItem txtLinkman = new TextItem("linkman");
		txtLinkman.setValue(selectedEnterprise.getAttribute("linkman"));
		txtLinkman.setHint(requiredHint);
		txtLinkman.setRequired(true);
		itemList.add(txtLinkman);
		
		//企业中文全称
	    TextItem txtEnterpriseName_zh = new TextItem("enterpriseName_zh");
	    txtEnterpriseName_zh.setValue(selectedEnterprise.getAttribute("enterpriseName_zh"));
	    txtEnterpriseName_zh.setWidth(250);
	    txtEnterpriseName_zh.setHint(requiredHint);
	    txtEnterpriseName_zh.setRequired(true);
	    itemList.add(txtEnterpriseName_zh);
	    
	    //电话号码
		TextItem txtTelephone = new TextItem("telephone");
		txtTelephone.setValue(selectedEnterprise.getAttribute("telephone"));
		txtTelephone.setHint(requiredHint); 
		txtTelephone.setRequired(true);
		txtTelephone.setValidateOnExit(true);
		txtTelephone.setValidators(ValidateUtils.telValidator(txtTelephone.getValueAsString()));
		itemList.add(txtTelephone);
		  
	  //企业英文全称
		TextItem txtEnterpriseName_en = new TextItem("enterpriseName_en");
		txtEnterpriseName_en.setValue(selectedEnterprise.getAttribute("enterpriseName_en"));
		txtEnterpriseName_en.setWidth(250);
		txtEnterpriseName_en.setHint(requiredHint);
		txtEnterpriseName_en.setRequired(true);
		itemList.add(txtEnterpriseName_en);
		
		//传真号码
	    TextItem txtFax = new TextItem("fax");
	    txtFax.setValue(selectedEnterprise.getAttribute("fax"));
	    txtFax.setHint(requiredHint);
	    txtFax.setRequired(true);
	    txtFax.setValidateOnExit(true);
	    txtFax.setValidators(ValidateUtils.faxValidator(txtFax.getValueAsString()));
	    itemList.add(txtFax);	
	    
		//企业主页
		TextItem txtHomePage = new TextItem("enterpriseHomepageURL");
		txtHomePage.setWidth(250);
		txtHomePage.setValue(selectedEnterprise.getAttribute("enterpriseHomepageURL"));
		txtHomePage.setHint(requiredHint);
		txtHomePage.setValidators(ValidateUtils.urlValidator(txtHomePage.getValueAsString()));
		txtHomePage.setRequired(true);
		txtHomePage.setValidateOnExit(true);
		itemList.add(txtHomePage);
		
		  //企业代码
	    TextItem txtCAGECode = new TextItem("cageCode");
	    txtCAGECode.setValue(selectedEnterprise.getAttribute("cageCode"));
	    txtCAGECode.setHint(requiredHint);
	    txtCAGECode.setRequired(true);
	    itemList.add(txtCAGECode);
	    
		//企业邮箱
		TextItem txtEmail = new TextItem("email");
	    txtEmail.setWidth(250);
	    txtEmail.setValue(selectedEnterprise.getAttribute("email"));
	    txtEmail.setHint(requiredHint);
		txtEmail.setRequired(true);
		txtEmail.setValidateOnExit(true);
		txtEmail.setValidators(ValidateUtils.emailValidator(txtEmail.getValueAsString()));
	    itemList.add(txtEmail);
	    
	    DataInfo dataInfo = enterpriseInfoTreeGrid.getDataInfo();
	    FieldInfo fieldInfo = dataInfo.getFieldInfoByName("m_CountryCode");
	    //国家代码
	    SelectItem sltCountryCode = (SelectItem)fieldInfo.generFormField();
	    sltCountryCode.setValue(selectedEnterprise.getAttribute("m_CountryCode"));
	    itemList.add(sltCountryCode);
	    
	    
	    //企业中文地址
	    TextAreaItem txtAddressZH = new TextAreaItem("address_zh");  
	    txtAddressZH.setWidth(250);  
	    txtAddressZH.setHeight(60); 
	    txtAddressZH.setRowSpan(2);
	    txtAddressZH.setValue(selectedEnterprise.getAttribute("address_zh"));
	    txtAddressZH.setHint(requiredHint);
	    txtAddressZH.setRequired(true);
	    itemList.add(txtAddressZH);
	    
		//是否主营企业
		SelectItem sltIsMain = new SelectItem("mainEnterprise");
		sltIsMain.setValueMap(valueMap);
		sltIsMain.setValue(selectedEnterprise.getAttribute("mainEnterprise"));
		itemList.add(sltIsMain);
		if(ClassifyHelper.isNotMainEnterprise(enterpriseInfoTreeGrid.getDataSource().getID())){
			sltIsMain.setDefaultValue("false");
			sltIsMain.setDisabled(true);
		}
		
		//是否已启动业务
	    SelectItem sltIsRunning = new SelectItem("running");
	    sltIsRunning.setValueMap(valueMap);
	    sltIsRunning.setValue(selectedEnterprise.getAttribute("running"));
	    sltIsRunning.setHint(requiredHint);
	    sltIsRunning.setRequired(true);
	    itemList.add(sltIsRunning);
  
	    //企业英文地址
		TextAreaItem txtAddressEN = new TextAreaItem("address_en"); 
		txtAddressEN.setWidth(250); 
		txtAddressEN.setHeight(60);  
		txtAddressEN.setRowSpan(2);

		txtAddressEN.setValue(selectedEnterprise.getAttribute("address_en"));
		txtAddressEN.setHint(requiredHint);
		txtAddressEN.setRequired(true);
		itemList.add(txtAddressEN);
		
		//代码类别：客户识别码、供应商代码、运代商代码、维修商代码、、、
		final String prop = ClassifyHelper.getPropByDataSource(enterpriseInfoTreeGrid.getDataSource().getID());
		if(prop != null){
			TextItem txtBaseCode = new TextItem(prop);
			txtBaseCode.setValue(selectedEnterprise.getAttribute(prop));
			txtBaseCode.setHint(requiredHint);
			txtBaseCode.setRequired(true);
			itemList.add(txtBaseCode);
		}
		
		//客户分类代码
		if(ClassifyHelper.isCustomer(enterpriseInfoTreeGrid.getDataSource().getID())){
			SelectItem sltCategoryCode = (SelectItem)dataInfo.getFieldInfoByName("customerCategoryCode").generFormField();
			sltCategoryCode.setValue(selectedEnterprise.getAttribute("customerCategoryCode")); 
			itemList.add(sltCategoryCode);
		}
		
		//是否指定商户
		if(ClassifyHelper.canAppoint(enterpriseInfoTreeGrid.getDataSource().getID())){
			SelectItem sltIsAppoint = new SelectItem("appoint");
			sltIsAppoint.setValueMap(valueMap);
			sltIsAppoint.setHint(requiredHint);
			sltIsAppoint.setRequired(true);
			sltIsAppoint.setValue(selectedEnterprise.getAttribute("appoint"));
			itemList.add(sltIsAppoint);
		}
		
		//邮政编码
		TextItem txtPostCode = new TextItem("postCode");
		txtPostCode.setValue(selectedEnterprise.getAttribute("postCode"));
		txtPostCode.setHint(requiredHint);
		txtPostCode.setRequired(true);
		txtPostCode.setValidateOnExit(true);
		txtPostCode.setValidators(ValidateUtils.ZipCodeValidator(txtPostCode.getValueAsString()));
	    itemList.add(txtPostCode);
	    
	    FormItem[] items = new FormItem[itemList.size()];
	    itemList.toArray(items);
	    formBase.setFields(items);
	    
	    //按钮条
	    HLayout buttonPanel = new BtnsHLayout();
        buttonPanel.setHeight(40);
        
	    IButton saveButton = new IButton();
	    saveButton.setIcon("icons/save.png"); 
	    saveButton.setTitle(ConstantsUtil.buttonConstants.saveButton());
	    saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(formBase.validate()){
					formBase.saveData();
					 SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
							@Override
							public void execute(Boolean value) {
								if (value.equals(true)) {
									//SC.say("删除成功！！");
									ShowWindowTools.showCloseWindow(srcRect, EnterpriseInfoModifyWindow.this, -1);
								}
							}
						});
					
					}
				}
			});
			//}
				/*if(formBase.validate()){
					formBase.saveData();
					//ShowWindowTools.showCloseWindow(srcRect, EnterpriseInfoModifyWindow.this, -1);
					SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (value.equals(true)) {
								ShowWindowTools.showCloseWindow(srcRect, EnterpriseInfoModifyWindow.this, -1);
							}
						}
					});
					
				}
			}
		});*/
	    
	    IButton cancelButton = new IButton();
	    cancelButton.setIcon("icons/remove.png");  
	    cancelButton.setTitle(ConstantsUtil.buttonConstants.cancelButton());
	    cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				formBase.reset();
				
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
		tileGrid.addChild(formBase);
		
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
