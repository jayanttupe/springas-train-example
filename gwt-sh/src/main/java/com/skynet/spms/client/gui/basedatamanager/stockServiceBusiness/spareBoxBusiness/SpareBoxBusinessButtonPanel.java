package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.spareBoxBusiness;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class SpareBoxBusinessButtonPanel extends BaseButtonToolStript {
	private SpareBoxBusinessListgrid spareBoxBusinessListgrid;

	public SpareBoxBusinessButtonPanel(final SpareBoxBusinessListgrid spareBoxBusinessListgrid) {
		super("stockServiceBusiness.spareBoxBusiness");
		this.spareBoxBusinessListgrid = spareBoxBusinessListgrid;
		}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
			
		if ("add".equalsIgnoreCase(buttonName)) {
					objWindow = new SpareBoxBusinessDetailWindow("新建航材包", false, rect, spareBoxBusinessListgrid, "showwindow/stock_add_01.png", false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (spareBoxBusinessListgrid.getSelection().length == 1) {
			objWindow = new SpareBoxBusinessDetailWindow("修改航材包", false, rect, spareBoxBusinessListgrid, "showwindow/stock_modify_01.png", true);
			} else {
						SC.say("请选择一条记录进行修改！");
					}
			}  else if ("delete".equalsIgnoreCase(buttonName)) {
				if (spareBoxBusinessListgrid.getSelection().length != 0) {
					boolean isDel = com.google.gwt.user.client.Window.confirm("是否要删除选中的记录！");
					if (isDel) {
						spareBoxBusinessListgrid.removeSelectedData();
							return;
						}
					} else {
						SC.say("请选择一条记录进行删除！");
					}
				}

				ShowWindowTools.showWondow(rect, objWindow, -1);
			}
		}