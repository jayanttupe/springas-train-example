package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import java.util.HashMap;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class LogisticsOutlayRecordDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 * @param updateFlg
	 * @param isView
	 */
	public LogisticsOutlayRecordDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			ListGrid listGrid,
			String iconUrl,
			Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(750);
		setHeight(400);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(500);
		mainForm.setPadding(5);
		mainForm.setNumCols(4);
		mainForm.setLayoutAlign(VerticalAlignment.TOP);
		
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		// 物流任务编号
		final TextItem txtLogisticsTaskNumber = new TextItem("logisticsTaskNumber", "物流任务编号");
		txtLogisticsTaskNumber.setDisabled(true);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber", "合同编号");
		txtContractNumber.setDisabled(true);
		// 指令编号
		final TextItem txtOrderNumber = new TextItem("orderNumber", "指令编号");
		txtOrderNumber.setDisabled(true);
		// 总金额
		final TextItem txtAmount = new TextItem("amount", "总金额");
		txtAmount.setDisabled(true);
		// 状态
		final TextItem txtStatus = new TextItem("status","状态");
		// 物流单ID
		final HiddenItem txtlogisticsOutlayItemID = new HiddenItem("id");
		
		// 明细项目
		final LogisticsOutlayItemListgrid logisticsOutlayItemListgrid = new LogisticsOutlayItemListgrid();
		logisticsOutlayItemListgrid.setWidth(550);
		logisticsOutlayItemListgrid.setHeight(200);
		logisticsOutlayItemListgrid.setMargin(5);
		logisticsOutlayItemListgrid.setAutoSaveEdits(false);
		logisticsOutlayItemListgrid.setSelectionType(SelectionStyle.SINGLE);
		
		// 获取明细数据
		String modeName = "logisticsCustomsDeclaration.logisticsOutlayRecordManage";
		String dsName = "logisticsOutlayItem_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					logisticsOutlayItemListgrid.setDataSource(dataSource);
					logisticsOutlayItemListgrid.drawLogisticsOutlayItemListgrid();
					if (isView == true) {
						logisticsOutlayItemListgrid.setCanEdit(false);
					} else {
						logisticsOutlayItemListgrid.setCanEdit(true);
					}
					logisticsOutlayItemListgrid.setEditEvent(ListGridEditEvent.CLICK);
					
					if (txtlogisticsOutlayItemID.getValue() != null)
					{
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("logisticsOutlayItemID", "" + txtlogisticsOutlayItemID.getValue());
						logisticsOutlayItemListgrid.fetchData(criteria);
					}
			}
		});

		// 新增按钮
		final IButton btnNew = new IButton();
		btnNew.setTitle("新增行");
		btnNew.setWidth(65);
		btnNew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				HashMap map = new HashMap();
				String itemNumber = String.valueOf(logisticsOutlayItemListgrid.getTotalRows() + 1);
				Integer len = String.valueOf(itemNumber).length();
				if (len == 1) {
					itemNumber = "00" + String.valueOf(itemNumber);
				} else if (len == 2) {
					itemNumber = "0" + String.valueOf(itemNumber);
				} else if (len == 3) {
					itemNumber = String.valueOf(itemNumber);
				}
				map.put("itemNumber", itemNumber);
				logisticsOutlayItemListgrid.startEditingNew();
			}
		});

		// 删除按钮
		final IButton btnDelete = new IButton();
		btnDelete.setTitle("删除行");
		btnDelete.setWidth(65);
		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (logisticsOutlayItemListgrid.getSelection().length == 1) {
					logisticsOutlayItemListgrid.removeSelectedData();
				} else {
					SC.say("请选择一条记录进行删除！");
				}
			}
		});

		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						String logisticsOutlayItemID = response.getData()[0].getAttribute("id");
						for (int i = 0; i < logisticsOutlayItemListgrid.getTotalRows(); i++)
						{
							logisticsOutlayItemListgrid.setEditValue(i, "logisticsOutlayItemID", logisticsOutlayItemID);
						}
						
						logisticsOutlayItemListgrid.saveAllEdits(new Function() {
							@Override
							public void execute() {
								SC.say("保存成功！");
							}
						});
					}
				});
			}
		});

		// 返回按钮
		final IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(txtLogisticsTaskNumber
				,txtContractNumber
				,txtOrderNumber
				,txtAmount
				,txtStatus
				,txtlogisticsOutlayItemID				
				);

		final BtnsHLayout detailBtnLayout = new BtnsHLayout(); 
		detailBtnLayout.addMember(btnNew);
		detailBtnLayout.addMember(btnDelete);

		final BtnsHLayout bototmBtnLayout = new BtnsHLayout(); 
		bototmBtnLayout.addMember(btnSave);
		bototmBtnLayout.addMember(btnReturn);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(detailBtnLayout);
		layout.addMember(logisticsOutlayItemListgrid);
		layout.addMember(bototmBtnLayout);
		
		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			detailBtnLayout.setVisible(false);
			btnSave.setVisible(false);
			btnReturn.setVisible(false);
		}

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}