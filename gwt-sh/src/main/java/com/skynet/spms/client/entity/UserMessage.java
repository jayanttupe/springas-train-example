package com.skynet.spms.client.entity;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserMessage implements IsSerializable{
	
	private transient Logger log = Logger.getLogger("UserMsgMember Panel");

	private String msgID;
	
	private String context;
	
	private String from;
	
	private Date time;

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	
	public static interface ReadedHandler {
		
		void doReadedMsg(String msgID);
		
	}

}
