package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder;
//箱号
import java.util.logging.Logger;

import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PickupDeliveryVanningListgrid extends ListGrid {

	private Logger log = Logger.getLogger("PickupDeliveryVanningListgrid");

	public void drawPickupDeliveryVanningListgrid()
	{
		setCanEdit(false);
		setShowFilterEditor(false);
		setEditEvent(ListGridEditEvent.CLICK);
		setSelectionType(SelectionStyle.SINGLE);
		setShowRowNumbers(true);

		// 装箱单号
		ListGridField packingListNumberFiled = new ListGridField("packingListNumber", "装箱单号");
		// 箱号
		ListGridField pacakgeNumberFiled = new ListGridField("pacakgeNumber", "箱号");
		// 货物重量(千克)
		ListGridField billOfLadingWeightFiled = new ListGridField("billOfLadingWeight", "货物重量(千克)");
		// 包装尺寸(体积)
		ListGridField containerSizeandWeightFiled = new ListGridField("containerSizeandWeight", "包装尺寸(体积)");
		// 危险品
		ListGridField dangerousGoodsFiled = new ListGridField("dangerousGoods", "危险品");
		// 描述
		ListGridField descriptionFiled = new ListGridField("description", "描述");
		// 货物性质
		ListGridField goodsNatureFiled = new ListGridField("goodsNature", "货物性质");
		// 特殊需求
		ListGridField specialRequirementsFiled = new ListGridField("specialRequirements", "特殊需求");

		setFields(packingListNumberFiled
				,pacakgeNumberFiled
				,billOfLadingWeightFiled
				,containerSizeandWeightFiled
				,dangerousGoodsFiled
				,descriptionFiled
				,goodsNatureFiled
				,specialRequirementsFiled
				);
		
		setCellHeight(22);
	}
}
