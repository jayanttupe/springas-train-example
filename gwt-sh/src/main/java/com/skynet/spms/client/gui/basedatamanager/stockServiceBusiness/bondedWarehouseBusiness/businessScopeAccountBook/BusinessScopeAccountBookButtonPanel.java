package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

	public class BusinessScopeAccountBookButtonPanel extends BaseButtonToolStript {
		private BusinessScopeAccountBookListgrid businessScopeAccountBookListgrid;

		public BusinessScopeAccountBookButtonPanel(
				final BusinessScopeAccountBookListgrid businessScopeAccountBookListgrid) {
			super("stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook");
			this.businessScopeAccountBookListgrid = businessScopeAccountBookListgrid;
		}

		protected void showWindow(String buttonName, ToolStripButton objButton) {
			Window objWindow = null;
			Rectangle rect = objButton.getPageRect();
			
			if ("add".equalsIgnoreCase(buttonName)) {
				objWindow = new BusinessScopeAccountBookDetailWindow("新增经营范围电子账册", false, rect, businessScopeAccountBookListgrid, "showwindow/stock_add_01.png", false,false);
			} else if ("modify".equalsIgnoreCase(buttonName)) {
				if (businessScopeAccountBookListgrid.getSelection().length == 1) {
					objWindow = new BusinessScopeAccountBookDetailWindow("修改经营范围电子账册", false, rect, businessScopeAccountBookListgrid, "showwindow/stock_modify_01.png", true,false);
				} else {
					SC.say("请选择一条记录进行修改！");
				}
			}else if ("modify".equalsIgnoreCase(buttonName)) {
				if (businessScopeAccountBookListgrid.getSelection().length == 1) {
					objWindow = new BusinessScopeAccountBookDetailWindow("查看经营范围电子账册", false, rect, businessScopeAccountBookListgrid, "showwindow/stock_modify_01.png", true,true);
				} else {
					SC.say("请选择一条记录进行查看！");
				}
			} 
			else if ("delete".equalsIgnoreCase(buttonName)) {
				if (businessScopeAccountBookListgrid.getSelection().length != 0) {
					boolean isDel = com.google.gwt.user.client.Window
							.confirm("是否要删除选中的记录！");
					if (isDel) {
						businessScopeAccountBookListgrid.removeSelectedData();
						return;
					}
				} else {
					SC.say("请选择一条记录进行删除！");
				}
			}

			ShowWindowTools.showWondow(rect, objWindow, -1);
		}
	}