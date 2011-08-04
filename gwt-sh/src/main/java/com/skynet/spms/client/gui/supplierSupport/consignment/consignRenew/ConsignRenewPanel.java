package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEvent;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEventHandler;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.commonOrder.ContractIndexKey;
import com.skynet.spms.client.gui.commonOrder.DirectiveBusinessType;
import com.skynet.spms.client.gui.commonOrder.MenuButtonBuilder;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.model.ConsignRenewModel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 寄售补库面板
 * 
 * @author fl
 * 
 */
public class ConsignRenewPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	//寄售补库
	public static final String DESCRIPTION = ui.getM().consignRenew_name();
	public ConsignRenewModel model = ConsignRenewModel.getInstance();
	public ConsignRenewToolStrip mainToolStrip;
	public ConsignRenewGrid mainGrid;
	public ConsignRenewItemGrid itemGrid;
	public SectionStackSection mainGridSection;
	public SectionStackSection itemGridSection;
	public SectionStack mainStack;
	public VLayout v;

	public Canvas getViewPanel() {
		v = new VLayout(0);
		// 主容器
		mainStack = new SectionStack();
		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		// 回购单表格
		mainGrid = new ConsignRenewGrid();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNRENEW,
				DSKey.S_CONSIGNRENEW_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						mainGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						mainGrid.fetchData(criteria);
						mainGrid.drawGrid();
						model.consignRenewGrid = mainGrid;
					}
				});
		mainGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = mainGrid.getSelectedRecord();
				String id = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.setAttribute("consignRenewId", id);
				itemGrid.fetchData(criteria);
			}
		});
		mainToolStrip = new ConsignRenewToolStrip();

		// 挂载提货指令
		mainToolStrip.handlerManager.addHandler(
				FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						mainToolStrip.addMenuButton(MenuButtonBuilder.create(
								ContractIndexKey.ConsignRenewManagerForOrder,
								mainGrid, MenuButtonBuilder.PICKUP,DirectiveBusinessType.consignrenew.name()), 4);
					}
				});

		v.addMember(mainToolStrip);

		//寄售补库信息
		mainGridSection = new SectionStackSection(ui.getM().consignRenew_name());
		mainGridSection.addItem(mainGrid);
		mainGridSection.setExpanded(true);
		mainStack.addSection(mainGridSection);
		// 寄售补库明细表格
		itemGrid = new ConsignRenewItemGrid();
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNRENEW,
				DSKey.S_CONSIGNRENEWITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemGrid.setDataSource(dataSource);
						itemGrid.drawGrid();
					}
				});
		//寄售补库明细
		itemGridSection = new SectionStackSection(ui.getM().consignRenew_item_name());
		itemGridSection.setItems(itemGrid);
		itemGridSection.setExpanded(true);
		mainStack.addSection(itemGridSection);
		v.addMember(mainStack);
		return v;
	}

	public static class Factory implements PanelFactory {

		public String DESCRIPTION = ui.getM().consignRenew_name();
		public String id;

		public Canvas create() {
			ConsignRenewPanel panel = new ConsignRenewPanel();
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
