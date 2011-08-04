package com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.ui;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.model.SheetModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 回购申请单主面板
 * 
 * @author fl
 * 
 */
public class BuyBackSheetPanel extends ShowcasePanel {

	
	private static I18n ui = new I18n();
	//回购申请单
	public static final String DESCRIPTION = ui.getM().mod_buyBackSheet_name();
	public SheetModelLocator model = SheetModelLocator.getInstance();
	public BuyBackSheetToolStrip sheetToolStrip;
	public BuyBackSheetGrid sheetGrid;
	public BuyBackSheetItemGrid sheetItemGrid;
	public SectionStackSection sheetGridSection;
	public SectionStackSection sheetItemGridSection;
	public SectionStack mainStack;
	public VLayout v;

	public Canvas getViewPanel() {
		v = new VLayout();
		// 回购单表格
		sheetGrid = new BuyBackSheetGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKSHEET,
				DSKey.C_BUYBACKSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						sheetGrid.setDataSource(dataSource);
						sheetGrid.drawGrid();
						Criteria criteria = new Criteria();
						criteria
								.setAttribute(
										"m_BussinessPublishStatusEntity.m_PublishStatus",
										"published");
						sheetGrid.fetchData(criteria);
						model.backSheetGrid = sheetGrid;
					}
				});

		sheetGrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ListGridRecord record = sheetGrid.getSelectedRecord();
				String id = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("sheet.id", id);
				sheetItemGrid.fetchData(criteria);
			}
		});

		sheetToolStrip = new BuyBackSheetToolStrip();
		v.addMember(sheetToolStrip);
		// 主容器
		mainStack = new SectionStack();
		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		v.addMember(mainStack);

		//回购申请单
		sheetGridSection = new SectionStackSection(ui.getM().mod_buyBackSheet_name());
		sheetGridSection.setExpanded(true);
		sheetGridSection.addItem(sheetGrid);
		mainStack.addSection(sheetGridSection);
		// // 回购单明细表格
		sheetItemGrid = new BuyBackSheetItemGrid();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKSHEET,
				DSKey.C_BUYBACKSHEETITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						sheetItemGrid.setDataSource(dataSource);
						sheetItemGrid.drawGrid();
						model.sheetItemGrid = sheetItemGrid;
					}
				});
		//回购申请单明细
		sheetItemGridSection = new SectionStackSection(ui.getM().mod_buyBackSheet_name());
		sheetItemGridSection.setExpanded(true);
		sheetItemGridSection.setItems(sheetItemGrid);
		mainStack.addSection(sheetItemGridSection);
		return v;
	}

	public static class Factory implements PanelFactory {

		public String DESCRIPTION = ui.getM().mod_buyBackSheet_name();
		public String id;

		public Canvas create() {
			BuyBackSheetPanel panel = new BuyBackSheetPanel();
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

	public String getIntro() {
		return DESCRIPTION;
	}
}
