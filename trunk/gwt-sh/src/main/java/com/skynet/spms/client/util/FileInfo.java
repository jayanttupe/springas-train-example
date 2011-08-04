package com.skynet.spms.client.util;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.smartgwt.client.util.Page;

public class FileInfo extends JavaScriptObject {

	// Overlay types always have protected, zero-arg ctors
	protected FileInfo() {
	}
	
	public final String getDownloadLink(){
		return Page.getAppDir()+"download/file/"+getIndex() +"/name/"+getFileNameUrl();
	}
	
	// Typically, methods on overlay types are JSNI
	public final native String getfileName()
	/*-{
		return this.fileName;
	}-*/;

	public final native String getIndex()
	/*-{
		return this.index;
	}-*/;

	public final native Long getSize()
	/*-{
		return this.size;
	}-*/;

	public final native String getType()
	/*-{
		return this.type;
	}-*/;
	
	public final native String getFileNameUrl()
	/*-{
		return this.fileNameUrl;
	}-*/;

	public static native JsArray<FileInfo> asArrayOfFileInfo(String json)
	/*-{
		return eval(json);
	}-*/;
}
