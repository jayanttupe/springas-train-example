package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.discardServiceBusiness;

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
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class DiscardServiceBusinessDetailWindow extends Window {

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
	public DiscardServiceBusinessDetailWindow(String windowTitle, 
									boolean isMax,
									final Rectangle rect,
									final ListGrid listGrid,
									String iconUrl,
									final Boolean updateFlg,
									final Boolean isView) {
		final Window objWindow = this;
		setWidth(720);
		setHeight(450);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setNumCols(7);
		mainForm.setWidth(520);
		mainForm.setHeight("90%");
		mainForm.setColWidths(100, 80, 80, 100, 60, 40, 60);
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		// 报废申请单编号
		final TextItem txtDiscardReportNumber = new TextItem("discardReportNumber");
		txtDiscardReportNumber.setWidth(80);
		txtDiscardReportNumber.setDisabled(true);
		// 是否送修登记
		final CheckboxItem chkIsRepair = new CheckboxItem("isRepair");
		chkIsRepair.setShowTitle(false);
		chkIsRepair.setWidth(80);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber");
		txtContractNumber.setWidth(160);
		txtContractNumber.setColSpan(3);
		// 件号
		final TextItem txtPartNumber = new TextItem("partNumber");
		txtPartNumber.setWidth(160);
		txtPartNumber.setColSpan(2);
		// 序列/批次号
		final TextItem txtPartSerialNumber = new TextItem("partSerialNumber");
		txtPartSerialNumber.setWidth(160);
		txtPartSerialNumber.setColSpan(3);
		// 名称
		final TextItem txtName = new TextItem("name");
		txtName.setWidth(160);
		txtName.setColSpan(2);
		// 数量
		final TextItem txtQuantity = new TextItem("quantity");
		txtQuantity.setWidth(60);
		// 单位
		final SelectItem txtUnitMeasureCode = new SelectItem("unitMeasureCode");
		txtUnitMeasureCode.setWidth(60);
		// 报废类型
		final SelectItem txtDiscardTypes = new SelectItem("discardTypes");
		txtDiscardTypes.setWidth(160);
		txtDiscardTypes.setColSpan(2);
		// 购买日期
		final CustomDateItem txtBuyData = new CustomDateItem("buyData");
		txtBuyData.setWidth(160);
		txtBuyData.setColSpan(3);
		// 原价(单价)
		final TextItem txtOriginalUnitPrice = new TextItem("originalUnitPrice");
		txtOriginalUnitPrice.setWidth(160);
		txtOriginalUnitPrice.setColSpan(2);
		// 当前残值(单价)
		final TextItem txtRemainderUnitPrice = new TextItem("remainderUnitPrice");
		txtRemainderUnitPrice.setWidth(160);
		txtRemainderUnitPrice.setColSpan(3);
		// 当前残值(总价)
		final TextItem txtRemainderTotalPrice = new TextItem("remainderTotalPrice");
		txtRemainderTotalPrice.setWidth(160);
		txtRemainderTotalPrice.setColSpan(2);
		// 使用小时/起落
		final TextItem txtUsedTime = new TextItem("usedTime");
		txtUsedTime.setWidth(160);
		txtUsedTime.setColSpan(3);
		// 库房编号
		final SelectItem txtStockRoomNumber = new SelectItem("stockRoomNumber");
		txtStockRoomNumber.setWidth(160);
		txtStockRoomNumber.setColSpan(2);
		txtStockRoomNumber.setValueField("id");
		txtStockRoomNumber.setDisplayField("stockRoomNumber");
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber");
		ListGridField stockRoomChineseNameField = new ListGridField("stockRoomChineseName");
		txtStockRoomNumber.setPickListFields(stockRoomNumberField, stockRoomChineseNameField);
		// 获取库房数据
		String stockRoomModeName = "stockServiceBusiness.basicData.stockRoom";
		String stockRoomDSName = "stockRoom_dataSource";
		DataSourceTool instockRoomDST = new DataSourceTool();
		instockRoomDST.onCreateDataSource(stockRoomModeName, stockRoomDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						txtStockRoomNumber.clearValue();
						txtStockRoomNumber.setOptionDataSource(dataSource);
						
						if (updateFlg == true)
						{
							final Record record = listGrid.getSelectedRecord();
							mainForm.editRecord(record);
						}
					}
				});
		// 货位编号
		final TextItem txtCargoSpaceNumber = new TextItem("cargoSpaceNumber");
		txtCargoSpaceNumber.setMask(">CCC-CC-CCC-CCC-CC");
		txtCargoSpaceNumber.setWidth(160);
		txtCargoSpaceNumber.setColSpan(3);
		// 处理意向
		final TextItem txtDisposeDescribe = new TextItem("disposeDescribe");
		txtDisposeDescribe.setWidth(160);
		txtDisposeDescribe.setColSpan(2);
		// 处理人
		final TextItem txtDiscardReportProcessor = new TextItem("discardReportProcessor");
		txtDiscardReportProcessor.setWidth(160);
		txtDiscardReportProcessor.setColSpan(3);
		// 待处理航材状态描述
		final TextAreaItem txtDiscardPartStatusDescribe = new TextAreaItem("discardPartStatusDescribe");
		txtDiscardPartStatusDescribe.setWidth(420);
		txtDiscardPartStatusDescribe.setHeight(60);
		txtDiscardPartStatusDescribe.setColSpan(6);
		// 申请人
		final TextItem txtApplicant = new TextItem("applicant");
		txtApplicant.setWidth(160);
		txtApplicant.setColSpan(2);
		// 申请日期
		final CustomDateItem txtApplicationDate = new CustomDateItem("applicationDate");
		txtApplicationDate.setWidth(160);
		txtApplicationDate.setColSpan(3);
		// 质检处理人
		final TextItem txtQualityTestPerson = new TextItem("qualityTestPerson");
		txtQualityTestPerson.setWidth(160);
		txtQualityTestPerson.setColSpan(2);
		// 处理日期
		final CustomDateItem txtDiscardReportProcessDate = new CustomDateItem("discardReportProcessDate");
		txtDiscardReportProcessDate.setWidth(160);
		txtDiscardReportProcessDate.setColSpan(3);
		
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

		mainForm.setFields(txtDiscardReportNumber
				,chkIsRepair
				,txtContractNumber
				,txtPartNumber
				,txtPartSerialNumber
				,txtName
				,txtQuantity
				,txtUnitMeasureCode
				,txtDiscardTypes
				,txtBuyData
				,txtOriginalUnitPrice
				,txtRemainderUnitPrice
				,txtRemainderTotalPrice
				,txtUsedTime
				,txtStockRoomNumber
				,txtCargoSpaceNumber
				,txtDisposeDescribe
				,txtDiscardReportProcessor
				,txtDiscardPartStatusDescribe
				,txtApplicant
				,txtApplicationDate
				,txtQualityTestPerson
				,txtDiscardReportProcessDate
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