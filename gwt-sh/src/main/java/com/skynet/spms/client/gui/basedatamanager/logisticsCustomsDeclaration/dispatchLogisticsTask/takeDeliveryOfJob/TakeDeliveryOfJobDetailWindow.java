
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class TakeDeliveryOfJobDetailWindow extends Window {

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
	public TakeDeliveryOfJobDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			final ListGrid listGrid,
			String iconUrl,
			final Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		objWindow.setWidth(700);
		objWindow.setHeight(350);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setHeight("90%");
		mainForm.setNumCols(4);
		mainForm.setWidth(500);
		mainForm.setColWidths(100, 150, 100, 150);
		mainForm.setPadding(5);
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		// 指令ID
		final HiddenItem orderID=new HiddenItem("orderID");
		// 取货工作编号
		final TextItem takeDeliveryNumber = new TextItem("takeDeliveryNumber", "取货工作编号");
		takeDeliveryNumber.setDisabled(true);
		takeDeliveryNumber.setWidth(150);
		// 物流任务编号
		final TextItem logisticsTasksNumber = new TextItem("logisticsTasksNumber", "物流任务编号");
		logisticsTasksNumber.setWidth(150);
		logisticsTasksNumber.setDisabled(true);
		// 联系人
		final TextItem contacter = new TextItem("contacter", "联系人");
		contacter.setWidth(150);
		// 取货日期
		final CustomDateItem takeDeliveryDate = new CustomDateItem("takeDeliveryDate", "取货日期");
		takeDeliveryDate.setWidth(150);
		// 运输方式代码
		final SelectItem txtTransportationCode = new SelectItem("pickupDeliveryOrder.transportationCode", "运输方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TransportationCode", txtTransportationCode);
		txtTransportationCode.setWidth(150);
		// 我方运代商
		final SelectItem cmbCarrier = new SelectItem("pickupDeliveryOrder.carrierID", "我方运代商");
		cmbCarrier.setWidth(150);
		ListGridField carrierCodeField = new ListGridField("carrierName", "运代商代码");
		ListGridField carrierNameField = new ListGridField("enterpriseName_zh", "运代商名称");
		cmbCarrier.setPickListFields(carrierCodeField, carrierNameField);
		// 获取运代商数据
		String carrierModeName = "organization.enterprise.carrier";
		String carrierDSName = "carrier_dataSource";
		DataSourceTool stockRoomDST = new DataSourceTool();
		stockRoomDST.onCreateDataSource(carrierModeName, carrierDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cmbCarrier.clearValue();
						cmbCarrier.setOptionDataSource(dataSource);

						cmbCarrier.setValueField("id");
						cmbCarrier.setDisplayField("enterpriseName_zh");

						if (updateFlg == true)
						{
							final Record record = listGrid.getSelectedRecord();
							cmbCarrier.setValue(record.getAttribute("pickupDeliveryOrder.carrierID"));
						}
					}
				});
		// 运输班次
		final TextItem numberOfRuns = new TextItem("numberOfRuns", "运输班次");
		numberOfRuns.setWidth(150);
		numberOfRuns.setColSpan(3);
		// 取货详细地址
		final TextItem addressOfTakeDelivery = new TextItem("addressOfTakeDelivery", "取货详细地址");
		addressOfTakeDelivery.setWidth(400);
		addressOfTakeDelivery.setColSpan(3);
		// 备注
		final TextAreaItem memo = new TextAreaItem("memo", "备注");
		memo.setColSpan(3);
		memo.setHeight(60);
		memo.setWidth(400);
		
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (takeDeliveryNumber.getValue() == null
						|| takeDeliveryNumber.getValue().equals("")) {
					SC.say("取货工作编号不能为空");
					return;
				}else{
				mainForm.saveData();
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
				}
			}
		});

		final IButton cancelButton = new IButton();
		cancelButton.setTitle("取消");
		cancelButton.setWidth(65);
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(orderID
				,takeDeliveryNumber
				,logisticsTasksNumber
				,contacter
				,takeDeliveryDate
				,txtTransportationCode
				,cmbCarrier
				,numberOfRuns
				,addressOfTakeDelivery
				,memo
				);

		final BtnsHLayout layoutHeadBtn = new BtnsHLayout();
		layoutHeadBtn.setHeight("10%");
		layoutHeadBtn.addMember(saveButton);
		layoutHeadBtn.addMember(cancelButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(layoutHeadBtn);
		
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