package com.skynet.spms.client.gui.supplierSupport.procurementOrder.pickUpOrder;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 主订单操作按钮
 * 
 * @author Tony FANG
 * 
 */
public class PickUpOrderToolStrip extends BaseButtonToolStript {

	// private ToolStripButton addButton;
	// private ToolStripButton delButton;
	// private ToolStripButton modButton;
	// private ToolStripButton vieButton;
	// private ToolStripButton publishButton;
	// private ToolStripButton cancelPublishButton;
	// private ToolStripMenuButton statusMBtn;//业务状态
	// private ToolStripButton messageBtn;//留言
	// private ToolStripButton logBtn;//日志
	// private ToolStripButton prtButton;
	// private ToolStripButton exportButton;
	// private ToolStripButton seniorSearchBtn;//高级查询

	private PickUpOrderListGrid listGrid;

	public PickUpOrderToolStrip(PickUpOrderListGrid listGrid) {
		this.listGrid = listGrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);

		// addButton = new ToolStripButton("新建");
		// addButton.setAutoFit(true);
		// addButton.setIcon("icons/del.gif");
		// addButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// showWindow("add");
		//
		// }
		// });
		//
		// delButton = new ToolStripButton("删除");
		// delButton.setAutoFit(true);
		// delButton.setIcon("icons/del.gif");
		// delButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// SC.confirm("操作提醒", "确认删除吗？", null);
		// }
		// });
		//
		// modButton = new ToolStripButton("修改");
		// modButton.setAutoFit(true);
		// modButton.setIcon("icons/edit.gif");
		// modButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// showWindow("modify");
		// }
		// });
		//
		// publishButton = new ToolStripButton("发布");
		// publishButton.setAutoFit(true);
		// publishButton.setIcon("icons/edit.gif");
		// publishButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		//
		// cancelPublishButton = new ToolStripButton("取消发布");
		// cancelPublishButton.setAutoFit(true);
		// cancelPublishButton.setIcon("icons/edit.gif");
		// cancelPublishButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		//
		// statusMBtn = new ToolStripMenuButton("业务状态");
		// Menu statusMenu = new Menu();
		// MenuItem menuItem1 = new MenuItem("已新建");
		// MenuItem menuItem2 = new MenuItem("已打回");
		// MenuItem menuItem3 = new MenuItem("已确认");
		// MenuItem menuItem4 = new MenuItem("已分派");
		// MenuItem menuItem5 = new MenuItem("已处理");
		// MenuItem menuItem6 = new MenuItem("已关闭");
		// statusMenu.setItems(menuItem1, menuItem2, menuItem3, menuItem4,
		// menuItem5, menuItem6);
		// statusMBtn.setMenu(statusMenu);
		//
		// messageBtn = new ToolStripButton("留言");
		// messageBtn.setAutoFit(true);
		// messageBtn.setIcon("icons/edit.gif");
		// messageBtn.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		//
		// prtButton = new ToolStripButton("打印");
		// prtButton.setAutoFit(true);
		// prtButton.setIcon("icons/edit.gif");
		// prtButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		//
		// logBtn = new ToolStripButton("日志");
		// logBtn.setAutoFit(true);
		// logBtn.setIcon("icons/edit.gif");
		// logBtn.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		//
		// exportButton = new ToolStripButton("导出");
		// exportButton.setAutoFit(true);
		// exportButton.setIcon("icons/edit.gif");
		// exportButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		//
		// vieButton = new ToolStripButton("查看");
		// vieButton.setAutoFit(true);
		// vieButton.setIcon("icons/view.png");
		// vieButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		//
		// // setItems(addButton, delButton, modButton, publishButton,
		// //
		// cancelPublishButton,addSalesQuotationBtn,messageBtn,logBtn,prtButton,exportButton);
		//
		// seniorSearchBtn = new ToolStripButton("高级查询");
		// seniorSearchBtn.setAutoFit(true);
		// seniorSearchBtn.setIcon("icons/view.png");
		// seniorSearchBtn.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// }
		// });
		// this.addButton(addButton);
		// this.addButton(delButton);
		// this.addButton(modButton);
		// this.addButton(publishButton);
		// this.addButton(cancelPublishButton);
		// this.addMenuButton(statusMBtn);
		// this.addButton(messageBtn);
		// this.addButton(logBtn);
		// this.addButton(prtButton);
		// this.addButton(exportButton);
		// this.addButton(seniorSearchBtn);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {
			PickUpOrderAddWindow ppa = new PickUpOrderAddWindow("新建提货指令", true,
					button.getPageRect(), null, "");
			ShowWindowTools.showWondow(button.getPageRect(), ppa, 200);
		} else if ("MODIFY".equals(buttonName)) {
			PickUpOrderModityWindow ppa = new PickUpOrderModityWindow("修改提货指令",
					true, button.getPageRect(), null, "");
			ShowWindowTools.showWondow(button.getPageRect(), ppa, 200);
		} else if ("DELETE".equals(buttonName)) {

		} else {

		}
	}
}
