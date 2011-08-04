package com.skynet.spms.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.skynet.spms.client.entity.ModuleDetail;
import com.smartgwt.client.widgets.tree.TreeNode;


public class RefreshModuleDetailEvent extends GwtEvent<RefreshModuleDetailEventHandler> {
	
	public static GwtEvent.Type<RefreshModuleDetailEventHandler> TYPE=new GwtEvent.Type<RefreshModuleDetailEventHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<RefreshModuleDetailEventHandler> getAssociatedType() {
		return TYPE;
	}

	private final String seleModName;
	
	private final String desc;
	
	private String businessKey;
	
	
	private TreeNode[] pathArray;
	
	
	public String getPathDesc(){
		String path="";
		for(TreeNode pathNode:pathArray){
			String pathDesc=pathNode.getAttributeAsString("desc");
			if(path.length()!=0){
				path+="->";
			}
			path+=pathDesc;
		}
		if(path.length()!=0){
			path+="->";
		}
		path+=desc;
		return path;
	}

	public TreeNode[] getPathArray() {
		return pathArray;
	}

	public void setPathArray(TreeNode[] pathArray) {
		this.pathArray = pathArray;
	}

	public RefreshModuleDetailEvent(String modName,String desc){
		this.seleModName=modName;
		this.desc=desc;
	}
	
	public void setBusinessKey(String key){
		this.businessKey=key;
	}
	
	public String getBusinessKey() {
		return businessKey;
	}



	public String getDesc(){
		return desc;
	}
	
	public String getSeleModName(){
		return seleModName;
	}

	
	@Override
	protected void dispatch(RefreshModuleDetailEventHandler handler) {
		handler.onSeleModuleDetail(this);
	}
	
}
