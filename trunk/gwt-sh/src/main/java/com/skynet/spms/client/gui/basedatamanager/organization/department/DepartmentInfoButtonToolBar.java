package com.skynet.spms.client.gui.basedatamanager.organization.department;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DepartmentInfoButtonToolBar extends BaseButtonToolStript{

	private DepartmentInfoTreeGrid departmentInfoTreeGrid;

	public DepartmentInfoButtonToolBar(String moduleName,
			DepartmentInfoTreeGrid departmentInfoTreeGrid) {
		super(moduleName);
		this.departmentInfoTreeGrid = departmentInfoTreeGrid;

	}
	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new DepartmentInfoAddWindow(
					ConstantsUtil.organizationConstants.addDepartment(), false,
					srcRect, departmentInfoTreeGrid,"showwindow/organization_add_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (departmentInfoTreeGrid.getSelection().length == 1) {
				useWindow = new DepartmentInfoModifyWindow(
						ConstantsUtil.organizationConstants.modifyDepartment(),
						false, srcRect, departmentInfoTreeGrid,"showwindow/organization_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}

		else if ("view".equalsIgnoreCase(buttonName)) {
			if (departmentInfoTreeGrid.getSelection().length == 1) {
				useWindow = new DepartmentInfoViewWindow(departmentInfoTreeGrid);
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}

		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (departmentInfoTreeGrid.getSelection().length != 0) {
				
				SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
							departmentInfoTreeGrid.removeSelectedData();
						}
					}
				});
				} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
	}
}
