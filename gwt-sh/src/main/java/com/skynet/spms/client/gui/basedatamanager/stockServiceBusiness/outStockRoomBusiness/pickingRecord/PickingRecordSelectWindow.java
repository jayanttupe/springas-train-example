package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingRecord;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListListgrid;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListPartItemsListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PickingRecordSelectWindow extends Window {
	
	public PickingRecordSelectWindow(String windowTitle
			, final Rectangle rect
			, String iconUrl
			, final DynamicForm mainForm
			, final ListGrid listGrid) {
		final Window objWindow = this;
		setWidth(800);
		setHeight(520);

		// 头部列表Grid
		final PickingListListgrid pickingListListgrid = new PickingListListgrid();
		pickingListListgrid.setWidth(600);
		pickingListListgrid.setHeight(220);
		pickingListListgrid.setMargin(5);
		pickingListListgrid.setSelectionType(SelectionStyle.SINGLE);
		
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.outStockRoomBusiness.pickingRecord";
		String headDSName = "pickingRecord_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingListListgrid.setDataSource(dataSource);
						pickingListListgrid.fetchData();
						pickingListListgrid.drawPickingListListgrid();
					}
				});

		// 明细列表Grid
		final PickingListPartItemsListgrid pickingListPartItemsListgrid = new PickingListPartItemsListgrid();
		pickingListPartItemsListgrid.setCanEdit(false);
		pickingListPartItemsListgrid.setCellHeight(22);
		pickingListPartItemsListgrid.setWidth(600);
		pickingListPartItemsListgrid.setHeight(220);
		pickingListPartItemsListgrid.setMargin(5);
		pickingListPartItemsListgrid.setSelectionType(SelectionStyle.SINGLE);
		
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.outStockRoomBusiness.pickingRecord";
		String detailDSName = "pickingListPartItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingListPartItemsListgrid.setDataSource(dataSource);
						pickingListPartItemsListgrid.drawPickingListPartItemsListgrid();
					}
				});
		
		// 根据选择的配料单，取得相应的配料明细
		pickingListListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				pickingListPartItemsListgrid.setData(new ListGridRecord[]{});
				pickingListPartItemsListgrid.fetchData(new Criteria("pickingListID", pickingListListgrid.getSelectedRecord().getAttribute("id").toString()));
			}
		});
		

		// 选择按钮
		final IButton selectButton = new IButton();
		selectButton.setTitle("选择");
		selectButton.setWidth(65);
		selectButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (pickingListListgrid.getSelection().length == 1) {
					Record pickingListRecord = pickingListListgrid.getSelectedRecord();
					FormItem[] formItems = mainForm.getFields();
					for (FormItem formItem : formItems)
					{
						// 配料单ID
						if (formItem.getName().contains("pickingListID"))
						{
							formItem.setValue(pickingListRecord.getAttribute("id"));
						}
						// 配料单号
						if (formItem.getName().contains("pickingListNumber"))
						{
							formItem.setValue(pickingListRecord.getAttribute("pickingListNumber"));
						}
						// 指令编号
						if (formItem.getName().contains("orderNumber"))
						{
							formItem.setValue(pickingListRecord.getAttribute("orderNumber"));
						}
						// 合同编号
						if (formItem.getName().contains("contractNumber"))
						{
							formItem.setValue(pickingListRecord.getAttribute("contractNumber"));
						}
						// 收货单位
						if (formItem.getName().contains("companyOfConsignee"))
						{
							formItem.setValue(pickingListRecord.getAttribute("companyOfConsignee"));
						}
						// 优先级
						if (formItem.getName().contains("priority"))
						{
							formItem.setValue(pickingListRecord.getAttribute("priority"));
						}
						// 交货日期
						if (formItem.getName().contains("deliveryDate"))
						{
							formItem.setValue(pickingListRecord.getAttribute("deliveryDate"));
						}
					}
					
					Criteria criteria = new Criteria();
					criteria.addCriteria("pickingListID", pickingListRecord.getAttribute("id"));
					listGrid.fetchData(criteria);

					ShowWindowTools.showCloseWindow(rect, objWindow, -1);
				} else {
					SC.say("请选择一条拣货记录！");
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
		layout.addMember(pickingListListgrid);
		layout.addMember(pickingListPartItemsListgrid);
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
