package com.skynet.spms.client.gui.supplierSupport.procurementOrder.pickUpOrder;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 指令单
 * @author Tony FANG
 *
 */
public class PickUpOrderPanel extends ShowcasePanel {
	private static final String DESCRIPTION = "提货指令单";

	private PickUpOrderToolStrip toolStripPanel;
	private PickUpOrderListGrid listGrid;
	private PickUpOrderItemListGrid itemListGrid;

	public String category;//类别
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "提货指令单";
		private String id;

		public Canvas create() {
			PickUpOrderPanel panel = new PickUpOrderPanel();
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
		listGrid = new PickUpOrderListGrid();

		listGrid.drawGrid();
		//订单操作ToolScript
		toolStripPanel = new PickUpOrderToolStrip(listGrid);

		//订单明细列表
		itemListGrid = new PickUpOrderItemListGrid();
		itemListGrid.drawGrid();

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
		SectionStackSection siStackSection = new SectionStackSection("提货指令");
		siStackSection.addItem(listGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		//订单明细容器
		SectionStackSection siItemStackSection = new SectionStackSection(
				"提货指令项");
		siItemStackSection.setItems(itemListGrid);

		siItemStackSection.setExpanded(true);
		sStack.addSection(siItemStackSection);

		v.addMember(sStack);
		return v;
	}

	public String getIntro() {
		return DESCRIPTION;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}