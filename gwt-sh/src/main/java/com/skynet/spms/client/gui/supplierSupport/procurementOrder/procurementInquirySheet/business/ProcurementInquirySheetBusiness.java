package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.model.ProcurementInquiryModel;
/**
 * 申请单业务类
 * **/
public class ProcurementInquirySheetBusiness extends BaseBusiness {

	public ProcurementInquiryModel model = ProcurementInquiryModel.getInstance();


	/**
	 * 刷新ItemListGrid
	 * 
	 * @param grid
	 */
	public void refeshItemListGrid() {
		super.refeshListGrid(model.itemListGrid);
	}

	/**
	 * 刷新ListGrid
	 * 
	 * @param grid
	 */
	public void refeshListGrid() {
		super.refeshListGrid(model.listGrid);
	}
	
}
