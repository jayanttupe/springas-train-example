package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class PrintWindow extends Window {
	public PrintWindow(String windowTitle, boolean isMax,
			final Rectangle srcRect, String iconUrl) {
		setTitle(windowTitle);
		setCanDragReposition(true);
		setShowShadow(true);
		setShadowSoftness(10);
		setShadowOffset(5);
		setKeepInParentRect(true);
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, PrintWindow.this, -1);
			}
		});
		
		if (isMax) {
			setMaximized(true);
		} else {
			setWidth(800);
			setHeight(500);
		}
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		
		final Img starImg1 = new Img("images/PO_001.jpg");
		starImg1.setImageType(ImageStyle.NORMAL);
		starImg1.setBorder("1px solid gray");
		starImg1.setWidth100();
		starImg1.setImageWidth(starImg1.getWidth());
		
		TileGrid mainGrid = new TileGrid();
		mainGrid.setWidth100();
		mainGrid.setHeight("94%");
		mainGrid.addChild(starImg1);
		mainGrid.setBorder("1px solid white");
		
		HLayout buttonPanel = new BtnsHLayout();
		buttonPanel.setWidth100();
		buttonPanel.setHeight("6%");
        IButton printButton = new IButton("打印");
        printButton.setIcon("icons/print.png");
        printButton.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				
				Canvas.showPrintPreview(starImg1);
			}
		});
        buttonPanel.addMember(printButton);
        
		this.addItem(mainGrid);
		this.addItem(buttonPanel);
	}
}
