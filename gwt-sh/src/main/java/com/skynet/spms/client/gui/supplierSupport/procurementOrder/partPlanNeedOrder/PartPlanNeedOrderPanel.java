package com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.model.MainModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 备件计划需求
 * 
 * @author Tony FANG
 * 
 */
public class PartPlanNeedOrderPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private static final String DESCRIPTION = ui.getM()
			.mod_partPlanNeedOrder_name();
	private MainModelLocator model = MainModelLocator.getInstance();
	private PartPlanNeedOrderToolStrip toolStripPanel;
	private PartPlanNeedOrderListGrid listGrid;
	private LayoutDynamicForm detailForm = new LayoutDynamicForm();

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_partPlanNeedOrder_name();
		private String id;

		public Canvas create() {
			PartPlanNeedOrderPanel panel = new PartPlanNeedOrderPanel();
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
		// 订单列表
		listGrid = new PartPlanNeedOrderListGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PARTREQUIREMENT,
				DSKey.S_PARTREQUIREMENT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();
						listGrid.drawGrid();
						model.partPlanNeedOrderListGrid = listGrid;
					}
				});
		// 初始化附件数据源
		dataSourceTool.onCreateDataSource(DSKey.S_PARTREQUIREMENT,
				DSKey.S_PARTREQUIREMENT_ATTACHMENT_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.attachmentDs = dataSource;
					}
				});

		// 订单操作ToolScript
		toolStripPanel = new PartPlanNeedOrderToolStrip(listGrid);
		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setWidth100();
		v.setOverflow(Overflow.AUTO);
		v.addMember(toolStripPanel);

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);

		// 订单容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_partPlanNeedOrder_name());
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		vmain.setLayoutMargin(5);
		vmain.setMembersMargin(5);
		vmain.addMember(listGrid);// 添加Grid列表
		vmain.addMember(getPartTechnicalOrderDetailView());// 单价订单明细Form

		VLayout v1 = new VLayout();
		v1.setWidth100();
		v1.setHeight(150);
		v1.setLayoutMargin(3);
		// 添加订单附件列表
		final PartPlanNeedAttachmentView partPlanNeedAttachmentView = new PartPlanNeedAttachmentView();
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				final String PartPlanId = listGrid.getSelectedRecord()
						.getAttribute("id");
				dataSourceTool.onCreateDataSource(DSKey.S_PARTREQUIREMENT,
						DSKey.S_PARTREQUIREMENT_ATTACHMENT_DS,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								partPlanNeedAttachmentView
										.setDataSource(dataSource);
								Criteria criteria = new Criteria();
								criteria.addCriteria("uuid", PartPlanId);
								criteria.addCriteria("_r", String.valueOf(Math
										.random()));
								partPlanNeedAttachmentView.fetchData(criteria);
								partPlanNeedAttachmentView.drawGrid();
							}
						});
			}
		});
		v1.addMember(partPlanNeedAttachmentView);
		siStackSection.addItem(vmain);
		siStackSection.setItems(vmain, v1);

		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);
		v.addMember(sStack);
		return v;
	}

	/**
	 * 构建明细容器
	 * 
	 * @return
	 */
	Label lbRightTitle;

	private Canvas getPartTechnicalOrderDetailView() {
		/** 主面板 * */
		VLayout vmain = new VLayout();
		vmain.setBorder("1px solid #a6abb4");

		/** 标题面板 * */
		HLayout titleLayout = new HLayout();
		titleLayout.setWidth100();
		titleLayout.setHeight("15");
		Label lbTitle = new Label(ui.getB().details());
		lbTitle.setHeight(15);
		lbTitle.setWidth("10%");
		lbRightTitle = new Label();
		lbRightTitle.setHeight(15);
		lbRightTitle.setAlign(Alignment.RIGHT);
		lbRightTitle.setWidth("90%");

		titleLayout.addMember(lbTitle);
		titleLayout.addMember(lbRightTitle);

		/** 内容面板 * */
		Canvas canvsForm = new Canvas();
		canvsForm.setBorder("1px solid #a6abb4");
		canvsForm.setWidth100();
		detailForm = getDetailForm();
		canvsForm.addChild(detailForm);

		vmain.addMember(titleLayout);
		vmain.addMember(canvsForm);
		return vmain;
	}

	/**
	 * 构建详细信息Form
	 * 
	 * @return
	 */
	SelectItem item0;
	StaticTextItem item1;
	StaticTextItem item2;
	StaticTextItem item3;
	StaticTextItem item4;
	StaticTextItem item5;
	StaticTextItem item6;

	private LayoutDynamicForm getDetailForm() {

		final LayoutDynamicForm detailForm = new LayoutDynamicForm();
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				item0.setValue(model.partPlanNeedOrderListGrid
						.getSelectedRecord().getAttribute("partNumber"));
				item1.setValue(model.partPlanNeedOrderListGrid
						.getSelectedRecord().getAttribute("description"));
				item2.setValue(model.partPlanNeedOrderListGrid
						.getSelectedRecord().getAttribute("remarkText"));
				item3.setValue(model.partPlanNeedOrderListGrid
						.getSelectedRecord()
						.getAttribute("m_UnitOfMeasureCode"));
				item4.setValue(model.partPlanNeedOrderListGrid
						.getSelectedRecord().getAttributeAsDate("createDate"));
				item5.setValue(model.partPlanNeedOrderListGrid
						.getSelectedRecord().getAttribute(
								"m_CustomerIdentificationCode.code"));

				item6.setValue(model.partPlanNeedOrderListGrid
						.getSelectedRecord().getAttribute(
								"m_ManufacturerCode.code"));

				lbRightTitle.setTitle(model.partPlanNeedOrderListGrid
						.getSelectedRecord().getAttribute("lastTime"));
			}
		});
		detailForm.setTitleAlign(Alignment.LEFT);
		detailForm.setNumCols(6);
		detailForm.setWidth100();
		item0 = Utils.getPartNumberList();
		item0.setVisible(true);

		item1 = new StaticTextItem();
		item1.setName("description");
		item1.setTitle(ui.getB().description());

		item2 = new StaticTextItem();
		item2.setName("remarkText");
		item2.setColSpan(3);
		item2.setTitle(ui.getB().remarkText());

		item3 = new StaticTextItem();
		item3.setName("m_UnitOfMeasureCode");
		item3.setTitle(ui.getB().m_UnitOfMeasureCode());

		item4 = new StaticTextItem();
		item4.setName("createDate");
		item4.setColSpan(3);
		item4.setRowSpan(3);
		item4.setTitle(ui.getB().createDate());
		item4.setTitleVAlign(VerticalAlignment.TOP);
		item4.setHeight("100%");
		item4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		item5 = new StaticTextItem();
		item5.setName("m_CustomerIdentificationCode.code");
		item5.setTitle(ui.getB().m_CustomerIdentificationCode());

		item6 = new StaticTextItem();
		item6.setName("m_ManufacturerCode.code");
		item6.setTitle(ui.getB().m_ManufacturerCode());

		detailForm.setFields(item1, item2, item3, item4, item5, item6);
		return detailForm;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}