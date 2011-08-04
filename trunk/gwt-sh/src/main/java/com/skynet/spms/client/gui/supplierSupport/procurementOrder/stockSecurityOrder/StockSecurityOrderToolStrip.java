package com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder.add.StockSecurityOrderAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder.modity.StockSecurityOrderModityWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 主订单操作按钮
 * @author Tony FANG
 *
 */
public class StockSecurityOrderToolStrip extends BaseButtonToolStript {

//	private ToolStripButton addButton;
//	private ToolStripButton delButton;
//	private ToolStripButton modButton;
//	private ToolStripButton vieButton;
//	private ToolStripButton publishButton;
//	private ToolStripButton cancelPublishButton;
//
//	private ToolStripButton partButton;//备件中心
//
//	private ToolStripButton messageBtn;//留言
//	private ToolStripButton logBtn;//日志
//	private ToolStripButton prtButton;
//	private ToolStripButton exportButton;
//	private ToolStripButton seniorSearchBtn;//高级查询

	private StockSecurityOrderListGrid listGrid;

	public StockSecurityOrderToolStrip(StockSecurityOrderListGrid listGrid) {
		this.listGrid = listGrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);

//		addButton = new ToolStripButton("新建");
//		addButton.setAutoFit(true);
//		addButton.setIcon("icons/del.gif");
//		addButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				showWindow("add");
//
//			}
//		});
//
//		delButton = new ToolStripButton("删除");
//		delButton.setAutoFit(true);
//		delButton.setIcon("icons/del.gif");
//		delButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				SC.confirm("操作提醒", "确认删除吗？", null);
//			}
//		});
//
//		modButton = new ToolStripButton("修改");
//		modButton.setAutoFit(true);
//		modButton.setIcon("icons/edit.gif");
//		modButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				showWindow("modify");
//			}
//		});
//
//		publishButton = new ToolStripButton("发布");
//		publishButton.setAutoFit(true);
//		publishButton.setIcon("icons/edit.gif");
//		publishButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//
//		cancelPublishButton = new ToolStripButton("取消发布");
//		cancelPublishButton.setAutoFit(true);
//		cancelPublishButton.setIcon("icons/edit.gif");
//		cancelPublishButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});

		//状态下拉菜单
		DynamicForm statusDf = new DynamicForm();
		statusDf.setWidth(100);
		SelectItem si = new DicSelectItem();
		si.setShowTitle(false);
		si.setWidth(100);
		statusDf.setFields(si);
//
//		partButton = new ToolStripButton("备件中心");
//		partButton.setAutoFit(true);
//		partButton.setIcon("icons/edit.gif");
//		partButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//
//		messageBtn = new ToolStripButton("留言");
//		messageBtn.setAutoFit(true);
//		messageBtn.setIcon("icons/edit.gif");
//		messageBtn.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//
//		prtButton = new ToolStripButton("打印");
//		prtButton.setAutoFit(true);
//		prtButton.setIcon("icons/edit.gif");
//		prtButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//
//		logBtn = new ToolStripButton("日志");
//		logBtn.setAutoFit(true);
//		logBtn.setIcon("icons/edit.gif");
//		logBtn.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//
//		exportButton = new ToolStripButton("导出");
//		exportButton.setAutoFit(true);
//		exportButton.setIcon("icons/edit.gif");
//		exportButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//
//		vieButton = new ToolStripButton("查看");
//		vieButton.setAutoFit(true);
//		vieButton.setIcon("icons/view.png");
//		vieButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//
//		seniorSearchBtn = new ToolStripButton("高级查询");
//		seniorSearchBtn.setAutoFit(true);
//		seniorSearchBtn.setIcon("icons/view.png");
//		seniorSearchBtn.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//			}
//		});
//		this.addButton(addButton);
//		this.addButton(delButton);
//		this.addButton(modButton);
//
//		this.addMember(statusDf);
//		this.addButton(partButton);
//		this.addButton(publishButton);
//		this.addButton(cancelPublishButton);
//		this.addButton(messageBtn);
//		this.addButton(logBtn);
//		this.addButton(prtButton);
//		this.addButton(exportButton);
//
//		this.addSeparator();
//		this.addButton(seniorSearchBtn);
	}

	protected void showWindow(String buttonName) {
		
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {
			StockSecurityOrderAddWindow ppa = new StockSecurityOrderAddWindow(
					"新建库存安全策略", false, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppa, 200);
		} else if ("MODIFY".equals(buttonName)) {
			StockSecurityOrderModityWindow ppa = new StockSecurityOrderModityWindow(
					"修改库存安全策略", false, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppa, 200);
		}else if ("DEL".equals(buttonName)) {

		} else {

		}		
	}
}
