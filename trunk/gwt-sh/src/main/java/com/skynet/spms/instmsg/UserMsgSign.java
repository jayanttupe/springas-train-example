package com.skynet.spms.instmsg;

import java.io.Serializable;

public class UserMsgSign implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5367432924006188505L;

	private String toUser;
	
	private String msgType;

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	

}
