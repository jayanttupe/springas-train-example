package com.skynet.spms.client.gui.contractManagement.common;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * 合同模板下拉列表数据源
 * 
 * @author tqc
 * 
 */
public class ContractSelectDS extends DataSource {

	public ContractSelectDS(String id) {
		setID(id);
		setRecordXPath("/templates/template");

		DataSourceTextField idFiled = new DataSourceTextField("id", "id", 128,
				true);
		idFiled.setPrimaryKey(true);
		
		DataSourceTextField templateName = new DataSourceTextField("name",
				"name", 128, true);
		
		DataSourceTextField typeAlias = new DataSourceTextField("typeAlias_zh",
				"typeAlias_zh", 128, true);

		DataSourceTextField fileName = new DataSourceTextField("fileName",
				"fileName", 128, true);

		setFields(idFiled, templateName, typeAlias,fileName);

		setDataURL("spms/xml.do?fn=templates_config&r="+Math.random());

	}
}
