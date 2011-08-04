/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationItemsListgrid;
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
 */
public class ExportCustomsTariffRecordDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param dataSource
	 * @param iconUrl
	 * @param updateFlg
	 * @param isCustomsDeclaration
	 * @param isView
	 */
	public ExportCustomsTariffRecordDetailWindow(String windowTitle,
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
		setHeight(500);
		
		// 出口报关关税ID
		final HiddenItem txtExportCustomsTariffID = new HiddenItem("id");
		// 报关ID
		final HiddenItem txtCustomsID = new HiddenItem("customsID");
		// 指令单ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 预录入编号
		final TextItem prepareEnterNumber = new TextItem("prepareEnterNumber", "预录入编号");
		prepareEnterNumber.setWidth(120);
		// 合同编号
		final TextItem contractNumber = new TextItem("contractNumber", "合同编号");
		contractNumber.setWidth(120);
		// 指令单号
		final TextItem orderNumber = new TextItem("orderNumber", "指令单号");
		orderNumber.setWidth(120);
		// 贸易方式
		final SelectItem tradeType  = new SelectItem("tradeType", "贸易方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TradeMethods", tradeType);
		tradeType.setWidth(120);
		// 海关编号
		final TextItem customsNumber = new TextItem("customsNumber", "海关编号");
		customsNumber.setWidth(120);
		// 运输方式
		final SelectItem transportationType = new SelectItem("transportationType", "运输方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TransportationCode", transportationType);
		transportationType.setWidth(120);
		// 口岸
		final TextItem portOfClearance=new TextItem("portOfClearance","口岸");
		portOfClearance.setWidth(120);
		// 备件中心位置
		final TextItem spareCenterPosition=new TextItem("spareCenterPosition","备件中心位置");
		spareCenterPosition.setWidth(120);
		// 运单号
		final TextItem trackingNumber=new TextItem("trackingNumber","运单号");
		trackingNumber.setWidth(120);
		// 件数
		final TextItem packagesNumber = new TextItem("packagesNumber", "件数");
		packagesNumber.setWidth(120);
		// 申报日期
		final CustomDateItem reportingDate = new CustomDateItem("reportingDate", "申报日期");
		reportingDate.setWidth(120);
		// 申报单位编号
		final TextItem applicant = new TextItem("applicant", "申报单位编号");
		applicant.setWidth(120);
		// 税款金额
		final TextItem taxPayment = new TextItem("taxPayment", "税款金额");
		taxPayment.setWidth(120);
		// 关税单号
		final TextItem tariffOrder = new TextItem("tariffOrder", "关税单号");
		tariffOrder.setWidth(120);
		// 总金额
		final TextItem totalAmount = new TextItem("totalAmount", "总金额");
		totalAmount.setWidth(120);
		// 填发日期
		final CustomDateItem paymentDate = new CustomDateItem("paymentDate", "填发日期");
		paymentDate.setWidth(120);
		// 缴款期限
		final CustomDateItem payDeadline = new CustomDateItem("payDeadline", "缴款期限");
		payDeadline.setWidth(120);
		// 汇率
		final TextItem exchangeRate = new TextItem("exchangeRate", "汇率");
		exchangeRate.setWidth(120);
		// 备件项
		final ExportCustomsDeclarationItemsListgrid exportCustomsDeclarationItemsListgrid = 
			new ExportCustomsDeclarationItemsListgrid();
		exportCustomsDeclarationItemsListgrid.setWidth(610);
		exportCustomsDeclarationItemsListgrid.setHeight(210);
		exportCustomsDeclarationItemsListgrid.setMargin(5);
		exportCustomsDeclarationItemsListgrid.setAutoSaveEdits(false);
		exportCustomsDeclarationItemsListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取明细数据
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsDeclaration";
		String dsName = "exportCustomsDeclarationItems_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						exportCustomsDeclarationItemsListgrid.setDataSource(dataSource);
						
						exportCustomsDeclarationItemsListgrid.drawExportCustomsDeclarationItemsListgrid();
						if (isView == true) {
							exportCustomsDeclarationItemsListgrid.setCanEdit(false);	
						} else {
							exportCustomsDeclarationItemsListgrid.setCanEdit(true);
						}
						exportCustomsDeclarationItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
						if (txtCustomsID.getValue() != null)
						{
							exportCustomsDeclarationItemsListgrid.fetchData(new Criteria("customsID", "" + txtCustomsID.getValue().toString()));
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
		headForm.setFields(txtExportCustomsTariffID
				,txtCustomsID
				,txtOrderID
				,contractNumber
				,prepareEnterNumber
				,trackingNumber
				,orderNumber
				,customsNumber
				,packagesNumber
				,spareCenterPosition
				,transportationType
				,tradeType
				);
		
		contractNumber.setDisabled(true);
		prepareEnterNumber.setDisabled(true);
		trackingNumber.setDisabled(true);
		orderNumber.setDisabled(true);
		customsNumber.setDisabled(true);
		packagesNumber.setDisabled(true);
		spareCenterPosition.setDisabled(true);
		transportationType.setDisabled(true);
		tradeType.setDisabled(true);
		
		
		// 关税信息
		final DynamicForm secondForm = new DynamicForm();
		secondForm.setGroupTitle("<font color='blue'>关税信息</font>");
		secondForm.setIsGroup(true);
		secondForm.setDataSource(listGrid.getDataSource());
		secondForm.setMargin(5);
		secondForm.setWidth(610);
		secondForm.setNumCols(6);
		secondForm.setColWidths(90,120,80,120,80,120);
		secondForm.setFields(txtExportCustomsTariffID
				,txtCustomsID
				,txtOrderID
				,applicant
				,tariffOrder
				,portOfClearance
				,reportingDate
				,taxPayment
				,totalAmount
				,paymentDate
				,payDeadline
				,exchangeRate
				);
		
		// 保存
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 设置明细数据
				ListGridRecord[] detailRecord = new ListGridRecord[exportCustomsDeclarationItemsListgrid.getTotalRows()];
				for (int i = 0; i < exportCustomsDeclarationItemsListgrid.getTotalRows(); i++)
				{
					detailRecord[i] = (ListGridRecord)exportCustomsDeclarationItemsListgrid.getEditedRecord(i);
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

		// 取消
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
			// 指令编号
			orderNumber.setValue(record.getAttribute("orderNumber"));
			// 合同编号
			contractNumber.setValue(record.getAttribute("contractNumber"));
		}
		
		final BtnsHLayout layoutHeadBtn = new BtnsHLayout(); 
		layoutHeadBtn.addMember(saveButton);
		layoutHeadBtn.addMember(cancelButton);
	
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(headForm);
		layout.addMember(secondForm);
		layout.addMember(exportCustomsDeclarationItemsListgrid);
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