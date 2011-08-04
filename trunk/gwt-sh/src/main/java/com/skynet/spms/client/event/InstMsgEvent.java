package com.skynet.spms.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.skynet.spms.client.entity.MsgEntity;


public class InstMsgEvent extends GwtEvent<InstMsgEventHandler>{
	
	public static GwtEvent.Type<InstMsgEventHandler> HANDLER=new GwtEvent.Type<InstMsgEventHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<InstMsgEventHandler> getAssociatedType() {
		return HANDLER;
	}

	private final MsgEntity msg;
	
	public InstMsgEvent(MsgEntity detail){
		this.msg=detail;
	}
	
	public MsgEntity getInstMsg(){
		return msg;
	}
	
	@Override
	protected void dispatch(InstMsgEventHandler handler) {
		handler.onReceiveInstMsg(this);
	}
	
}
