package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 主订单列表
 * @author Tony FANG
 *
 */
public class ProcurementInquirySheetListGrid extends FilterListGrid {

	public ProcurementInquirySheetListGrid() {

	}

	public void drawGrid() {
		this.setShowRowNumbers(false);
//		ListGridField field1 = new ListGridField("field1", "留言");
//		Utils.setMessageFieldWidth(field1);
//		field1.setHidden(true);
		
		//询价单编号
		ListGridField field2 = new ListGridField("inquirySheetNumber");

		//优先级
		ListGridField field3 = new ListGridField("m_Priority");

		//需求日期 
//		ListGridField field4 = new ListGridField("partRequireDate");
//		field4.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		
		//创建日期
		ListGridField field5 = new ListGridField("createDate");
		field5.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		//发布人
		ListGridField field6 = new ListGridField("createBy");

		//采购指令编号
		ListGridField field7 = new ListGridField("orderNumber");

		//备注
		ListGridField field8 = new ListGridField("remark");

		//已询价供应商
		ListGridField field9 = new ListGridField("inquirySuppliersCount");
		field9.setWidth(110);
		
		
		//已报价供应商
		ListGridField field10 = new ListGridField("quotationSuppliersCount");
		field10.setWidth(110);
		
		//发布状态
		ListGridField field11 = new ListGridField("m_BussinessPublishStatusEntity.m_PublishStatus");
		
		//业务状态
		ListGridField field12 = new ListGridField("m_BussinessStatusEntity.status");
//		
//		field1.setCanFilter(true);
		field2.setCanFilter(true);
		field3.setCanFilter(true);
		field5.setCanFilter(true);
		field6.setCanFilter(true);
		field7.setCanFilter(true);
		field8.setCanFilter(true);
		field9.setCanFilter(true);
		field10.setCanFilter(true);
		field11.setCanFilter(true);

		setFields( field2, field3, field5, field6, field7,
				field8, field9, field10,field11,field12);
		setCellHeight(22);

		
	}

	/**
	 * 渲染Grid列
	 */
	@Override
	protected Canvas createRecordComponent(final ListGridRecord record,
			Integer colNum) {

		String fieldName = this.getFieldName(colNum);
		//采购指令编号 链接
//		if (fieldName.equals("CGZLBH")) {
//			String orderNumber=record.getAttribute("orderNumber");
//			if(!orderNumber.isEmpty()){
//				final LayoutDynamicForm form = new LayoutDynamicForm();
//				form.setCellPadding(0);
//				form.setAlign(Alignment.CENTER);
//
//				LinkItem item1 = new LinkItem();
//				item1.setShowTitle(false);
//				item1.setLinkTitle(orderNumber);
//				item1.addClickHandler(new ClickHandler() {
//
//					public void onClick(ClickEvent event) {
////						ProcurementOrderView win = new ProcurementOrderView(
////								"采购指令详细信息", true, null, null, null);
////						ShowWindowTools.showWindow(form.getPageRect(), win, 200);
//
//					}
//
//				});
//
//				form.setFields(item1);
//				return form;
//			}
//		
//		}
		//已询价供应商 链接
//		if (fieldName.equals("YXJGYS")) {
//			String inquirySuppliersCount=record.getAttribute("inquirySuppliersCount");
//			if(!inquirySuppliersCount.isEmpty()){
//				final LayoutDynamicForm form = new LayoutDynamicForm();
//				form.setCellPadding(0);
//				form.setAlign(Alignment.CENTER);
//
//				LinkItem item1 = new LinkItem();
//				item1.setShowTitle(false);
//				item1.setLinkTitle(inquirySuppliersCount);
//				item1.addClickHandler(new ClickHandler() {

//					public void onClick(ClickEvent event) {
//						SuppliersParityShowWindow win = new SuppliersParityShowWindow(
//								"查看询价单", true, null, null, null);
//						ShowWindowTools.showWindow(form.getPageRect(), win, 200);
//					}

//				});
//
//				form.setFields(item1);
//				return form;
//			}
//		}
		//已报价供应商 链接
//		if (fieldName.equals("YBJGYS")) {
//			String quotationSuppliersCount=record.getAttribute("quotationSuppliersCount");
//			if(!quotationSuppliersCount.isEmpty()){
//				LayoutDynamicForm form = new LayoutDynamicForm();
//				form.setCellPadding(0);
//				form.setAlign(Alignment.CENTER);
//				
//				LinkItem item1 = new LinkItem();
//				item1.setShowTitle(false);
//				item1.setLinkTitle(quotationSuppliersCount);
//				item1.addClickHandler(new ClickHandler() {
//
//					public void onClick(ClickEvent event) {
////						SuppliersParityShowWindow win = new SuppliersParityShowWindow(
////								"查看询价单", true, null, null, null);
////						ShowWindowTools.showWindow(form.getPageRect(), win, 200);
//					}
//
//				});
//
//				form.setFields(item1);
//				return form;
//			}
//			
//		}
		return null;
	}

}
