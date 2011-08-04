package com.skynet.spms.client.gui.commonOrder.pickup.ui;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 提货指令单
 * @author tqc
 *
 */
public class PickUpOrderPanel  extends VLayout {
	
	final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);

	private PickUpOrderToolStrip toolStripPanel;
	private PickUpOrderListGrid listGrid;
	private PickUpOrderItemListGrid itemListGrid;
	private String modName;
	
	
	public PickUpOrderPanel(){}
	
	public PickUpOrderPanel(String businessType,String modName){
		if(modName==null||"".equals(modName)){
			modName="supplierSupport.repairClaim.repairDeliveryOrder";
		}
		this.modName=modName;
		setWidth("99%");
		setHeight("99%");
		boolean topIntro = isTopIntro();
		Layout layout = topIntro ? new VLayout() : new HLayout();
		layout.setWidth100();
		layout.setMargin(1);
		layout.setMembersMargin(2);
		Canvas panel = getViewPanel(businessType);
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.addMember(panel);
		layout.addMember(wrapper);
		addMember(layout);
	}

	public String category;//类别

	public Canvas getViewPanel(final String businessType) {
		//订单列表
		listGrid = new PickUpOrderListGrid();
		final DataSourceTool tool = new DataSourceTool();
		tool.onCreateDataSource(
				"supplierSupport.repairClaim.repairDeliveryOrder",
				"PickupDeliveryOrder_datasource", new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("type", "1");
						criteria.addCriteria("businessType",businessType);
						listGrid.fetchData(criteria);
						listGrid.drawGrid();
					}
				});
		
		//订单操作ToolScript
		toolStripPanel = new PickUpOrderToolStrip(listGrid,modName);

		//订单明细列表
		itemListGrid = new PickUpOrderItemListGrid();
		
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				tool.onCreateDataSource(
						"supplierSupport.repairClaim.repairDeliveryOrder",
						"PickupDeliveryVanningItems_datasource",
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								itemListGrid.setDataSource(dataSource);
								Criteria criteria = new Criteria();
								criteria.addCriteria("_key", "fetchItem");
								criteria.addCriteria("_r", String.valueOf(Math.random()));
								criteria.addCriteria("orderId", listGrid
										.getSelectedRecord().getAttribute("id"));
								itemListGrid.fetchData(criteria);
								itemListGrid.drawGrid();
							}
						});
			}
		});
		

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.addMember(toolStripPanel);

		//主容器
		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		//订单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.picup_order());
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		//订单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(ui.picup_order_item());
		siItemStackSection.setItems(itemListGrid);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}

	public String getIntro() {
		return ui.picup_order();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	protected boolean isTopIntro() {
		return false;
	}
}