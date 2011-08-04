package com.skynet.spms.persistence.entity.base.remarkEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RelEntityInfo {
	
	private String relBusKey;

	private String tableName;

	@Column(name = "REL_BUSINESS_KEY")
	public String getRelBusKey() {
		return relBusKey;
	}

	public void setRelBusKey(String relBusKey) {
		this.relBusKey = relBusKey;
	}

	@Column(name = "REL_TABLE_NAME")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
