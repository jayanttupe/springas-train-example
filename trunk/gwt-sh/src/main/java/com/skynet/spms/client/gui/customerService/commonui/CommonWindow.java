package com.skynet.spms.client.gui.customerService.commonui;

import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class CommonWindow extends Window {

	public HLayout mainHLayout = new HLayout();
	public Canvas leftLayout;
	public Canvas rightLayout;

	public CommonWindow(String windowTitle, boolean isMax, Rectangle srcRect,
			ListGrid listGrid, String iconUrl) {
		this.setTitle(windowTitle);
		leftLayout = getLeftLayout(srcRect, listGrid);
		leftLayout.setOverflow(Overflow.AUTO);

		rightLayout = getRightLayout(iconUrl);
		rightLayout.setBorder("1px solid #E8E8E8");
		if (isMax) {
			setMaximized(true);
			leftLayout.setWidth("80%");
			rightLayout.setWidth("20%");
		} else {
			setWidth(900);
			setHeight(500);
			leftLayout.setWidth("70%");
			rightLayout.setWidth("30%");
		}
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		setShowCustomScrollbars(false);
		setCanDragScroll(false);

		mainHLayout.addMember(leftLayout);
		mainHLayout.addMember(rightLayout);

		this.addItem(mainHLayout);
	}

	protected abstract Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid);

	public Canvas getRightLayout(String iconUrl) {
		VLayout vLayout = new VLayout();
		Img img = new Img(iconUrl);
		vLayout.addMember(img);
		vLayout.setBorder("1");
		return vLayout;
	}
	/**
	 * 关闭当前窗口
	 */
	public void close(){
		this.destroy();
	}
}
