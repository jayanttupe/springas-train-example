package com.skynet.spms.client.gui.portal;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Frame;
import com.skynet.spms.client.constants.PortalConstants;
import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

public class StaticInfoMember implements PortalMember {

	private Logger log = Logger.getLogger("static info");

	private PortalConstants portalConst=GWT.create(PortalConstants.class);

	private VLayout layout=new VLayout();
	
	
	public StaticInfoMember(){
		
		for(int i=0;i<3;i++){
			Frame htmlPane = new Frame();
			htmlPane.setWidth("99%");
			htmlPane.setHeight("99%");
		    htmlPane.setTitle("title");
		    htmlPane.setUrl(Page.getAppDir()+"content/demo"+i+".html");
		    
		    Window win=new Window();
		    win.setOverflow(Overflow.VISIBLE);
		    win.setShowMaximizeButton(false);
		    win.setShowMinimizeButton(false);
		    win.setShowCloseButton(false);
		    win.setShowTitle(true);
		    win.setWidth100();
		    win.setHeight("30%");
		    win.addItem(htmlPane);
		    layout.addMember(win);

		}	
	}
	@Override
	public Canvas getCanvas() {
	
		layout.setWidth100();
		layout.setHeight100();
		
//		HTMLFlow htmlPane = new HTMLFlow();
//		htmlPane.setWidth100();
//		htmlPane.setHeight100();
//		htmlPane.setCanSelectText(false);
//		htmlPane.setEvalScriptBlocks(false);
//		htmlPane.setContents("contents");
//		htmlPane.setContentsURL(Page.getAppDir()+"content/demo1.html");
		
//		htmlPane.setContentsType(ContentsType.PAGE);
		return layout;
	}

	@Override
	public String getName() {
		return "Info";
	}

	@Override
	public String getDescription() {
		return portalConst.infoDesc();
	}

}
