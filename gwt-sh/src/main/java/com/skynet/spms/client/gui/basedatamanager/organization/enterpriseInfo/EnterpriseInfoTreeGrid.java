package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import java.util.ArrayList;
import java.util.List;

import com.skynet.spms.client.entity.DataInfo;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class EnterpriseInfoTreeGrid extends TreeGrid {
	private boolean isComacUser;
	private String enterpriseId;
	private DataInfo dataInfo;
	
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public boolean isComacUser() {
		return isComacUser;
	}
	public void setComacUser(boolean isComacUser) {
		this.isComacUser = isComacUser;
	}
	public DataInfo getDataInfo() {
		return dataInfo;
	}
	public void setDataInfo(DataInfo dataInfo) {
		this.dataInfo = dataInfo;
	}

	public EnterpriseInfoTreeGrid() {
	}

	public void drawEnterpriseInfoTreeGrid() {
		setShowConnectors(true);
        setWidth100();  
        setNodeIcon("icons/16/person.png");  
        setFolderIcon("icons/16/person.png");
        setShowAllRecords(true);
        setShowOpenIcons(false);  
        setShowDropIcons(false);  
        setClosedIconSuffix("");
        setCanEdit(false);  
        
        setSelectionAppearance(SelectionAppearance.CHECKBOX); 
        setSelectionType(SelectionStyle.SIMPLE); 
        setCellHeight(22);
        
        List<TreeGridField> list = new ArrayList<TreeGridField>();
        
        //企业简称
        TreeGridField tgfAbbreviation = new TreeGridField("abbreviation",160); 
        tgfAbbreviation.setFrozen(true);
        list.add(tgfAbbreviation);
        //企业代码
        TreeGridField tgfCAGECode = new TreeGridField("cageCode",100);  
        list.add(tgfCAGECode);
        //是否主营企业
		TreeGridField tgfIsMain = new TreeGridField("mainEnterprise",100);
	    list.add(tgfIsMain);
		//是否已运营
	    TreeGridField tgfIsRunning = new TreeGridField("running",100);
		list.add(tgfIsRunning);
		
        //商户代码
        String prop = ClassifyHelper.getPropByDataSource(getDataSource().getID());
        if(prop != null){
        	TreeGridField tgfClassify = new TreeGridField(prop,100);
        	list.add(tgfClassify);
        }
        
        //客户分类代码
        if(ClassifyHelper.isCustomer(getDataSource().getID())){
        	TreeGridField tgfCustomerCategoryCode = new TreeGridField("customerCategoryCode",100);
        	list.add(tgfCustomerCategoryCode);
        }
        
        //是否指定商户
        if(ClassifyHelper.canAppoint(getDataSource().getID())){
        	TreeGridField tgfIsAppoint = new TreeGridField("appoint",100);
        	list.add(tgfIsAppoint);
        }
        
        //企业中文名称
	    TreeGridField tgfNameCN = new TreeGridField("enterpriseName_zh",100);
		list.add(tgfNameCN);
	    //企业英文名称
		TreeGridField tgfNameEN = new TreeGridField("enterpriseName_en",100);
		list.add(tgfNameEN);
		//企业Email
		TreeGridField tgfEmail = new TreeGridField("email",100);   
		list.add(tgfEmail);
		//联系人
		TreeGridField tgfLinkman = new TreeGridField("linkman",100);
	    list.add(tgfLinkman);
		//电话
	    TreeGridField tgfTelephone = new TreeGridField("telephone",100);
	    list.add(tgfTelephone);    
	    //传真
	    TreeGridField tgfFax = new TreeGridField("fax",100);
		list.add(tgfFax);
		//邮编
	    TreeGridField tgfPostCode = new TreeGridField("postCode",100);
	    list.add(tgfPostCode);
        //企业中文地址
        TreeGridField tgfAddressCN = new TreeGridField("address_zh",100);  
		list.add(tgfAddressCN);
        //企业英文地址
		TreeGridField tgfAddressEN = new TreeGridField("address_en",100); 
		list.add(tgfAddressEN);
		//国家代码
        TreeGridField tgfCountryCode = new TreeGridField("m_CountryCode",100);  
        list.add(tgfCountryCode);
		//企业主页
        TreeGridField tgfHomePage = new TreeGridField("enterpriseHomepageURL",100);
        list.add(tgfHomePage);
		//企业标志URL
		TreeGridField tgfLogo = new TreeGridField("logoURL",100);
		list.add(tgfLogo);
		//母企业名称
		TreeGridField tgfParentCompany = new TreeGridField("parentCompany",100);
		list.add(tgfParentCompany);
		
	    TreeGridField[] fields = new TreeGridField[list.size()];
	    list.toArray(fields);
        this.setFields(fields);
        
        addDataArrivedHandler(new DataArrivedHandler() {
			@Override
			public void onDataArrived(DataArrivedEvent event) {
				getData().openAll();
			}
		}); 
     }  	   
}  