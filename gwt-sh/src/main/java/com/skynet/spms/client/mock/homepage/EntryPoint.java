package com.skynet.spms.client.mock.homepage;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.smartgwt.client.SmartGwtEntryPoint;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.HLayout;

public class EntryPoint extends SmartGwtEntryPoint{
	
	
	public void onModuleLoad() {
		HandlerManager eventBus = new HandlerManager(null);

		HLayout main=new HLayout();
		
		Frame frame = new Frame();
		frame.setUrl(Page.getAppDir()+"content/demo1.html");
//		add(frame);
//
//		HTMLPanel pane=new HTMLPanel();
		
//		pane.setContentsURL();
		
		main.addMember(frame);
		
		main.draw();
		
	}

}
