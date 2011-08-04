package com.skynet.spms.persistence.entity.supplierSupport.repairClaim.RepairQuoteRecord;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.BaseOutlayRecord;

/**
 * 供应商修理报价记录
 * 
 * @author tqc
 * @version 1.0
 * @created 03-四月-2011 11:33:17
 */
@Entity
@Table(name = "SPMS_REPAIRQUOTERECORD")
public class RepairQuoteRecord extends BaseOutlayRecord {

	/** 供应商修理报价编号 **/
	private String repairQuoteNumber;

	/** 修理报价描述 **/
	private String repairQuotedescription;

	/** 有效期 **/
	private Integer usefulLife;
	
	/** 检测日期 **/
	private Date detectionDate;
	
	@Column(name="DETECTIONDATE")
	public Date getDetectionDate() {
		return detectionDate;
	}

	public void setDetectionDate(Date detectionDate) {
		this.detectionDate = detectionDate;
	}

	@Column(name = "USEFULLIFE")
	public Integer getUsefulLife() {
		return usefulLife;
	}

	public void setUsefulLife(Integer usefulLife) {
		this.usefulLife = usefulLife;
	}

	@Column(name = "REPAIR_QUOTE_NUM")
	public String getRepairQuoteNumber() {
		return repairQuoteNumber;
	}

	public void setRepairQuoteNumber(String repairQuoteNumber) {
		this.repairQuoteNumber = repairQuoteNumber;
	}

	@Column(name = "REPAIR_QUOTE_DESC")
	public String getRepairQuotedescription() {
		return repairQuotedescription;
	}

	public void setRepairQuotedescription(String repairQuotedescription) {
		this.repairQuotedescription = repairQuotedescription;
	}

}