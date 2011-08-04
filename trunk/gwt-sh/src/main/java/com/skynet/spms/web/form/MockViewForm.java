package com.skynet.spms.web.form;

import java.util.Date;
import java.util.List;

public class MockViewForm {
	
	private String entityId;
	
	
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	private List<MockItem> itemList;
	
	private String name;
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}

	private float val;
	
	private String[] valList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getValList() {
		return valList;
	}

	public void setValList(String[] valList) {
		this.valList = valList;
	}

	public List<MockItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<MockItem> itemList) {
		this.itemList = itemList;
	}
	
	

}
