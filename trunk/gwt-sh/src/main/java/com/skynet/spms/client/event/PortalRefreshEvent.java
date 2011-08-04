package com.skynet.spms.client.event;

import com.google.gwt.event.shared.GwtEvent;


public class PortalRefreshEvent extends GwtEvent<PortalRefreshEventHandler>{

	public static final GwtEvent.Type<PortalRefreshEventHandler> HANDLER=new GwtEvent.Type<PortalRefreshEventHandler>();
	@Override
	public GwtEvent.Type<PortalRefreshEventHandler> getAssociatedType() {
		
		return HANDLER;
	}

	@Override
	protected void dispatch(PortalRefreshEventHandler handler) {
		handler.onRefreshPortal(this);
	}
	
	
}
