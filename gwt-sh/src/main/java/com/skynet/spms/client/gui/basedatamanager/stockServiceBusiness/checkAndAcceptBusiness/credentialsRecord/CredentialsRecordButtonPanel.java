/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentialsRecord;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.service.FileOperService;
import com.skynet.spms.client.service.FileOperServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 * 
 */
public class CredentialsRecordButtonPanel extends BaseButtonToolStript {

	private CredentialsRecordListgrid credentialsRecordListgrid;

	private FileOperServiceAsync fileOperService = GWT
			.create(FileOperService.class);

	public CredentialsRecordButtonPanel(
			final CredentialsRecordListgrid credentialsRecordListgrid) {
		super("stockServiceBusiness.checkAndAcceptBusiness.credentialsRecord");
		this.credentialsRecordListgrid = credentialsRecordListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("MANUALFILE".equalsIgnoreCase(buttonName)) {
			SC.confirm("是否确认归档？", new BooleanCallback() {

				@Override
				public void execute(Boolean value) {
					// TODO Auto-generated method stub
					if (value == false) {
						return;
					}
					fileOperService.fileManualOper(new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void arg0) {
							SC.say("归档成功");

						}
						@Override
						public void onFailure(Throwable arg0) {
							SC.say("系统忙，请稍后重试");
						}

					});
				}

			});
			ShowWindowTools.showWondow(rect, objWindow, -1);
		}
	}
}