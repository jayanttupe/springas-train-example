package com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.tabs;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model.SuppliersParityModel;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 合同基本信息
 * 
 * @author tqc
 * 
 */
public class SuppliersParityBaseForm extends VLayout {

	private LayoutDynamicForm form = new LayoutDynamicForm();
	SuppliersParityModel model=SuppliersParityModel.getInstance();
	public SuppliersParityBaseForm() {
		
		// form.setDataSource(ds);
		// form.editSelectedData(repairRequisitionListGrid);
		form.setNumCols(4);
		form.setCellPadding(2);
		
		build();
	}

	private void build() {
	
		// 供应商代码
		TextItem item1 = new TextItem();
		item1.setName("procurementQuotationSheetRecord.supplierCode.code");
		item1.setTitle("供应商代码");
		item1.setDisabled(true);
		
		// 供应商名称
		TextItem item2 = new TextItem();
		item2.setName("procurementQuotationSheetRecord.supplierCode.code");
		item2.setDisabled(true);
		item2.setTitle("供应商名称");

		// 报价件号
		SelectItem item3 = Utils.getPartNumberList();
		item3.setTitle("报价件号");
		item3.setName("quotationPartNumber");
		item3.setDisabled(true);

		// 交货期(天)
		TextItem item4 = new TextItem();
		item4.setTitle("交货期(天)");
		item4.setName("deliveryLeadTime");
		item4.setDisabled(true);


		// 互换性
		SelectItem item5 = new SelectItem();
		item5.setName("item5");
		item5.setTitle("互换性");
		item5.setVisible(false);
		

		// 报价数量
		TextItem item6 = new TextItem();
		item6.setName("quantity");
		item6.setTitle("报价数量");
		item6.setDisabled(true);

		// 特殊包装说明
		TextItem item7 = new TextItem();
		item7.setName("packageDescription");
		item7.setTitle("特殊包装说明");
		item7.setDisabled(true);
		

		// 单位
		SelectItem item8 = new SelectItem();
		item8.setName("m_UnitOfMeasureCode");
		item8.setTitle("单位");
		item8.setDisabled(true);

		//特殊包装费用
		SelectItem item9 = new SelectItem();
		item9.setName("packagePrice");
		item9.setTitle("特殊包装费用");
		item9.setDisabled(true);

		// 标准包装数量
		TextItem item10 = new TextItem();
		item10.setName("standardPackageQuantity");
		item10.setTitle("标准包装数量");
		item10.setDisabled(true);

		// 币种
		SelectItem item11 = new SelectItem();
		item11.setName("m_InternationalCurrencyCode");
		item11.setTitle("币种");
		item11.setDisabled(true);

		// 最小销售数量
		TextItem item12 = new TextItem();
		item12.setName("minimumSalesQuantity");
		item12.setTitle("最小销售数量");
		item12.setDisabled(true);

		// 报价单价
		TextItem item13 = new TextItem();
		item13.setName("unitPriceAmount");
		item13.setTitle("报价单价");
		item13.setDisabled(true);
		
		//报价总价
		TextItem item17 = new TextItem();
		item17.setName("amount");
		item17.setTitle("报价金额");
		item17.setDisabled(true);

		// 付款要求
		TextAreaItem item14 = new TextAreaItem();
		item14.setName("paymentRequirement");
		item14.setTitle("付款要求");
		item14.setDisabled(true);
		item14.setRowSpan(2);
		item14.setHeight("100%");

		// 分段报价
		CheckboxItem item15 = new CheckboxItem();
		item15.setAlign(Alignment.RIGHT);
		item15.setName("isBreakPrice");
		item15.setTitle("分段报价");
		FormItemIcon fii15 =new FormItemIcon();
		fii15.setPrompt("分段报价");
		item15.setIcons(fii15);
		item15.setDisabled(true);
		
		// 备注
		TextAreaItem item16 = new TextAreaItem();
		item16.setTitleVAlign(VerticalAlignment.TOP);
		item16.setName("remark");
		item16.setTitle("备注");
		item16.setHeight("50");
		item16.setDisabled(true);

		form.setFields(item1, item2, item3, item4, item5, item6, item7, item8,
				item9, item10, item11, item12, item13, item14,item17, item15, item16);
		this.addMember(form);
		model.procurementQuotationForm=form;

	}
}
