package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook;

import java.util.HashMap;
import java.util.Map;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class ClearanceAccountBookDetailsWindow extends Window {

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
	public ClearanceAccountBookDetailsWindow(String windowTitle, 
											boolean isMax,
											final Rectangle rect, 
											ListGrid listGrid, 
											String iconUrl, 
											Boolean updateFlg,
											final Boolean isView) {

		final Window objWindow = this;
		setWidth(700);
		setHeight(390);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(450);
		mainForm.setDataSource(listGrid.getDataSource());
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		// ID
		final HiddenItem txtClearanceAccountBookID = new HiddenItem("id");
		// 通关电子账册号
		final TextItem txtAccountBookItemsNumber = new TextItem("clearanceAccountBookNumber");
		txtAccountBookItemsNumber.setDisabled(updateFlg);
		txtAccountBookItemsNumber.setHint("<font color = 'red'>*</font>");
		// 描述
		final TextAreaItem txtDescribe = new TextAreaItem("describe");
		txtDescribe.setDisabled(updateFlg);
		txtDescribe.setWidth(400);
		txtDescribe.setHeight(60);
		
		// 通关电子帐册明细
		final ClearanceAccountBookItemsListgrid clearanceAccountBookItemsListgrid = new ClearanceAccountBookItemsListgrid();
		clearanceAccountBookItemsListgrid.setWidth100();
		clearanceAccountBookItemsListgrid.setHeight(180);
		clearanceAccountBookItemsListgrid.setMargin(5);
		clearanceAccountBookItemsListgrid.setAutoSaveEdits(false);
		clearanceAccountBookItemsListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取通关电子帐册明细数据
		String areaModeName = "stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook";
		String areaDsName = "clearanceAccountBookItems_dataSource";
		DataSourceTool businessDST = new DataSourceTool();
		businessDST.onCreateDataSource(areaModeName, areaDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						clearanceAccountBookItemsListgrid.setDataSource(dataSource);
						
						clearanceAccountBookItemsListgrid.drawClearanceAccountBookItemsListgrid();
						clearanceAccountBookItemsListgrid.setCanEdit(true);
						clearanceAccountBookItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
						if (txtClearanceAccountBookID.getValue() != null)
						{
							clearanceAccountBookItemsListgrid.fetchData(new Criteria("clearanceAccountBookID", "" + txtClearanceAccountBookID.getValue().toString()));
						}
						if (isView == true) {
							clearanceAccountBookItemsListgrid.setCanEdit(false);
						} else {
							clearanceAccountBookItemsListgrid.setCanEdit(true);
						}
					}
					});

		final IButton addButton = new IButton();
		addButton.setTitle("新增");
		addButton.setWidth(65);
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//					Integer nextRowNO = clearanceAccountBookItemsListgrid.getTotalRows() + 1;
//					String nextItemNO = "";
//					Map map = new HashMap();
//					Integer len = String.valueOf(nextRowNO).length();
//					if (len == 1) {
//						nextItemNO = "00" + String.valueOf(nextRowNO);
//					} else if (len == 2) {
//						nextItemNO = "0" + String.valueOf(nextRowNO);
//					} else if (len == 3) {
//						nextItemNO = String.valueOf(nextRowNO);
//					}
//					map.put("itemNumber", nextItemNO);

					clearanceAccountBookItemsListgrid.startEditingNew();
				} 
		});
		
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtAccountBookItemsNumber.getAttribute("Value") == null
						|| txtAccountBookItemsNumber.getAttribute("Value").equals("")) {
					SC.say("通关电子账册号不能为空");
					return;
				}

				// 设置明细数据
				ListGridRecord[] detailRecord = new ListGridRecord[clearanceAccountBookItemsListgrid.getTotalRows()];
				for (int i = 0; i < clearanceAccountBookItemsListgrid.getTotalRows(); i++)
				{
					detailRecord[i] = (ListGridRecord)clearanceAccountBookItemsListgrid.getEditedRecord(i);
				}
				mainForm.setValue("clearanceAccountBookItemsList", detailRecord);
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						String clearanceAccountBookID = response.getData()[0].getAttribute("id");
						txtClearanceAccountBookID.setValue(clearanceAccountBookID);
						txtAccountBookItemsNumber.setValue(response.getData()[0].getAttribute("clearanceAccountBookNumber"));

						// 刷新区域数据
						Criteria accountCriteria = new Criteria();
						accountCriteria.addCriteria("temp", String.valueOf(Math.random()));
						accountCriteria.addCriteria("clearanceAccountBookID", "" + txtClearanceAccountBookID.getValue().toString());
						DataSource businessDS = clearanceAccountBookItemsListgrid.getDataSource();
						clearanceAccountBookItemsListgrid.setDataSource(businessDS);
						clearanceAccountBookItemsListgrid.drawClearanceAccountBookItemsListgrid();
						clearanceAccountBookItemsListgrid.fetchData(accountCriteria);
					}
				});
				mainForm.clearValues();
				SC.say("保存成功！");
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

		mainForm.setFields(txtClearanceAccountBookID
						,txtAccountBookItemsNumber
						,txtDescribe
						);

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);
		
		final BtnsHLayout addButtonLayout = new BtnsHLayout();
		addButtonLayout.addMember(addButton);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(addButtonLayout);
		detailVLayout.addMember(clearanceAccountBookItemsListgrid);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(detailVLayout);
		layout.addMember(buttonLayout);

		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			addButtonLayout.setVisible(false);
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