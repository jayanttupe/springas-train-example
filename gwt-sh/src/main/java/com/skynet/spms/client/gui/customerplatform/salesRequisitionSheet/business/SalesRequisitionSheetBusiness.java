package com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.model.SalesRequisitionModel;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.data.Criteria;
/**
 * 申请单业务类
 * **/
public class SalesRequisitionSheetBusiness extends BaseBusiness{

	public SalesRequisitionModel model = SalesRequisitionModel.getInstance();

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
		criteria.setAttribute("m_BussinessPublishStatusEntity.m_PublishStatus", "published");
		model.listGrid.fetchData(criteria);
	}

}
