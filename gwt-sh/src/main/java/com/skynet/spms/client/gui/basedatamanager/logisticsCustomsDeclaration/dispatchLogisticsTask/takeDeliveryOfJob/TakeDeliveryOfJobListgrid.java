package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob;

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
public class TakeDeliveryOfJobListgrid extends ListGrid {

	private Logger log = Logger.getLogger("TakeDeliverOfJobListgrid");

	public void drawTakeDeliveryOfJobListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 取货工作编号
		ListGridField takeDeliveryNumberFiled = new ListGridField("takeDeliveryNumber");
		// 物流任务编号
		ListGridField logisticsTasksNumberFiled = new ListGridField("logisticsTasksNumber");
		// 取货日期
		ListGridField takeDeliveryDateFiled = new ListGridField("takeDeliveryDate");
		takeDeliveryDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		//取货地址
		ListGridField addressOfTakeDeliveryFiled=new ListGridField("addressOfTakeDelivery");
		//联系人
		ListGridField contacterFiled=new ListGridField("contacter");
		//运输班次
		ListGridField numberOfRunsFiled=new ListGridField("numberOfRuns");
		//备注
		ListGridField memoFiled=new ListGridField("memo");
		
		
        
		takeDeliveryNumberFiled.setCanFilter(true);
		logisticsTasksNumberFiled.setCanFilter(true);
		takeDeliveryDateFiled.setCanFilter(true);
		addressOfTakeDeliveryFiled.setCanFilter(true);
		contacterFiled.setCanFilter(true);
		numberOfRunsFiled.setCanFilter(true);
		memoFiled.setCanFilter(true);
		
		
		setFields(
				 takeDeliveryNumberFiled
				,logisticsTasksNumberFiled
				,takeDeliveryDateFiled
				,addressOfTakeDeliveryFiled
				,contacterFiled
				,numberOfRunsFiled
				,memoFiled
				);
		setCellHeight(22);
	}

}
