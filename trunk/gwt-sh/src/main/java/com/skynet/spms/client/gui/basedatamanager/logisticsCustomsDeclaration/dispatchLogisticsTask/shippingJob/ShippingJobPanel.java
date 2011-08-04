package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

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

public class ShippingJobPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "起运工作管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "起运工作管理模块";
		private String id;
		
		public Canvas create() {
			ShippingJobPanel panel = new ShippingJobPanel();
			
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
		final ShippingJobListgrid shippingJobListgrid = new ShippingJobListgrid();
		shippingJobListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob";
		String headDSName = "shippingJob_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						shippingJobListgrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("workStatus", "2");
						shippingJobListgrid.fetchData(criteria);
						shippingJobListgrid.drawShippingJobListgrid();
					}
				});
		
		// 重构过滤查询条件
		shippingJobListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("workStatus", "2");
			}
		});

		// 明细列表Grid
		final PickupDeliveryVanningListgrid pickupVanningListgrid = new PickupDeliveryVanningListgrid();
		pickupVanningListgrid.setHeight100();
		pickupVanningListgrid.setCanEdit(true);
		pickupVanningListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob";
		String detailDSName = "shippingJobDetails_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupVanningListgrid.setDataSource(dataSource);
						pickupVanningListgrid.drawPickupDeliveryVanningListgrid();
					}
				});
		
		// 根据选择的起运工作，取得相应的明细数据
		shippingJobListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				pickupVanningListgrid.setData(new ListGridRecord[]{});
				pickupVanningListgrid.fetchData(new Criteria("shippingJobID", shippingJobListgrid.getSelectedRecord().getAttribute("id").toString()));
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
		SectionStackSection headSection = new SectionStackSection("起运工作信息");
		headSection.setItems(shippingJobListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(pickupVanningListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("起运工作明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final ShippingJobButtonPanel bookingJobButtonPanel =
			new ShippingJobButtonPanel(shippingJobListgrid, pickupVanningListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(bookingJobButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
