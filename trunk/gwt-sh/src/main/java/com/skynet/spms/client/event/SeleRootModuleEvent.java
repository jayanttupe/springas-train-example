package com.skynet.spms.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SeleRootModuleEvent extends GwtEvent<SeleRootModuleEventHandler> {

	public static GwtEvent.Type<SeleRootModuleEventHandler> TYPE=new GwtEvent.Type<SeleRootModuleEventHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SeleRootModuleEventHandler> getAssociatedType() {
		return TYPE;
	}

	private final String modName;
	
	private final String subItem;
	
	public SeleRootModuleEvent(String name,String subItem){
		this.modName=name;
		this.subItem=subItem;
	}
	
	public String getSubItem(){
		return subItem;
	}
	
	public String getSeleModName(){
		return modName;
	}
	
	@Override
	protected void dispatch(SeleRootModuleEventHandler handler) {
		handler.onSeleRootModule(this);
	}
	

}
