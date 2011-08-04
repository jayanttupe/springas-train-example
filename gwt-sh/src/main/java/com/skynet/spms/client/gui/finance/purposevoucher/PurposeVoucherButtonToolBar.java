package com.skynet.spms.client.gui.finance.purposevoucher;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.organization.user.TestWindow;
import com.skynet.spms.client.gui.finance.invoice.PurchaseInvoiceAddWindow;
import com.skynet.spms.client.gui.finance.invoice.PurchaseInvoiceModifyWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class PurposeVoucherButtonToolBar extends BaseButtonToolStript {

	private ListGrid listGrid;
	public PurposeVoucherButtonToolBar(String modName,
			ListGrid listGrid) {
		// TODO Auto-generated constructor stub
		super(modName);
		this.listGrid = listGrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// TODO Auto-generated method stub
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new PurposeVoucherAddWindow("新建凭证", false,srcRect, listGrid, "showwindow/finance_add_01.png");
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (listGrid.getSelection().length == 1) {
				 useWindow = new PurposeVoucherModifyWindow("修改凭证", false,srcRect, listGrid, "showwindow/finance_modify_01.png");
			} else {
				SC.say("请选择一条记录进行修改！");
				return;
			}

		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (listGrid.getSelection().length == 1) {
				
			} else {
				SC.say("请选择一条记录进行查看！");
				return;
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (listGrid.getSelection().length != 0) {
	
				SC.confirm("删除确认", "是否要删除选中的记录！", new BooleanCallback() {
						
						@Override
						public void execute(Boolean value) {
							// TODO Auto-generated method stub
							if (value) {
								listGrid.removeSelectedData();
							}
							return;
						}
					});
				}else{
					SC.warn("请选择要删除的记录！");
					return;
				}
		} else {
			useWindow = new TestWindow("TEST", true, srcRect, listGrid,
					"icons/add.gif");
		}
		if (button != null&&!"delete".equalsIgnoreCase(buttonName))
			ShowWindowTools.showWondow(srcRect, useWindow, -1);

	}

	}


