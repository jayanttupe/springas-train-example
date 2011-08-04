package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class CargoSpaceDetailWindow extends Window {

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
	public CargoSpaceDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			final CargoSpaceManagerListgrid listGrid,
			String iconUrl,
			final Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(550);
		setHeight(380);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(290);
		mainForm.setColWidths(90, 200);
		mainForm.setHeight("90%");
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 库房编号
		final SelectItem cmbStockRoomNumber = new SelectItem("stockRoomID", "所属库房");
		cmbStockRoomNumber.setValueField("id");
		cmbStockRoomNumber.setDisplayField("stockRoomChineseName");
		cmbStockRoomNumber.setWidth(200);
		cmbStockRoomNumber.setHint("<font color='red'>*</font>");
		ListGridField stockRoomNumberField = new ListGridField("stockRoomNumber");
		ListGridField stockRoomChineseNameField = new ListGridField("stockRoomChineseName");
		cmbStockRoomNumber.setPickListFields(stockRoomNumberField, stockRoomChineseNameField);
		// 获取库房数据
		String stockRoomModeName = "stockServiceBusiness.basicData.stockRoom";
		String stockRoomDSName = "stockRoom_dataSource";
		DataSourceTool stockRoomDST = new DataSourceTool();
		stockRoomDST.onCreateDataSource(stockRoomModeName, stockRoomDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cmbStockRoomNumber.clearValue();
						cmbStockRoomNumber.setOptionDataSource(dataSource);

						if (updateFlg == true) {
							cmbStockRoomNumber.setValue(listGrid.getSelectedRecord().getAttribute("stockRoomID"));
						}
					}
				});

		// 库房区域
		final SelectItem cmbStockArea = new SelectItem("stockAreaID", "库房区域"){
			@Override
			protected Criteria getPickListFilterCriteria() {
				String stockRoomID = (String) cmbStockRoomNumber.getValue();
				if (stockRoomID == null) {
					stockRoomID = "temp";
				}
				
				Criteria criteria = new Criteria("stockRoomID", stockRoomID);
				return criteria;
			}
		};
		cmbStockArea.setValueField("id");
		cmbStockArea.setDisplayField("areaName");
		cmbStockArea.setWidth(200);
		cmbStockArea.setHint("<font color='red'>*</font>");
		ListGridField stockAreaCodeField = new ListGridField("areaCode", "区域代码");
		ListGridField stockAreaNameField = new ListGridField("areaName", "区域名称");
		cmbStockArea.setPickListFields(stockAreaCodeField, stockAreaNameField);
		// 获取库房区域数据
		String stockAreaModeName = "stockServiceBusiness.basicData.stockRoom";
		String stockAreaDSName = "stockArea_dataSource";
		DataSourceTool stockAreaDST = new DataSourceTool();
		stockAreaDST.onCreateDataSource(stockAreaModeName, stockAreaDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cmbStockArea.clearValue();
						cmbStockArea.setOptionDataSource(dataSource);
						cmbStockArea.setValue(listGrid.getSelectedRecord().getAttribute("stockAreaID"));
					}
				});
		
		// 逻辑库
		final SelectItem cmbLogicStock = new SelectItem("logicStockID", "逻辑库"){
			@Override
			protected Criteria getPickListFilterCriteria() {
				String stockRoomID = (String) cmbStockRoomNumber.getValue();
				if (stockRoomID == null) {
					stockRoomID = "temp";
				}
				
				Criteria criteria = new Criteria("stockRoomID", stockRoomID);
				return criteria;
			}
		};
		cmbLogicStock.setValueField("id");
		cmbLogicStock.setDisplayField("logicStockName");
		cmbLogicStock.setWidth(200);
		ListGridField logicStockCodeField = new ListGridField("logicStockCode", "逻辑库代码");
		ListGridField logicStockNameField = new ListGridField("logicStockName", "逻辑库名称");
		cmbLogicStock.setPickListFields(logicStockCodeField, logicStockNameField);
		// 获取逻辑库数据
		String logicStockModeName = "stockServiceBusiness.basicData.stockRoom";
		String logicStockDSName = "logicStock_dataSource";
		DataSourceTool logicStockDST = new DataSourceTool();
		logicStockDST.onCreateDataSource(logicStockModeName, logicStockDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cmbLogicStock.clearValue();
						cmbLogicStock.setOptionDataSource(dataSource);
						cmbLogicStock.setValue(listGrid.getSelectedRecord().getAttribute("logicStockID"));
					}
				});
		
		// 选择仓库的时候，更新该仓库下的逻辑库
		cmbStockRoomNumber.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				cmbLogicStock.clearValue();
				cmbStockArea.clearValue();
			}
		});
		// 货架排
		final TextItem txtStorageRacksRowCode = new TextItem("storageRacksRowCode", "货架排");
		txtStorageRacksRowCode.setHint("<font color='red'>*</font>");
		txtStorageRacksRowCode.setWidth(200);
		txtStorageRacksRowCode.setLength(2);
		// 货架形式
		final SelectItem selStorageRacksTypeCode = new SelectItem();
		selStorageRacksTypeCode.setValueMap("A", "B");
		selStorageRacksTypeCode.setName("storageRacksTypeCode");
		selStorageRacksTypeCode.setDefaultToFirstOption(true);
		selStorageRacksTypeCode.setHint("<font color='red'>*</font>");
		selStorageRacksTypeCode.setWidth(200);
		// 货架列
		final TextItem txtStorageRacksColumnCode = new TextItem("storageRacksColumnCode", "列数");
		txtStorageRacksColumnCode.setHint("<font color='red'>*</font>");
		txtStorageRacksColumnCode.setWidth(200);
		txtStorageRacksColumnCode.setLength(2);
		// 货架层
		final TextItem txtStorageRacksTierCode = new TextItem("storageRacksTierCode", "层数");
		txtStorageRacksTierCode.setHint("<font color='red'>*</font>");
		txtStorageRacksTierCode.setWidth(200);
		txtStorageRacksTierCode.setLength(1);
		// 货位箱(格)
		final TextItem txtStorageRacksCaseCode = new TextItem("storageRacksCaseCode", "货位箱数");
		txtStorageRacksCaseCode.setHint("<font color='red'>*</font>");
		txtStorageRacksCaseCode.setWidth(200);
		txtStorageRacksCaseCode.setLength(2);
		txtStorageRacksCaseCode.setDefaultValue("1");
		// 货位尺寸（长*宽*高）
		final SelectItem selStorageSizeType  = (SelectItem)listGrid.getDataInfo().getFieldInfoByName("storageSizeType").generFormField();
		selStorageSizeType.setName("storageSizeType");
		selStorageSizeType.setDefaultToFirstOption(true);
		selStorageSizeType.setWidth(200);
		// 货位承重
		final IntegerItem txtStorageWeight = new IntegerItem("storageWeight", "货位承重");
		txtStorageWeight.setWidth(200);
		txtStorageWeight.setHint("KG");
		
		mainForm.setFields(cmbStockRoomNumber
						,cmbStockArea
						,txtStorageRacksRowCode
						,selStorageRacksTypeCode
						,txtStorageRacksColumnCode
						,txtStorageRacksTierCode
						,txtStorageRacksCaseCode
						,cmbLogicStock
						,selStorageSizeType
						,txtStorageWeight					
						);
		
		// 生成货位
		final IButton createCargoSpaceButton = new IButton();
		createCargoSpaceButton.setTitle("生成货位");
		createCargoSpaceButton.setWidth(80);
		createCargoSpaceButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (cmbStockRoomNumber.getAttribute("Value") == null
						|| cmbStockRoomNumber.getAttribute("Value").equals("")) {
					SC.say("所属库房不能为空");
					return;
				}
				if (cmbStockArea.getAttribute("Value") == null
						|| cmbStockArea.getAttribute("Value").equals("")) {
					SC.say("库房区域不能为空");
					return;
				}
				if (txtStorageRacksRowCode.getAttribute("Value") == null
						|| txtStorageRacksRowCode.getAttribute("Value").equals("")) {
					SC.say("货架排不能为空");
					return;
				}
				if (selStorageRacksTypeCode.getAttribute("Value") == null
						|| selStorageRacksTypeCode.getAttribute("Value").equals("")) {
					SC.say("货架形式不能为空");
					return;
				}
				if (txtStorageRacksColumnCode.getAttribute("Value") == null
						|| txtStorageRacksColumnCode.getAttribute("Value").equals("")) {
					SC.say("列数不能为空");
					return;
				}
				if (txtStorageRacksTierCode.getAttribute("Value") == null
						|| txtStorageRacksTierCode.getAttribute("Value").equals("")) {
					SC.say("层数不能为空");
					return;
				}
				if (txtStorageRacksCaseCode.getAttribute("Value") == null
						|| txtStorageRacksCaseCode.getAttribute("Value").equals("")) {
					SC.say("货位箱数不能为空");
					return;
				}
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						// 是否生成过货位(0:未生成/1:已经生成)
						String createdFlg = response.getData()[0].getAttribute("createdFlg");
						if (createdFlg.equals("1")) {
							SC.say("该货架已经生成过货位！");
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							listGrid.fetchData(criteria);
							return;
						} else {
							SC.say("生成成功！");
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							listGrid.fetchData(criteria);
							ShowWindowTools.showCloseWindow(rect, objWindow, -1);
					}}
				});
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
		buttonLayout.addMember(createCargoSpaceButton);
		buttonLayout.addMember(cancelButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);
	
		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			createCargoSpaceButton.setVisible(false);
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
