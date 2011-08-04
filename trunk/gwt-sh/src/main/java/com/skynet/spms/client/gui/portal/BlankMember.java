package com.skynet.spms.client.gui.portal;

import com.skynet.spms.client.gui.portal.PortalPanel.PortalMember;
import com.smartgwt.client.widgets.Canvas;

public class BlankMember implements PortalMember{

	@Override
	public Canvas getCanvas() {
		Canvas canvas=new Canvas();
		canvas.setWidth100();
		canvas.setHeight100();
		return canvas;
	}

	@Override
	public String getName() {
		return "Blank";
	}

	@Override
	public String getDescription() {
		return "blank";
	}

}
