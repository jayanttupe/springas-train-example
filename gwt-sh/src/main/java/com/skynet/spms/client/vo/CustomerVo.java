package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CustomerVo  implements IsSerializable {

	private String id;
	private String code;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
