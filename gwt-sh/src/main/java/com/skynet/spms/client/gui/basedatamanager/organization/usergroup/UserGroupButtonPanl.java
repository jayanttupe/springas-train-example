package com.skynet.spms.client.gui.basedatamanager.organization.usergroup;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class UserGroupButtonPanl extends BaseButtonToolStript {

	private UserGroupListgrid userGroupListgrid;

	public UserGroupButtonPanl(final UserGroupListgrid userGroupListgrid) {
		super("organization.userGroup");
		this.userGroupListgrid = userGroupListgrid;

	}

	protected void showWindow(String windowName, ToolStripButton button) {

		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();
		if ("add".equalsIgnoreCase(windowName)) {
			useWindow = new UserGroupAddWindow(
					ConstantsUtil.organizationConstants.addUserGroup(), 
					false, srcRect,userGroupListgrid,"showwindow/organization_add_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);
		} 
		else if ("modify".equalsIgnoreCase(windowName)) {
			if (userGroupListgrid.getSelection().length == 1) {
				useWindow = new UserGroupModifyWindow(
						ConstantsUtil.organizationConstants.modifyUserGroup(),
						false,srcRect,userGroupListgrid,"showwindow/organization_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
		else if ("view".equalsIgnoreCase(windowName)) {
			if (userGroupListgrid.getSelection().length == 1) {
				useWindow = new UserGroupViewWindow(userGroupListgrid);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}

		} else if ("usertogroup".equalsIgnoreCase(windowName)) {
			if (userGroupListgrid.getSelection().length == 1) {
				useWindow = new AddUserToGroupWindow(
						ConstantsUtil.organizationConstants.addUserToGroup(),
						false,srcRect,userGroupListgrid,"showwindow/organization_group_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
			
		} else if("roletogroup".equalsIgnoreCase(windowName)){
			if (userGroupListgrid.getSelection().length == 1) {
				useWindow = new AddRoleToGroupWindow(
						ConstantsUtil.organizationConstants.addRoleToGroup(),
						false,srcRect,userGroupListgrid,"showwindow/organization_group_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
		else if("delete".equalsIgnoreCase(windowName)) {
			if (userGroupListgrid.getSelection().length != 0) {
				
				
				SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
							userGroupListgrid.removeSelectedData();
						}
					}
				});
				/*//addToButton.addClickHandler(new ClickHandler() {  
		           // public void onClick(ClickEvent event) {  
		                SC.ask(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {  
		                    public void execute(Boolean value) {  
		                        if (value != null && value) {  
		                           SC.say("成功删除数据"); 
		                           userGroupListgrid.removeSelectedData();
		                        } else {  
		                           // labelAnswer.setContents("No");  
		                        }  
		                    }  
		                });  */
		     
			}else{
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
	}
}
