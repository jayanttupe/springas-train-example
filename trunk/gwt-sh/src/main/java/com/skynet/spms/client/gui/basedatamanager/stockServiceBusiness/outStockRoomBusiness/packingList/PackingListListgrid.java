package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.packingList;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PackingListListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PackingListListgrid");

	public void drawPackingListListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);

		// 装箱单编号
		ListGridField packingListNumberFiled = new ListGridField("packingListNumber");
		// 配料单号
		ListGridField pickingListNumberFiled = new ListGridField("pickingListNumber");
		// 指令编号
		ListGridField orderNumberFiled = new ListGridField("orderNumber");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
		// 业务类型
		ListGridField businessTypeFiled = new ListGridField("businessType");
		// 收货单位
		ListGridField companyOfConsigneeFiled = new ListGridField("companyOfConsignee");
		// 优先级
		ListGridField priorityFiled = new ListGridField("priority");
		// 交货日期
		ListGridField deliveryDateFiled = new ListGridField("deliveryDate");
		deliveryDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 箱数
		ListGridField boxQtyFiled = new ListGridField("boxQty");
		// 状态
		ListGridField statusFiled = new ListGridField("status");
		
		packingListNumberFiled.setCanFilter(true);
		pickingListNumberFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		businessTypeFiled.setCanFilter(true);
		companyOfConsigneeFiled.setCanFilter(true);
		priorityFiled.setCanFilter(true);
		deliveryDateFiled.setCanFilter(true);
		boxQtyFiled.setCanFilter(true);
		statusFiled.setCanFilter(true);

		setFields(packingListNumberFiled
				,pickingListNumberFiled
				,orderNumberFiled
				,contractNumberFiled
				,businessTypeFiled
				,companyOfConsigneeFiled
				,priorityFiled
				,deliveryDateFiled
				,boxQtyFiled
				,statusFiled		
				);

		setCellHeight(22);
	}
}