package com.skynet.spms.client.gui.base;

import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class BaseWindow extends Window {

	private HLayout mainHLayout = new HLayout();
	private Canvas leftLayout;
	private Canvas rightLayout;
	protected Boolean updateFlg = false;

	public BaseWindow(String windowTitle, boolean isMax, Rectangle srcRect,
			ListGrid listGrid, String iconUrl) {
		setTitle(windowTitle);
		setCanDragReposition(true);
		setShowShadow(true);
		setShadowSoftness(10);
		setShadowOffset(5);
		setKeepInParentRect(true);
		this.addCloseClickHandler(new CloseClickHandler() {

			@Override
			public void onCloseClick(CloseClientEvent event) {
				close();
			}
		});
		leftLayout = getLeftLayout(srcRect, listGrid);
		leftLayout.setBorder("1px solid #E8E8E8");
		leftLayout.setShowCustomScrollbars(true);
		leftLayout.scrollToRight();
		leftLayout.setCanDragScroll(true);

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

	public BaseWindow(String windowTitle, boolean isMax, Rectangle srcRect,
			ListGrid listGrid, String iconUrl, Boolean updateFlg) {
		this.updateFlg = updateFlg;
		setTitle(windowTitle);
		this.addCloseClickHandler(new CloseClickHandler() {

			@Override
			public void onCloseClick(CloseClientEvent event) {
				close();
			}
		});
		leftLayout = getLeftLayout(srcRect, listGrid);
		leftLayout.setBorder("1px solid #E8E8E8");
		leftLayout.setShowCustomScrollbars(true);
		leftLayout.scrollToRight();
		leftLayout.setCanDragScroll(true);

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

	protected Canvas getRightLayout(String iconUrl) {
		VLayout vLayout = new VLayout();
		vLayout.setWidth("90");
		HLayout hlayout = new HLayout();
		hlayout.setMargin(20);

		HLayout wrapper = new HLayout();
		wrapper.setWidth100();

		Img img = new Img(iconUrl);

		img.setWidth(128);
		img.setHeight(128);
		// img.setLeft(180);
		// img.setValign(valign);
		// img.setAlign(Side.RIGHT);

		hlayout.addMember(wrapper);
		hlayout.addMember(img);

		vLayout.addMember(hlayout);
		vLayout.setBorder("1");
		return vLayout;
	}

	/**
	 * 关闭当前窗口
	 */
	public void close() {
		this.destroy();
	}
}
