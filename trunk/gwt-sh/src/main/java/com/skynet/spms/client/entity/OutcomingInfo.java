package com.skynet.spms.client.entity;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OutcomingInfo implements IsSerializable{
	
	private String desc;
	
	private String outcoming;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getOutcoming() {
		return outcoming;
	}

	public void setOutcoming(String outcoming) {
		this.outcoming = outcoming;
	}
	
	
	
	

}
