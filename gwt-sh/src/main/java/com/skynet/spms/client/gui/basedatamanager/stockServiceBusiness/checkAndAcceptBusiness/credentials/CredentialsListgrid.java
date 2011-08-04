package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentials;

import java.util.logging.Logger;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CredentialsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("CredentialsListgrid");
	DataSource userDataSource;

	public DataSource getUserDataSource() {
		return userDataSource;
	}

	public CredentialsListgrid() {
		
	}
	
	public void drawCredentialsListgrid(){
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 备件中心位置
		ListGridField partCentreLocationField = new ListGridField("partCentreLocation");
		// 证书存档编号
		ListGridField credentialsCodeField = new ListGridField("credentialsCode");
		// 证书存档位置
		ListGridField stackPositionCodeField = new ListGridField("stackPositionCode");
		// 证书扫描文件
		ListGridField credentialsFileField = new ListGridField("credentialsFile");
		// 使用状况
		ListGridField credentialsUseStatusField = new ListGridField("credentialsUseStatus");
		// 备注
		ListGridField remarkField = new ListGridField("remark");

		credentialsCodeField.setCanFilter(true);
		partCentreLocationField.setCanFilter(true);
		stackPositionCodeField.setCanFilter(true);
		credentialsFileField.setCanFilter(true);
		credentialsUseStatusField.setCanFilter(true);
		remarkField.setCanFilter(true);	
		
		setFields(credentialsCodeField
				,partCentreLocationField
				,stackPositionCodeField
				,credentialsFileField
				,credentialsUseStatusField
				,remarkField
				);
		setCellHeight(22);
	}
}