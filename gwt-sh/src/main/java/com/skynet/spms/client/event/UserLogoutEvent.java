package com.skynet.spms.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class UserLogoutEvent extends GwtEvent<UserLogoutEventHandler>{
	
	public static final Type<UserLogoutEventHandler> HANDLER=new Type<UserLogoutEventHandler>();
	@Override
	public Type<UserLogoutEventHandler> getAssociatedType() {
		return HANDLER;
	}

	
	@Override
	protected void dispatch(UserLogoutEventHandler handler) {
		handler.onUserLogout(this);
	}
}
