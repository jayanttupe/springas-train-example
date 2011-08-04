/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit;

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
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class ExportSecurityDepositDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 */
	public ExportSecurityDepositDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			final ListGrid listGrid,
			DataSource dataSource,
			String iconUrl,
			Boolean updateFlg,
			final Boolean isCustomsDeclaration,
			final Boolean isView) {
		final Window objWindow = this;
		setTitle(windowTitle);
		setWidth(810);
		setHeight(510);

		// 进口报关保证金ID
		final HiddenItem txtExportSecurityDepositID = new HiddenItem("id");
		// 报关ID
		final HiddenItem txtCustomsID = new HiddenItem("customsID");
		// 指令ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber", "合同编号");
		txtContractNumber.setWidth(120);
		// 申报日期
		final CustomDateItem txtDeclarationDate = new CustomDateItem("declarationDate", "申报日期");
		txtDeclarationDate.setWidth(120);
		// 到期时间
		final CustomDateItem txtExpireDates = new CustomDateItem("expireDates", "到期时间");
		txtExpireDates.setWidth(120);
		// 主运单号
		final TextItem txtMasterWaybillNumber = new TextItem("masterWaybillNumber", "主运单号");
		txtMasterWaybillNumber.setWidth(120);
		// 分运单号
		final TextItem txtHouseWaybillNumber = new TextItem("houseWaybillNumber", "分运单号");
		txtHouseWaybillNumber.setWidth(120);
		// 件数
		final TextItem txtNumberOfPackage = new TextItem("numberOfPackage", "件数");
		txtNumberOfPackage.setWidth(120);
		// 办保原因
		final TextItem txtReason = new TextItem("reason", "办保原因");
		txtReason.setWidth(120);
		// 担保金额
		final TextItem txtSecurityDepositAmount = new TextItem("securityDepositAmount", "担保金额");
		txtSecurityDepositAmount.setWidth(120);
		// 保证金单编号
		final TextItem txtSecurityDepositNumber = new TextItem("securityDepositNumber", "保证金单编号");
		txtSecurityDepositNumber.setWidth(120);
		// 保证起始日期
		final CustomDateItem txtStartDate = new CustomDateItem("startDate", "保证起始日期");
		txtStartDate.setWidth(120);
		// 运输方式代码
		final SelectItem txtTransportationCode = new SelectItem("transportationCode", "运输方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TransportationCode", txtTransportationCode);
		txtTransportationCode.setWidth(120);
		// 报关代理商
		final TextItem txtClearanceAgent = new TextItem("clearanceAgent", "报关代理商");
		txtClearanceAgent.setWidth(120);
		// 保证金类型
		final SelectItem selSecurityDepositType = new SelectItem("securityDepositType", "保证金类型");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.SecurityDepositType", selSecurityDepositType);
		selSecurityDepositType.setWidth(120);
		// 报关口岸
		final TextItem txtPortOfClearance = new TextItem("portOfClearance", "报关口岸");
		txtPortOfClearance.setWidth(120);
		// 备件中心位置
		final TextItem txtSparePartCenterPosition = new TextItem("sparePartCenterPosition", "备件中心位置");
		txtSparePartCenterPosition.setWidth(120);
		// 出口日期
		final CustomDateItem txtExportDate = new CustomDateItem("exportDate", "出口日期");
		txtExportDate.setWidth(120);
		// 备件项
		final ExportSecurityDepositItemsListgrid exportSecurityDepositSparepartsListgrid =
			new ExportSecurityDepositItemsListgrid();
		exportSecurityDepositSparepartsListgrid.setWidth(610);
		exportSecurityDepositSparepartsListgrid.setHeight(210);
		exportSecurityDepositSparepartsListgrid.setMargin(5);
		exportSecurityDepositSparepartsListgrid.setAutoSaveEdits(false);
		exportSecurityDepositSparepartsListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取明细数据
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportSecurityDeposit";
		String dsName = "exportCustomsDeclarationItems_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						exportSecurityDepositSparepartsListgrid.setDataSource(dataSource);
						
						exportSecurityDepositSparepartsListgrid.drawExportSecurityDepositSparepartsListgrid();
						if (isView == true) {
							exportSecurityDepositSparepartsListgrid.setCanEdit(false);
						} else {
							exportSecurityDepositSparepartsListgrid.setCanEdit(true);
						}
						exportSecurityDepositSparepartsListgrid.setEditEvent(ListGridEditEvent.CLICK);
						if (txtCustomsID.getValue() != null)
						{
							exportSecurityDepositSparepartsListgrid.fetchData(new Criteria("customsID", "" + txtCustomsID.getValue().toString()));
						}
					}
				});
		
		// 基本信息
		final DynamicForm headForm = new DynamicForm();
		headForm.setGroupTitle("<font color='blue'>基本信息</font>");
		headForm.setIsGroup(true);
		headForm.setDataSource(listGrid.getDataSource());
		headForm.setMargin(5);
		headForm.setWidth(610);
		headForm.setNumCols(6);
		headForm.setColWidths(90,120,80,120,80,120);
		headForm.setFields(txtExportSecurityDepositID
				,txtCustomsID
				,txtOrderID
				,txtContractNumber
				,txtMasterWaybillNumber
				,txtHouseWaybillNumber
				,txtTransportationCode
				,txtClearanceAgent
				,txtNumberOfPackage
				);
		
		txtContractNumber.setDisabled(true);
		txtMasterWaybillNumber.setDisabled(true);
		txtHouseWaybillNumber.setDisabled(true);
		txtTransportationCode.setDisabled(true);
		txtClearanceAgent.setDisabled(true);
		txtNumberOfPackage.setDisabled(true);
		
		// 保证金信息
		final DynamicForm secondForm = new DynamicForm();
		secondForm.setGroupTitle("<font color='blue'>保证金信息</font>");
		secondForm.setIsGroup(true);
		secondForm.setDataSource(listGrid.getDataSource());
		secondForm.setMargin(5);
		secondForm.setWidth(610);
		secondForm.setNumCols(6);
		secondForm.setColWidths(90,120,80,120,80,120);
		secondForm.setFields(txtExportSecurityDepositID
				,txtCustomsID
				,txtOrderID
				,txtSecurityDepositNumber
				,selSecurityDepositType
				,txtSecurityDepositAmount
				,txtPortOfClearance
				,txtExportDate
				,txtDeclarationDate
				,txtExpireDates
				,txtStartDate
				,txtSparePartCenterPosition
				,txtReason
				);
		
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 设置明细数据
				ListGridRecord[] detailRecord = new ListGridRecord[exportSecurityDepositSparepartsListgrid.getTotalRows()];
				for (int i = 0; i < exportSecurityDepositSparepartsListgrid.getTotalRows(); i++)
				{
					detailRecord[i] = (ListGridRecord)exportSecurityDepositSparepartsListgrid.getEditedRecord(i);
				}
				secondForm.setValue("exportCustomsDeclarationItems", detailRecord);
				
				secondForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						if (isCustomsDeclaration == true) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							criteria.addCriteria("workStatus", "2");
							listGrid.fetchData(criteria);
						}
					}
				});
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
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


		if (isCustomsDeclaration == false) {
			secondForm.setDataSource(listGrid.getDataSource());
			
			if (updateFlg == true) {
				final Record record = listGrid.getSelectedRecord();
				secondForm.editRecord(record);
			}
		} else {
			secondForm.setDataSource(dataSource);
			Record record = listGrid.getSelectedRecord();

			// 报关ID
			txtCustomsID.setValue(record.getAttribute("id"));
			// 指令ID
			txtOrderID.setValue(record.getAttribute("orderID"));
			// 合同编号
			txtContractNumber.setValue(record.getAttribute("contractNumber"));
			// 报关代理商
			txtClearanceAgent.setValue(record.getAttribute("pickupDeliveryOrder.clearanceAgent"));
		}

		final BtnsHLayout layoutHeadBtn = new BtnsHLayout(); 
		layoutHeadBtn.addMember(saveButton);	
		layoutHeadBtn.addMember(cancelButton);
	
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(headForm);
		layout.addMember(secondForm);
		layout.addMember(exportSecurityDepositSparepartsListgrid);
		layout.addMember(layoutHeadBtn);
		
		if (isView == true) {
			Utils.setReadOnlyForm(headForm);
			Utils.setReadOnlyForm(secondForm);
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