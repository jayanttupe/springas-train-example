package com.skynet.spms.client.entity;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class FeatureInfo implements IsSerializable{

	private String name;
	
	private boolean enable;
	
	private String desc;
	
	private String modName;
	
	public void setName(String name){
		this.name=name;
	}
	public void setDesc(String desc){
		this.desc=desc;
	}
	
	
	public String getName(){
		return name;
	}
	
	public ToolStripButton getButton(){
		
		ToolStripButton btn=new ToolStripButton(name);
		btn.setTitle(desc);
		if(!enable){
			btn.disable();
		}		
		return btn;
	}
	public void setEnable(boolean enable) {
		this.enable=enable;
	}
}
