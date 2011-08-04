package com.skynet.spms.client.gui.basedatamanager.organization.user;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;


public class UserManagerListgrid extends ListGrid {

	private Logger log = Logger.getLogger("UserManagerListgrid");
	
	private DataInfo dataInfo;
	
	private boolean isComacUser;
	
	private String enterpriseId;
	
	private List<ListGridField> fieldList = new ArrayList<ListGridField>();

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

	public UserManagerListgrid() {
		
	}
	
	public void drawSimpleListgrid(){
		drawBaseListgrid();
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
        setFields(fields);
	}
	
	public void drawUserManagerListgrid(){
		drawBaseListgrid();
		//启用状态
		ListGridField statusField = new ListGridField("m_EnableStatus");
		statusField.setCanFilter(true);
		fieldList.add(statusField);
		
		ListGridField[] fields = new ListGridField[fieldList.size()];
		fieldList.toArray(fields);
        setFields(fields);
	}
	
	private void drawBaseListgrid(){
		setCanSelectText(true);
		setCanEdit(false);
		setShowAllRecords(false);
        setShowFilterEditor(true);
        setSelectionType(SelectionStyle.SIMPLE);
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setCellHeight(22); 
        
        
        //用户名
		ListGridField nameField = new ListGridField("username");
		nameField.setCanFilter(true);
		fieldList.add(nameField);
       //真是姓名
		ListGridField trueNameField = new ListGridField("realname");
		trueNameField.setCanFilter(true);
		fieldList.add(trueNameField);
		//工作编号
		ListGridField jobNumberField = new ListGridField("jobNumber");
		jobNumberField.setCanFilter(true);
		fieldList.add(jobNumberField);
        //邮编
		ListGridField emailField = new ListGridField("email");
		emailField.setCanFilter(true);
		fieldList.add(emailField);
         //所属企业
		ListGridField enterpriseField = new ListGridField("m_BaseEnterpriseInformation.abbreviation");
		enterpriseField.setCanFilter(true);
		fieldList.add(enterpriseField);
		
		//所属部门
		ListGridField departmentField = new ListGridField("m_DepartmentInformation.department");
		departmentField.setCanFilter(true);
		fieldList.add(departmentField);
		//职务
		ListGridField dutyField = new ListGridField("m_Duty.dutyName");
		dutyField.setCanFilter(true);
		fieldList.add(dutyField);
	}
}
