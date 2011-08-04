package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.data.Criteria;

/**
 * 处理送修申请业务
 * 
 * @author tqc
 * 
 */
public class ExchangeRequisitionSheetBusiness extends BaseBusiness {

	private I18n ui = new I18n();

	public SheetModelLocator model = SheetModelLocator.getInstance();

	public ExchangeRequisitionSheetBusiness() {

	}

	/**
	 * 刷新送修申请表格
	 */
	public void refeshRQGrird() {
		Criteria c = new Criteria();
		c.setAttribute("_r", String.valueOf(Math.random()));
		model.repairRequisitionListGrid.fetchData(c);
	}


	/***
	 * 关闭窗体
	 */
	public void closeWindow(){
		model.parentWindow.destroy();
	}

}
