package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentials;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class CredentialsDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 * @param updateFlg
	 * @param isView
	 */
	public CredentialsDetailWindow(String windowTitle, boolean isMax,
			final Rectangle rect, final ListGrid listGrid, String iconUrl,
			Boolean updateFlg, final Boolean isView) {

		final Window objWindow = this;
		setWidth(510);
		setHeight(270);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(280);
		mainForm.setHeight("90%");
		mainForm.setNumCols(4);
		mainForm.setColWidths(80, 50, 15, 135);
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true) {
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 备件中心位置
		final TextItem txtPartCentreLocation = new TextItem(
				"partCentreLocation");
		txtPartCentreLocation.setHint("<font color='red'>*<font>");
		txtPartCentreLocation.setWidth(150);
		txtPartCentreLocation.setColSpan(3);
		// 证书存档位置
		final TextItem txtStackPositionCode = new TextItem("stackPositionCode");
		txtStackPositionCode.setHint("<font color='red'>*<font>");
		txtStackPositionCode.setMask(">CCC");
		txtStackPositionCode.setColSpan(3);
		txtStackPositionCode.setWidth(50);
		txtStackPositionCode.setLength(3);
		// 序列号
		final TextItem txtPartNumberFrom = new TextItem("partNumberFrom");
		txtPartNumberFrom.setWidth(50);
		txtPartNumberFrom.setMask("#####");
		// 至
		final TextItem txtPartNumberTo = new TextItem("partNumberTo");
		txtPartNumberTo.setHint("<font color='red'>*<font>");
		txtPartNumberTo.setMask("#####");
		txtPartNumberTo.setAlign(Alignment.LEFT);
		txtPartNumberTo.setWidth(50);
		// 备注
		final TextAreaItem txtRemark = new TextAreaItem("remark");
		txtRemark.setColSpan(3);
		txtRemark.setWidth(200);
		txtRemark.setHeight(50);

		mainForm.setFields(txtPartCentreLocation, txtStackPositionCode,
				txtPartNumberFrom, txtPartNumberTo, txtRemark);

		// 保存按钮
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (mainForm.validate() == false) {
					return;
				}
				if (txtPartCentreLocation.getAttribute("Value") == null
						|| txtPartCentreLocation.getAttribute("Value").equals("")) {
					SC.say("备件中心位置不能为空！");
					txtPartCentreLocation.focusInItem();
					return;
				}
				if (txtStackPositionCode.getAttribute("Value") == null
						|| txtStackPositionCode.getAttribute("Value").equals("")) {
					SC.say("证书存档位置不能为空！");
					txtStackPositionCode.focusInItem();
					return;
				}
				if (txtPartNumberFrom.getAttribute("Value") == null
						|| txtPartNumberFrom.getAttribute("Value").equals("")) {
					SC.say("起始序列号不能为空！");
					txtPartNumberFrom.focusInItem();
					return;
				}
				if (txtPartNumberTo.getAttribute("Value") == null
						|| txtPartNumberTo.getAttribute("Value").equals("")) {
					SC.say("结束序列号不能为空！");
					txtPartNumberTo.focusInItem();
					return;
				}
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						// 是否生成过的证书(0:未生成/1:已经生成)
						String createdFlg = response.getData()[0].getAttribute("createdFlg");
						if (createdFlg.equals("1")) {
							SC.say("存在重复证书存档编号，请重新设定序列号范围！");
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							listGrid.fetchData(criteria);
							return;
						} else {
							SC.say("创建成功！");
							mainForm.clearValues();
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							listGrid.fetchData(criteria);
							ShowWindowTools.showCloseWindow(rect, objWindow, -1);
						}
					}	
				});
			}
		});

		// 返回按钮
		final IButton returnButton = new IButton();
		returnButton.setTitle("返回");
		returnButton.setWidth(65);
		returnButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(returnButton);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);
		
		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			saveButton.setVisible(false);
			returnButton.setVisible(false);
		}


		SetWindow.SetWindowLayout(windowTitle, false, iconUrl, rect, objWindow,
				layout);
	}
}
