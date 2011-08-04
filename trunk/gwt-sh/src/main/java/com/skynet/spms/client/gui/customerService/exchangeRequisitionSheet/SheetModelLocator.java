package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Window;
import com.skynet.spms.client.model.IModelLocator;

/**
 * 存放业务数据
 * 
 * @author tqc
 * 
 */
public class SheetModelLocator implements IModelLocator<SheetModelLocator> {
	
	private static SheetModelLocator instance;

	private SheetModelLocator() {
	}

	public static SheetModelLocator getInstance() {
		if (instance == null) {
			instance = new SheetModelLocator();
		}
		return instance;
	}

	/** 送修申请单数据表格 **/
	public ExchangeRequisitionSheetListGrid repairRequisitionListGrid;

	public DataSource dataSource;
	public DataSource itemDataSource;
	/**申请单编号**/
	public String repairSheetId;
	
	/**申请单明细编号**/
	public String repairSheetItemId;
	
	
	public Window parentWindow;

}
