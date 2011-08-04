package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
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
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class DeliverTheGoodsJobDetailWindow extends Window {

	public DeliverTheGoodsJobDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			DeliverTheGoodsJobListgrid listGrid,
			String iconUrl,
			Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		objWindow.setHeight(350);
		objWindow.setWidth(550);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setHeight("90%");
		mainForm.setNumCols(2);
		mainForm.setColWidths(120, 180);
		mainForm.setWidth(300);
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		// 交货计划编号
		final TextItem deliverTheGoodsNumber = new TextItem("deliverTheGoodsNumber", "交货计划编号");
		deliverTheGoodsNumber.setDisabled(true);
		deliverTheGoodsNumber.setWidth(180);
		// 物流任务编号
		final TextItem logisticsTasksNumber = new TextItem("logisticsTasksNumber", "物流任务编号");
		logisticsTasksNumber.setWidth(180);
		logisticsTasksNumber.setDisabled(true);
		// 运输班次
		final TextItem numberOfRuns = new TextItem("numberOfRuns", "运输班次");
		numberOfRuns.setWidth(180);
		// 交货日期
		final CustomDateItem deliverTheGoodsDate = new CustomDateItem("deliverTheGoodsDate", "交货日期");
		deliverTheGoodsDate.setWidth(180);
		// 交货地址
		final TextItem addressOfDeliverTheGoods = new TextItem("addressOfDeliverTheGoods","交货地址");
		addressOfDeliverTheGoods.setWidth(180);
		// 联系人
		final TextItem linkMan = new TextItem("contacter", "联系人");
		linkMan.setWidth(180);
		// 备注
		final TextAreaItem remarkText = new TextAreaItem("memo", "备注");
		remarkText.setWidth(180);
		remarkText.setHeight(80);

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

		mainForm.setFields(deliverTheGoodsNumber
							,logisticsTasksNumber
							,numberOfRuns
							,deliverTheGoodsDate
							,addressOfDeliverTheGoods
							,linkMan
							,remarkText
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