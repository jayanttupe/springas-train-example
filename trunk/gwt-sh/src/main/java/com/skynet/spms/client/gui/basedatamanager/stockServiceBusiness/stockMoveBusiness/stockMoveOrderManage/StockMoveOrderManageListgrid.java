/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveOrderManage;

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
public class StockMoveOrderManageListgrid extends ListGrid{
	
	private Logger log = Logger.getLogger("StockMoveOrderListgrid");
	
	public void drawStockMoveOrderManageListgrid(){               
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
	
		setSelectionType(SelectionStyle.SINGLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);
	
		// 任务创建人
		ListGridField taskByField = new ListGridField("taskBy");	
		taskByField.setCanEdit(false);
		// 任务号
		ListGridField taaskField = new ListGridField("taskNo");
		taaskField.setCanEdit(false);
		// 任务日期
		ListGridField tastDateField = new ListGridField("taskDate");
		tastDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		tastDateField.setCanEdit(false);
		// 任务类型
		ListGridField tasktypeField = new ListGridField("taskType");
		
		taskByField.setCanFilter(true);
		taaskField.setCanFilter(true);
		tastDateField.setCanFilter(true);
		tasktypeField.setCanFilter(true);
		
		setFields(taskByField
				,taaskField
				,tastDateField
				,tasktypeField
		);
		setCellHeight(22);
	}
}
