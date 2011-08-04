package com.skynet.spms.client.gui.supplierSupport.leaseService.leaseContract;

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
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.leaseService.model.SSMainModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class SSLeaseContractPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private SSLeaseContractListGrid ssLeaseContractListGrid;
	private SSLeaseContractItemListGrid listitem;
	private SSMainModelLocator model = SSMainModelLocator.getInstance();
	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM().mod_ssLeaseContract_name();
		private String id;

		public Canvas create() {
			SSLeaseContractPanel panel = new SSLeaseContractPanel();
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
		VLayout v = new VLayout();
		// 加载菜单栏
		HLayout h1 = new HLayout();
		h1.setHeight(20);
		ssLeaseContractListGrid = new SSLeaseContractListGrid();
		final SSLeaseContractToolStrip ts = new SSLeaseContractToolStrip(
				ssLeaseContractListGrid);

		ts.handlerManager.addHandler(FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						ts
								.addMenuButton(
										MenuButtonBuilder
												.create(
														ContractIndexKey.SSLeaseContractTemplateManagerForOrder,
														ssLeaseContractListGrid,
														MenuButtonBuilder.ALL,
														DirectiveBusinessType.lease
																.name()), 4);
					}
				});

		h1.addMember(ts);
		// 创建主容器
		SectionStack s = new SectionStack();
		s.setVisibilityMode(VisibilityMode.MULTIPLE);
		s.setAnimateSections(true);
		SectionStackSection s1 = new SectionStackSection(ui.getM()
				.mod_ssLeaseContract_name());
		s1.setExpanded(true);

		s1.addItem(ssLeaseContractListGrid);

		SectionStackSection s2 = new SectionStackSection(ui.getB()
				.ssLeaseContractItems());
		s2.setExpanded(true);
		listitem = new SSLeaseContractItemListGrid();

		// 构建供应商租赁合同数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
				DSKey.S_LEASECONTRACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						ssLeaseContractListGrid.setDataSource(dataSource);
						ssLeaseContractListGrid.fetchData();
						ssLeaseContractListGrid.drawGrid();
						model.ssleaseContractListGrid = ssLeaseContractListGrid;
					}
				});
		// 构建供应商租赁合同明细数据源
		dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
				DSKey.S_LEASECONTRACTITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.SSleaseContractItemDs = dataSource;
					}
				});
		// 初始化附件数据源
		dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
				DSKey.S_LEASEATTACHMENT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.SSattachmentDs = dataSource;
					}
				});
		// 初始化地址添加数据源
		dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
				DSKey.S_LEASEADDRESS_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.SSaddressDataSource = dataSource;
					}
				});

		ssLeaseContractListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				final String leaseId = ssLeaseContractListGrid
						.getSelectedRecord().getAttribute("id");
				model.SSLeaseContractId = leaseId;
				addressModel.addr_sheetId = leaseId;
				Criteria criteria = new Criteria();
				criteria.addCriteria("id", leaseId);
				criteria.addCriteria("_r", String.valueOf(Math.random()));
				listitem.fetchData(criteria);
			}
		});
		dataSourceTool.onCreateDataSource(DSKey.S_LEASECONTRACT,
				DSKey.S_LEASECONTRACTITEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listitem.setDataSource(dataSource);

						listitem.drawGrid();
					}
				});
		s2.addItem(listitem);
		s.setSections(s1, s2);
		v.setMembers(h1, s);
		return v;
	}
}
