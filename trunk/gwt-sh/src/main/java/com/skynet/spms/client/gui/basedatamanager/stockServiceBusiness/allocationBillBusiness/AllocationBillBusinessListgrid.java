package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.allocationBillBusiness;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AllocationBillBusinessListgrid extends ListGrid {

	private Logger log = Logger.getLogger("AllocationBillBusinessListgrid");

	public void drawAllocationBillBusinessListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 调拨单编号 
		ListGridField allocationBillNumberFiled = new ListGridField("allocationBillNumber");
		// 调拨日期
		ListGridField businessDateFiled = new ListGridField("businessDate");
		businessDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 业务类型
		ListGridField businessTypeFiled = new ListGridField("businessType");
		// 审核日期
		ListGridField checkDateFiled = new ListGridField("checkDate");
		// 审核人
		ListGridField checkUserFiled = new ListGridField("checkUser");
		// 制单日期
		ListGridField createByDateFiled = new ListGridField("createByDate");
		createByDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 制单人
		ListGridField createUserFiled = new ListGridField("createUser");
		// 收货库房
		ListGridField inWareHouseFiled = new ListGridField("inWareHouseName");
		// 发货库房
		ListGridField outWareHouseFiled = new ListGridField("outWareHouseName");
		// 源单据类型
		ListGridField sourceBillTypeFiled = new ListGridField("sourceBillType");
		// 状态
		ListGridField stateFiled = new ListGridField("state");
		// 备注
		ListGridField remarkFiled = new ListGridField("remark");

		allocationBillNumberFiled.setCanFilter(true);
		businessDateFiled.setCanFilter(true);
		businessTypeFiled.setCanFilter(true);
		checkDateFiled.setCanFilter(true);
		checkUserFiled.setCanFilter(true);
		createByDateFiled.setCanFilter(true);
		createUserFiled.setCanFilter(true);
		inWareHouseFiled.setCanFilter(true);
		outWareHouseFiled.setCanFilter(true);
		sourceBillTypeFiled.setCanFilter(true);
		stateFiled.setCanFilter(true);
		remarkFiled.setCanFilter(true);
		
		setFields(allocationBillNumberFiled
				,businessDateFiled
				,businessTypeFiled
				,checkDateFiled
				,checkUserFiled
				,createByDateFiled
				,createUserFiled
				,inWareHouseFiled
				,outWareHouseFiled
				,sourceBillTypeFiled
				,stateFiled
				,remarkFiled
				);
		setCellHeight(22);
	}
}