package com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.business;

import java.util.Date;

import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model.MainModelLocator;
import com.skynet.spms.client.gui.customerplatform.DoQuotationPanel.model.SalesInquiryModel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.tools.UserTools;
/**
 * 申请单业务类
 * **/
public class SalesInquiryBusiness extends BaseBusiness{

	public SalesInquiryModel model = SalesInquiryModel.getInstance();

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
		Criteria criteria = new Criteria();
		criteria.setAttribute("m_CustomerIdentificationCode.id", UserTools.getUserVo().getCustomerVo().getId());
		model.listGrid.fetchData(criteria);
	}

}
