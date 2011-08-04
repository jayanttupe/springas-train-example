package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration.ExportCustomsDeclarationListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsDeclaration.ImportCustomsDeclarationListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJobDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob.ArrivalOfGoodsJobListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJobDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob.DeliverTheGoodsJobListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob.ShippingJobListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJobDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob.TakeDeliveryOfJobListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssignListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder.PickupOrderListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class PickupDispatchTaskPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "发货任务分派维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "发货任务分派管理模块";
		private String id;
		
		public Canvas create() {
			PickupDispatchTaskPanel panel = new PickupDispatchTaskPanel();
			
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
		// 头部列表Grid
		final PickupOrderListgrid pickupOrderListgrid = new PickupOrderListgrid();
		pickupOrderListgrid.setHeight("65%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.pickupDispatchTask";
		String headDSName = "dispatchTask_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupOrderListgrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("isPublish", "2");
						criteria.addCriteria("pickupDeliveryOrderType", "1");
						pickupOrderListgrid.fetchData(criteria);
						pickupOrderListgrid.drawPickupDeliveryOrderListgrid();
					}
				});
		
		// 重构过滤时的查询条件
		pickupOrderListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("isPublish", "2");
				criteria.addCriteria("pickupDeliveryOrderType", "1");
			}
		});

		// 明细列表Grid
		final PickupDeliveryTaskAssignListgrid pickupDeliveryTaskAssignListgrid = new PickupDeliveryTaskAssignListgrid(){
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
				String fieldName = this.getFieldName(colNum);
				final PickupDeliveryTaskAssignListgrid listGrid = (PickupDeliveryTaskAssignListgrid)this;
				if (fieldName.equals("assignWork")) {
					final DynamicForm assignWorkForm = new DynamicForm();
					assignWorkForm.setWidth(50);
					assignWorkForm.setCellPadding(0);
					assignWorkForm.setAlign(Alignment.CENTER);
					
					LinkItem linkItem = new LinkItem();
					String workTypeName = "";
					String workType = record.getAttribute("workType");
					if (workType.equals("booking")) {
						workTypeName = "订舱";
					} else if (workType.equals("shipping")) {
						workTypeName = "起运";
					} else if (workType.equals("takeDeliveryOf")) {
						workTypeName = "取货";
					} else if (workType.equals("arrivalOfGoods")) {
						workTypeName = "到达";
					} else if (workType.equals("customsClearance")) {
						workTypeName = "清关";
					} else if (workType.equals("deliverTheGoods")) {
						workTypeName = "交货";
					}
					linkItem.setAlign(Alignment.CENTER);
					linkItem.setShowTitle(false);
					linkItem.setLinkTitle(workTypeName);
					assignWorkForm.setFields(linkItem);

					linkItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
						@Override
						public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
							final Rectangle rect = assignWorkForm.getPageRect();
							Record newRecord = listGrid.getEditedRecord(listGrid.getEventRow());
							String workStatus = newRecord.getAttributeAsString("workStatus");
							LogisticsTaskJobType workType = LogisticsTaskJobType.valueOf(newRecord.getAttributeAsString("workType"));
							String customsDeclarationType = pickupOrderListgrid.getSelectedRecord().getAttributeAsString("customsDeclarationType");
							
							if (workStatus.equals("1")) {
								SC.say("当前工作尚未分配！");
								return;
							}

							if (workType == LogisticsTaskJobType.booking) {
								// 1:订舱
								final BookingJobListgrid bookingJobListgrid = new BookingJobListgrid();
								DataSourceTool headDST = new DataSourceTool();
								String headModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob";
								String headDSName = "bookingJob_dataSource";
								headDST.onCreateDataSource(headModeName, headDSName,
										new PostDataSourceInit() {
											public void doPostOper(DataSource dataSource,
													DataInfo dataInfo) {
												bookingJobListgrid.setDataSource(dataSource);
												
												Criteria criteria = new Criteria();
												criteria.addCriteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
												bookingJobListgrid.fetchData(criteria, new DSCallback() {
													@Override
													public void execute(DSResponse response, Object rawData, DSRequest request) {
														bookingJobListgrid.selectRecord(0);
														
														Window objWindow = new BookingJobDetailWindow("编辑委托书",
																true,
																rect,
																bookingJobListgrid,
																"showwindow/logistics_modify_01.png",
																true,
																false);
														ShowWindowTools.showWondow(rect, objWindow, -1);
														return;
													}
												});
											}
										});
							} else if (workType == LogisticsTaskJobType.takeDeliveryOf) {
								// 2:取货
								final TakeDeliveryOfJobListgrid takeDeliveryOfJobListgrid = new TakeDeliveryOfJobListgrid();
								// 获取数据源
								String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob";
								String dsName = "takeDeliveryOfJob_dataSource";
								DataSourceTool dataSourceTool = new DataSourceTool();
								dataSourceTool.onCreateDataSource(modeName, dsName,
										new PostDataSourceInit() {
											public void doPostOper(DataSource dataSource,
													DataInfo dataInfo) {
												takeDeliveryOfJobListgrid.setDataSource(dataSource);
												Criteria criteria = new Criteria();
												criteria.addCriteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
												takeDeliveryOfJobListgrid.fetchData(criteria, new DSCallback() {
													@Override
													public void execute(DSResponse response, Object rawData, DSRequest request) {
														takeDeliveryOfJobListgrid.selectRecord(0);

														Window objWindow = new TakeDeliveryOfJobDetailWindow("编辑取货计划",
																false,
																rect,
																takeDeliveryOfJobListgrid,
																"showwindow/logistics_modify_01.png",
																true,
																false);
														ShowWindowTools.showWondow(rect, objWindow, -1);
														return;
													}
												});
											}
										});
							} else if (workType == LogisticsTaskJobType.shipping) {
								// 3:起运
								final ShippingJobListgrid shippingJobListgrid = new ShippingJobListgrid();
								DataSourceTool headDST = new DataSourceTool();
								String headModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob";
								String headDSName = "shippingJob_dataSource";
								headDST.onCreateDataSource(headModeName, headDSName,
										new PostDataSourceInit() {
											public void doPostOper(DataSource dataSource,
													DataInfo dataInfo) {
												shippingJobListgrid.setDataSource(dataSource);
												Criteria criteria = new Criteria();
												criteria.addCriteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
												shippingJobListgrid.fetchData(criteria, new DSCallback() {
													@Override
													public void execute(DSResponse response, Object rawData, DSRequest request) {
														shippingJobListgrid.selectRecord(0);

														Window objWindow = new ShippingJobDetailWindow("编辑运单",
																true,
																rect,
																shippingJobListgrid,
																"showwindow/logistics_modify_01.png",
																true,
																false);
														ShowWindowTools.showWondow(rect, objWindow, -1);
														return;
													}
												});
											}
										});
							} else if (workType == LogisticsTaskJobType.arrivalOfGoods) {
								// 4:到达
								final ArrivalOfGoodsJobListgrid arrivalOfGoodsJobListgrid = new ArrivalOfGoodsJobListgrid();
								DataSourceTool dataSourceTool = new DataSourceTool();
								String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob";
								String dsName = "arrivalOfGoodsJob_dataSource";
								dataSourceTool.onCreateDataSource(modeName, dsName,
										new PostDataSourceInit() {
											public void doPostOper(DataSource dataSource,
													DataInfo dataInfo) {
												arrivalOfGoodsJobListgrid.setDataSource(dataSource);
												Criteria criteria = new Criteria();
												criteria.addCriteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
												arrivalOfGoodsJobListgrid.fetchData(criteria, new DSCallback() {
													@Override
													public void execute(DSResponse response, Object rawData, DSRequest request) {
														arrivalOfGoodsJobListgrid.selectRecord(0);
														Window objWindow = new ArrivalOfGoodsJobDetailWindow("编辑到达计划",
																false,
																rect,
																arrivalOfGoodsJobListgrid,
																"showwindow/logistics_modify_01.png",
																true,
																false);
														ShowWindowTools.showWondow(rect, objWindow, -1);
														return;
													}
												});
											}
										});
							} else if (workType == LogisticsTaskJobType.customsClearance) {
								// 5:清关
								if (customsDeclarationType.equals("进口")) {
									// 进口报关
									final ImportCustomsDeclarationListgrid importCustomsDeclarationListgrid = new ImportCustomsDeclarationListgrid();
									String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importCustomsDeclaration";
									String dsName = "importCustomsDeclaration_dataSource";
									DataSourceTool dataSourceTool = new DataSourceTool();
									dataSourceTool.onCreateDataSource(modeName, dsName,
											new PostDataSourceInit() {
												public void doPostOper(DataSource dataSource,
														DataInfo dataInfo) {
													importCustomsDeclarationListgrid.setDataSource(dataSource);
													Criteria criteria = new Criteria();
													criteria.addCriteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
													importCustomsDeclarationListgrid.fetchData(criteria, new DSCallback() {
														@Override
														public void execute(DSResponse response, Object rawData, DSRequest request) {
															importCustomsDeclarationListgrid.selectRecord(0);

															Window objWindow = new ImportCustomsDeclarationDetailWindow("修改进口报关记录",
																	false,
																	rect,
																	importCustomsDeclarationListgrid,
																	"showwindow/logistics_modify_01.png",
																	true,
																	false);
															ShowWindowTools.showWondow(rect, objWindow, -1);
															return;
														}
													});
												}
											});
								}
								if (customsDeclarationType.equals("出口")) {
									// 出口报关
									final ExportCustomsDeclarationListgrid exportCustomsDeclarationListgrid = new ExportCustomsDeclarationListgrid();
									String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsDeclaration";
									String dsName = "exportCustomsDeclaration_dataSource";
									DataSourceTool dataSourceTool = new DataSourceTool();	
									dataSourceTool.onCreateDataSource(modeName, dsName,
											new PostDataSourceInit() {
												public void doPostOper(DataSource dataSource,
														DataInfo dataInfo) {
													exportCustomsDeclarationListgrid.setDataSource(dataSource);
													Criteria criteria = new Criteria();
													criteria.addCriteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
													exportCustomsDeclarationListgrid.fetchData(criteria, new DSCallback() {
														@Override
														public void execute(DSResponse response, Object rawData, DSRequest request) {
															exportCustomsDeclarationListgrid.selectRecord(0);

															Window objWindow = new ExportCustomsDeclarationDetailWindow("修改出口报关记录",
																	false,
																	rect,
																	exportCustomsDeclarationListgrid,
																	"showwindow/logistics_modify_01.png",
																	true,
																	false);
															ShowWindowTools.showWondow(rect, objWindow, -1);
															return;
														}
													});
												}
											});
								}
							} else if (workType == LogisticsTaskJobType.deliverTheGoods) {
								// 6:交货
								final DeliverTheGoodsJobListgrid deliverTheGoodsJobListgrid = new DeliverTheGoodsJobListgrid();
								DataSourceTool dataSourceTool = new DataSourceTool();
								String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob";
								String dsName = "deliverTheGoodsJob_dataSource";
								dataSourceTool.onCreateDataSource(modeName, dsName,
										new PostDataSourceInit() {
											public void doPostOper(DataSource dataSource,
													DataInfo dataInfo) {
												deliverTheGoodsJobListgrid.setDataSource(dataSource);
												Criteria criteria = new Criteria();
												criteria.addCriteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
												deliverTheGoodsJobListgrid.fetchData(criteria, new DSCallback() {
													@Override
													public void execute(DSResponse response, Object rawData, DSRequest request) {
														deliverTheGoodsJobListgrid.selectRecord(0);

														Window objWindow = new DeliverTheGoodsJobDetailWindow("编辑交货计划",
																false,
																rect,
																deliverTheGoodsJobListgrid,
																"showwindow/logistics_modify_01.png",
																true,
																false);
														ShowWindowTools.showWondow(rect, objWindow, -1);
														return;
													}
												});
											}
										});
							}
						}
					});
					
					return assignWorkForm;
				} else {
					return null;
				}
			}
		};
		
		pickupDeliveryTaskAssignListgrid.setHeight("100%");
		pickupDeliveryTaskAssignListgrid.setEditEvent(ListGridEditEvent.CLICK);
		pickupDeliveryTaskAssignListgrid.setShowRecordComponents(true);
		pickupDeliveryTaskAssignListgrid.setShowRecordComponentsByCell(true);
		
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.pickupDispatchTask";
		String detailDSName = "dispatchTaskAssign_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupDeliveryTaskAssignListgrid.setDataSource(dataSource);
						pickupDeliveryTaskAssignListgrid.drawPickupDeliveryTaskAssignListgrid(true);
						pickupDeliveryTaskAssignListgrid.setCanEdit(false);
					}
				});
		
		// 取得相应的明细数据
		pickupOrderListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				pickupDeliveryTaskAssignListgrid.setData(new ListGridRecord[]{});
				pickupDeliveryTaskAssignListgrid.fetchData(new Criteria("orderID", "" + pickupOrderListgrid.getSelectedRecord().getAttribute("id").toString()));
			}
		});
		
		// 主Layout
		final VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主Section容器
		final SectionStack mainSectionStack = new SectionStack();
		mainSectionStack.setHeight100();
		mainSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainSectionStack.setAnimateSections(true);
		
		// 头部列表面板
		SectionStackSection headSection = new SectionStackSection("提货任务分派信息");
		headSection.setItems(pickupOrderListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(pickupDeliveryTaskAssignListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("提货任务分派明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final PickupDispatchTaskButtonPanel dispatchTaskButtonPanel =
			new PickupDispatchTaskButtonPanel(pickupOrderListgrid, pickupDeliveryTaskAssignListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(dispatchTaskButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
