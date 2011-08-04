package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class ArrivalOfGoodsJobDetailWindow extends Window {

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
	public ArrivalOfGoodsJobDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			ArrivalOfGoodsJobListgrid listGrid,
			String iconUrl,
			Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		objWindow.setWidth(550);
		objWindow.setHeight(350);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setHeight("90%");
		mainForm.setNumCols(2);
		mainForm.setColWidths(100, 180);
		mainForm.setWidth(280);
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		// 物流任务编号
		final TextItem logisticsTasksNumber = new TextItem("logisticsTasksNumber", "物流任务编号");
		logisticsTasksNumber.setWidth(180);
		logisticsTasksNumber.setDisabled(true);
		// 到货工作编号
		final TextItem arrivalOfGoodsNumber = new TextItem("arrivalOfGoodsNumber","到货工作编号");
		arrivalOfGoodsNumber.setDisabled(true);
		arrivalOfGoodsNumber.setWidth(180);
		// 到达日期
		final CustomDateItem arrivalOfGoodsDate = new CustomDateItem("arrivalOfGoodsDate", "到达日期");
		arrivalOfGoodsDate.setWidth(180);
		arrivalOfGoodsDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 联系人
		final TextItem contacter = new TextItem("contacter", "联系人");
		contacter.setWidth(180);
		// 运输班次
		final TextItem numberOfRuns = new TextItem("numberOfRuns", "运输班次");
		numberOfRuns.setWidth(180);
		// 到达口岸
		final TextItem portOfDestinat = new TextItem("portOfDestinat","到达口岸");
		portOfDestinat.setWidth(180);
		// 备注
		final TextAreaItem txtMemo = new TextAreaItem("memo", "备注");
		txtMemo.setWidth(180);
		txtMemo.setHeight(80);

		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.saveData();
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
		
		mainForm.setFields(logisticsTasksNumber
				,arrivalOfGoodsNumber
				,arrivalOfGoodsDate
				,contacter
				,numberOfRuns
				,portOfDestinat
				,txtMemo
				);

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);
		
		if (isView == true) {
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