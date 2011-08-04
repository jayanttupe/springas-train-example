package com.skynet.spms.client.entity;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class BindModInfo extends JavaScriptObject{

	protected BindModInfo(){
		
	}
	
	public final native String getModName()
	/*-{
	  	return this.formName;
	 }-*/;
	
	public final native String getBusinessKey()
	/*-{
	  	return this.refKey;
	 }-*/;
	
	public final native String getTitle()
	/*-{
	  return this.title;
	 }-*/;
	
	public static native JsArray<BindModInfo> asArrayOfModList(String json)
	/*-{
		return eval(json);
	}-*/;
}
