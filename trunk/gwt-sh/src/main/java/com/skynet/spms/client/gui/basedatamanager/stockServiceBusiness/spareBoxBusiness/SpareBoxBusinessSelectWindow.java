package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.spareBoxBusiness;

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

public class SpareBoxBusinessSelectWindow extends Window {
	
	public SpareBoxBusinessSelectWindow(String windowTitle
			, final Rectangle rect
			, String iconUrl
			, final DynamicForm mainForm
			, final ListGrid listGrid) {
		final Window objWindow = this;
		setWidth(800);
		setHeight(520);

		// 指令Grid
		final ListGrid pickingInstructionListGrid = new ListGrid();
		pickingInstructionListGrid.setCanEdit(false);
		pickingInstructionListGrid.setCellHeight(22);
		pickingInstructionListGrid.setWidth(600);
		pickingInstructionListGrid.setHeight(220);
		pickingInstructionListGrid.setMargin(5);
		pickingInstructionListGrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取指令数据源
		String pickingInstructionModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String pickingInstructionDsName = "pickingInstruction_dataSource";
		DataSourceTool receivingDS = new DataSourceTool();
		receivingDS.onCreateDataSource(pickingInstructionModeName, pickingInstructionDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingInstructionListGrid.setDataSource(dataSource);
						pickingInstructionListGrid.fetchData();

						ListGridField instructionNumbersFiled = new ListGridField("instructionNumbers","指令单号");
						ListGridField contractNumberFiled = new ListGridField("contractNumber","合同编号");
						ListGridField deliveryFiled = new ListGridField("delivery","收货单位");
						ListGridField priorityFiled = new ListGridField("priority","优先级");
						ListGridField deliveryDateFiled = new ListGridField("deliveryDate","交货日期");
						ListGridField tradeMethodsFiled = new ListGridField("tradeMethods","贸易方式");
						ListGridField operatorFiled = new ListGridField("operator","业务员");
						ListGridField machineNumberFiled = new ListGridField("machineNumber","机号");
						ListGridField isBondedFiled = new ListGridField("isBonded","是否保税");

						pickingInstructionListGrid.setFields(instructionNumbersFiled
								,contractNumberFiled
								,deliveryFiled
								,priorityFiled
								,deliveryDateFiled
								,tradeMethodsFiled
								,operatorFiled
								,machineNumberFiled
								,isBondedFiled
								);
					}
				});

		// 指令明细Grid
		final ListGrid pickingInstructionItemsListGrid = new ListGrid();
		pickingInstructionItemsListGrid.setCanEdit(false);
		pickingInstructionItemsListGrid.setCellHeight(22);
		pickingInstructionItemsListGrid.setWidth(600);
		pickingInstructionItemsListGrid.setHeight(220);
		pickingInstructionItemsListGrid.setMargin(5);
		pickingInstructionItemsListGrid.setSelectionType(SelectionStyle.SIMPLE);

		// 获取指令明细数据源
		String pickingInstructionItemsModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String pickingInstructionItemsDsName = "pickingInstructionItems_dataSource";
		DataSourceTool receivingDetailDS = new DataSourceTool();
		receivingDetailDS.onCreateDataSource(pickingInstructionItemsModeName, pickingInstructionItemsDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingInstructionItemsListGrid.setDataSource(dataSource);

						ListGridField instructionIDFiled = new ListGridField("instructionID","指令ID");
						instructionIDFiled.setHidden(true);
						ListGridField itemNumberFiled = new ListGridField("itemNumber","项号");
						ListGridField partNumberFiled = new ListGridField("partNumber","件号");
						ListGridField manufacturerFiled = new ListGridField("manufacturer","制造商");
						ListGridField unitFiled = new ListGridField("unit","单位");
						ListGridField partStatusFiled = new ListGridField("partStatus","备件状况");
						ListGridField partTypeFiled = new ListGridField("partType","备件类型");
						ListGridField stockQtyFiled = new ListGridField("stockQty","当前库存数量");
						ListGridField qtyFiled = new ListGridField("qty","应发数量");
						ListGridField sendQtyFiled = new ListGridField("sendQty","已配发数量");
						ListGridField memoFiled = new ListGridField("memo","备注");

						pickingInstructionItemsListGrid.setFields(instructionIDFiled
								,itemNumberFiled
								,partNumberFiled
								,manufacturerFiled
								,unitFiled
								,partStatusFiled
								,partTypeFiled
								,stockQtyFiled
								,qtyFiled
								,sendQtyFiled
								,memoFiled
								);
					}
				});
		
		// 选择指令的时候，读取指令明细的数据
		pickingInstructionListGrid.addRecordClickHandler(new RecordClickHandler() {  
			public void onRecordClick(RecordClickEvent event) {  
				Criteria criteria = new Criteria();
				Record record = pickingInstructionListGrid.getSelectedRecord();
				String instructionID = record.getAttribute("id");
				criteria.addCriteria("instructionID", instructionID);
				pickingInstructionItemsListGrid.filterData(criteria);
			}  
		});

		// 选择按钮
		final IButton selectButton = new IButton();
		selectButton.setTitle("选择");
		selectButton.setWidth(65);
		selectButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (pickingInstructionListGrid.getSelection().length == 1) {
					Record pickingInstructionListRecord = pickingInstructionListGrid.getSelectedRecord();
					FormItem[] formItems = mainForm.getFields();
					for (FormItem formItem : formItems)
					{
						// 指令ID
						if (formItem.getName().contains("instructionID"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("id"));
						}
						// 指令单号
						if (formItem.getName().contains("instructionNumbers"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("instructionNumbers"));
						}
						// 合同编号
						if (formItem.getName().contains("contractNumber"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("contractNumber"));
						}
						// 收货单位
						if (formItem.getName().contains("delivery"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("delivery"));
						}
						// 优先级
						if (formItem.getName().contains("priority"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("priority"));
						}
						// 交货日期
						if (formItem.getName().contains("deliveryDate"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("deliveryDate"));
						}
						// 贸易方式
						if (formItem.getName().contains("tradeMethods"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("tradeMethods"));
						}
						// 业务员
						if (formItem.getName().contains("operator"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("operator"));
						}
						// 机号
						if (formItem.getName().contains("machineNumber"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("machineNumber"));
						}
						// 是否保税
						if (formItem.getName().contains("isBonded"))
						{
							formItem.setValue(pickingInstructionListRecord.getAttribute("isBonded"));
						}
					}
					
					listGrid.fetchData(new Criteria("instructionID", pickingInstructionListRecord.getAttribute("id")));

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
		layout.addMember(pickingInstructionListGrid);
		layout.addMember(pickingInstructionItemsListGrid);
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
