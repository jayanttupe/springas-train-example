package com.skynet.spms.client.gui.basedatamanager.organization.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.organization.role.RoleModifyQuotaWindow;
import com.skynet.spms.client.service.PrintUserIdCardService;
import com.skynet.spms.client.service.PrintUserIdCardServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class UserManagerButtonToolBar extends BaseButtonToolStript {

	private UserManagerListgrid userManagerListgrid;
	private PrintUserIdCardServiceAsync printUserIdCardService = GWT.create(PrintUserIdCardService.class);

	public UserManagerButtonToolBar(UserManagerListgrid userManagerListgrid,String moduleName) {
		super(moduleName);
		this.userManagerListgrid = userManagerListgrid;
		
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		Rectangle srcRect = null;
		if (button != null)
			srcRect = button.getPageRect();

		if ("add".equalsIgnoreCase(buttonName)) {
			useWindow = new UserAddWindow(
					ConstantsUtil.organizationConstants.addUser(), 
					false, srcRect, userManagerListgrid, "showwindow/organization_add_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);	
		} 
		else if ("modify".equalsIgnoreCase(buttonName)) {
			if (userManagerListgrid.getSelection().length == 1) {
				useWindow = new UserModifyWindow(
						ConstantsUtil.organizationConstants.modifyUser(),
						false,srcRect, userManagerListgrid,"showwindow/organization_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);	
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}

		} 
		else if ("view".equalsIgnoreCase(buttonName)) {
			if (userManagerListgrid.getSelection().length == 1) {
				useWindow = new UserViewWindow(
						ConstantsUtil.organizationConstants.viewUser(),
						false,srcRect, userManagerListgrid,"showwindow/organization_modify_01.png");
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		} 
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (userManagerListgrid.getSelection().length != 0) {
				
				SC.confirm(ConstantsUtil.commonConstants.ConfirmForDelete(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							SC.say(ConstantsUtil.commonConstants.alertForsuccessDelete());
							userManagerListgrid.removeSelectedData();
						}
					}
				});
				
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
			
		} else if("roletogroup".equalsIgnoreCase(buttonName)){
			if (userManagerListgrid.getSelection().length == 1) {
				useWindow = new AddRoleToUserWindow(
						ConstantsUtil.organizationConstants.addRoleToUser(),
						false,srcRect, userManagerListgrid,"showwindow/organization_modify_01.png");
				ShowWindowTools.showWondow(srcRect, useWindow, -1);	
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
				//SC.warn(ConstantsUtil.organizationConstants.alertForSelectUser());
			}
		}else if("printidcard".equalsIgnoreCase(buttonName)){
			if (userManagerListgrid.getSelection().length == 1) {
				Record record = userManagerListgrid.getSelectedRecord();
				String userId = record.getAttribute("id");
				printUserIdCardService.print(userId, "C", new AsyncCallback<String>() {
					@Override
					public void onSuccess(String arg0) {
						
						if(arg0.equals("success")){ 
							SC.say(ConstantsUtil.commonConstants.postcardSuccess());	
						}else if(arg0.equals("failure")){
							SC.warn(ConstantsUtil.commonConstants.postcardFailure());	
						}else if(arg0.equals("timeout")){
							//SC.warn("打印超时，任务取消！");
							SC.warn(ConstantsUtil.commonConstants.printerOverTime());
						}
						
					}
	
					@Override
					public void onFailure(Throwable arg0) {
						//SC.warn("系统出错！");
						SC.warn(ConstantsUtil.commonConstants.systemFail());
					}
				});
				
			} else {
				SC.say(ConstantsUtil.commonConstants.alertSelectFortoolbar());
			}
		}
		else if("modifyrolequota".equalsIgnoreCase(buttonName)){
			useWindow = new RoleModifyQuotaWindow("修改审批额度", false, srcRect, "showwindow/organization_modify_01.png");
			ShowWindowTools.showWondow(srcRect, useWindow, -1);	
		}
		else if("PRINT".equalsIgnoreCase(buttonName)){
			Window.open("reportJsp/showReport.jsp?raq=report_2.raq", "_blank", "");
		}
		else {
			useWindow = new TestWindow("TEST",true,srcRect, userManagerListgrid,"icons/add.gif");
		}
	}

}
