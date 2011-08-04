package com.skynet.spms.client.event;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.GwtEvent;
import com.skynet.spms.client.entity.BindModInfo;
import com.skynet.spms.client.entity.BindOutcomingInfo;

public class PopupTaskFormEvent extends GwtEvent<PopupTaskFormEventHandler>{

	public static final Type<PopupTaskFormEventHandler> HANDLER=new Type<PopupTaskFormEventHandler>(); 
	@Override
	public Type<PopupTaskFormEventHandler> getAssociatedType() {
		
		return HANDLER;
	}

	@Override
	protected void dispatch(PopupTaskFormEventHandler handler) {

		handler.onDisplayForm(this);
		
	}
	
	private final String taskForm;
	
	private final String businessKey;
	
	private String taskID;
	
	
	private JsArray<BindOutcomingInfo>  outcomingArray;
	
	
	public PopupTaskFormEvent(BindModInfo modInfo){
		this.taskForm=modInfo.getModName();
		this.businessKey=modInfo.getBusinessKey();
	}
	
	public void setTaskID(String id){
		this.taskID=id;
	}
	
	public String getTaskID(){
		return taskID;
	}
	
	public String getTaskForm() {
		return taskForm;
	}


	public String getBusinessKey() {
		return businessKey;
	}
	
	public JsArray<BindOutcomingInfo>  getOutcomingArray(){
		return outcomingArray;
	}
	
	public void setOutcomingArray(JsArray<BindOutcomingInfo> array){
		this.outcomingArray=array;
	}

}
