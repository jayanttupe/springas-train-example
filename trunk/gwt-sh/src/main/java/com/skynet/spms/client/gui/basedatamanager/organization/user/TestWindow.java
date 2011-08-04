package com.skynet.spms.client.gui.basedatamanager.organization.user;

import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.ValidateUtils;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class TestWindow extends BaseWindow {

	
	
	public TestWindow(String windowTitle, boolean isMax, Rectangle srcRect,
			ListGrid listGrid,String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid,iconUrl);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		// TODO Auto-generated method stub
		final Window thisWin = this;
		VLayout vLayout = new VLayout();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect,thisWin,-1);
			}
		});

		final DynamicForm form = new DynamicForm();
		vLayout.addMember(form);
		form.setDataSource(listGrid.getDataSource());
		form.setPadding(5);
		form.setLayoutAlign(VerticalAlignment.BOTTOM);

		TextItem txtUsername = new TextItem("username");
		txtUsername.setRequired(true);
		FormItemIcon icon = new FormItemIcon();
		txtUsername.setIcons(icon);
		txtUsername.setWidth(100);
		txtUsername.setValidators(ValidateUtils.isCharValidator());

		TextItem txtRealname = new TextItem("realname", "真实姓名");
		txtRealname.setWidth(100);

		PasswordItem txtPassword = new PasswordItem("password", "密码");
		txtPassword.setWidth(120);

		PasswordItem txtPwdConfirm = new PasswordItem("password", "密码确认");
		txtPwdConfirm.setWidth(120);

		SelectItem test = new SelectItem("accessLevel", "额度等级");
		test.setDefaultValue(listGrid.getDataSource().getAttribute(
				"accessLevel"));

		TextItem txtEmail = new TextItem("email", "电子邮箱");
		txtEmail.setWidth(200);

		ButtonItem saveButton = new ButtonItem();
		saveButton.setTitle("保存");
		saveButton.setIcon("icons/save.png");  
		saveButton.setAlign(Alignment.CENTER);

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				form.saveData();
				/*clear();
				ShowWindowTools.showCloseWindow(srcRect,thisWin,-1);
				SC.say("");*/
				SC.say(ConstantsUtil.commonConstants.alertForSaveSuccess(), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value.equals(true)) {
							ShowWindowTools.showCloseWindow(srcRect, TestWindow.this, -1);
						}
					}
				});
			}
		});



		form.setFields(txtUsername, txtRealname, txtPassword, txtPwdConfirm,
				txtEmail, test, saveButton);
		
		return vLayout;
	}




}
