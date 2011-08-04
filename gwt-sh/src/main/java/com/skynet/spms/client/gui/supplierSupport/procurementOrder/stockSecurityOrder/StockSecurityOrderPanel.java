package com.skynet.spms.client.gui.supplierSupport.procurementOrder.stockSecurityOrder;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.gui.base.CustomPanel;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;

/**
 * 库存安全策略
 * @author Tony FANG
 *
 */
public class StockSecurityOrderPanel extends ShowcasePanel {
	private static final String DESCRIPTION = "库存安全策略";

	private StockSecurityOrderToolStrip toolStripPanel;
	private StockSecurityOrderListGrid listGrid;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "库存安全策略";
		private String id;

		public Canvas create() {
			StockSecurityOrderPanel panel = new StockSecurityOrderPanel();
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
		//订单列表
		listGrid = new StockSecurityOrderListGrid();
		listGrid.drawGrid();
		//订单操作ToolScript
		toolStripPanel = new StockSecurityOrderToolStrip(listGrid);

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setWidth100();
		v.setOverflow(Overflow.AUTO);
		v.addMember(toolStripPanel);

		//主容器
		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		//订单容器
		SectionStackSection siStackSection = new SectionStackSection("库存安全策略");
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setLayoutMargin(5);
		vmain.setMembersMargin(5);
		vmain.addMember(listGrid);//添加Grid列表
		vmain.addMember(getPartTechnicalOrderDetailView());//单价订单明细Form
		siStackSection.addItem(vmain);

		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);
		v.addMember(sStack);
		return v;
	}

	/**
	 * 构建明细容器
	 * @return
	 */
	private Canvas getPartTechnicalOrderDetailView() {
		CustomPanel cp = new CustomPanel();
		cp.setTitleLabelContents("安全策略详细内容");
		cp.getContentCanvas().addChild(getDetailForm());
		return cp;
	}

	/**
	 * 构建订单Form
	 * @return
	 */
	private DynamicForm getDetailForm() {
		LayoutDynamicForm ldf = new LayoutDynamicForm();
		ldf.setTitleAlign(Alignment.LEFT);
		ldf.setTitleWidth(120);
		ldf.setCellPadding(5);
		ldf.setNumCols(6);
		ldf.setWidth100();
		StaticTextItem item1 = new StaticTextItem();
		item1.setName("item1");
		item1.setTitle("备件描述");
		item1.setValue("嘿嘿嘿嘿嘿嘿 ");

		StaticTextItem item2 = new StaticTextItem();
		item2.setName("item2");
		item2.setColSpan(3);
		item2.setTitle("库房位置");
		item2.setValue("哈哈哈哈哈哈哈哈哈 ");

		StaticTextItem item3 = new StaticTextItem();
		item3.setName("item3");
		item3.setTitle("安全库存量");

		StaticTextItem item4 = new StaticTextItem();
		item4.setName("item4");
		item4.setColSpan(3);
		item4.setTitle("发货数量基准日期");
		item4.setTitleVAlign(VerticalAlignment.TOP);
		item4.setHeight("100%");
		item4.setValue("2011/05/10");

		StaticTextItem item5 = new StaticTextItem();
		item5.setName("item5");
		item5.setTitle("再订货点");

		StaticTextItem item6 = new StaticTextItem();
		item6.setName("item6");
		item6.setColSpan(3);
		item6.setTitle("最近三个月发货量");

		StaticTextItem item7 = new StaticTextItem();
		item7.setName("item7");
		item7.setTitle("再订货量");

		StaticTextItem item8 = new StaticTextItem();
		item8.setName("item8");
		item8.setColSpan(3);
		item8.setTitle("最近半年发货量");

		StaticTextItem item9 = new StaticTextItem();
		item9.setName("item9");
		item9.setTitle("订货单位");

		StaticTextItem item10 = new StaticTextItem();
		item10.setName("item10");
		item10.setColSpan(3);
		item10.setTitle("年发货量");

		StaticTextItem item11 = new StaticTextItem();
		item11.setName("item11");
		item11.setTitle("制造商代码");

		StaticTextItem item12 = new StaticTextItem();
		item12.setName("item12");
		item12.setColSpan(3);
		item12.setTitle("两年发货量");

		ldf.setFields(item1, item2, item3, item4, item5, item6, item7, item8,
				item9, item10, item11, item12);
		return ldf;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}