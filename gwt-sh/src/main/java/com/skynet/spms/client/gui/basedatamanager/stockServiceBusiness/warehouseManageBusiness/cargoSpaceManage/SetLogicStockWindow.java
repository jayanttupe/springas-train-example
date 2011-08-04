package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class SetLogicStockWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 */
	public SetLogicStockWindow(String windowTitle,
								boolean isMax,
								final Rectangle rect,
								final ListGrid listGrid,
								String iconUrl) {
		final Window objWindow = this;
		objWindow.setWidth(450);
		objWindow.setHeight(300);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(200);
		mainForm.setColWidths(80, 120);
		mainForm.setHeight("90%");
		mainForm.setDataSource(listGrid.getDataSource());

		final HiddenItem txtOperatorType = new HiddenItem("operatorType");
		txtOperatorType.setValue("setCargo");
		// 逻辑库
		final SelectItem selLogicStock = new SelectItem("logicStockID", "逻辑库");
		selLogicStock.setValueField("id");
		selLogicStock.setDisplayField("logicStockName");
		ListGridField logicStockCodeField = new ListGridField("logicStockCode", "逻辑库代码");
		ListGridField logicStockNameField = new ListGridField("logicStockName", "逻辑库名称");
		selLogicStock.setPickListFields(logicStockCodeField, logicStockNameField);
		// 获取逻辑库数据
		String logicStockModeName = "stockServiceBusiness.basicData.stockRoom";
		String logicStockDSName = "logicStock_dataSource";
		DataSourceTool logicStockDST = new DataSourceTool();
		logicStockDST.onCreateDataSource(logicStockModeName, logicStockDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						selLogicStock.clearValue();
						selLogicStock.setOptionDataSource(dataSource);
					}
				});
		mainForm.setFields(selLogicStock
						,txtOperatorType);
		
		// 生成货位
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String[] cargoSpaceID = new String[listGrid.getSelection().length];
				ListGridRecord[] selRecord = listGrid.getSelection();
				for (int i = 0; i < selRecord.length; i++)
				{
					cargoSpaceID[i] = selRecord[i].getAttribute("id");
				}
				mainForm.setValue("cargoSpaceID", cargoSpaceID);
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("设置成功！");
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						listGrid.fetchData(criteria);
						((CargoSpaceManagerListgrid)listGrid).drawCargoSpaceManagerListgrid();}
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
		
		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(btnSave);
		buttonLayout.addMember(cancelButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}
