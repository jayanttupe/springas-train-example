package com.skynet.spms.client.entity;

import com.google.gwt.user.client.rpc.IsSerializable;

public class QueryParam implements IsSerializable{
	
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	

}
