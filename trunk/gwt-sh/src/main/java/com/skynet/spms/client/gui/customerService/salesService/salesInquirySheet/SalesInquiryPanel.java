package com.skynet.spms.client.gui.customerService.salesService.salesInquirySheet;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
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
 * 询价单管理主面板
 * 
 * @author tqc
 * 
 */
public class SalesInquiryPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private SalesInquiryToolStrip salesInquiryToolStripPanel;
	private SalesInquiryItemToolStrip salesInquiryItemToolStrip;
	private SalesInquiryListGrid salesInquiryListGrid;
	private SalesInquiryItemListGrid salesInquiryItemListGrid;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_salesInquiry_desc();
		private String id;

		public Canvas create() {
			SalesInquiryPanel panel = new SalesInquiryPanel();
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
		// 询价单列表
		salesInquiryListGrid = new SalesInquiryListGrid();

		// 初始化询价单数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESINQURIYSHEET,
				DSKey.C_SALESINQURIYSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						salesInquiryListGrid.setDataSource(dataSource);
						salesInquiryListGrid.fetchData();
						salesInquiryListGrid.drawGrid();
						/*// 将数据源加入缓存中,下次可以直接从缓存中取出
						Cache.getInstance().putDataSource(
								DSKey.C_SALESINQURIYSHEET,
								DSKey.C_SALESINQURIYSHEET_DS, dataSource);*/
					}
				});
		

		// 询价单操作工具栏
		salesInquiryToolStripPanel = new SalesInquiryToolStrip(
				salesInquiryListGrid);

		// 询价单明细列表
		salesInquiryItemListGrid = new SalesInquiryItemListGrid();
		salesInquiryItemListGrid.setWidth100();
		salesInquiryItemListGrid.setMargin(5);
		salesInquiryItemListGrid.setShowFilterEditor(true);
		
		//初始化询价单详细数据源
		dataSourceTool.onCreateDataSource(
				DSKey.C_SALESINQURIYSHEET_ITEM,
				DSKey.C_SALESINQURIYSHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						salesInquiryItemListGrid.setDataSource(dataSource);
						salesInquiryItemListGrid.drawGrid();
					}
				});

		// 点击询价单查询询价单明细
		salesInquiryListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				// 获取询价单主键
				String sheetId = salesInquiryListGrid.getSelectedRecord()
						.getAttributeAsString("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("sheetId", sheetId);
				criteria.addCriteria("_r", String.valueOf(Math.random()));
				salesInquiryItemListGrid.fetchData(criteria);
			}
		});

		// 询价单明细操作ToolStrip
		salesInquiryItemToolStrip = new SalesInquiryItemToolStrip(
				salesInquiryItemListGrid, salesInquiryListGrid);
		salesInquiryItemToolStrip.setMargin(5);

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();
		v.addMember(salesInquiryToolStripPanel);

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setHeight(400);
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);
		// 询价单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_salesInquiry_list_title());
		siStackSection.addItem(salesInquiryListGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);
		// 询价单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(ui
				.getM().mod_salesInquiry_item_list_title());
		siItemStackSection.setItems(salesInquiryItemListGrid,
				salesInquiryItemToolStrip);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);

		return v;
	}

	public String getIntro() {
		return ui.getM().mod_salesInquiry_desc();
	}
}