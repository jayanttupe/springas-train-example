/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentialsRecord;

import java.util.logging.Logger;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class CredentialsRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("CredentialsRecordListgrid");

	/**
	 * 
	 */
	public void drawCredentialsRecordListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 证书存档编号
		ListGridField certificateCodeField = new ListGridField("certificateCode");
		// 电子证件文件
		ListGridField certificateFileNameField = new ListGridField("certificateFileName");
		certificateFileNameField.setType(ListGridFieldType.LINK);
		certificateFileNameField.setLinkURLPrefix("ftp://spmsftp:spmsftp@10.20.42.250/cerpdf/");
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 序号/批号
		ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		// 单位
		ListGridField unitField = new ListGridField("unit");
        
		certificateCodeField.setCanFilter(true);
		certificateFileNameField.setCanFilter(true);
		partNumberField.setCanFilter(true);
		partSerialNumberField.setCanFilter(true);
		quantityField.setCanFilter(true);
		unitField.setCanFilter(true);
		
		setFields(certificateCodeField
				,certificateFileNameField
				,partNumberField
				,partSerialNumberField
				,quantityField
				,unitField
				);
		setCellHeight(22);
	}

}
