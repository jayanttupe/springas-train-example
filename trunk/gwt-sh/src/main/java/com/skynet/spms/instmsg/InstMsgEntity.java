package com.skynet.spms.instmsg;

import java.io.Serializable;

import com.skynet.common.prop.PropertyEntity;
import com.skynet.spms.client.entity.MsgEntity;


public class InstMsgEntity implements InstMsg,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 108554931821292623L;

	private String from;
	
	private String msg;
	
	private String toTarget;
	
	private boolean isBroadCast;
	public void setIsBroadCast(boolean isBroad) {
		this.isBroadCast=isBroad;
	}
	
	public boolean isBroadCast(){
		return isBroadCast;
	}
		
	public void setFrom(String from) {
		this.from = from;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public void setToTarget(String toTarget) {
		this.toTarget = toTarget;
	}
	
	public String getTarget(){
		return toTarget;
	}


	@Override
	public MsgEntity getMsgEntity(PropertyEntity prop) {
		MsgEntity entity=new MsgEntity();
		entity.setMessage(prop.getProperty("inst.msg.char.tmp",from,msg));
		return entity;
	}




}
