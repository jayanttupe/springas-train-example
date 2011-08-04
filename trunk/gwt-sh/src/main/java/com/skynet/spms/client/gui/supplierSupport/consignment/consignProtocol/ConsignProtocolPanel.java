package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model.ConsignProtocolModel;
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
 * 寄售协议面板
 * 
 * @author fl
 * 
 */
public class ConsignProtocolPanel extends ShowcasePanel {

	private static I18n ui = new I18n();
	
	//寄售协议
	private static final String DESCRIPTION = ui.getM().consignProtocol_name();
	public ConsignProtocolModel model = ConsignProtocolModel.getInstance();
	private ConsignProtocolToolStrip mainToolStrip;
	private ConsignProtocolGrid mainGrid;
	private ConsignProtocolItemGrid itemGrid;
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
		// 回购单表格
		mainGrid = new ConsignProtocolGrid();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNPROTOCOL,
				DSKey.S_CONSIGNPROTOCOL_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						mainGrid.setDataSource(dataSource);
						mainGrid.fetchData();
						mainGrid.drawGrid();
						model.proMainGrid = mainGrid;
					}
				});
		mainGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = mainGrid.getSelectedRecord();
				String id = record.getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.setAttribute("consignId", id);
				itemGrid.fetchData(criteria);
			}
		});
		mainToolStrip = new ConsignProtocolToolStrip();
		v.addMember(mainToolStrip);

		mainGridSection = new SectionStackSection(ui.getM().consignProtocol_name());
		mainGridSection.addItem(mainGrid);
		mainGridSection.setExpanded(true);
		mainStack.addSection(mainGridSection);
		// 明细表格
		itemGrid = new ConsignProtocolItemGrid();
		dataSourceTool.onCreateDataSource(DSKey.S_CONSIGNPROTOCOL,
				DSKey.S_CONSIGNPROTOCOLITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemGrid.setDataSource(dataSource);
						itemGrid.drawGrid();
					}
				});
		itemGridSection = new SectionStackSection(ui.getM().consignProtocol_item_name());
		itemGridSection.setItems(itemGrid);
		itemGridSection.setExpanded(true);
		mainStack.addSection(itemGridSection);

		v.addMember(mainStack);
		return v;
	}

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().consignProtocol_name();
		private String id;

		public Canvas create() {
			ConsignProtocolPanel panel = new ConsignProtocolPanel();
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
