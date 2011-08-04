package com.skynet.spms.client.gui.supplierSupport.procurementOrder.deliveryOrder;

import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.view.SalesContractViewWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 主订单列表
 * @author Tony FANG
 *
 */
public class DeliveryOrderListGrid extends FilterListGrid {

	public DeliveryOrderListGrid() {

	}

	public void drawGrid() {
		//自动抓取数据的关键
		this.setAutoFetchData(true);

		this.setSelectionType(SelectionStyle.SIMPLE);
		this.setSelectionAppearance(SelectionAppearance.CHECKBOX);

		this.setWidth100();
		this.setMargin(5);

		//启动可渲染
		this.setShowRecordComponents(true);
		this.setShowRecordComponentsByCell(true);

		//设置显示筛选栏
		this.setShowFilterEditor(true);

		this.setCellHeight(22);

		ListGridField field1 = new ListGridField("field1", "留言");

		ListGridField field2 = new ListGridField("field2", "发货指令编号");

		ListGridField field3 = new ListGridField("field3", "优先级");

		ListGridField field4 = new ListGridField("field4", "指令类型");

		ListGridField field5 = new ListGridField("HTBH", "依据合同编号");

		ListGridField field6 = new ListGridField("field6", "发货单位");

		ListGridField field7 = new ListGridField("field7", "收货单位");

		ListGridField field8 = new ListGridField("field8", "发货日期");

		ListGridField field9 = new ListGridField("field9", "发布状态");
		field9.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String defortValue = "<font color='red'>未发布</font>";
				if (value == null) {
					return defortValue;
				} else {
					try {
						if (value.equals("didNotOffer")) {
							return defortValue;
						} else if (value.equals("alreadyOffer")) {
							return "已发布";
						}
						return defortValue;
					} catch (Exception e) {
						return value.toString();
					}
				}
			}
		});

		ListGridField field10 = new ListGridField("field10", "发布时间");
		ListGridField field11 = new ListGridField("field11", "业务状态");
		field11.setCellFormatter(new CellFormatter() {
			public String format(Object value,
					com.smartgwt.client.widgets.grid.ListGridRecord record,
					int rowNum, int colNum) {
				String defortValue = "<font color='red'>已新建</font>";
				if (value == null) {
					return defortValue;
				} else {
					try {
						if (value.equals("didNotOffer")) {
							return defortValue;
						} else if (value.equals("alreadyOffer")) {
							return "已确认";
						} else if (value.equals("partsOffer")) {
							return "已分派";
						} else if (value.equals("partsOffer")) {
							return "已处理";
						} else if (value.equals("partsOffer")) {
							return "已关闭";
						} else if (value.equals("partsOffer")) {
							return "已打回";
						}
						return defortValue;
					} catch (Exception e) {
						return value.toString();
					}
				}
			}
		});
		setFields(field1, field2, field3, field4, field5, field6, field7,
				field8, field9, field10, field11);

		//绑定假数据
		this.setDataSource(Utils.getXmlDataSource());

	}

	/**
	 * 渲染Grid列
	 */
	@Override
	protected Canvas createRecordComponent(final ListGridRecord record,
			Integer colNum) {

		String fieldName = this.getFieldName(colNum);
		//合同编号
		if (fieldName.equals("HTBH")) {
			final LayoutDynamicForm form = new LayoutDynamicForm();
			form.setCellPadding(0);
			form.setAlign(Alignment.CENTER);

			LinkItem item1 = new LinkItem();
			item1.setShowTitle(false);
			item1.setLinkTitle(record.getAttribute("field5"));
			item1.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					//暂时先放销售合同，之后会根据类型判断，打开不同合同详细面板
					SalesContractViewWin view = new SalesContractViewWin("合同详细信息",
							true, null, null, null);
					ShowWindowTools.showWindow(form.getPageRect(), view, 200);
				}

			});

			form.setFields(item1);
			return form;
		}
		return null;
	}

}
