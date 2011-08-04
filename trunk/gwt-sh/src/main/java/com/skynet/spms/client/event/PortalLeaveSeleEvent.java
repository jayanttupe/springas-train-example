package com.skynet.spms.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PortalLeaveSeleEvent extends GwtEvent<PortalLeaveEventHandler>{
	
	public static final GwtEvent.Type<PortalLeaveEventHandler> HANDLER=new GwtEvent.Type<PortalLeaveEventHandler>();
	@Override
	public GwtEvent.Type<PortalLeaveEventHandler> getAssociatedType() {
		
		return HANDLER;
	}

	@Override
	protected void dispatch(PortalLeaveEventHandler handler) {
		handler.onLeavePortal(this);
	}
	
}
