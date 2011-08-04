package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.SuppliersParityWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model.SuppliersParityModel;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
/**
 * 订单明细列表
 * @author Tony FANG
 */
public class ProcurementInquirySheetItemListGrid extends FilterListGrid {


	private static I18n ui = new I18n();

	public ProcurementInquirySheetItemListGrid() {

	}

	public void drawGrid() {
		
		this.setShowRowNumbers(false);
		//项号
		ListGridField field1 = new ListGridField("itemNumber");
		Utils.setMessageFieldWidth(field1);
		
		//件号
		ListGridField field2 = new ListGridField("partNumber");

		//关键字
		ListGridField field3 = new ListGridField("field3");
		field3.setHidden(true);

		//需求日期
		ListGridField field4 = new ListGridField("partRequireDate");
		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		

		//询价数量
		ListGridField field5 = new ListGridField("quantity");
		Utils.formatQuantityField(field5);
		field5.setWidth(150);


		//备注
		ListGridField field10 = new ListGridField("remark");
		
		//比价
		ListGridField field11 = new ListGridField("BJ");
		field11.setWidth(40);
		
		field1.setCanFilter(true);
		field2.setCanFilter(true);
		field3.setCanFilter(true);
		field4.setCanFilter(true);
		field5.setCanFilter(true);
		field10.setCanFilter(true);
		field11.setCanFilter(false);
		
		this.setFields(field1, field2,field3,field4, field5,
				field10,field11);

		
		//绑定假数据

	}
	/**
	 * 渲染Grid列
	 */
	@Override
	protected Canvas createRecordComponent(final ListGridRecord record,
			Integer colNum) {

		String fieldName = this.getFieldName(colNum);
		//比价链接
		if (fieldName.equals("BJ")) {
			DynamicForm form = new DynamicForm();
			form.setAlign(Alignment.CENTER);
			LinkItem item1 = new LinkItem();
			//比价
			item1.setShowTitle(false);
			item1.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					SuppliersParityModel model = SuppliersParityModel.getInstance();
					model.procurementInquiryLGR=record;
					//供应商比价
					SuppliersParityWindow spw = new SuppliersParityWindow(ui.getM().mod_procurementSuppliersParity_name(),true,null,null,null);
					spw.show();
				}
			});
			form.setFields(item1);
			return form;
		}
		return null;
	}
}
