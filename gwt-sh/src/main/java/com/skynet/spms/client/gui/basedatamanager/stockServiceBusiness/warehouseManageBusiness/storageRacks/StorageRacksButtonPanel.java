package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.storageRacks;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class StorageRacksButtonPanel extends BaseButtonToolStript {
	private StorageRacksListgrid storageRacksListgrid;

	public StorageRacksButtonPanel(
			final StorageRacksListgrid stockroomManageListgrid) {
		super("stockServiceBusiness.basicData.storageRacks");
		this.storageRacksListgrid = stockroomManageListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
	}
}
