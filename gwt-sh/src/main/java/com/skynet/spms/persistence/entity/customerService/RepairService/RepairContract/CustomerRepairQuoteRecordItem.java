package com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.baseOutlayItem.BaseOutlayItem;

/**
 * 客户修理报价记录明细项
 * 
 * @author tqc
 * @version 1.0
 * @created 03-四月-2011 11:33:17
 */
@Entity
@Table(name = "SPMS_CUSREPAIRQUOTERECORDITEM")
public class CustomerRepairQuoteRecordItem extends BaseOutlayItem {

	/** 报价记录编号 **/
	private String repairQuoteRecordId;

	@Column(name = "REPAIRQUOTERECORDID")
	public String getRepairQuoteRecordId() {
		return repairQuoteRecordId;
	}

	public void setRepairQuoteRecordId(String repairQuoteRecordId) {
		this.repairQuoteRecordId = repairQuoteRecordId;
	}

}