package com.skynet.spms.client.vo.repairmodule;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.rpc.IsSerializable;

public class MockRecord implements IsSerializable {
	public static final String MOCK_A="inspect";
	public static final String MOCK_B="repair";

	private String field1;
	private String field2;
	private String field3;
	List<MockItem> items = new ArrayList<MockItem>();

	public MockRecord() {
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public List<MockItem> getItems() {
		return items;
	}

	public void setItems(List<MockItem> items) {
		this.items = items;
	}

}
