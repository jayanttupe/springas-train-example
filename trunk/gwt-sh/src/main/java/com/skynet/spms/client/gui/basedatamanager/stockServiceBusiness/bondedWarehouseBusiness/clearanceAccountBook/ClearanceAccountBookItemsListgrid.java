/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook;


import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class ClearanceAccountBookItemsListgrid extends ListGrid{

	private Logger log = Logger.getLogger("ClearanceAccountBookItemsListgrid");
	
	public void drawClearanceAccountBookItemsListgrid(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setCanEdit(false);
		setSelectionType(SelectionStyle.SIMPLE);
		setShowRowNumbers(true);

		// ID
		ListGridField clearanceAccountBookID = new ListGridField("clearanceAccountBookID");
		clearanceAccountBookID.setHidden(true);
		// 商品编码
		ListGridField hsCodeField = new ListGridField("hsCode");
		// 关键字
		ListGridField keywordField = new ListGridField("keyword");
		// 备案计量单位
		ListGridField registerUnitMeasureCodeFiled = new ListGridField("registerUnitMeasureCode");
		// 备案日期
		ListGridField registerDateField = new ListGridField("registerDate");
		registerDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 描述
		ListGridField describeField = new ListGridField("describe");
		
		setFields(clearanceAccountBookID
				,hsCodeField
				,keywordField
				,registerUnitMeasureCodeFiled
				,registerDateField
				,describeField
				);
		setCellHeight(22);
	}
}
