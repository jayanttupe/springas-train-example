package com.skynet.spms.client.gui.commonOrder.delivery.ui.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.OperationMode;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.skynet.spms.client.gui.commonOrder.delivery.model.DataModelLocator;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @author tqc
 * 
 */
public class DeliveryOrderWindowView extends BaseWindow {

	private FilterListGrid lg = null;

	private FilterListGrid itemLg = null;

	/**
	 * 此构造函数，为了便于Form数据源的绑定。可直接传递主从表的Grid
	 * 
	 * @param opm
	 *            当前操作方式
	 * @param listGrids
	 *            Gird数组便于绑定数据源
	 */
	public DeliveryOrderWindowView(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl,
			OperationMode opm, ListGrid[] listGrids) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public DeliveryOrderWindowView(Rectangle srcRect, ListGrid listGrid,
			String contractNumber, String contractType) {
		super("发货指令查看", true, srcRect, listGrid, "");
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		lg = new FilterListGrid();
		itemLg = new FilterListGrid();
		DataModelLocator.getInstance().contractItemGrid = lg;
		this.setOverflow(Overflow.AUTO);
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		DeliveryBaseFormView baseInfoForm = new DeliveryBaseFormView();
		baseInfoForm.setHeight(313);
		vmain.addMember(baseInfoForm);
		VLayout twoView = getShowGridView();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);
		return vmain;
	}

	private VLayout getShowGridView() {
		CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		VLayout v = new VLayout();
		v.setMembersMargin(5);
		v.setLayoutTopMargin(3);
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(10);
		VLayout rightLayout = new VLayout();
		Label rightLb = new Label(ui.delivery_order_item());
		rightLb.setHeight("30");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());
		h.addMember(rightLayout);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setLayoutLeftMargin(50);
		btnGroup.setMargin(5);
		
		
		IButton closeSave = new IButton(ui.btnClose());
		closeSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		btnGroup.addMember(closeSave);
		v.addMember(h);
		v.addMember(btnGroup);
		return v;
	}


	/**
	 * 发货明细
	 * 
	 * @return
	 */
	private ListGrid getRightGrid() {
		itemLg.setHeight("100%");
		itemLg.setShowRowNumbers(true);
		itemLg.setShowAllRecords(true);
		itemLg.setCanEdit(false);
		
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);

		final DataSourceTool tool = new DataSourceTool();
		tool.onCreateDataSource(
				"supplierSupport.repairClaim.repairDeliveryOrder",
				"PickupDeliveryVanningItems_datasource",
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemLg.setDataSource(dataSource);
						Criteria query = new Criteria();
						query.addCriteria("orderId", DataModelLocator
								.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
						itemLg.fetchData(query, new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								ListGridField field1 = new ListGridField(
										"orderID", ui.orderID());
								field1.setHidden(true);
								ListGridField field2 = new ListGridField(
										"partNumber", ui.partNumber());
								ListGridField field3 = new ListGridField(
										"quantity", ui.quantity());
								ListGridField field4 = new ListGridField(
										"unitPriceAmount", ui.unitPriceAmount());
								ListGridField field5 = new ListGridField(
										"totalAmount", ui.totalAmount());
								// 币种
								ListGridField field6 = new ListGridField(
										"currency");
								field6.setHidden(true);

								itemLg.setFields(field1, field2, field3,
										field4, field5, field6);
							}
						});

					}
				});
		return itemLg;
	}




}
