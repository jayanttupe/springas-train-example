package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class EnterpriseInfoButtonToolBar extends BaseButtonToolStript {

	private EnterpriseInfoTreeGrid enterpriseInfoTreeGrid;

	public EnterpriseInfoButtonToolBar(String moduleName,
			EnterpriseInfoTreeGrid enterpriseInfoTreeGrid) {
		super(moduleName);
		this.enterpriseInfoTreeGrid = enterpriseInfoTreeGrid;

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new EnterpriseInfoAddWindow(
					ConstantsUtil.organizationConstants.addEnterprise(), false,
					srcRect, enterpriseInfoTreeGrid, "showwindow/organization_add_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (enterpriseInfoTreeGrid.getSelection().length == 1) {
				useWindow = new EnterpriseInfoModifyWindow(
						ConstantsUtil.organizationConstants.modifyEnterprise(),
						false, srcRect, enterpriseInfoTreeGrid,"showwindow/organization_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}

		else if ("view".equalsIgnoreCase(buttonName)) {
			if (enterpriseInfoTreeGrid.getSelection().length == 1) {
				useWindow = new EnterpriseInfoViewWindow(enterpriseInfoTreeGrid);
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}

		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (enterpriseInfoTreeGrid.getSelection().length == 1) {
				SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
							enterpriseInfoTreeGrid.removeSelectedData();
						}
					}
				});	
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
	}

}
