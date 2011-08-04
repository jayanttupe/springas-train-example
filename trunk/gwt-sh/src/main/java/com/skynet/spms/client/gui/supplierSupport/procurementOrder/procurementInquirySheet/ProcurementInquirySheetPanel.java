

package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.model.ProcurementInquiryModel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
/**
 * 采购询价单
 * @author Tony FANG
 *
 */
public class ProcurementInquirySheetPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	
	private static final String DESCRIPTION = ui.getM().mod_procurementInquiry_desc();

	private ProcurementInquirySheetToolStrip toolStripPanel;
	private ProcurementInquirySheetItemToolStrip itemToolStrip;
	private ProcurementInquirySheetListGrid listGrid;
	private ProcurementInquirySheetItemListGrid itemListGrid;

	private ProcurementInquiryModel modelLocator = ProcurementInquiryModel.getInstance();
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_procurementInquiry_desc();
		private String id;

		public Canvas create() {
			ProcurementInquirySheetPanel panel = new ProcurementInquirySheetPanel();
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

	@Override
	public Canvas getViewPanel() {
		//订单列表
		listGrid = new ProcurementInquirySheetListGrid();
		
		// 初始化订单列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTINQUIRYSHEET,
				DSKey.S_PROCUREMENTINQUIRYSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();
						listGrid.drawGrid();
						modelLocator.dataSource=dataSource;
						modelLocator.listGrid = listGrid;
					}
				});
		// 初始化订单明细数据源
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTINQUIRYSHEET_ITEM,
				DSKey.S_PROCUREMENTINQUIRYSHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.itemDataSource = dataSource;
					}
				});
		
		// 初始化订报价单数据源
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTQUOTATIONHEET,
				DSKey.S_PROCUREMENTQUOTATIONHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.quotationDS=dataSource;
					}
				});
		// 初始化报价单明细据源
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM,
				DSKey.S_PROCUREMENTQUOTATIONHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.quotationItemDS = dataSource;
					}
				});
		
		
		
		//订单操作ToolScript
		toolStripPanel = new ProcurementInquirySheetToolStrip(
				listGrid);

		//订单明细列表
		itemListGrid = new ProcurementInquirySheetItemListGrid();
		
		// 点击报价单查询明细
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				DataSource itemDs = modelLocator.itemDataSource;
				if (itemDs == null) {
					dataSourceTool.onCreateDataSource(
							DSKey.S_PROCUREMENTINQUIRYSHEET_ITEM,
							DSKey.S_PROCUREMENTINQUIRYSHEET_ITEM_DS,
							new PostDataSourceInit() {
								public void doPostOper(DataSource dataSource,
										DataInfo dataInfo) {
									loadItems(dataSource);
								}
							});
				} else {
					loadItems(itemDs);
				}

			}
		});
		
//		//订单明细操作ToolStrip
		itemToolStrip = new ProcurementInquirySheetItemToolStrip(
				itemListGrid);

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.addMember(toolStripPanel);

		//主容器
		SectionStack sStack = new SectionStack();
		sStack.setShowCustomScrollbars(false);
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		//订单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM().mod_procurementInquiry_name());
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		//订单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(
				ui.getM().mod_procurementInquiry_item_name());
//		siItemStackSection.setItems(itemListGrid,
//				itemToolStrip);
		siItemStackSection.setItems(itemListGrid);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}

	/***************************************************************************
	 * 根据申请单ID获取申请单明细信息
	 **************************************************************************/
	private void loadItems(DataSource dataSource) {
		// 获取申请单主键
		String id = listGrid.getSelectedRecord().getAttribute("id");
		itemListGrid.setDataSource(dataSource);
		Criteria criteria = new Criteria();
		criteria.addCriteria("id", id);
		criteria.addCriteria("_r", String.valueOf(Math.random()));
		itemListGrid.fetchData(criteria);
		itemListGrid.drawGrid();
		modelLocator.itemListGrid = itemListGrid;
	}

	
	public String getIntro() {
		return DESCRIPTION;
	}
}