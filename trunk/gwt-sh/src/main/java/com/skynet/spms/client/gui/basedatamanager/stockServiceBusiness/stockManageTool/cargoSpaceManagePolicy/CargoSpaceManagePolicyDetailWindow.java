package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class CargoSpaceManagePolicyDetailWindow extends Window {

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
	public CargoSpaceManagePolicyDetailWindow(String windowTitle, 
			boolean isMax,
			final Rectangle rect, 
			ListGrid listGrid, 
			String iconUrl, 
			Boolean updateFlg,
			final Boolean isView) {
		
		final Window objWindow = this;
		setWidth(560);
		setHeight(400);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 货位策略编号
		final TextItem txtPolicyNumber = new TextItem("policyNumber", "货位策略编号");
		// 货位编号
		final SelectItem txtCargoSpaceNumber = new SelectItem("cargoSpaceNumber", "货位编号");
		txtCargoSpaceNumber.setWidth(280);
		// 货位策略描述
		final TextItem txtRemark = new TextItem("remark", "货位策略描述");
		txtRemark.setWidth(280);
		// 适用件号
		final SelectItem txtStorablePartNumber = new SelectItem("storablePartNumber", "适用件号");
		// 状态
		final SelectItem txtStatus = new SelectItem("status", "状态");
		txtStatus.setValueMap("1","2","3");

		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.saveData();
				mainForm.clearValues();
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final IButton cancelButton = new IButton();
		cancelButton.setTitle("返回");
		cancelButton.setWidth(65);
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(txtPolicyNumber
						,txtCargoSpaceNumber
						,txtRemark
						,txtStorablePartNumber
						,txtStatus
						);

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);
		
		
		if(isView == true) {
			Utils.setReadOnlyForm(mainForm);
			saveButton.setVisible(false);
			cancelButton.setVisible(false);
		}

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}