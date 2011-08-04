package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheck;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class StockCheckDetailWindow extends Window{

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
	public StockCheckDetailWindow(String windowTitle, 
									boolean isMax,
									final Rectangle rect,
									final StockCheckListgrid listGrid, 
									String iconUrl,
									final Boolean updateFlg,
									final Boolean isView) {
		final Window objWindow = this;
		setWidth(800);
		setHeight(485);
		
		//基本信息
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(480);
		mainForm.setNumCols(4);
		mainForm.setColWidths(90, 150, 90, 150);

		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true) {
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 盘点ID
		final HiddenItem txtStockCheckID = new HiddenItem("id");
		// 盘点编号
		final TextItem txtCheckNumber = new TextItem("checkNumber");
		txtCheckNumber.setWidth(150);
        txtCheckNumber.setDisabled(true);
        // 盘点项目描述
		final TextItem txtCheckDescribe = new TextItem("checkDescribe");
		txtCheckDescribe.setWidth(150);
        // 盘点开始日期
		final CustomDateItem txtCheckStartDate = new CustomDateItem("checkStartDate");
		txtCheckStartDate.setWidth(150);
		// 盘点结束日期
		final CustomDateItem txtCheckEndDate = new CustomDateItem("checkEndDate");
		txtCheckEndDate.setWidth(150);
        // 起始货位
		final TextItem txtFromCargoSpaceNumber = new TextItem("fromCargoSpaceNumber");
		txtFromCargoSpaceNumber.setMask(">CCC-CC-CCC-CCC-CC");
		txtCheckDescribe.setWidth(150);
        // 结束货位
		final TextItem txtToCargoSpaceNumber = new TextItem("toCargoSpaceNumber");
		txtToCargoSpaceNumber.setMask(">CCC-CC-CCC-CCC-CC");
		txtCheckDescribe.setWidth(150);
		// 备注
		final TextAreaItem txtremark = new TextAreaItem("remark");
		txtremark.setColSpan(3);
		txtremark.setWidth(400);
		txtremark.setHeight(60);

		// 盘点明细数据
		final StockCheckItemListgrid stockCheckItemListgrid = new StockCheckItemListgrid();
		stockCheckItemListgrid.setAutoSaveEdits(false);
		stockCheckItemListgrid.setHeight(250);
		stockCheckItemListgrid.setWidth(600);
		stockCheckItemListgrid.setMargin(5);
		//获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckManager";
		String detaildsName = "stockCheckItem_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {						
		    			stockCheckItemListgrid.setDataSource(dataSource);
						stockCheckItemListgrid.drawStockCheckItemListgrid();
						
						if (updateFlg == true) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							criteria.addCriteria("stockCheckID", "" + txtStockCheckID.getValue().toString());
							stockCheckItemListgrid.fetchData(criteria);
						}
					}
				});

		mainForm.setFields(txtStockCheckID,
				txtCheckNumber,
				txtCheckDescribe,
				txtCheckStartDate,
				txtCheckEndDate,
				txtFromCargoSpaceNumber,
				txtToCargoSpaceNumber,
				txtremark
				);
		
		IButton createDetailButton = new IButton();
		createDetailButton.setTitle("取得盘点明细");
		createDetailButton.setWidth(80);
		createDetailButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtFromCargoSpaceNumber.getAttribute("Value") == null
						|| txtFromCargoSpaceNumber.getAttribute("Value").equals("")) {
					SC.say("起始货位不能为空");
					return;
				}
				if (txtToCargoSpaceNumber.getAttribute("Value") == null
						|| txtToCargoSpaceNumber.getAttribute("Value").equals("")) {
					SC.say("结束货位不能为空");
					return;
				}

				Criteria criteria = new Criteria();
				criteria.addCriteria("temp", String.valueOf(Math.random()));
				criteria.addCriteria("StockData", "1");
				criteria.addCriteria("fromCargoSpaceNumber", txtFromCargoSpaceNumber.getDisplayValue());
				criteria.addCriteria("toCargoSpaceNumber", txtToCargoSpaceNumber.getDisplayValue());
				stockCheckItemListgrid.fetchData(criteria);
			}
		});
		
		IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (stockCheckItemListgrid.getTotalRows() == 0) {
					SC.say("盘点明细不能为空！");
					return;
				}
				if (txtCheckStartDate.getAttribute("Value") == null
						|| txtCheckStartDate.getAttribute("Value").equals("")) {
					SC.say("盘点开始日期不能为空！");
					return;
				}
				if (txtCheckEndDate.getAttribute("Value") == null
						|| txtCheckEndDate.getAttribute("Value").equals("")) {
					SC.say("盘点结束日期不能为空！");
					return;
				}
				if (txtFromCargoSpaceNumber.getAttribute("Value") == null
						|| txtFromCargoSpaceNumber.getAttribute("Value").equals("")) {
					SC.say("起始货位不能为空！");
					return;
				}
				if (txtToCargoSpaceNumber.getAttribute("Value") == null
						|| txtToCargoSpaceNumber.getAttribute("Value").equals("")) {
					SC.say("结束货位不能为空！");
					return;
				}

				// 设置明细数据
				ListGridRecord[] stockCheckItemRecord = new ListGridRecord[stockCheckItemListgrid.getTotalRows()];
				for (int i = 0; i < stockCheckItemListgrid.getTotalRows(); i++)
				{
					stockCheckItemRecord[i] = (ListGridRecord)stockCheckItemListgrid.getEditedRecord(i);
				}
				mainForm.setValue("stockCheckItemList", stockCheckItemRecord);
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("保存成功！");

						// 刷新明细数据
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						String stockCheckID = response.getData()[0].getAttribute("id");
						criteria.addCriteria("stockCheckID", stockCheckID);
						DataSource stockCheckItemDS = stockCheckItemListgrid.getDataSource();
						stockCheckItemListgrid.setDataSource(stockCheckItemDS);
						stockCheckItemListgrid.drawStockCheckItemListgrid();
						stockCheckItemListgrid.fetchData(criteria);
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
		
		final BtnsHLayout bottomBtnLayout = new BtnsHLayout();
		bottomBtnLayout.addMember(createDetailButton);
		bottomBtnLayout.addMember(btnSave);
		bottomBtnLayout.addMember(btnReturn);

		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.setMembersMargin(5);
        vLayout.addMember(mainForm);
        vLayout.addMember(stockCheckItemListgrid);
        vLayout.addMember(bottomBtnLayout);
        
        if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
        	createDetailButton.setVisible(false);
        	btnSave.setVisible(false);
        	btnReturn.setVisible(false);
        }
        
        SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);
	}
}