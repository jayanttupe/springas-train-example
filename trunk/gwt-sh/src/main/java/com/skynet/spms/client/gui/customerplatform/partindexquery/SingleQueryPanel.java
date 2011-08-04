package com.skynet.spms.client.gui.customerplatform.partindexquery;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.add.SalesRequisitionAddByPartInquiryWindow;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.model.SalesInquiryModel;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.modity.SalesInquiryModityWindow;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.AircraftConfiguration.AircraftConfigurationPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.appliationData.ApplicationDataPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.basicInformation.BasicInfoPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.customsClearanceData.CustomsClearanceDataPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.indexInfomation.IndexInfoListGrid;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.optionalData.OptionalDataPanel;
import com.skynet.spms.client.gui.partcatalog.technicalCatalog.technicalData.TechnicalDataPanel;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.add.SalesInquiryAddByPartInquiryWindow;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.model.SalesRequisitionModel;

public class SingleQueryPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "单件号查询页面";

	private HLayout searchBar;
	private DynamicForm searchForm;
	private IndexInfoListGrid indexInfoListGrid;
	private VLayout mainPanelLayout;
	private Label lblPartNumber;
	private SalesInquiryModel modelLocator;
	private SalesRequisitionModel requisitionMode;
	private IButton inquiryButton;
	private IButton downOrderButton;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "单件号查询模块";
		private String id;

		public Canvas create() {
			SingleQueryPanel panel = new SingleQueryPanel();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}

	}

	public Canvas getViewPanel() {
		modelLocator = SalesInquiryModel.getInstance();
		requisitionMode = SalesRequisitionModel.getInstance();
		// 主Layout
		mainPanelLayout = new VLayout();
		VLayout barGridLayout = new VLayout();
		barGridLayout.setHeight("40%");
		barGridLayout.setShowResizeBar(true);

		indexInfoListGrid = new IndexInfoListGrid();

		searchBar = new HLayout(10);
		searchBar.setHeight(30);

		searchForm = new DynamicForm();
		searchForm.setWidth(360);
		searchForm.setColWidths(160, 200);

		final TextItem txtPartNumber = new TextItem();
		txtPartNumber.setTitle("单件号查询");
		IButton searchButton = new IButton("查询");
		searchForm.setFields(txtPartNumber);
		searchBar.addMember(searchForm);
		searchBar.addMember(searchButton);

		searchButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Criteria c = new Criteria("manufacturerPartNumber",
						txtPartNumber.getValueAsString().trim());
				indexInfoListGrid.fetchData(c);
			}
		});

		/**
		 * 李宁添加
		 */
		inquiryButton = new IButton("询价");
		inquiryButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addNewSalesInquirySheet();

			}
		});

		downOrderButton = new IButton("订货");
		downOrderButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addNewSalesRequisitionSheet();

			}
		});
		searchBar.addMember(inquiryButton);
		searchBar.addMember(downOrderButton);

		barGridLayout.addMember(searchBar);
		barGridLayout.addMember(indexInfoListGrid);

		// 中间的所选件号提示
		HLayout tipLayout = new HLayout(5);
		tipLayout.setHeight(20);
		Label lblTip = new Label("所选件号：");
		lblPartNumber = new Label();
		lblPartNumber.setWidth(200);
		tipLayout.addMember(lblTip);
		tipLayout.addMember(lblPartNumber);

		// ------------------tabs主容器--------
		final TabSet childNodeTab = new TabSet();
		childNodeTab.setHeight("59%");

		Tab basicInfoTab = new Tab("基本数据", "pieces/16/star_green.png");
		final BasicInfoPanel basicInfoPanel = new BasicInfoPanel(false);
		basicInfoTab.setPane(basicInfoPanel);
		childNodeTab.addTab(basicInfoTab);

		Tab applicationDataTab = new Tab("应用数据", "pieces/16/star_green.png");
		final ApplicationDataPanel applicationDataPanel = new ApplicationDataPanel(
				false);
		applicationDataTab.setPane(applicationDataPanel);
		childNodeTab.addTab(applicationDataTab);

		Tab technicalDataTab = new Tab("技术数据", "pieces/16/star_green.png");
		final TechnicalDataPanel technicalDataPanel = new TechnicalDataPanel(
				false);
		technicalDataTab.setPane(technicalDataPanel);
		childNodeTab.addTab(technicalDataTab);

		Tab customsClearanceDataPanelTab = new Tab("报关数据",
				"pieces/16/star_green.png");
		final CustomsClearanceDataPanel customsClearanceDataPanel = new CustomsClearanceDataPanel(
				false);
		customsClearanceDataPanelTab.setPane(customsClearanceDataPanel);
		childNodeTab.addTab(customsClearanceDataPanelTab);

		Tab optionanDataTab = new Tab("可互换备件", "pieces/16/star_green.png");
		final OptionalDataPanel optionanDataPanel = new OptionalDataPanel(false);
		optionanDataTab.setPane(optionanDataPanel);
		childNodeTab.addTab(optionanDataTab);

		Tab aircraftConfigurationTab = new Tab("飞机构型",
				"pieces/16/star_green.png");
		final AircraftConfigurationPanel aircraftConfigurationPanel = new AircraftConfigurationPanel();
		aircraftConfigurationTab.setPane(aircraftConfigurationPanel);
		childNodeTab.addTab(aircraftConfigurationTab);

		indexInfoListGrid
				.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
					@Override
					public void onFilterEditorSubmit(
							FilterEditorSubmitEvent event) {
						Criteria c = event.getCriteria();
						indexInfoListGrid.fetchData(c, new DSCallback() {
							@Override
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								Tab selectedTab = childNodeTab.getSelectedTab();
								ListGridRecord selectedRecord = indexInfoListGrid
										.getSelectedRecord();
								refreshTab(selectedTab, selectedRecord);
							}
						});
					}
				});

		indexInfoListGrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if (event.getColNum() != 0) {
					indexInfoListGrid.selectRecords(indexInfoListGrid
							.getSelection(), false);
					indexInfoListGrid.selectRecord(selectedRecord);
				} else if (indexInfoListGrid.getSelection().length == 1) {
					selectedRecord = indexInfoListGrid.getSelection()[0];
					indexInfoListGrid.scrollToRow(indexInfoListGrid
							.getRecordIndex(selectedRecord));
				}

				Tab selectedTab = childNodeTab.getSelectedTab();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		childNodeTab.addTabSelectedHandler(new TabSelectedHandler() {

			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = childNodeTab.getSelectedTab();
				ListGridRecord selectedRecord = indexInfoListGrid
						.getSelectedRecord();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(tipLayout);
		mainPanelLayout.addMember(childNodeTab);

		/** 李宁添加 **/

		return mainPanelLayout;
	}

	private void refreshTab(Tab selectedTab, ListGridRecord selectedRecord) {
		Canvas panel = selectedTab.getPane();
		if (indexInfoListGrid.getSelection().length == 1) {
			lblPartNumber.setContents(selectedRecord
					.getAttribute("manufacturerPartNumber"));
		} else {
			lblPartNumber.setContents("");
		}
		// 基本数据
		if (panel instanceof BasicInfoPanel) {
			BasicInfoPanel biPanel = (BasicInfoPanel) panel;
			if (indexInfoListGrid.getSelection().length == 1) {
				String basicInfoId = selectedRecord
						.getAttribute("m_BasicInformation.id");
				biPanel.fetchData(basicInfoId);
			} else {
				biPanel.clearFormValues();
			}
		}
		// 应用数据
		if (panel instanceof ApplicationDataPanel) {
			ApplicationDataPanel adPanel = (ApplicationDataPanel) panel;
			if (indexInfoListGrid.getSelection().length == 1) {
				String partIndexId = selectedRecord.getAttribute("id");
				adPanel.fetchData(partIndexId);
			} else {
				adPanel.clearFormValues();
			}
		}

		// 技术数据
		if (panel instanceof TechnicalDataPanel) {
			TechnicalDataPanel tdPanel = (TechnicalDataPanel) panel;
			if (indexInfoListGrid.getSelection().length == 1) {
				String partTechnicalDataId = selectedRecord
						.getAttribute("m_PartTechnicalData.id");
				tdPanel.fetchData(partTechnicalDataId);
			} else {
				tdPanel.clearFormValues();
			}
		}

		// 报关数据
		if (panel instanceof CustomsClearanceDataPanel) {
			CustomsClearanceDataPanel ccdPanel = (CustomsClearanceDataPanel) panel;
			if (indexInfoListGrid.getSelection().length == 1) {
				String customsClearanceDataId = selectedRecord
						.getAttribute("m_CustomsClearanceData.id");
				ccdPanel.fetchData(customsClearanceDataId);
			} else {
				ccdPanel.clearFormValues();
			}
		}

		// 可互换备件
		if (panel instanceof OptionalDataPanel) {
			OptionalDataPanel adPanel = (OptionalDataPanel) panel;
			if (indexInfoListGrid.getSelection().length == 1) {
				String partIndexId = selectedRecord.getAttribute("id");
				adPanel.fetchData(partIndexId);
			} else {
				adPanel.clearData();
			}

		}
		// 飞机构型
		if (panel instanceof AircraftConfigurationPanel) {
			AircraftConfigurationPanel acPanel = (AircraftConfigurationPanel) panel;
			if (indexInfoListGrid.getSelection().length == 1) {
				String partIndexId = selectedRecord.getAttribute("id");
				acPanel.fetchData(partIndexId);
			} else {
				acPanel.fetchData("clear");
			}
		}
	}

	private void addNewSalesInquirySheet() {
		if (indexInfoListGrid.getSelectedRecord() != null) {
			// 初始化询价单列表数据源
			final DataSourceTool dataSourceTool = new DataSourceTool();
			dataSourceTool.onCreateDataSource(DSKey.C_DOQUOTATION,
					DSKey.C_DOQUOTATION_DS, new PostDataSourceInit() {
						public void doPostOper(DataSource dataSource,
								DataInfo dataInfo) {
							modelLocator.dataSource = dataSource;
							// 初始化询价单明细数据源
							dataSourceTool.onCreateDataSource(
									DSKey.C_DOQUOTATION_ITEM,
									DSKey.C_DOQUOTATION_ITEM_DS,
									new PostDataSourceInit() {
										public void doPostOper(
												DataSource dataSource,
												DataInfo dataInfo) {
											modelLocator.itemDataSource = dataSource;
											modelLocator.seleRecords = indexInfoListGrid
													.getSelection();
											SalesInquiryAddByPartInquiryWindow win = new SalesInquiryAddByPartInquiryWindow(
													null, true, inquiryButton
															.getPageRect(),
													null, "");
											ShowWindowTools
													.showWindow(inquiryButton
															.getPageRect(),
															win, 200);
										}
									});
						}
					});

		} else {
			SC.say("请先选择备件记录");
		}
	}

	private void addNewSalesRequisitionSheet() {
		if (indexInfoListGrid.getSelectedRecord() != null) {
			// 初始化订单列表数据源
			final DataSourceTool dataSourceTool = new DataSourceTool();
			dataSourceTool.onCreateDataSource(DSKey.C_SALESREQUISITIONSHEET,
					DSKey.C_SALESREQUISITIONSHEET_DS, new PostDataSourceInit() {
						public void doPostOper(DataSource dataSource,
								DataInfo dataInfo) {
							requisitionMode.dataSource = dataSource;
							// 初始化订单明细数据源
							dataSourceTool.onCreateDataSource(
									DSKey.C_SALESREQUISITIONSHEET_ITEM,
									DSKey.C_SALESREQUISITIONSHEET_ITEM_DS,
									new PostDataSourceInit() {
										public void doPostOper(
												DataSource dataSource,
												DataInfo dataInfo) {
											requisitionMode.itemDataSource = dataSource;
											requisitionMode.seleRecords = indexInfoListGrid
													.getSelection();
											SalesRequisitionAddByPartInquiryWindow win = new SalesRequisitionAddByPartInquiryWindow(
													null, true, downOrderButton
															.getPageRect(),
													null, "");
											ShowWindowTools.showWindow(
													downOrderButton
															.getPageRect(),
													win, 200);
										}
									});
						}
					});
		}else{
			SC.say("请先选择备件记录");
		}
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
