package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet.WaitReceivingSheetItemsListgrid;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet.WaitReceivingSheetListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PickupOrderSelectWindow extends Window {
	
	public PickupOrderSelectWindow(
			String windowTitle
			, final Rectangle rect
			, String iconUrl
			, final DynamicForm mainForm
			, final ListGrid receivingSheetItemsListgrid) {
		final Window objWindow = this;
		setWidth(850);
		setHeight(530);

		// 指令Grid
		final WaitReceivingSheetListgrid waitReceivingSheetListgrid = new WaitReceivingSheetListgrid();
		waitReceivingSheetListgrid.setWidth(620);
		waitReceivingSheetListgrid.setHeight(220);
		waitReceivingSheetListgrid.setMargin(5);

		// 获取指令数据源
		String headModeName = "stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet";
		String headDSName = "pickupOrder_dataSource";
		DataSourceTool receivingDS = new DataSourceTool();
		receivingDS.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						waitReceivingSheetListgrid.setDataSource(dataSource);
						waitReceivingSheetListgrid.fetchData();
						waitReceivingSheetListgrid.drawWaitReceivingSheetListgrid();
					}
				});

		// 指令明细Grid
		final WaitReceivingSheetItemsListgrid waitReceivingSheetItemsListgrid = new WaitReceivingSheetItemsListgrid();
		waitReceivingSheetItemsListgrid.setWidth(620);
		waitReceivingSheetItemsListgrid.setHeight(230);
		waitReceivingSheetItemsListgrid.setMargin(5);

		// 获取指令明细数据源
		String detailModeName = "stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet";
		String detailDSName = "pickupOrderItems_dataSource";
		DataSourceTool receivingDetailDS = new DataSourceTool();
		receivingDetailDS.onCreateDataSource(detailModeName, detailDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					waitReceivingSheetItemsListgrid.setDataSource(dataSource);
					waitReceivingSheetItemsListgrid.drawWaitReceivingSheetItemsListgrid();
				}
			});
		
		// 选择指令的时候，读取指令明细的数据
		waitReceivingSheetListgrid.addRecordClickHandler(new RecordClickHandler() {  
			public void onRecordClick(RecordClickEvent event) {
				Criteria criteria = new Criteria();
				criteria.addCriteria("temp", String.valueOf(Math.random()));
				criteria.addCriteria("orderID", waitReceivingSheetListgrid.getSelectedRecord().getAttribute("orderID"));
				waitReceivingSheetItemsListgrid.fetchData(criteria);
			}
		});

		// 选择按钮
		final IButton selectButton = new IButton();
		selectButton.setTitle("选择");
		selectButton.setWidth(65);
		selectButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (waitReceivingSheetListgrid.getSelection().length == 1) {
					Record record = waitReceivingSheetListgrid.getSelectedRecord();
					
					// 提货指令ID
					mainForm.getItem("orderID").setAttribute("Value", record.getAttribute("orderID"));
					// 提货指令单编号
					mainForm.getItem("orderNumber").setAttribute("Value", record.getAttribute("orderNumber"));
					// 业务类型
					mainForm.getItem("businessType").setAttribute("Value", record.getAttribute("businessType"));
					// 合同编号
					mainForm.getItem("contractNumber").setAttribute("Value", record.getAttribute("contractNumber"));
					// 运单号
					mainForm.getItem("mainWayBill").setAttribute("Value", record.getAttribute("mainWayBill"));
					// 物流操作人员
					mainForm.getItem("logisticsCreateBy").setAttribute("Value", record.getAttribute("logisticsCreateBy"));
					// 物流操作日期
					mainForm.getItem("logisticsCreateDate").setAttribute("Value", record.getAttribute("logisticsCreateDate"));

					Criteria criteria = new Criteria();
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("type", "order");
					criteria.addCriteria("orderID", record.getAttribute("orderID"));
					receivingSheetItemsListgrid.fetchData(criteria);

					ShowWindowTools.showCloseWindow(rect, objWindow, -1);
				} else {
					SC.say("请选择一条指令记录！");
				}
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
		buttonLayout.addMember(selectButton);
		buttonLayout.addMember(returnButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(waitReceivingSheetListgrid);
		layout.addMember(waitReceivingSheetItemsListgrid);
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
