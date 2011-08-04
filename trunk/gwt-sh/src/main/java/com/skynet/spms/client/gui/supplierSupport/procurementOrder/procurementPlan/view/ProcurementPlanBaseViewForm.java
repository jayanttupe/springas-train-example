package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.model.MainModelLocator;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpinnerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProcurementPlanBaseViewForm extends VLayout {
	private static I18n ui = new I18n();
	private MainModelLocator model = MainModelLocator.getInstance();
	private static final String uploadUrl = "spms/upload.do";

	public ProcurementPlanBaseViewForm() {
		this.setTitle(ui.getM().mod_procurementPlan_name());
		/** 主容器 **/
		VLayout v = new VLayout();
		HLayout h1 = new HLayout();

		/** 表单内容 **/
		final LayoutDynamicForm form = new LayoutDynamicForm();

		form.setNumCols(6);
		form.setCellPadding(2);
		form.setWidth100();

		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTPLAN,
				DSKey.S_PROCUREMENTPLAN_DS, new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						form.setDataSource(dataSource);
						// 采购计划编号
						TextItem item1 = new TextItem();
						item1.setName("procurementPlanNumber");
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
						item4
								.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
						item4.setUseTextField(true);
						// 计划结束日期
						DateItem item5 = new DateItem();
						item5.setName("endDate");
						item5
								.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
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
						// 金额总计
						SpinnerItem item9 = new SpinnerItem();
						item9.setName("totalAmount");

						form.setFields(item1, item2, item3, item4, item5,
								item6, item7, item8, item9);
						Utils.setReadOnlyForm(form);
						StringBuilder builder = new StringBuilder();
						builder.append(model.Pid);
						Criteria criteria = new Criteria();
						criteria.addCriteria("id", builder.toString());
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						form.fetchData(criteria);
					}
				});

		h1.addMember(form);
		v.addMember(h1);
		this.addMember(v);
	}
}
