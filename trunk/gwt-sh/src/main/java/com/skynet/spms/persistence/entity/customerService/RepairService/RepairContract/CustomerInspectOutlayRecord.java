package com.skynet.spms.persistence.entity.customerService.RepairService.RepairContract;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.skynet.spms.persistence.entity.base.baseOutlayRecordEntity.BaseOutlayRecord;

/**
 * 客户检验费用处理记录
 * 
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:21
 */
@Entity
@Table(name = "SPMS_CUSINSPECTOUTLAYRECORD")
public class CustomerInspectOutlayRecord extends BaseOutlayRecord {

	/** 检测单位 **/
	private String detectionUnit;

	/** 检验报告描述 **/
	private String inspectionDescription;

	/** 检测日期 **/
	private Date detectionDate;

	@Column(name = "DETECTIONUNIT")
	public String getDetectionUnit() {
		return detectionUnit;
	}

	public void setDetectionUnit(String detectionUnit) {
		this.detectionUnit = detectionUnit;
	}

	@Column(name = "INSPECTIONDES")
	public String getInspectionDescription() {
		return inspectionDescription;
	}

	public void setInspectionDescription(String inspectionDescription) {
		this.inspectionDescription = inspectionDescription;
	}

	@Column(name = "DETECTIONDATE")
	public Date getDetectionDate() {
		return detectionDate;
	}

	public void setDetectionDate(Date detectionDate) {
		this.detectionDate = detectionDate;
	}


}