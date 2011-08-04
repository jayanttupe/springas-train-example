package com.skynet.spms.client.gui.contractManagement.common;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;

/**
 * 合同条款数据源
 * 
 * @author tqc
 * 
 */
public class ContractTreeDS extends DataSource {

	public ContractTreeDS(String fileName, String id) {
		setID(id);
		setTitleField("title_en");
		setDataFormat(DSDataFormat.XML);
		setRecordXPath("/provisions/provision");

		DataSourceTextField itemNameField = new DataSourceTextField("id", "id");
		itemNameField.setPrimaryKey(true);

		DataSourceTextField parentField = new DataSourceTextField("parentId",
				"parentId");
		parentField.setRequired(true);
		parentField.setRootValue("root");
		parentField.setForeignKey("provision.id");

		DataSourceTextField titleField = new DataSourceTextField("title_en",
				"title_en", 128, true);
		titleField.setRequired(true);

		setFields(itemNameField, parentField, titleField);

		setDataURL("spms/xml.do?fn="
				+ fileName.substring(0, fileName.lastIndexOf(".")) + "&r="
				+ Math.random());
	}
}
