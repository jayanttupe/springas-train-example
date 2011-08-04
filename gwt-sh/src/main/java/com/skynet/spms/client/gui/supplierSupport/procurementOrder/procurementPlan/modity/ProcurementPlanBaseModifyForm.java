package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.modity;

import com.google.gwt.user.client.Timer;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model.MainModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProcurementPlanBaseModifyForm extends VLayout {
	private static I18n ui = new I18n();
	private MainModelLocator model = MainModelLocator.getInstance();
	private static final String uploadUrl = "spms/upload.do";

	public ProcurementPlanBaseModifyForm(final ListGrid lg) {
		this.setTitle(ui.getM().mod_procurementPlan_name());
		/** 主容器 **/
		VLayout v = new VLayout();
		HLayout h1 = new HLayout();

		/** 表单内容 **/
		final LayoutDynamicForm form = new LayoutDynamicForm();

		form.setNumCols(6);
		form.setCellPadding(2);
		form.setWidth100();
		form.setDataSource(model.procurementPlanListGrid.getDataSource());
		form.reset();
		form.editSelectedData(model.procurementPlanListGrid);
		// 采购计划编号
		TextItem item1 = new TextItem();
		item1.setName("procurementPlanNumber");
		item1.setDisabled(true);
		// 采购计划类型
		SelectItem item2 = new SelectItem();
		item2.setName("m_ProcurementPlanType");
		// 计划用途
		TextAreaItem item3 = new TextAreaItem();
		item3.setName("purposes");
		item3.setHeight("100%");
		item3.setRowSpan(2);
		// 计划开始日期
		DateItem item4 = new DateItem();
		item4.setName("startDate");
		item4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item4.setUseTextField(true);
		// 计划结束日期
		DateItem item5 = new DateItem();
		item5.setName("endDate");
		item5.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		item5.setUseTextField(true);
		// 计划描述
		TextAreaItem item6 = new TextAreaItem();
		item6.setName("description");
		item6.setTitleVAlign(VerticalAlignment.TOP);
		item6.setHeight(60);
		item6.setColSpan(3);
		// 备注
		TextAreaItem item7 = new TextAreaItem();
		item7.setName("remarkText");
		item7.setTitleVAlign(VerticalAlignment.TOP);
		item7.setHeight(60);
		// 总项数
		SpinnerItem item8 = new SpinnerItem();
		item8.setName("itemCount");
		item8.setColSpan(3);
		item8.setAlign(Alignment.RIGHT);
		item8.setDisabled(true);
		// 金额总计
		SpinnerItem item9 = new SpinnerItem();
		item9.setName("totalAmount");
		item9.setDisabled(true);

		form.setFields(item1, item2, item3, item4, item5, item6, item7, item8,
				item9);

		Timer timer = Utils.startAmountTimer(lg, item8, item9,
				"unitPriceAmount");
		timer.scheduleRepeating(500);

		h1.addMember(form);

		/** 操作按钮容器 **/
		BtnsHLayout btnGroup = new BtnsHLayout();

		IButton btn1 = new IButton(ui.getB().btnSaveAdd());
		btn1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.saveData(new DSCallback() {
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						model.Pid = response.getData()[0].getAttribute("id");
						SC.say(ui.getB().msgAddOpSuccess());
						model.defaultUploader.setVisible(true);
						model.defaultUploader.setServletPath(uploadUrl
								+ "?uuid=" + model.Pid);
					}
				});
			}
		});

		btnGroup.setMembers(btn1);
		v.setMembers(h1, btnGroup);

		this.addMember(v);
	}
}
