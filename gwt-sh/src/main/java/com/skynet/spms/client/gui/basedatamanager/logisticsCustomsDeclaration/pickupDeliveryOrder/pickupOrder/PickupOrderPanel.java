package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class PickupOrderPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "处理提货指令维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "处理提货指令模块";
		private String id;
		
		public Canvas create() {
			PickupOrderPanel panel = new PickupOrderPanel();
			
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
		pickupOrderListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "logisticsCustomsDeclaration.pickupDeliveryBusiness.pickupOrder";
		String headDSName = "pickupDeliveryOrder_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupOrderListgrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("isPublish", "1");
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
				criteria.addCriteria("isPublish", "1");
				criteria.addCriteria("pickupDeliveryOrderType", "1");
			}
		});

		// 明细列表Grid
		final PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid = new PickupDeliveryVanningListgrid();
		pickupDeliveryVanningListgrid.setHeight("100%");
		pickupDeliveryVanningListgrid.setCanEdit(true);
		pickupDeliveryVanningListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "logisticsCustomsDeclaration.pickupDeliveryBusiness.pickupOrder";
		String detailDSName = "pickupDeliveryVanning_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupDeliveryVanningListgrid.setDataSource(dataSource);
						pickupDeliveryVanningListgrid.drawPickupDeliveryVanningListgrid();
					}
				});
		
		// 根据选择的指令，取得相应的备件明细数据
		pickupOrderListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				pickupDeliveryVanningListgrid.setData(new ListGridRecord[]{});
				pickupDeliveryVanningListgrid.fetchData(new Criteria("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id").toString()));
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
		SectionStackSection headSection = new SectionStackSection("指令信息列表");
		headSection.setItems(pickupOrderListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(pickupDeliveryVanningListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("装箱信息列表");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final PickupOrderButtonPanel stockroomManageButtonPanel =
			new PickupOrderButtonPanel(pickupOrderListgrid, pickupDeliveryVanningListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(stockroomManageButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
