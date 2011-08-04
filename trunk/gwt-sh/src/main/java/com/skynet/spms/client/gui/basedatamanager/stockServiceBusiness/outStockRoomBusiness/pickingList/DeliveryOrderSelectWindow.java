package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.waitPickingList.WaitPickingItemsListgrid;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.waitPickingList.WaitPickingListgrid;
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
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class DeliveryOrderSelectWindow extends Window {
	
	public DeliveryOrderSelectWindow(
			String windowTitle
			, String iconUrl
			, final Rectangle rect
			, final DynamicForm mainForm
			, final DeliveryOrderItemsListGrid deliveryOrderItemsListGrid) {
		final Window objWindow = this;
		setWidth(830);
		setHeight(530);
		
		// 指令Grid
		final WaitPickingListgrid waitPickingListgrid = new WaitPickingListgrid();
		waitPickingListgrid.setCanEdit(false);
		waitPickingListgrid.setCellHeight(22);
		waitPickingListgrid.setWidth(620);
		waitPickingListgrid.setHeight(220);
		waitPickingListgrid.setMargin(5);
		waitPickingListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取指令数据源
		String headModeName = "stockServiceBusiness.outStockRoomBusiness.waitDeliveryOrder";
		String headDSName = "deliveryOrder_dataSource";
		DataSourceTool receivingDS = new DataSourceTool();
		receivingDS.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						waitPickingListgrid.setDataSource(dataSource);
						waitPickingListgrid.fetchData();
						waitPickingListgrid.drawWaitPickingListgrid();
					}
				});
		
		// 指令明细Grid
		final WaitPickingItemsListgrid waitPickingItemsListgrid = new WaitPickingItemsListgrid();
		waitPickingItemsListgrid.setCanEdit(false);
		waitPickingItemsListgrid.setCellHeight(22);
		waitPickingItemsListgrid.setWidth(620);
		waitPickingItemsListgrid.setHeight(230);
		waitPickingItemsListgrid.setMargin(5);
		waitPickingItemsListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取指令明细数据源
		String detailModeName = "stockServiceBusiness.outStockRoomBusiness.waitDeliveryOrder";
		String detailDSName = "deliveryOrderItems_dataSource";
		DataSourceTool receivingDetailDS = new DataSourceTool();
		receivingDetailDS.onCreateDataSource(detailModeName, detailDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					waitPickingItemsListgrid.setDataSource(dataSource);
					waitPickingItemsListgrid.drawWaitPickingItemsListgrid();
				}
			});

		// 根据选择的，取得相应的明细数据
		waitPickingListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				waitPickingItemsListgrid.setData(new ListGridRecord[]{});
				Criteria criteria = new Criteria();
				criteria.addCriteria("temp", String.valueOf(Math.random()));
				criteria.addCriteria("orderID", waitPickingListgrid.getSelectedRecord().getAttribute("orderID"));
				waitPickingItemsListgrid.fetchData(criteria);
			}
		});

		// 选择按钮
		final IButton selectButton = new IButton();
		selectButton.setTitle("选择");
		selectButton.setWidth(65);
		selectButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (waitPickingListgrid.getSelection().length == 1) {
					Record record = waitPickingListgrid.getSelectedRecord();

					// 业务类型
					mainForm.getItem("businessType").setValue(record.getAttribute("businessType"));
					// 指令ID
					mainForm.getItem("orderID").setValue(record.getAttribute("orderID"));
					// 指令单号
					mainForm.getItem("orderNumber").setValue(record.getAttribute("orderNumber"));
					// 合同编号
					mainForm.getItem("contractNumber").setValue(record.getAttribute("contractNumber"));
					// 收货单位
					mainForm.getItem("delivery").setValue(record.getAttribute("delivery"));
					// 优先级
					mainForm.getItem("priority").setValue(record.getAttribute("priority"));
					// 交货日期
					mainForm.getItem("deliveryDate").setValue(record.getAttribute("deliveryDate"));

					deliveryOrderItemsListGrid.drawDeliveryOrderItemsListGrid();

					Criteria criteria = new Criteria();
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("orderID", "" + record.getAttribute("orderID"));
					deliveryOrderItemsListGrid.fetchData(criteria);

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
		layout.addMember(waitPickingListgrid);
		layout.addMember(waitPickingItemsListgrid);
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
