/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class OutStockRoomRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("OutStockRoomRecordListgrid");

	public void drawOutStockRoomRecordListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);

		// 装箱单编号
		ListGridField packingListNumberFiled = new ListGridField("packingListNumber");
		// 业务类型
		ListGridField businessTypeFiled = new ListGridField("businessType");
		// 配料单ID
		ListGridField pickingListIDFiled = new ListGridField("pickingListID");
		pickingListIDFiled.setHidden(true);
		// 配料单号
		ListGridField pickingListNumberFiled = new ListGridField("pickingListNumber");
		// 指令编号
		ListGridField orderNumberFiled = new ListGridField("orderNumber");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
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
		// 检验人
		ListGridField identifierFiled = new ListGridField("identifier");

		packingListNumberFiled.setCanFilter(true);
		businessTypeFiled.setCanFilter(true);
		companyOfConsigneeFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		pickingListIDFiled.setCanFilter(true);
		pickingListNumberFiled.setCanFilter(true);
		priorityFiled.setCanFilter(true);
		deliveryDateFiled.setCanFilter(true);
		identifierFiled.setCanFilter(true);
		boxQtyFiled.setCanFilter(true);
		statusFiled.setCanFilter(true);

		setFields(packingListNumberFiled
				,businessTypeFiled
				,companyOfConsigneeFiled
				,contractNumberFiled
				,orderNumberFiled
				,pickingListIDFiled
				,pickingListNumberFiled	
				,priorityFiled
				,deliveryDateFiled
				,identifierFiled
				,boxQtyFiled
				,statusFiled			
				);

		setCellHeight(22);
	}

}
