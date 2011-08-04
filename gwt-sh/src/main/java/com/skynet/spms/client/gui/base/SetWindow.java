package com.skynet.spms.client.gui.base;

import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SetWindow {
	public static void SetWindowLayout(String windowTitle
									,boolean isMax
									,String iconUrl
									,final Rectangle rect
									,final Window objWindow
									,Canvas leftLayout) {
		objWindow.setTitle(windowTitle);
		objWindow.addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});
		
		if (isMax) {
			objWindow.setMaximized(true);
		}
		
		leftLayout.setBorder("1px solid #E8E8E8");
		leftLayout.setShowCustomScrollbars(true);
		leftLayout.scrollToRight();
		leftLayout.setCanDragScroll(true);
		
		Canvas rightLayout = getRightLayout(iconUrl);
		rightLayout.setBorder("1px solid #E8E8E8");
		rightLayout.setWidth(150);
		rightLayout.setHeight100();
		
		objWindow.setShowMinimizeButton(false);
		objWindow.setIsModal(true);
		objWindow.setShowModalMask(true);
		objWindow.centerInPage();
		objWindow.setShowCustomScrollbars(false);
		objWindow.setCanDragScroll(false);

		HLayout mainHLayout = new HLayout();
		mainHLayout.addMember(leftLayout);
		mainHLayout.addMember(rightLayout);
		objWindow.addItem(mainHLayout);
	}

	protected static Canvas getRightLayout(String iconUrl) {
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		
		Img img = new Img(iconUrl);
		img.setWidth(128);
		img.setHeight(128);
		
		VLayout vLayout = new VLayout();
		HLayout hlayout=new HLayout();
		hlayout.setMargin(20);
		hlayout.addMember(wrapper);
		hlayout.addMember(img);
		
		vLayout.addMember(hlayout);
		vLayout.setBorder("1");
		return vLayout;
	}
}
