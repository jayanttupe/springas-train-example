package com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.outStockApprovalRecord;
import java.util.Date;

import com.skynet.spms.persistence.entity.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.base.baseApprovalRecord.BaseApprovalRecord;

/**
 * @author skynet189
 * @version 1.0
 * @created 15-三月-2011 12:33:14
 */
public class OutStockApprovalRecord extends BaseApprovalRecord {

	private String customsNumber;
	private String outStockApprovalNumber;
	private Date outStockDate;
	public OutStockApprovalRecordItems m_OutStockApprovalRecordItems;

	public OutStockApprovalRecord(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}