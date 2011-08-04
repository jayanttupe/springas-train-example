package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

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
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class StockRoomDetailWindow extends Window {

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
	public StockRoomDetailWindow(String windowTitle, boolean isMax,
								final Rectangle rect,
								StockRoomListgrid listGrid,
								String iconUrl,
								Boolean updateFlg,
								final Boolean isView) {
		final Window objWindow = this;
		setWidth(770);
		setHeight(500);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(400);
		mainForm.setNumCols(4);
		mainForm.setColWidths(120, 100, 80, 100);
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 备件中心位置
		SelectItem selPartCentreLocation = new SelectItem("partCentreLocation");
		selPartCentreLocation.setValueMap("SHA", "WHA");
		selPartCentreLocation.setDefaultValue("SHA");
		selPartCentreLocation.setWidth(100);
		selPartCentreLocation.setHint("<font color='red'>*</font>");
		// 库房ID
		final HiddenItem txtStockRoomID = new HiddenItem("id");
		// 库房编号
		final TextItem txtStockRoomNumber = new TextItem("stockRoomNumber");
		txtStockRoomNumber.setWidth(100);
		txtStockRoomNumber.setValue(selPartCentreLocation.getValueAsString());
		txtStockRoomNumber.setDisabled(true);
		// 库房中文名称
		final TextItem txtStockRoomChineseName = new TextItem("stockRoomChineseName");
		txtStockRoomChineseName.setWidth(290);
		txtStockRoomChineseName.setColSpan(3);
		txtStockRoomChineseName.setHint("<font color='red'>*</font>");
		// 库房英文名称
		final TextItem txtStockRoomEngilshName = new TextItem("stockRoomEngilshName");
		txtStockRoomEngilshName.setWidth(290);
		txtStockRoomEngilshName.setColSpan(3);
		// 库房类型
	    final SelectItem selStockRoomType = (SelectItem)listGrid.getDataInfo().getFieldInfoByName("stockRoomType").generFormField();
	    selStockRoomType.setName("stockRoomType");
	    selStockRoomType.setDefaultToFirstOption(true);
		selStockRoomType.setWidth(100);
		selStockRoomType.setHint("<font color='red'>*</font>");
		//库房楼层
		final SelectItem selStockRoomFloor = (SelectItem)listGrid.getDataInfo().getFieldInfoByName("stockRoomFloor").generFormField();
		selStockRoomFloor.setName("stockRoomFloor");
		selStockRoomFloor.setDefaultToFirstOption(true);
		selStockRoomFloor.setWidth(100);
		selStockRoomFloor.setHint("<font color='red'>*</font>");
		// 库房地址
		TextItem txtAddress = new TextItem("address");
		txtAddress.setWidth(290);
		txtAddress.setColSpan(3);
		// 电话
		TextItem txtTele = new TextItem("telephone");
		txtTele.setWidth(100);
		// 邮编
		TextItem txtPostCode = new TextItem("postCode");
		txtPostCode.setWidth(100);
		// 备注
		TextAreaItem txtRemark = new TextAreaItem("remark");
		txtRemark.setWidth(290);
		txtRemark.setHeight(50);
		txtRemark.setColSpan(3);
		
		// 库房区域
		final StockAreaListgrid stockAreaListgrid = new StockAreaListgrid();
		stockAreaListgrid.setWidth(280);
		stockAreaListgrid.setHeight(180);
		stockAreaListgrid.setMargin(5);
		stockAreaListgrid.setAutoSaveEdits(false);
		stockAreaListgrid.setSelectionType(SelectionStyle.SINGLE);
		
		// 获取库房区域数据
		String areaModeName = "stockServiceBusiness.basicData.stockRoom";
		String areaDsName = "stockArea_dataSource";
		DataSourceTool areaDST = new DataSourceTool();
		areaDST.onCreateDataSource(areaModeName, areaDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						stockAreaListgrid.setDataSource(dataSource);
						
						stockAreaListgrid.drawStockAreaListgrid();
						if (isView == true) {
							stockAreaListgrid.setCanEdit(false);
						} else {
							stockAreaListgrid.setCanEdit(true);	
						}
						if (txtStockRoomID.getValue() != null)
						{
							stockAreaListgrid.fetchData(new Criteria("stockRoomID", "" + txtStockRoomID.getValue().toString()));
						}
					}
				});
		
		// 逻辑库
		final LogicStockListgrid logicStockListgrid = new LogicStockListgrid();
		logicStockListgrid.setWidth(280);
		logicStockListgrid.setHeight(180);
		logicStockListgrid.setMargin(5);
		logicStockListgrid.setAutoSaveEdits(false);
		logicStockListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取库房逻辑库明细数据
		String logicModeName = "stockServiceBusiness.basicData.stockRoom";
		String logicDsName = "logicStock_dataSource";
		DataSourceTool logicDST = new DataSourceTool();
		logicDST.onCreateDataSource(logicModeName, logicDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						logicStockListgrid.setDataSource(dataSource);
						
						logicStockListgrid.drawLogicStockListgrid();
						if (isView == true) {
							logicStockListgrid.setCanEdit(false);
						} else {
							logicStockListgrid.setCanEdit(true);
						}
						if (txtStockRoomID.getValue() != null)
						{
							logicStockListgrid.fetchData(new Criteria("stockRoomID", "" + txtStockRoomID.getValue().toString()));
						}
					}
				});

		// 新增区域按钮
		final IButton btnNewArea = new IButton();
		btnNewArea.setTitle("新增");
		btnNewArea.setWidth(65);
		btnNewArea.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				stockAreaListgrid.startEditingNew();
			}
		});

		// 删除区域按钮
		final IButton btnDelArea = new IButton();
		btnDelArea.setTitle("删除");
		btnDelArea.setWidth(65);
		btnDelArea.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				stockAreaListgrid.removeSelectedData();
			}
		});

		// 新增逻辑库按钮
		final IButton btnNewLogic = new IButton();
		btnNewLogic.setTitle("新增");
		btnNewLogic.setWidth(65);
		btnNewLogic.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logicStockListgrid.startEditingNew();
			}
		});

		// 删除逻辑库按钮
		final IButton btnDelLogic = new IButton();
		btnDelLogic.setTitle("删除");
		btnDelLogic.setWidth(65);
		btnDelLogic.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logicStockListgrid.removeSelectedData();
			}
		});

		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (mainForm.validate() == false) {
					return;
				}
				if (txtStockRoomChineseName.getAttribute("Value") == null
						|| txtStockRoomChineseName.getAttribute("Value").equals("")) {
					SC.say("库房名称不能为空");
					return;
				}

				// 设置库房的区域明细数据
				ListGridRecord[] areaDetailRecord = new ListGridRecord[stockAreaListgrid.getTotalRows()];
				for (int i = 0; i < stockAreaListgrid.getTotalRows(); i++)
				{
					areaDetailRecord[i] = (ListGridRecord)stockAreaListgrid.getEditedRecord(i);
				}
				mainForm.setValue("stockArea", areaDetailRecord);
				
				// 设置库房的逻辑库明细数据
				ListGridRecord[] logicDetailRecord = new ListGridRecord[logicStockListgrid.getTotalRows()];
				for (int i = 0; i < logicStockListgrid.getTotalRows(); i++)
				{
					logicDetailRecord[i] = (ListGridRecord)logicStockListgrid.getEditedRecord(i);
				}
				mainForm.setValue("logicStock", logicDetailRecord);
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("保存成功！");
						String stockRoomID = response.getData()[0].getAttribute("id");
						txtStockRoomID.setValue(stockRoomID);
						txtStockRoomNumber.setValue(response.getData()[0].getAttribute("stockRoomNumber"));

						// 刷新区域数据
						Criteria areaCriteria = new Criteria();
						areaCriteria.addCriteria("temp", String.valueOf(Math.random()));
						areaCriteria.addCriteria("stockRoomID", "" + txtStockRoomID.getValue().toString());
						DataSource areaDS = stockAreaListgrid.getDataSource();
						stockAreaListgrid.setDataSource(areaDS);
						stockAreaListgrid.drawStockAreaListgrid();
						stockAreaListgrid.setCanEdit(true);
						stockAreaListgrid.fetchData(areaCriteria);
						
						// 刷新逻辑库数据
						Criteria logicCriteria = new Criteria();
						logicCriteria.addCriteria("temp", String.valueOf(Math.random()));
						logicCriteria.addCriteria("stockRoomID", "" + txtStockRoomID.getValue().toString());
						DataSource logicDS = logicStockListgrid.getDataSource();
						logicStockListgrid.setDataSource(logicDS);
						logicStockListgrid.drawLogicStockListgrid();
						logicStockListgrid.setCanEdit(true);
						logicStockListgrid.fetchData(logicCriteria);
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

		mainForm.setFields(txtStockRoomID
						,selPartCentreLocation
						,txtStockRoomNumber
						,txtStockRoomChineseName
						,txtStockRoomEngilshName
						,selStockRoomType
						,selStockRoomFloor
						,txtAddress
						,txtTele
						,txtPostCode
						,txtRemark
						);

		final BtnsHLayout areaBtnLayout = new BtnsHLayout();
		areaBtnLayout.addMember(btnNewArea);
		areaBtnLayout.addMember(btnDelArea);

		final BtnsHLayout logicBtnLayout = new BtnsHLayout();
		logicBtnLayout.addMember(btnNewLogic);
		logicBtnLayout.addMember(btnDelLogic);

		VLayout logicVLayout = new VLayout();
		logicVLayout.addMember(logicBtnLayout);
		logicVLayout.addMember(logicStockListgrid);

		VLayout areaVLayout = new VLayout();
		areaVLayout.addMember(areaBtnLayout);
		areaVLayout.addMember(stockAreaListgrid);

		final HLayout detailLayout = new HLayout();
		detailLayout.addMember(areaVLayout);
		detailLayout.addMember(logicVLayout);

		final HLayout detailGridLayout = new HLayout();
		detailGridLayout.setMargin(5);
		detailGridLayout.setMembersMargin(5);
		detailGridLayout.addMember(stockAreaListgrid);
		detailGridLayout.addMember(logicStockListgrid);

		final BtnsHLayout bototmBtnLayout = new BtnsHLayout(); 
		bototmBtnLayout.addMember(btnSave);
		bototmBtnLayout.addMember(btnReturn);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(detailLayout);
		layout.addMember(bototmBtnLayout);
		
		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			logicBtnLayout.setVisible(false);
			areaBtnLayout.setVisible(false);
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