package com.skynet.spms.client.entity;

import com.google.gwt.user.client.rpc.IsSerializable;


public class MsgEntity implements IsSerializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3303516126850956365L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
