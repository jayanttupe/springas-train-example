package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ReceivingSheetSelectWindow extends Window {
	
	public ReceivingSheetSelectWindow(
			String windowTitle
			, final Rectangle rect
			, String iconUrl
			, final DynamicForm mainForm
			, final boolean isRepair) {
		final Window objWindow = this;
		setWidth(800);
		setHeight(520);

		final ListGrid receivingListGrid = new ListGrid();
		receivingListGrid.setCanEdit(false);
		receivingListGrid.setCellHeight(22);
		receivingListGrid.setWidth(600);
		receivingListGrid.setHeight(220);
		receivingListGrid.setMargin(5);
		receivingListGrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取数据源
		String modeName = "stockServiceBusiness.inStockRoomBusiness.receivingSheet";
		String dsName = "receivingSheet_dataSource";
		DataSourceTool receivingDS = new DataSourceTool();
		receivingDS.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						receivingListGrid.setDataSource(dataSource);
						
						// 收料单编号
						ListGridField receivingSheetNumberFiled = new ListGridField("receivingSheetNumber", "收料单编号");
						// 业务类型
						ListGridField businessTypeField = new ListGridField("businessType", "业务类型");
						// 指令单编号
						ListGridField orderNumberField = new ListGridField("orderNumber", "指令单编号");
						// 合同编号
						ListGridField contractNumberField = new ListGridField("contractNumber", "合同编号");
						// 运单号
						ListGridField mainWayBillField = new ListGridField("mainWayBill", "运单号");
						// 收料人
						ListGridField receivingUserField = new ListGridField("receivingUser", "收料人");
						// 收料日期
						ListGridField receivingDateField = new ListGridField("receivingDate", "收料日期");

						receivingListGrid.setFields(receivingSheetNumberFiled
												,businessTypeField
												,orderNumberField
												,contractNumberField
												,mainWayBillField
												,receivingUserField
												,receivingDateField);

						Criteria criteria = new Criteria();
						if (isRepair == true) {
							criteria.addCriteria("isRepair", true);
						} else {
							criteria.addCriteria("isRepair", false);
							criteria.addCriteria("isWaitCheck", "1");
						}
						receivingListGrid.fetchData(criteria);
					}
				});

		// 获取数据源
		modeName = "stockServiceBusiness.inStockRoomBusiness.receivingSheet";
		dsName = "receivingSheetItems_dataSource";
		final ReceivingSheetItemsListgrid receivingDetailListGrid = new ReceivingSheetItemsListgrid();
		receivingDetailListGrid.setCanEdit(false);
		receivingDetailListGrid.setCellHeight(22);
		receivingDetailListGrid.setWidth(600);
		receivingDetailListGrid.setHeight(220);
		receivingDetailListGrid.setMargin(5);
		receivingDetailListGrid.setSelectionType(SelectionStyle.SINGLE);

		DataSourceTool receivingDetailDS = new DataSourceTool();
		receivingDetailDS.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						receivingDetailListGrid.setDataSource(dataSource);
						receivingDetailListGrid.drawReceivingSheetItemsListgrid(false);
					}
				});
		// 选择收料的时候，读取收料明细的数据
		receivingListGrid.addRecordClickHandler(new RecordClickHandler() {  
			public void onRecordClick(RecordClickEvent event) {  
				Criteria criteria = new Criteria();
				Record record = receivingListGrid.getSelectedRecord();
				String receivingSheetID = record.getAttribute("id");
				criteria.addCriteria("isCheck", "0");
				criteria.addCriteria("receivingSheetID", "" + receivingSheetID);
				criteria.addCriteria("temp", String.valueOf(Math.random()));
				receivingDetailListGrid.filterData(criteria);
			}  
		});
		
		// 选择处理
		final IButton selectButton = new IButton();
		selectButton.setTitle("选择");
		selectButton.setWidth(65);
		selectButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 检验的时候，选择收料单
				if (isRepair == false) {
					if (receivingListGrid.getSelection().length == 1) {
						Record receivingRecord = receivingListGrid.getSelectedRecord();
						// 收料单ID
						mainForm.getField("receivingSheetID").setValue(receivingRecord.getAttribute("id"));
					} else {
						SC.say("请选择一条收料单进行检验！");
					}
				}
				// 送修登记的时候，选择送修件
				if (isRepair == true) {
					if (receivingDetailListGrid.getSelection().length == 1) {
						Record receivingRecord = receivingListGrid.getSelectedRecord();
						Record receivingDetailRecord = receivingDetailListGrid.getSelectedRecord();
						FormItem[] formItems = mainForm.getFields();
						for (FormItem formItem : formItems)
						{
							// 合同编号
							if (formItem.getName().contains("contractNumber")) {
								formItem.setValue(receivingRecord.getAttribute("contractNumber"));
							}
							// 收料单ID
							if (formItem.getName().contains("receivingSheetID")) {
								formItem.setValue(receivingRecord.getAttribute("id"));
							}
							// 收料单编号
							if (formItem.getName().contains("receivingSheetNumber")) {
								formItem.setValue(receivingRecord.getAttribute("receivingSheetNumber"));
							}
							// 件号
							if (formItem.getName().contains("partNumber")) {
								formItem.setValue(receivingDetailRecord.getAttribute("partNumber"));
							}
							// 序号/批号
							if (formItem.getName().contains("partSerialNumber")) {
								formItem.setValue(receivingDetailRecord.getAttribute("partSerialNumber"));
							}
							// 制造商
//							if (formItem.getName().contains("manufacturer")) {
//								formItem.setValue(receivingDetailRecord.getAttribute("partNumber"));
//							}
							// 数量
							if (formItem.getName().contains("quantity")) {
								formItem.setValue(receivingDetailRecord.getAttribute("quantity"));
							}
							// 单位
							if (formItem.getName().contains("unit")) {
								formItem.setValue(receivingDetailRecord.getAttribute("partUnit"));
							}
							// 件名称
							if (formItem.getName().contains("partName")) {
								formItem.setValue(receivingDetailRecord.getAttribute("partName"));
							}
						}
					} else {
						SC.say("请选择一条收料单明细！");
					}
				}

				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		// 返回处理
		final IButton returnButton = new IButton();
		returnButton.setTitle("返回");
		returnButton.setWidth(65);
		returnButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final BtnsHLayout buttonLayout = new BtnsHLayout(); 
		buttonLayout.addMember(selectButton);
		buttonLayout.addMember(returnButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(receivingListGrid);
		layout.addMember(receivingDetailListGrid);
		layout.addMember(buttonLayout);
		layout.draw();

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}
