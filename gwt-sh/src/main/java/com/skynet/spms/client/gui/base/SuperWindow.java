package com.skynet.spms.client.gui.base;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class SuperWindow extends Window {

	private HLayout mainHLayout = new HLayout();
	private VLayout leftLayout = new VLayout();
	private VLayout rightLayout = new VLayout();
	private boolean isMax = true;
	private String iconUrl = "xxx/test.gif";

	public SuperWindow() {
		_init();
	}

	public SuperWindow(boolean isMax, String iconUrl) {
		this.isMax = isMax;
		this.iconUrl = iconUrl;
		_init();
	}

	private void _init() {
		leftLayout.setBorder("1px solid #E8E8E8");
		leftLayout.setShowCustomScrollbars(true);
		leftLayout.scrollToRight();
		leftLayout.setCanDragScroll(true);

		getRightLayout(iconUrl);
		rightLayout.setBorder("1px solid #E8E8E8");
		if (isMax) {
			setMaximized(true);
			this.setWidth100();
			this.setHeight100();
			leftLayout.setWidth("80%");
			rightLayout.setWidth("20%");
		} else {
			setWidth(900);
			setHeight(500);
			leftLayout.setWidth("70%");
			rightLayout.setWidth("30%");
		}
		setShowMinimizeButton(false);
		//setIsModal(true);
		//setShowModalMask(true);
		centerInPage();
		setShowCustomScrollbars(false);
		setCanDragScroll(false);

		mainHLayout.addMember(leftLayout);
		mainHLayout.addMember(rightLayout);
		addItem(mainHLayout);
	}

	public void addMemberToLeft(Canvas component) {
		leftLayout.addMember(component);
	}

	private void getRightLayout(String iconUrl) {
		Img img = new Img(iconUrl);
		rightLayout.addMember(img);
		rightLayout.setBorder("1");
	}
}
