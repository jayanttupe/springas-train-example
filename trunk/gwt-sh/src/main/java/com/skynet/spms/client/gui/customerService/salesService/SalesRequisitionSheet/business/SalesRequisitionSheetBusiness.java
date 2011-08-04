package com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model.MainModelLocator;
import com.smartgwt.client.data.Criteria;
/**
 * 申请单业务类
 * **/
public class SalesRequisitionSheetBusiness extends BaseBusiness{

	public MainModelLocator model = MainModelLocator.getInstance();

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
	/**
	 * 刷新ListGrid
	 * @param grid
	 */
	public void refeshListGrid() {
		Criteria criteria = new Criteria();
		criteria.setAttribute("m_BussinessPublishStatusEntity.m_PublishStatus", "published");
		criteria.setAttribute("_r", String.valueOf(Math.random()));
		model.listGrid.fetchData(criteria);
	}

}
