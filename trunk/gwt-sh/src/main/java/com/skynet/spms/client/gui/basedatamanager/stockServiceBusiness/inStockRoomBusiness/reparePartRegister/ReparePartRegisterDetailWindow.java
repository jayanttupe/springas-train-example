package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.reparePartRegister;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetSelectWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class ReparePartRegisterDetailWindow extends Window {

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
	public ReparePartRegisterDetailWindow(String windowTitle, 
			boolean isMax,
			final Rectangle rect,
			ListGrid listGrid,
			String iconUrl,
			Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(700);
		setHeight(280);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setNumCols(6);
		mainForm.setWidth(500);
		mainForm.setHeight("90%");
		mainForm.setColWidths(90, 60, 40, 60, 90, 160);

		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 送修登记单号
		final TextItem txtRepairRegisterSheetNumber = new TextItem("repairRegisterSheetNumber");
		txtRepairRegisterSheetNumber.setWidth(160);
		txtRepairRegisterSheetNumber.setColSpan(3);
		txtRepairRegisterSheetNumber.setDisabled(true);
		// 收料单编号
		final HiddenItem txtReceivingSheetID = new HiddenItem("receivingSheetID");
		// 收料单编号
		final TextItem txtReceivingSheetNumber = new TextItem("receivingSheetNumber");
		txtReceivingSheetNumber.setDisabled(true);
		txtReceivingSheetNumber.setWidth(160);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber");
		txtContractNumber.setDisabled(true);
		txtContractNumber.setColSpan(3);
		txtContractNumber.setWidth(160);
		// 件号
		final TextItem txtPartNumber = new TextItem("partNumber");
		txtPartNumber.setWidth(160);
		final FormItemIcon icoPartNumber = new FormItemIcon();
		txtPartNumber.setIcons(icoPartNumber);
		// 选择收料单处理
		txtPartNumber.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent  event) {
				ReceivingSheetSelectWindow objWindow =
					new ReceivingSheetSelectWindow("选择送修件", txtPartNumber.getPageRect(), "showwindow/stock_add_01.png", mainForm, true);
				ShowWindowTools.showWondow(txtPartNumber.getPageRect(), objWindow, -1);
			}
		});
		// 序号/批号
		final TextItem txtPartSerialNumber = new TextItem("partSerialNumber");
		txtPartSerialNumber.setDisabled(true);
		txtPartSerialNumber.setColSpan(3);
		txtPartSerialNumber.setWidth(160);
		// 制造商
		final TextItem txtManufacturer = new TextItem("manufacturer");
		txtManufacturer.setDisabled(true);
		txtManufacturer.setWidth(160);
		// 数量
		final TextItem txtQuantity = new TextItem("quantity");
		txtQuantity.setDisabled(true);
		txtQuantity.setWidth(60);
		// 单位
		final SelectItem txtUnit = new SelectItem("unit");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode", txtUnit);
		txtUnit.setDisabled(true);
		txtUnit.setWidth(60);
		// 件描述
		final TextItem txtPartName = new TextItem("partName");
		txtPartName.setDisabled(true);
		txtPartName.setWidth(160);
		// 逻辑库
		final SelectItem txtLogicStock = new SelectItem("logicStock");
		txtLogicStock.setWidth(160);
		txtLogicStock.setColSpan(3);
		// 货位
		final SelectItem txtCargoSpace = new SelectItem("cargoSpace");
		txtCargoSpace.setWidth(160);
		// 备注
		final TextAreaItem txtRemark = new TextAreaItem("remark");
		txtRemark.setColSpan(5);
		txtRemark.setWidth(410);
		txtRemark.setHeight(70);
		
		// 取得收料单编号
		String headModeName = "stockServiceBusiness.inStockRoomBusiness.receivingSheet";
		String headDSName = "receivingSheet_dataSource";
		DataSourceTool headDST = new DataSourceTool();
		headDST.onCreateDataSource(headModeName, headDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					dataSource.fetchData();
					txtReceivingSheetID.setOptionDataSource(dataSource);
	                
					txtReceivingSheetID.setDisplayField("receivingSheetNumber");
					txtReceivingSheetID.setValueField("receivingSheetNumber");
				}
			});
	
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

		mainForm.setFields(txtRepairRegisterSheetNumber
				,txtReceivingSheetID
				,txtReceivingSheetNumber
				,txtContractNumber
				,txtPartNumber
				,txtPartSerialNumber
				,txtManufacturer
				,txtQuantity
				,txtUnit
				,txtPartName 
				,txtRemark
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