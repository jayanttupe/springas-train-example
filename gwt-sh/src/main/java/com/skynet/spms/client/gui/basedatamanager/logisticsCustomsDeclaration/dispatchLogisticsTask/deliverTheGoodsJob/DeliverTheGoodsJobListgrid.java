package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class DeliverTheGoodsJobListgrid extends ListGrid {

	private Logger log = Logger.getLogger("DeliverTheGoodsJobListgrid");

	public void drawDeliverTheGoodsJobListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 交货工作编号
		ListGridField deliverTheGoodsNumberFiled = new ListGridField("deliverTheGoodsNumber");
		// 物流任务编号
		ListGridField logisticsTasksNumberFiled = new ListGridField("logisticsTasksNumber");
		// 交货日期
		ListGridField deliverTheGoodsDateDateFiled = new ListGridField("deliverTheGoodsDate");
		deliverTheGoodsDateDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 交货详细地址
		ListGridField addressOfDeliverTheGoodsFiled=new ListGridField("addressOfDeliverTheGoods");
		// 运输班次
		ListGridField numberOfRunsFiled=new ListGridField("numberOfRuns");
		// 联系人
		ListGridField linkManFiled=new ListGridField("contacter");
		// 交货状态
		ListGridField deliverStateFiled=new ListGridField("status");
        
		deliverTheGoodsNumberFiled.setCanFilter(true);
		logisticsTasksNumberFiled.setCanFilter(true);
		deliverTheGoodsDateDateFiled.setCanFilter(true);
		addressOfDeliverTheGoodsFiled.setCanFilter(true);
		numberOfRunsFiled.setCanFilter(true);
		linkManFiled.setCanFilter(true);
		deliverStateFiled.setCanFilter(true);
		
		setFields(deliverTheGoodsNumberFiled
				,logisticsTasksNumberFiled
				,deliverTheGoodsDateDateFiled
				,addressOfDeliverTheGoodsFiled
				,numberOfRunsFiled
				,linkManFiled
				,deliverStateFiled
				);
		setCellHeight(22);
	}

}
