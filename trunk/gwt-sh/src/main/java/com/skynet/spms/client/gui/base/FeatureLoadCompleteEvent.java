package com.skynet.spms.client.gui.base;

import com.google.gwt.event.shared.GwtEvent;

public class FeatureLoadCompleteEvent extends
		GwtEvent<FeatureLoadCompleteEventHandler> {

	public static Type<FeatureLoadCompleteEventHandler> HANDLER = new Type<FeatureLoadCompleteEventHandler>();

	public com.google.gwt.event.shared.GwtEvent.Type<FeatureLoadCompleteEventHandler> getAssociatedType() {
		return HANDLER;
	}
	
	

	public FeatureLoadCompleteEvent() {
	}
	

	protected void dispatch(FeatureLoadCompleteEventHandler handler) {
		handler.onFeatureLoadComplete(this);
	}

}
