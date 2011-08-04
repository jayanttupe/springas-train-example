package com.skynet.spms.client.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO for base code.
 * 
 * @author tqc
 * 
 */
public class BaseCode implements IsSerializable {

	private String id;
	private String code;
	
	public BaseCode() {
	}

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
