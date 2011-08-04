package com.skynet.spms.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ClickFeatureEvent extends GwtEvent<ClickFeatureEventHandler> {

	public static Type<ClickFeatureEventHandler> HANDLER=new Type<ClickFeatureEventHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ClickFeatureEventHandler> getAssociatedType() {
		return HANDLER;
	}

	
	private final String name;
	
	private final String moduleName;
		
	public ClickFeatureEvent(String name,String moduleName){
		this.name=name;
		this.moduleName=moduleName;
	}
	
	
	
	public String getModuleName(){
		return moduleName;
	}
	
	
	
	public String getName(){
		return name;
	}
	
	@Override
	protected void dispatch(ClickFeatureEventHandler handler) {
		handler.onFeatureClick(this);	
		
	}

}
