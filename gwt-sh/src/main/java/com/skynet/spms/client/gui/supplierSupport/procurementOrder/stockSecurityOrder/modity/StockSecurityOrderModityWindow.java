package com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder.modity;

import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomPanel;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.form.fields.DicSelectItem;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/***
 * 主订单 与 明细 添加页面
 * @author Tony FANG
 *
 */
public class StockSecurityOrderModityWindow extends BaseWindow {

	/**
	 * 无需传递数据源 重载
	 * @param opm 当前操作方式
	 */
	public StockSecurityOrderModityWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		this.setTitle("修改库存安全策略");
		/**主面板**/
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setMembersMargin(5);
		vmain.setOverflow(Overflow.AUTO);

		/**订单信息容器*/
		VLayout detailLayout = new VLayout();
		detailLayout.setLayoutTopMargin(10);
		detailLayout.setWidth100();
		//订单Form
		detailLayout.addMember(getDetailForm());

		/**备件发货数量分析数据日容器**/
		VLayout detailTwoLayout = new VLayout();
		detailTwoLayout.setWidth100();
		detailTwoLayout.setMargin(5);
		//自定义面板
		CustomPanel cp = new CustomPanel();
		cp.setTitleLabelContents("附件");
		cp.getContentCanvas().addChild(getDeliveryAnalysisView());
		detailTwoLayout.addMember(cp);

		/**按钮操作容器**/
		VLayout btnsLayout = new VLayout();
		btnsLayout.setLayoutMargin(8);
		btnsLayout.setHeight(15);
		btnsLayout.setWidth100();
		btnsLayout.addMember(getSubmitLayout());
		btnsLayout.setBorder("1px solid #E8E8E8");

		vmain.addMember(detailLayout);
		vmain.addMember(detailTwoLayout);
		vmain.addMember(btnsLayout);

		return vmain;
	}

	/***
	 * 表单信息
	 * @return
	 */
	private DynamicForm getDetailForm() {
		LayoutDynamicForm ldf = new LayoutDynamicForm();
		ldf.setTitleAlign(Alignment.LEFT);
		ldf.setCellPadding(2);
		ldf.setPadding(1);
		ldf.setNumCols(4);
		ldf.setWidth100();

		SelectItem item12 = new DicSelectItem();
		item12.setName("item12");
		item12.setTitle("业务编号");
		item12.setDisabled(true);

		SelectItem item1 = new DicSelectItem();
		item1.setName("item1");
		item1.setTitle("备件中心");

		DicSelectItem item2 = new DicSelectItem();
		item2.setName("item2");
		item2.setTitle("制造商");

		SelectItem item3 = Utils.getPartNumberList();
		item3.setName("item3");
		item3.setTitle("件号");

		TextAreaItem item4 = new TextAreaItem();
		item4.setName("item4");
		item4.setRowSpan(7);
		item4.setTitle("安全策略描述");
		item4.setTitleVAlign(VerticalAlignment.TOP);
		item4.setHeight("100%");

		DateItem item5 = new DateItem();
		item5.setName("item5");
		item5.setTitle("有效起始日期");

		DateItem item6 = new DateItem();
		item6.setName("item6");
		item6.setTitle("失效结束日期");

		SelectItem item7 = new SelectItem();
		item7.setName("item7");
		item7.setTitle("库房位置");

		SelectItem item8 = new SelectItem();
		item8.setName("item8");
		item8.setTitle("库存单位");

		TextItem item9 = new TextItem();
		item9.setName("item9");
		item9.setTitle("安全库存量");

		TextItem item10 = new TextItem();
		item10.setName("item10");
		item10.setTitle("再订货点");

		TextItem item11 = new TextItem();
		item11.setName("item11");
		item11.setTitle("再订货量");

		ldf.setFields(item12, item2, item1, item3, item4, item5, item6, item7,
				item8, item9, item10, item11);

		return ldf;
	}

	/**
	 * 构建
	 * 备件发货数量分析数据
	 * 显示视图
	 * @return
	 */
	private DynamicForm getDeliveryAnalysisView() {
		LayoutDynamicForm ldf = new LayoutDynamicForm();
		ldf.setTitleWidth(110);
		ldf.setCellPadding(2);
		ldf.setNumCols(2);
		ldf.setWidth100();

		StaticTextItem item1 = new StaticTextItem();
		item1.setName("item1");
		item1.setTitle("基准日期");

		StaticTextItem item2 = new StaticTextItem();
		item2.setName("item2");
		item2.setTitle("单位");

		StaticTextItem item3 = new StaticTextItem();
		item3.setName("item3");
		item3.setTitle("最近三个月发货数量");

		StaticTextItem item4 = new StaticTextItem();
		item4.setName("item4");
		item4.setTitle("最近半年发货数量");

		StaticTextItem item5 = new StaticTextItem();
		item5.setName("item5");
		item5.setTitle("最近一年发货数量");

		StaticTextItem item6 = new StaticTextItem();
		item6.setName("item6");
		item6.setTitle("近两年发货数量");

		ldf.setFields(item1, item2, item3, item4, item5, item6);
		return ldf;
	}

	/**
	 * 表单提交操作按钮
	 * @return
	 */
	private HLayout getSubmitLayout() {
		BtnsHLayout h = new BtnsHLayout();
		h.setWidth100();
		Button saveBtn = new Button("保存");
		saveBtn.setPadding(6);
		saveBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

			}
		});

		Button cancelBtn = new Button("取消");
		cancelBtn.setPadding(6);
		cancelBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});

		Button helpBtn = new Button("帮助");
		helpBtn.setPadding(6);
		helpBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			}
		});

		h.addMember(saveBtn);
		h.addMember(cancelBtn);
		h.addMember(helpBtn);
		return h;
	}
}
