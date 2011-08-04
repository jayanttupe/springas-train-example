package com.skynet.spms.client.entity;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class BindOutcomingInfo extends JavaScriptObject{
	
	protected BindOutcomingInfo(){
		
	}
	
	public final native String getDesc()
	/*-{
  	return this.desc;
 	}-*/;
	
	public final native String getOutcoming()
	/*-{
  	return this.out;
 	}-*/;

	public static native JsArray<BindOutcomingInfo> asArrayOfOutcoming(String json)
	/*-{
		return eval(json);
	}-*/;

}
