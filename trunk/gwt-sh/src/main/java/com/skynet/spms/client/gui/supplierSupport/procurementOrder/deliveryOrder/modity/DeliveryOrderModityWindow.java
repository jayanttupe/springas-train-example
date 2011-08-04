package com.skynet.spms.client.gui.supplierSupport.procurementOrder.deliveryOrder.modity;

import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 添加窗体
 * 
 * @author tqc
 * 
 */
public class DeliveryOrderModityWindow extends BaseWindow {

	/**
	 * 无需传递数据源 重载
	 * @param opm 当前操作方式
	 */
	public DeliveryOrderModityWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		this.setTitle("修改发货指令");
		this.setOverflow(Overflow.AUTO);
		/**主面板**/
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		/**面板1**/
		DeliveryModityTabSet tabSet = new DeliveryModityTabSet();
		tabSet.setHeight(313);
		vmain.addMember(tabSet);

		/**面板2**/
		VLayout twoView = getShowGridView();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		return vmain;
	}

	//布局2
	private VLayout getShowGridView() {
		/**主布局**/
		VLayout v = new VLayout();
		v.setMembersMargin(5);
		v.setLayoutTopMargin(3);

		/**上布局（Grid）**/
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(10);

		/*左布局*/
		VLayout leftLayout = new VLayout();
		/*左容器里的标题容器*/
		HLayout titleHL = new HLayout();
		titleHL.setHeight(30);
		titleHL.setWidth100();
		//添加标题
		Label leftLb = new Label("依据合同明细");
		leftLb.setWidth("80");
		leftLb.setHeight("15");
		//添加按钮
		IButton batchAddBtn = new IButton("批量加入");
		batchAddBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

			}

		});
		titleHL.addMember(leftLb);
		titleHL.addMember(batchAddBtn);

		leftLayout.addMember(titleHL);//先放标题容器
		leftLayout.addMember(getLeftGrid());//再放Grid

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label("发货指令明细");
		rightLb.setHeight("30");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());

		h.addMember(leftLayout);
		h.addMember(rightLayout);

		/**下布局（操作按钮）**/
		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setLayoutLeftMargin(50);
		btnGroup.setMargin(5);
		IButton btnSave = new IButton("保存");
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			}
		});

		IButton closeSave = new IButton("关闭");
		closeSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		btnGroup.addMember(btnSave);
		btnGroup.addMember(closeSave);

		v.addMember(h);
		v.addMember(btnGroup);
		return v;
	}

	//选择Grid
	private ListGrid getLeftGrid() {
		FilterListGrid lg = new FilterListGrid();
		lg.setAutoFetchData(true);
		lg.setHeight("100%");
		ListGridField field1 = new ListGridField("field1", "项号");

		ListGridField field2 = new ListGridField("field2", "件号");

		ListGridField field3 = new ListGridField("field3", "数量");
		Utils.formatQuantityField(field3);//格式化数量

		ListGridField field4 = new ListGridField("field4", "单价");
		Utils.formatPriceField(field4);//格式化单价
		field4.setAlign(Alignment.RIGHT);

		ListGridField field5 = new ListGridField("field5", "总价");
		Utils.formatPriceField(field5);//格式化总价
		field5.setAlign(Alignment.RIGHT);

		lg.setFields(field1, field2, field3, field4, field5);

		//绑定假数据
		lg.setDataSource(Utils.getXmlDataSource());

		lg.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {

			}

		});

		return lg;
	}

	//新采购指令明细项
	private ListGrid getRightGrid() {
		FilterListGrid lg = new FilterListGrid();
		lg.setCanRemoveRecords(true);
		lg.setAutoFetchData(true);
		lg.setHeight("100%");
		ListGridField field1 = new ListGridField("field1", "项号");

		ListGridField field2 = new ListGridField("field2", "件号");

		ListGridField field3 = new ListGridField("field3", "数量");
		Utils.formatQuantityField(field3);//格式化数量

		ListGridField field4 = new ListGridField("field4", "单价");
		Utils.formatPriceField(field4);//格式化单价
		field4.setAlign(Alignment.RIGHT);

		ListGridField field5 = new ListGridField("field5", "总价");
		Utils.formatPriceField(field4);//格式化总价
		field5.setAlign(Alignment.RIGHT);

		lg.setFields(field1, field2, field3, field4, field5);
		lg.addCellClickHandler(new CellClickHandler() {

			public void onCellClick(CellClickEvent event) {

			}

		});
		//绑定假数据
		lg.setDataSource(Utils.getXmlDataSource());
		return lg;
	}

}
