package com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.add.TransferOrderAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.modity.TransferOrderModityWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 订单明细操作按钮
 * 
 * @author Tony FANG
 * 
 */
public class TransferOrderItemToolStrip extends BaseButtonToolStript {

	// private ToolStripButton addPlanProcurementBtn;//添加采购计划
	// private ToolStripButton addButton;
	// private ToolStripButton delButton;
	// private ToolStripButton modButton;

	private TransferOrderItemListGrid itemListGrid;

	public TransferOrderItemToolStrip(TransferOrderItemListGrid itemListGrid) {
		this.itemListGrid = itemListGrid;
		this.setWidth100();
		this.setHeight(40);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.setMargin(5);

		// addPlanProcurementBtn = new ToolStripButton("添加采购计划");
		// addPlanProcurementBtn.setAutoFit(true);
		// addPlanProcurementBtn.setIcon("icons/add.gif");
		// addPlanProcurementBtn.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// showWindow("addPlanProcurementBtn");
		// }
		// });
		//
		// addButton = new ToolStripButton("添加");
		// addButton.setAutoFit(true);
		// addButton.setIcon("icons/add.gif");
		// addButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// showWindow("add");
		// }
		// });
		//
		// delButton = new ToolStripButton("移除");
		// delButton.setAutoFit(true);
		// delButton.setIcon("icons/del.gif");
		// delButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// showWindow("del");
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
		// this.addButton(addButton);
		// this.addButton(delButton);
		// this.addButton(modButton);

	}

	protected void showWindow(String buttonName) {

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {
			TransferOrderAddWindow ppo = new TransferOrderAddWindow("",
					true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppo, 200);
		} else if ("MODIFY".equals(buttonName)) {
			TransferOrderModityWindow ppo = new TransferOrderModityWindow(
					"", true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppo, 200);
		}else if ("DELETE".equals(buttonName)) {

		} else {

		}
	}
}
