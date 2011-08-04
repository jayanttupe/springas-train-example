package com.skynet.spms.client.gui.base;

import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class FileUploadWindow extends Window {

	private String uuid;
	private HLayout mainHLayout = new HLayout();
	private Canvas leftLayout;
	private Canvas rightLayout;
	protected Boolean updateFlg = false;
	private AttachmentListCanvas attachmentListCanvas = null;
	
	public AttachmentListCanvas getAttachmentListCanvas(){
		return attachmentListCanvas;
	}
	public FileUploadWindow(String windowTitle, boolean isMax, Rectangle srcRect,
			ListGrid listGrid, String iconUrl,String uuid) {
		setTitle(windowTitle);
		this.uuid = uuid;
		
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
			setWidth(700);
			setHeight(300);
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

	

	private Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		// TODO Auto-generated method stub

		if(uuid!=null&&!"".equals(uuid))
			attachmentListCanvas = new AttachmentListCanvas("account.applyManager.payApplyManager","attachments_dataSource",uuid);
		else
			attachmentListCanvas =  new AttachmentListCanvas("account.applyManager.payApplyManager","attachments_dataSource");
		attachmentListCanvas.setWidth100();
		attachmentListCanvas.setHeight100();
		return attachmentListCanvas;
	}
	
	protected Canvas getRightLayout(String iconUrl) {
		VLayout vLayout = new VLayout();
		vLayout.setWidth("90");
		HLayout hlayout=new HLayout();
		hlayout.setMargin(20);
		
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		
		Img img = new Img(iconUrl);
		
		img.setWidth(128);
		img.setHeight(128);
		
		hlayout.addMember(wrapper);
		hlayout.addMember(img);
		
		vLayout.addMember(hlayout);
		vLayout.setBorder("1");
		return vLayout;
	}

}
