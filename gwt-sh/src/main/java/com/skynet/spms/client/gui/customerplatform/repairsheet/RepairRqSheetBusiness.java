package com.skynet.spms.client.gui.customerplatform.repairsheet;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.smartgwt.client.data.Criteria;

/**
 * 处理送修申请业务
 * 
 * @author tqc
 * 
 */
public class RepairRqSheetBusiness extends BaseBusiness {


	public MainModelLocator model = MainModelLocator.getInstance();

	public RepairRqSheetBusiness() {

	}

	/**
	 * 刷新送修申请表格
	 */
	public void refeshRQGrird() {
		Criteria c = new Criteria();
		c.setAttribute("_key", "reload");
		c.setAttribute("_r", String.valueOf(Math.random()));
		model.repairRequisitionListGrid.fetchData(c);
	}


}
