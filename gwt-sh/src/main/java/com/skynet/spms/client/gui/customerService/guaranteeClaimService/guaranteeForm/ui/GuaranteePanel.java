package com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.ui;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.guaranteeClaimService.guaranteeForm.model.GuaranteeModelLocator;
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
 * 担保索赔面板
 * 
 * @author fl
 * 
 */
public class GuaranteePanel extends ShowcasePanel {

	private static I18n ui = new I18n();
	//担保索赔申请单
	private static final String DESCRIPTION = ui.getM().guarantee_name();
	public GuaranteeModelLocator modelLocator = GuaranteeModelLocator
			.getInstance();
	private GuaranteeToolStrip mainToolStrip;
	private GuaranteeGrid mainGrid;
	private GuaranteeItemGrid itemGrid;
	private SectionStackSection mainGridSection;
	private SectionStackSection itemGridSection;
	private SectionStack mainStack;
	private VLayout v;

	public Canvas getViewPanel() {
		v = new VLayout(0);
		// 主容器
		mainStack = new SectionStack();
		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		// 担保索赔单表格
		mainGrid = new GuaranteeGrid();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_GUARANTEESHEET,
				DSKey.C_GUARANTEESHEET_DS, new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						mainGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria
								.setAttribute(
										"m_BussinessPublishStatusEntity.m_PublishStatus",
										"published");
						mainGrid.fetchData(criteria);
						mainGrid.drawGrid();
						modelLocator.mainSheetGrid = mainGrid;
					}
				});
		mainGrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ListGridRecord record = mainGrid.getSelectedRecord();
				String id = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("sheet.id", id);
				itemGrid.setCriteria(criteria);
				itemGrid.fetchData(criteria);
			}
		});
		mainToolStrip = new GuaranteeToolStrip(mainGrid);
		v.addMember(mainToolStrip);

		//担保索赔申请单
		mainGridSection = new SectionStackSection(ui.getM().guarantee_name());
		mainGridSection.addItem(mainGrid);
		mainGridSection.setExpanded(true);
		mainStack.addSection(mainGridSection);
		// 担保索赔单明细表格
		itemGrid = new GuaranteeItemGrid();
		itemGridSection = new SectionStackSection(ui.getM().guarantee_item_name());

		dataSourceTool.onCreateDataSource(DSKey.C_GUARANTEESHEET,
				DSKey.C_GUARANTEESHEETITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria criteria = new Criteria();
						criteria.addCriteria("sheet.id", "-1");
						itemGrid.setDataSource(dataSource);
						itemGrid.fetchData(criteria);
						itemGrid.drawGrid();
					}
				});
		itemGridSection.addItem(itemGrid);
		itemGridSection.setExpanded(true);
		mainStack.addSection(itemGridSection);

		v.addMember(mainStack);
		return v;
	}

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = GuaranteePanel.DESCRIPTION;
		private String id;

		public Canvas create() {
			GuaranteePanel panel = new GuaranteePanel();
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
